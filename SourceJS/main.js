var {app, BrowserWindow, ipcMain} = require('electron');
var path = require('path');
var os = require('os');
var fs = require('fs');
var Shell = require('child_process');

var port = parseInt(fs.readFileSync("./port.txt").toString());

function createWindow () {
  const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    title: "FileCon",
    webPreferences: {
      nodeIntegration: true,
      navigateOnDragDrop: true,
      enableRemoteModule: true,
      preload: path.join(__dirname, 'public/preload.js'),
      renderer: path.join(__dirname, 'public/renderer.js')
    },
    icon: "./public/images/house.png"
  });

  mainWindow.loadURL('http://localhost:'+port+'/main.html');
  mainWindow.maximize();
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