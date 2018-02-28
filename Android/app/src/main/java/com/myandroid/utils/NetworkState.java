package com.myandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class NetworkState {

    /**
     * 检查网络是否连接
     * @param context
     * @return
     */
    public static boolean NetworkIsConnect(Context context){

        if (context!=null){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info!=null){
                return info.isAvailable();
            }
        }
        return false;
    }


    /**
     * 检查wifi是否可用
     * @param context
     * @return
     */
    public static boolean WiFiIsConnect(Context context){

        if (context!=null){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                if (info.getType()==ConnectivityManager.TYPE_WIFI){
                    return info.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 检查移动网络是否可用
     * @param context
     * @return
     */
    public static boolean MobileIsConnect(Context context){
        if (context!=null){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                if (info.getType() == ConnectivityManager.TYPE_MOBILE){
                    return info.isAvailable();
                }
            }

        }
        return false;
    }
}
