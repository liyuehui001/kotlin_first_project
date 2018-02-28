package com.myandroid.module.loginActivity;

import android.content.Context;

import com.myandroid.androidManager.volleyManager.BaseSubscriber;
import com.myandroid.androidManager.volleyManager.VolleyManager;
import com.myandroid.base.AndroidBasePresent;
import com.myandroid.module.loginActivity.bean.UserInfoDto;
import com.myandroid.utils.GsonHelper;
import com.staticVar.IUrl;
import com.staticVar.RequestStr;

import org.json.JSONObject;

import java.util.WeakHashMap;


/**
 * Created by 小黑 on 2017/11/2.
 */

public class LoginPresenter extends AndroidBasePresent implements LoginContract{

    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    public void login(WeakHashMap<String, String> map) {

        VolleyManager.schedulerThread(VolleyManager.RxVolleyRequest(IUrl.LOGIN,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(JSONObject jsonObject) {

                String str = jsonObject.toString();

                UserInfoDto userInfoDto = GsonHelper.convertEntity(str, UserInfoDto.class);

                if (userInfoDto.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                    mRxBus.post(LoginActivity.LOGIN_SUCCESS,userInfoDto);
                }else{
                    mRxBus.post(LoginActivity.LOGIN_FAIL,userInfoDto);
                }
            }
        });

    }
}
