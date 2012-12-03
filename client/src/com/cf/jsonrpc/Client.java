package com.cf.jsonrpc;

import org.alexd.jsonrpc.JSONRPCClient;
import org.alexd.jsonrpc.JSONRPCException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Client extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv = (TextView) findViewById(R.id.text_view);
        //tv.setText(testMethod());
        double beforeMethodCall = System.currentTimeMillis();        
        tv.setText(getDataMethod(200));
        double afterMethodCall = System.currentTimeMillis();
        Log.i("JSON", "Method call: " + Double.toString(afterMethodCall - beforeMethodCall));
    }
    
    private String testMethod()
    {
    	JSONRPCClient client = JSONRPCClient.create("http://10.0.2.2/xmlrpc-test/public/server/json");
    	//client.setConnectionTimeout(2000);
    	//client.setSoTimeout(2000);
    	String string = "";
    	try {
    		string = client.callString("cf.test");    		
    	} catch (JSONRPCException e) {
    		Log.i("MainActivity", e.toString());
    		//e.printStackTrace();
    	}
    	return string;
    }
    
    private String getDataMethod(int numRows)
    {
    	JSONRPCClient client = JSONRPCClient.create("http://10.0.2.2/xmlrpc-test/public/server/json");
    	//client.setConnectionTimeout(2000);
    	//client.setSoTimeout(2000);
    	String string = "";
    	try {
    		JSONArray items = client.callJSONArray("cf.getData", numRows);
    		double beforeLoop = System.currentTimeMillis(); 
    		for (int i = 0; i < items.length(); i++) {
    			try {
					JSONObject item = items.getJSONObject(i);
					string = string + item.getString("number") + ". " + item.getString("title") + " - " + item.getString("datetime") + "\n\n";
				} catch (JSONException e) {
					Log.i("MainActivity", e.toString());
					//e.printStackTrace();
				}
    		}
    		double afterLoop = System.currentTimeMillis(); 
    		Log.i("JSON", "Loop: " + Double.toString(afterLoop - beforeLoop));
    	} catch (JSONRPCException e) {
    		Log.i("MainActivity", e.toString());
    		//e.printStackTrace();
    	}
    	return string;
    }
}