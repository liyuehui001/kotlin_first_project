package com.myandroid.androidManager.realmManager;

import android.content.Context;
import android.os.Build;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

/**
 * Created by 小黑 on 2017/11/1.
 */

public final class RealmManager {
    private static Realm realm;
    private static volatile RealmManager realmManager;
    private static byte[] syncObj = new byte[0];//?????

    private static final String REALM_NAME="MyAndroid";
    private static final int REALM_VERSION=1;

    private RealmManager(){}

    public static RealmManager getRealmManager(){
        if (realmManager == null){
            synchronized (syncObj){
                realmManager = new RealmManager();
                realm = realm.getDefaultInstance();
            }
        }
        return realmManager;
    }

    public static void initRealm(Context context){
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(REALM_NAME).schemaVersion(REALM_VERSION)
                .deleteRealmIfMigrationNeeded().build();//当数据库结构改变时，删除原数据库中的所有数据
        Realm.setDefaultConfiguration(configuration);
    }


    public Realm getRealm(){
        return realm;
    }

    /**
     * 获取事务
     */
    private void _getTransaction(){
        if(realm.isInTransaction()){
            return;
        }else {
            realm.beginTransaction();
        }
    }


    /**
     * 添加(性能优于下面的saveOrUpdate（）方法)
     *
     * @param object
     * @return 保存或者修改是否成功
     */
    public boolean insert(RealmObject object) {
        try {

            _getTransaction();
            realm.insert(object);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
            return false;
        }
    }
}
