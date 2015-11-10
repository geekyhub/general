/**
 * 
 */
package com.geekylab.general.network.events;


import android.content.Context;

import com.geekylab.general.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class BaseEvent{
	public Context context;
	public int method;
	public String url;
	public HashMap<String, String> params;
	public JSONObject response;

	public BaseEvent(Context context, HashMap<String, String> params, String path){
		this.context = context;
		this.params = params;
		if(path!=null) {
			this.url = context.getResources().getString(R.string.api_url) + path;
		}

	}

}