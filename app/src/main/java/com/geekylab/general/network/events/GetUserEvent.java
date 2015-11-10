package com.geekylab.general.network.events;

import android.content.Context;

import com.android.volley.Request;
import com.geekylab.general.R;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by User on 9/11/15.
 */

public class GetUserEvent extends BaseEvent{
    public GetUserEvent(Context context, HashMap<String, String> params){
        super(context, params, context.getResources().getString(R.string.api_GET_USER));
        method = Request.Method.POST;
    }
}
