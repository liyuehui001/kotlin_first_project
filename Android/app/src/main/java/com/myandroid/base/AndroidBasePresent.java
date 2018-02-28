package com.myandroid.base;

import android.content.Context;

import com.hwangjr.rxbus.AnRxBus;
import com.hwangjr.rxbus.Bus;
import com.myandroid.androidManager.realmManager.RealmManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 小黑 on 2017/11/1.
 */

public class AndroidBasePresent implements Serializable {
    protected Context mContext;
    protected Bus mRxBus;
    protected HashMap<String, String> headersMap;
    protected RealmManager realmManager;

    public AndroidBasePresent(Context context){
        this.mContext = context;
        mRxBus = AnRxBus.getBus();
        mRxBus.register(this);
        realmManager = RealmManager.getRealmManager();
        initHeader();

    }


    protected void initHeader(){
        headersMap = new HashMap<>();

    }

}
