package com.geekylab.general.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;

/**
 * Created by User on 11/11/15.
 */
public class SharePrefUtil {
    private static final String SETTINGS_NAME = "GL_PREF";
    public enum Key {
        VERSION_KEY
    }
    private static SharePrefUtil instance = null;
    private SharedPreferences pref;

    public static SharePrefUtil getInstance(Context context) {
        if (instance == null || instance.pref==null) {
            instance = new SharePrefUtil(context);
        }
        return instance;
    }

    private SharePrefUtil(Context context) {
        pref = context.getSharedPreferences(SETTINGS_NAME, 0);
    }

    public void set(Key key, String val){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key.toString(), val);
        editor.commit();
    }

    public String get(Key key){
        return get(key, null);
    }

    public String get(Key key, String defaultVal){
        return pref.getString(key.toString(), defaultVal);
    }

    public void remove(Key key){
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key.toString());
        editor.commit();
    }


}
