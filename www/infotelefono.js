var ejecutar = require('cordova/exec');

var invocarNativo = function (exito, fracaso){
    ejecutar(exito, fracaso, 'infotelefono', 'GET_IDENTIFICACION', []);
    
});

var pluin={};
plugin.obtenerInformacion=invocarNativo;


module.exports=plugin;