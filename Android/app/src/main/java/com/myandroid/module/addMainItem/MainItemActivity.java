package com.myandroid.module.addMainItem;

import android.content.Intent;
import android.lyh.com.android.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.myandroid.base.AndroidBaseActivity;
import com.myandroid.widget.TopActionBar;

import java.util.ArrayList;

/**
 * Created by 小黑 on 2017/12/12.
 */

public class MainItemActivity extends AndroidBaseActivity<MainItemPresenter> implements MainItemContract {
    private TopActionBar topActionBar;
    private ImageView ivShowImage;
    private int REQUEST_CODE = 1;
    @Override
    public MainItemPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_item);


        _Init();


    }

    private void _Init() {
        topActionBar = (TopActionBar)findViewById(R.id.topActionBar);

        topActionBar.setOnMyClickListener(new TopActionBar.MyOnClickListener() {
            @Override
            public void onReturnClick() {

            }

            @Override
            public void OnRightFirstClick() {

            }

            @Override
            public void OnRightSecondClick() {

            }
        });

        ivShowImage = (ImageView)findViewById(R.id.ivShowImage);

        ivShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSelectorUtils.openPhoto(MainItemActivity.this, REQUEST_CODE, false, 9);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            for (int i = 0; i < images.size(); i++) {
                Log.e("images",images.get(i));
            }
        }
    }
}
