//create main.js file
//npm  init -y
//install electron
//create index.html file
// add "start":"electron ."
const electron=require("electron");
const ejs=require("ejs-electron");

const app=electron.app;
const BrowserWindow=electron.BrowserWindow;

function createWindow(){
    // Create the browser window.
  const win = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      nodeIntegration:true// 
    }
  })

  // and load the index.html of the app.
  win.loadFile('index.ejs').then(function(){
    win.maximize();
    win.webContents.openDevTools();
  }) 
}
app.whenReady().then(createWindow);