var path = require('path');
var os = require('os');
var fs = require('fs');
// var chalk = require('chalk');

function FileFinder(socket){this.socket = socket}

FileFinder.prototype.found = function(folder,name,file,results,type,foundCb) {
	var t = this;
	var result = {
		parent: folder,
		name: name,
		path: file,
		type: type
	};
	t.socket.emit('found-from-folder',result);
	if(foundCb) foundCb(result);
	results.push(result);
};


FileFinder.prototype.find = function(folder, string, type, extentions, casei, eachCb, foundCb){
	if(!extentions) extentions = "all";
	var t = this;
	if(casei) {
		string = new RegExp(string,'ig')
	} else {
		if(!string instanceof RegExp) {string = new RegExp(string,'g')};
	};
	var list = fs.readdirSync(folder);
	var results = [];
	var dirs = [];
	var total = 0;
	var found = 0;
	function folderCase(folder,flist){
		listFileAndSearch(folder,flist);
	}
	function listFileAndSearch(folder,flist){
		flist.forEach((file, index)=>{
			total++;
			var name = file;
			file = path.join(folder,file);
			var isthisfound = false;
			t.socket.emit('each-from-folder',folder,file,name,index,flist.length);
			if(eachCb) eachCb(folder,file,name,index,flist.length);
			if(fs.lstatSync(file).isDirectory()){
				dirs.push({
					name: name,
					path: file
				});
				if((type == "name" || type == "all") && name.match(string) && extentions == "all"){
					found++;
					t.found(folder,name,file,results,"dir",foundCb);
					isthisfound = true;
				} 
				folderCase(file, fs.readdirSync(file));
			} else {
				try{
					var f = fs.readFileSync(file).toString();
				} catch(e){
					var f = "";
					return;
				}
				var ext_;
				if(extentions != "all")
					extentions.forEach((ext) => {
						ext_ = path.extname(name) == "."+ext;
					});
				else
					ext_ = true;
				var _matc;
				if(type == "name") _matc = name.match(string);
				if(type == "content") _matc = f.match(string);
				if(type == "all") _matc = f.match(string) || name.match(string);

				if(_matc && ext_){
					found++;
					t.found(folder,name,file,results,"file",foundCb);
					isthisfound = true;
				}
			}
			console.log(chalk.blue((isthisfound ? "Found " : "") + "Entry: ")+chalk.yellow(file));
		});
	}
	listFileAndSearch(folder, list);
	return {
		total: total,
		found: found,
		result: results,
		dirs: dirs
	};
}

module.exports = FileFinder;
