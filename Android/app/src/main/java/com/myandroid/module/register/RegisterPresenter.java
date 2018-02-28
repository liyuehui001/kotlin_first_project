package com.myandroid.module.register;

import android.content.Context;

import com.myandroid.androidManager.volleyManager.BaseSubscriber;
import com.myandroid.androidManager.volleyManager.VolleyManager;
import com.myandroid.application.AndroidApplication;
import com.myandroid.base.AndroidBasePresent;
import com.myandroid.module.register.bean.RegisterDto;
import com.myandroid.utils.GsonHelper;
import com.myandroid.utils.NetworkState;
import com.staticVar.IUrl;
import com.staticVar.RequestStr;


import org.json.JSONObject;

import java.util.WeakHashMap;

import static com.myandroid.module.register.RegisterActivity.REGISTER_SUCCESS;
import static com.myandroid.module.register.RegisterActivity.REGISTER_FAIL;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class RegisterPresenter extends AndroidBasePresent implements RegisterContract{

    public RegisterPresenter(Context context) {
        super(context);
    }

    @Override
    public void register(WeakHashMap<String, String> map) {

        if(!NetworkState.NetworkIsConnect(AndroidApplication.getContext())){
            return ;
        }

        VolleyManager.schedulerThread(
                VolleyManager.RxVolleyRequest(IUrl.REGISTER,map,headersMap))
                .subscribe(new BaseSubscriber<JSONObject>() {

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        String jsonstr = jsonObject.toString();
                        RegisterDto registerDto =  GsonHelper.convertEntity(jsonstr,RegisterDto.class);
                        //在activity中判断是否成功
                        if (registerDto.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
                            mRxBus.post(REGISTER_SUCCESS,registerDto);
                            //保存数据
                        }else{
                            mRxBus.post(REGISTER_FAIL,registerDto);
                        }

                    }
                });
    }
}
