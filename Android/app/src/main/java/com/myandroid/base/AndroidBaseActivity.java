package com.myandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.hwangjr.rxbus.AnRxBus;
import com.hwangjr.rxbus.Bus;

/**
 * Created by 小黑 on 2017/11/1.
 */

public abstract class AndroidBaseActivity<T> extends FragmentActivity {

    protected Bus mRxbus;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRxbus = AnRxBus.getBus();
        mRxbus.register(this);
        mPresenter=getPresenter();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    public abstract T getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxbus.unregister(this);
    }
}
