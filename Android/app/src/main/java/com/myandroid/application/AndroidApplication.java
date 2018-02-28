package com.myandroid.application;

import android.app.Application;
import android.content.Context;

import com.myandroid.androidManager.realmManager.RealmManager;
import com.myandroid.androidManager.volleyManager.VolleyManager;
import com.myandroid.utils.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class AndroidApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        RealmManager.initRealm(mContext);
        ToastUtils.init(mContext);
        VolleyManager.init(mContext);
        initImageLoader(mContext);

    }
    public static void initImageLoader(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }

    public static Context getContext(){
        return mContext;
    }
}
