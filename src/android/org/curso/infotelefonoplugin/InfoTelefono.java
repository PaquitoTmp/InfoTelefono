package org.curso.infotelefonoplugin;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;



public class InfoTelefono extends CordovaPlugin{
	
	@Override
	public boolean execute( String action, JSONArray args, CallbackContext callbackContext) throws JSONException{
		
		try{
			if("GET_IDENTIFICACION".equals(action)){
				TelephonyManager telephonyManager = (TelephonyManager)super.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
				String imei=telephonyManager.getDeviceId();
				String imsi=telephonyManager.getSubscriberId();
				String numerotelefono=telephonyManager.getLine1Number();
				String cadena="{"+
				"	\"numero\":\""+numerotelefono+"\", 	" +
				"	\"imsi\":\""+imsi+"\", 		" +
				"	\"imei\":\""+imei+"\", 		" +
				"}";
				JSONObject resultadoJSON=new JSONObject(cadena);
				callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, resultadoJSON));
				
			}else{
				callbackContext.error("No se hacer eso");
			}
		}
		catch(RuntimeException exc){
			StringWriter sw=new StringWriter();
			PrintWriter pw=new PrintWriter(sw);
			exc.printStackTrace();
			pw.close();
			String stackTrace=sw.getBuffer().toString();
			callbackContext.error(stackTrace);
		}
		return false;
		
	}

}
