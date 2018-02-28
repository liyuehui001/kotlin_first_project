package com.myandroid.module.loginActivity;

import android.content.Intent;
import android.lyh.com.android.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.myandroid.base.AndroidBaseActivity;
import com.myandroid.module.loginActivity.bean.UserInfoDto;
import com.myandroid.module.mainActivity.MainActivity;
import com.myandroid.module.register.RegisterActivity;
import com.myandroid.utils.ToastUtils;
import com.myandroid.widget.InputFieldHaveDel;

import java.util.WeakHashMap;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class LoginActivity extends AndroidBaseActivity<LoginPresenter> implements LoginContract,View.OnClickListener {

    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static final String LOGIN_FAIL = "LOGIN_FAIL";

    private Button btnLogin;
    private TextView tvRegister;

    private InputFieldHaveDel inputUserName,inputPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _init();
    }

    private void _init() {
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvRegister = (TextView)findViewById(R.id.tvRegister);
        inputUserName = (InputFieldHaveDel)findViewById(R.id.inputUserName);
        inputPassword = (InputFieldHaveDel)findViewById(R.id.inputPassword);


        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void login(WeakHashMap<String, String> map) {
        map.put("username",inputUserName.getEditText().getText().toString().trim());
        map.put("password",inputPassword.getEditText().getText().toString().trim());
        mPresenter.login(map);
    }

    @Override
    public void onClick(View v) {
        int viewid= v.getId();
        switch (viewid){
            case R.id.tvRegister:
                _jumpToResigter();
                break;
            case R.id.btnLogin:
                WeakHashMap<String,String> map = new WeakHashMap<>();
                this.login(map);
                break;
        }
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(LoginActivity.LOGIN_SUCCESS)
            }
    )
    public void getLoginSuccess(UserInfoDto userInfoDto){
        ToastUtils.showToast("登录成功");
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("userinfo",userInfoDto);
        startActivity(intent);
    }



    private void _jumpToResigter() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
