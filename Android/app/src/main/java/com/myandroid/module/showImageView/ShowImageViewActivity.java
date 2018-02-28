package com.myandroid.module.showImageView;

import android.app.Activity;
import android.content.Intent;
import android.lyh.com.android.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.myandroid.utils.ImageLoaderUtil;
import com.staticVar.IUrl;
import com.staticVar.VarString;

/**
 * Created by 小黑 on 2017/12/10.
 */

public class ShowImageViewActivity extends Activity{
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_image_view);
        init();

    }

    private void init() {

        imageView = (ImageView)findViewById(R.id.imageView);
        Intent intent = getIntent();
        String imageurl = intent.getStringExtra(VarString.imageUrl);
        if (imageurl!=null && !imageurl.equals("")){
            ImageLoaderUtil
                    .getImageLoader(this)
                    .displayImage(imageurl, imageView, ImageLoaderUtil.getPhotoImageOption());
        }

    }
}
