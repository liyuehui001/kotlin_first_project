package com.myandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class UserInfoDataUtil {

    public static void putData(Context context, Map<String,String> data){
        SharedPreferences preferences = context.getSharedPreferences("android_data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (Map.Entry<String,String> entry : data.entrySet()){
            editor.putString(entry.getKey(),entry.getValue());
        }
        editor.commit();
    }

    public static void putData(Context context,String key,String value){
        SharedPreferences preferences =context.getSharedPreferences("android_data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getData(Context context,String key){
        SharedPreferences preferences = context.getSharedPreferences("android_data",Context.MODE_PRIVATE);
        String value = preferences.getString(key,null);
        return value;
    }

}
