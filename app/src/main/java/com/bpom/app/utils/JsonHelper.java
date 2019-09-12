package com.bpom.app.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonHelper {

    public Object obj = null;
    public boolean isJsonArray = false;

    JsonHelper(Object obj, boolean isJsonArray){
        this.obj = obj;
        this.isJsonArray = isJsonArray;
    }

    public static JsonHelper fromStringToJSON(String jsonString){

        boolean isJsonArray = false;
        Object obj = null;

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            Log.d("JsonHelper", jsonArray.toString());
            obj = jsonArray;
            isJsonArray = true;
        }
        catch (Throwable t) {
            Log.e("JsonHelper", "Malformed JsonHelper: \"" + jsonString + "\"");
        }

        if (obj == null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                Log.d("JsonHelper", jsonObject.toString());
                obj = jsonObject;
                isJsonArray = false;
            } catch (Throwable t) {
                Log.e("JsonHelper", "Malformed JsonHelper: \"" + jsonString + "\"");
            }
        }

        return new JsonHelper(obj, isJsonArray);
    }
}