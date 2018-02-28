package com.myandroid.module.mainActivity;

import android.content.Context;
import android.util.Log;

import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.myandroid.androidManager.volleyManager.BaseSubscriber;
import com.myandroid.androidManager.volleyManager.VolleyManager;
import com.myandroid.base.AndroidBasePresent;
import com.myandroid.bean.HttpResponse;
import com.myandroid.module.loginActivity.LoginActivity;
import com.myandroid.module.loginActivity.bean.UserInfoDto;
import com.myandroid.module.mainActivity.bean.FragmentMainDto;
import com.myandroid.module.mainActivity.fragment.FragmentInfo;
import com.myandroid.module.mainActivity.fragment.FragmentMain;
import com.myandroid.module.mainActivity.fragment.FragmentMsg;
import com.myandroid.utils.GsonHelper;
import com.staticVar.IUrl;
import com.staticVar.RequestStr;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by 小黑 on 2017/11/3.
 */

public class MainPresenter extends AndroidBasePresent implements MainContract{
    public MainPresenter(Context context) {
        super(context);
    }

    @Override
    public void getList(WeakHashMap<String, String> map) {
        VolleyManager.schedulerThread(
                VolleyManager.RxVolleyRequest(IUrl.GETLIST,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

                    @Override
            public void onNext(JSONObject jsonObject) {

                String str = jsonObject.toString();

                Log.e("strjson",str);
                FragmentMainDto dto = GsonHelper.convertEntity(str, FragmentMainDto.class);
                if (dto.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                    mRxBus.post(FragmentMain.getListSuccess,dto);
                }else{
                    mRxBus.post(FragmentMain.getListFail,dto);
                }
            }
        });
    }

    @Override
    public void getUserInfo(WeakHashMap<String, String> map) {
        VolleyManager.schedulerThread(
                VolleyManager.RxVolleyRequest(IUrl.GETUserInfo,map,headersMap)).subscribe(new BaseSubscriber<JSONObject>() {
            @Override
            public void onNext(JSONObject jsonObject) {
                String str = jsonObject.toString();
                Log.e("strjson",str);
                UserInfoDto userInfoDto = GsonHelper.convertEntity(str,UserInfoDto.class);

                if (userInfoDto.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                    mRxBus.post(FragmentInfo.GETINFOSUCCESS,userInfoDto);
                }else{
                    mRxBus.post(FragmentInfo.GETINFOFAIL,userInfoDto);
                }

            }
        });
    }

    @Override
    public void uploadFile(String filePath,HashMap<String,String> params) {

        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequestFile(IUrl.uploadFile,params,headersMap,filePath)).subscribe(new BaseSubscriber<JSONObject>() {
            @Override
            public void onNext(JSONObject jsonObject) {
                String str = jsonObject.toString();
                Log.e("strjson",str);
                HttpResponse httpResponse = GsonHelper.convertEntity(str,HttpResponse.class);
                if (httpResponse.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                    mRxBus.post(FragmentMsg.uploadsuccess,httpResponse);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }
}
