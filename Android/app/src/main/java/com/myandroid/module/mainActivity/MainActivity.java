package com.myandroid.module.mainActivity;


import android.lyh.com.android.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.myandroid.base.AndroidBaseActivity;
import com.myandroid.module.mainActivity.fragment.FragmentInfo;
import com.myandroid.module.mainActivity.fragment.FragmentMain;
import com.myandroid.module.mainActivity.fragment.FragmentMsg;
import com.myandroid.widget.MainButtomItem;
import com.myandroid.widget.TopActionBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class MainActivity extends AndroidBaseActivity<MainPresenter> implements MainContract,View.OnClickListener{
    private MainButtomItem mbiMain,mbiInfo,mbiMessage;
    private int lastPosition;
    private ArrayList<MainButtomItem> mbilists= new ArrayList<>();
    private ArrayList<Fragment> listFragments= new ArrayList<>();
    private Fragment fragmentInfo,fragmentMain,fragmentMsg;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapterPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _init();

    }

    private void _init() {
        lastPosition = 0;
        fragmentInfo = new FragmentInfo();
        fragmentMain = new FragmentMain();
        fragmentMsg = new FragmentMsg();

        listFragments.add(fragmentMain);
        listFragments.add(fragmentInfo);
        listFragments.add(fragmentMsg);

        mbiInfo = (MainButtomItem) findViewById(R.id.mbiInfo);
        mbiMain = (MainButtomItem) findViewById(R.id.mbiMain);
        mbiMessage = (MainButtomItem) findViewById(R.id.mbiMessage);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        mbiInfo.setOnClickListener(this);
        mbiMain.setOnClickListener(this);
        mbiMessage.setOnClickListener(this);

        mbiMain.setSelected(true);

        mbilists.add(mbiMain);//主界面
        mbilists.add(mbiInfo);//个人信息
        mbilists.add(mbiMessage);//留言



        adapterPager = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragments.get(position);
            }

            @Override
            public int getCount() {
                return listFragments.size();
            }
        };

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                _intoFragment(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapterPager);

    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        switch (viewid){
            case R.id.mbiMain:

                _intoFragment(0);

                break;
            case R.id.mbiInfo:

                _intoFragment(1);

                break;
            case R.id.mbiMessage:

                _intoFragment(2);

                break;

        }
    }

    private void _intoFragment(int position){

        viewPager.setCurrentItem(position,false);
        mbilists.get(lastPosition).setSelected(false);
        mbilists.get(position).setSelected(true);
        lastPosition = position;


    }

    @Override
    public void getList(WeakHashMap<String, String> map) {

    }

    @Override
    public void getUserInfo(WeakHashMap<String, String> map) {

    }

    @Override
    public void uploadFile(String filePath,HashMap<String,String> params) {

    }
}
