package com.myandroid.module.loginActivity;

import java.util.WeakHashMap;

/**
 * Created by 小黑 on 2017/11/2.
 */

public interface LoginContract {
    void login(WeakHashMap<String,String> map);
}
