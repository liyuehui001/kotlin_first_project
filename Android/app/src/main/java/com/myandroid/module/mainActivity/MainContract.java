package com.myandroid.module.mainActivity;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by 小黑 on 2017/11/3.
 */

public interface MainContract {
    void getList(WeakHashMap<String,String> map);

    void getUserInfo(WeakHashMap<String,String> map);

    void uploadFile(String filePath,HashMap<String,String> params);
    
}
