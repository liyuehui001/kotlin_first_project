package com.staticVar;

/**
 * Created by 小黑 on 2017/11/3.
 */

public interface RequestStr {
    interface requestCode{
        String requestSuccess = "1";
        String requestFail = "0";
    }

    interface userInfo{
        String username = "USERNAME";
        String password = "PASSWORD";
    }

    interface mainViewType{
        int TYPE_NORMAL = 0;
        int TYPE_GUANGGAO = 1;
    }

}
