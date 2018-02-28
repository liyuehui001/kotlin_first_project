package com.myandroid.bean;

import java.io.Serializable;

/**
 * Created by 小黑 on 2017/11/3.
 */

public class HttpResponse implements Serializable{
    private String resultCode;
    private String message;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
