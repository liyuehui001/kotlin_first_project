package com.myandroid.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hwangjr.rxbus.AnRxBus;
import com.hwangjr.rxbus.Bus;

/**
 * Created by 小黑 on 2017/11/1.
 */

public abstract class AndroidBaseFragment<T> extends Fragment {

    protected Bus mRxBus;
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxBus = AnRxBus.getBus();
        mRxBus.register(this);
        mPresenter = getPresenter();
    }

    protected abstract T getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxBus.unregister(this);
    }
}
