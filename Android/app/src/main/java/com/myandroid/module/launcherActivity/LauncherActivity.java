package com.myandroid.module.launcherActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.lyh.com.android.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.myandroid.module.loginActivity.LoginActivity;
import com.myandroid.module.mainActivity.MainActivity;
import com.myandroid.utils.CommonUtil;
import com.myandroid.utils.UserInfoDataUtil;
import com.staticVar.VarString;

/**
 * Created by 小黑 on 2017/11/1.
 */

public class LauncherActivity extends Activity {
    private TextView tvVersion,tvTime;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    tvTime.setText("2秒");
                    break;
                case 1:
                    tvTime.setText("1秒");
                    break;
                case 2:

                    _intoMainActivity();

                    break;
            }
        }


    };

    private void _intoMainActivity() {
        this.finish();
        if (UserInfoDataUtil.getData(this, VarString.isLogin)==null){
            Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
        }else if (UserInfoDataUtil.getData(this, VarString.isLogin).equals(VarString.notLogin)){
            Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
        }else if (UserInfoDataUtil.getData(this, VarString.isLogin).equals(VarString.logined)){
            Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        _init();



        new Thread(){
            @Override
            public void run() {

                for (int i = 0; i < 3; i++) {
                    Message message = Message.obtain();
                    message.what = i;
                    mHandler.sendMessageDelayed(message,1000);
                }
            }
        }.start();


        _setVersionName();


    }
    private void _init() {
        tvTime = (TextView)findViewById(R.id.tvTime);
        tvVersion =(TextView)findViewById(R.id.tvVersion);
    }

    private void _setVersionName() {
        try {
            String verssionname = CommonUtil.getVersionName(this);
            tvVersion.setText(verssionname);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.e("获取版本号","出错");
        }
    }


}
