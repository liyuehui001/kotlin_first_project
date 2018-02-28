package com.myandroid.module.mainActivity.fragment;

import android.content.Intent;
import android.lyh.com.android.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.myandroid.base.AndroidBaseActivity;
import com.myandroid.base.AndroidBaseFragment;
import com.myandroid.module.addMainItem.MainItemActivity;
import com.myandroid.module.loginActivity.LoginActivity;
import com.myandroid.module.mainActivity.MainActivity;
import com.myandroid.module.mainActivity.MainContract;
import com.myandroid.module.mainActivity.MainPresenter;
import com.myandroid.module.mainActivity.adapter.FragmentMainAdapter;
import com.myandroid.module.mainActivity.bean.FragmentMainDto;
import com.myandroid.module.mainActivity.bean.MainItem;
import com.myandroid.utils.FullyLinearLayoutManager;
import com.myandroid.widget.TopActionBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by 小黑 on 2017/11/26.
 */

public class FragmentMain extends AndroidBaseFragment<MainPresenter> implements MainContract{

    private TopActionBar topActionBar;
    private View view;
    private RecyclerView rvContent;
    public static final String getListSuccess="getListSuccess";
    public static final String getListFail="getListFail";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main,container,false);
        mPresenter = (MainPresenter) ((AndroidBaseActivity)getActivity()).getPresenter();

        _init();

        return view;
    }

    private void _init() {

        topActionBar = (TopActionBar) view.findViewById(R.id.topActionBar);
        topActionBar.setOnMyClickListener(new TopActionBar.MyOnClickListener() {
            @Override
            public void onReturnClick() {
                getActivity().finish();
            }

            @Override
            public void OnRightFirstClick() {

            }

            @Override
            public void OnRightSecondClick() {
                startActivity(new Intent(getActivity(), MainItemActivity.class));
            }
        });




        WeakHashMap<String,String> map = new WeakHashMap<>();
        this.getList(map);

    }

    @Override
    protected MainPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void getList(WeakHashMap<String, String> map) {
        map.put("type","1");
        map.put("userid","45");
        mPresenter.getList(map);
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(FragmentMain.getListSuccess)
            }
    )
    public void getList(FragmentMainDto dto){
        ArrayList<MainItem> lists = new ArrayList<>();
        lists = dto.getLists();

        rvContent = (RecyclerView) view.findViewById(R.id.rvContent);
        FragmentMainAdapter adapter = new FragmentMainAdapter(getActivity(),lists);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(getActivity());

        rvContent.setLayoutManager(mLayoutManager);
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setAdapter(adapter);

    }
    @Override
    public void getUserInfo(WeakHashMap<String, String> map) {

    }

    @Override
    public void uploadFile(String filePath,HashMap<String,String> params) {

    }
}
