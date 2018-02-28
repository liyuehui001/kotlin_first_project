package com.myandroid.androidManager.volleyManager;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by 小黑 on 2017/11/1.
 */

public class VolleyErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Internet-ERROR", error.getMessage(), error);
        error.printStackTrace();
    }
}
