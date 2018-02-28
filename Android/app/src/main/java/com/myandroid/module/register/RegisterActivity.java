package com.myandroid.module.register;

import android.content.Intent;
import android.lyh.com.android.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.myandroid.base.AndroidBaseActivity;
import com.myandroid.module.mainActivity.MainActivity;
import com.myandroid.module.register.bean.RegisterDto;
import com.myandroid.utils.ToastUtils;
import com.myandroid.utils.UserInfoDataUtil;
import com.myandroid.widget.InputFieldHaveDel;
import com.staticVar.RequestStr;
import com.staticVar.VarString;

import java.util.WeakHashMap;


/**
 * Created by 小黑 on 2017/11/2.
 */

public class RegisterActivity extends AndroidBaseActivity<RegisterPresenter> implements RegisterContract,View.OnClickListener {
    private InputFieldHaveDel inputUserName;
    private InputFieldHaveDel inputPassword;
    private Button btnRegister;

    public static final String REGISTER_SUCCESS = "REGISTER_SUCCESS";
    public static final String REGISTER_FAIL = "REGISTER_FAIL";

    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _init();

    }

    private void _init() {
        inputPassword = (InputFieldHaveDel)findViewById(R.id.inputPassword);
        inputUserName = (InputFieldHaveDel)findViewById(R.id.inputUserName);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void register(WeakHashMap<String, String> map) {

        username = inputUserName.getEditText().getText().toString().trim();
        password = inputPassword.getEditText().getText().toString().trim();
        map.put("username",username);
        map.put("password",password);

        mPresenter.register(map);
    }

    @Override
    public RegisterPresenter getPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        switch (viewid){
            case R.id.btnRegister:
                WeakHashMap<String,String> map = new WeakHashMap<>();
                this.register(map);
                break;
        }
    }

    @Subscribe(
        thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(RegisterActivity.REGISTER_SUCCESS)
            }
    )
    public void registerSuccess(RegisterDto response){
        //注册成功，保存数据跳转主界面

        UserInfoDataUtil.putData(this, RequestStr.userInfo.username,username);
        UserInfoDataUtil.putData(this, RequestStr.userInfo.password,password);
        this.finish();

        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(RegisterActivity.REGISTER_FAIL)
            }
    )
    public void registerFail(RegisterDto response){
        //注册失败，提示错误
        ToastUtils.showToast("注册失败");

    }

}
