package com.myandroid.androidManager.volleyManager;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class ApiException extends RuntimeException {
    private String errorCode;
    public ApiException(String code){
        this.errorCode = code;
    }

    public String errorMessage(){
        return errorCode;
    }
}
