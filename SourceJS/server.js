var express = require('express');
var app = express();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var path = require('path');
var os = require('os');
var fs = require('fs');
var Shell = require('child_process');
var FileFinder = require('./src/filefinder.js');

var port = parseInt(fs.readFileSync("./port.txt").toString());
var mf = JSON.parse(fs.readFileSync("./manifest.json").toString());

app.use(express.static(path.join(__dirname, './public/')));

app.get('/app/*', (req, res) => {
  res.sendFile(path.resolve('../app/',req.url.replace('/app/','')));
});

app.get('/mf', (req, res) => {
  res.send(mf);
});

global.io = io;
global.app = app;
global.path = path;
global.os = os;
global.fs = fs;

io.on('connection', function(socket){

	socket.on('path-resolve', (f, done) => {
		done(path.resolve(f));
	});

	socket.on('fs-readdir', (f, done) => {
		if(!fs.existsSync(path.resolve(f))) { done([]); return};
		if(!fs.lstatSync(path.resolve(f)).isDirectory()) { done([]); return};
		done(fs.readdirSync(path.resolve(f)));
	});

	socket.on('fs-writefile', (f, done) => {
		done(fs.writeFileSync(path.resolve(f[0]), f[1]));
	});

	socket.on('fs-readfile', (f, done) => {
		if(!fs.existsSync(path.resolve(f))) { done(""); return}
		done(fs.readFileSync(path.resolve(f)).toString());
	});

	socket.on('fs-mkdir', (f, done) => {
		done(fs.mkdirSync(path.resolve(f)));
	});

	socket.on('fs-exists', (f, done) => {
		if(!fs.existsSync(path.resolve(f))) { done(""); return}
		done(fs.existsSync(path.resolve(f)));
	});

	socket.on('fs-type', (f, done) => {
		if(!fs.existsSync(path.resolve(f))) { done("file"); return}
		done(fs.lstatSync(path.resolve(f)).isDirectory() ? "dir" : "file");
	});

	socket.on('shell-exec', (f,fn) => {
		Shell.exec(f, function(){
			if(typeof fn == "function") fn(arguments[0],arguments[1],arguments[2]);
		});
	});

	socket.on('fs-delete', (f, done) => {
		if(!fs.existsSync(path.resolve(f))) { done([]); return}
		done(fs.unlinkSync(path.resolve(f)));
	});

	socket.on('search-files',(data,done) => {
		var results = new FileFinder(socket.broadcast).find(
			data[0],
			data[1],
			data[2],
			data[3],
			data[4]
		);
		if(done) done(results);
	});

});

app.get("/path-resolve/", (req, res) => {
	res.send(path.resolve(req.query.q));
});

app.get("/homedrive/", (req, res) => {
	res.send(process.env.HOMEDRIVE);
});

server.listen(port, () => console.log("Server on port "+port));