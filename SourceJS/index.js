const customTitlebar = require('custom-electron-titlebar');
const { Themebar } = require('custom-electron-titlebar');

new customTitlebar.Titlebar({
  backgroundColor: customTitlebar.Color.fromHex('#000'),
  drag: true,
  minimizable: true,
  maximizable: true,
  closeable: true,
  menu: false,
  iconsTheme: Themebar.mac,
  overflow: 'hidden',
  unfocusEffect: true,
  icon: './images/house.png',
});
