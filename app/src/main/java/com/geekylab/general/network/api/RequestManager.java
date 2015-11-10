package com.geekylab.general.network.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.geekylab.general.network.events.BaseEvent;
import com.geekylab.general.network.events.EventManager;

import org.json.JSONObject;

/**
 * Created by User on 9/11/15.
 */
public class RequestManager {
    private static final String TAG = RequestManager.class.getSimpleName();
    private static RequestManager mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private RequestManager(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestManager(context.getApplicationContext());
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public void makeRequest(final BaseEvent event) {
        try {
            Log.d(TAG, "makeRequest to:"+ event.url);
            //if (event.useCache) {
                //TODO get cache from db
            //}


            final Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d(TAG, "makeRequest response:"+ response);
                    event.response = response;
                    EventManager.getBus().post(event);
                }
            };
            final Response.ErrorListener errorListener = new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    int code = -1;
                    if (error.networkResponse!=null){
                        code = error.networkResponse.statusCode;
                    }
                    //TODO show error dialog
                    Log.d(TAG, "makeRequest error:"+ error.getMessage());
                }
            };

            final CustomRequest jsonRequest = new CustomRequest(event.method, event.url,
                     listener, errorListener, event.params, null);
            mRequestQueue.add(jsonRequest);

        } catch (Exception e) {
            //TODO show error dialog
            Log.d(TAG, "makeRequest exception:"+ e.getMessage());
        }
    }







}

