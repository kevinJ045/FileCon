var express = require('express');
var expressApp = express();
var server = require('http').Server(expressApp);
var io = require('socket.io')(server);
var {app, BrowserWindow, ipcMain} = require('electron');
var path = require('path');
var os = require('os');
var fs = require('fs');
var Shell = require('child_process');


var rand = (min,max) => Math.floor(Math.random()*(max-min+1)+min);

var ic = "./res/icon.png";

var port = rand(rand(6553,65536/2),rand(6553,65536/2));

expressApp.use(express.static(path.join(__dirname, './')));

expressApp.get('/app/*', (req, res) => {
  res.sendFile(path.resolve('../app/',req.url.replace('/app/','')));
});

io.on('connection', function(socket){

	socket.on('cnt', (f, done) => {
		done.call(global,io,expressApp,port,path,os,fs,process);
	});

	socket.on('fs-readdir', (f, done) => {
		done(fs['readdir'+"Sync"](path.resolve(f)));
	});

	socket.on('fs-writefile', (f, done) => {
		done(fs['writeFile'+"Sync"](path.resolve(f)));
	});

	socket.on('fs-readfile', (f, done) => {
		done(fs['readFile'+"Sync"](path.resolve(f)).toString());
	});

	socket.on('fs-type', (f, done) => {
		var type;
		try{
			let dir = fs.opendirSync(f);
			type = 'dir';
		} catch(e){
			type = 'file';
		}
		done(type);
	});

	socket.on('shell-exec', (f,fn) => {
		Shell.exec(f, function(){
			fn.apply(global,arguments);
		});
	});

});

server.listen(port, () => {
  function createWindow () {
	  
	  const mainWindow = new BrowserWindow({
	    width: 800,
	    height: 600,
	    webPreferences: {
	      nodeIntegration: true,
	      navigateOnDragDrop: true,
	      enableRemoteModule: true
	    },
	    icon: ic
	  });

	  mainWindow.loadURL('http://localhost:'+port+'/main.html');
	}

	app.whenReady().then(() => {
	  createWindow()
	  
	  app.on('activate', function () {
	    if (BrowserWindow.getAllWindows().length === 0) createWindow();
	  })
	});

	app.on('window-all-closed', function () {
	  if (process.platform !== 'darwin') app.quit();
	});
});