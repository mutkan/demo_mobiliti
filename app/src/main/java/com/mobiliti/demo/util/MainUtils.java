package com.mobiliti.demo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobiliti.demo.MainApp;
import com.mobiliti.demo.R;

import java.lang.reflect.Type;
import java.util.Map;

public class MainUtils {
    private static Context mContext;

    public static Map<Integer, Boolean> getFavoriteMapFromJson(String json) {
        Gson gson = new Gson();
        Type stringStringMap = new TypeToken<Map<Integer, Boolean>>() {
        }.getType();
        Map<Integer, Boolean> map = gson.fromJson(json, stringStringMap);
        return map;
    }

    public static String setFavoriteMapToJson(Map<Integer, Boolean> map) {
        Gson gson = new Gson();
        Type typeOfResult = new TypeToken<Map<Integer, Boolean>>() {
        }.getType();

        String json = "";
        if (map != null) {
            json = gson.toJson(map, typeOfResult);
        }
        return json;
    }

    public static void saveJson(String json) {
        mContext = MainApp.getAppContext();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(mContext.getResources().getString(R.string.saved_json), json);
        editor.apply();
    }

    public static String readJson() {
        mContext = MainApp.getAppContext();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(mContext);
        String json = sharedPref.getString(mContext.getResources().getString(R.string.saved_json), "");
        return json;
    }
}
