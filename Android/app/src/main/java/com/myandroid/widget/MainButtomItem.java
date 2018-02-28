package com.myandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.lyh.com.android.R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 小黑 on 2017/11/7.
 */

public class MainButtomItem extends RelativeLayout {
    private Drawable imgBackground;
    private boolean isHaveNews;
    private String text;

    private ImageView ivMainIcon;
    private ImageView ivHaveNews;
    private TextView tvMainText;


    public MainButtomItem(Context context) {
        super(context);
    }

    public MainButtomItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_bottom_item,this,true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MainButtomItem);
        imgBackground = typedArray.getDrawable(R.styleable.MainButtomItem_imgBackground);
        isHaveNews = typedArray.getBoolean(R.styleable.MainButtomItem_isHaveNews,false);//默认不显示
        text = typedArray.getString(R.styleable.MainButtomItem_text_m);

        typedArray.recycle();
        _init();
        _setProps();

    }

    private void _init() {
        ivMainIcon = (ImageView)findViewById(R.id.ivMainIcon);
        ivHaveNews = (ImageView)findViewById(R.id.ivHaveNews);
        tvMainText = (TextView)findViewById(R.id.tvMainText);
    }

    private void _setProps() {
        ivMainIcon.setBackground(imgBackground);
        setIsHaveNews(isHaveNews);
        tvMainText.setText(text);
    }

    public MainButtomItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setIsHaveNews(boolean isShow){
        if (isShow){
            ivHaveNews.setVisibility(VISIBLE);
        }else{
            ivHaveNews.setVisibility(INVISIBLE);
        }
    }



    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        ivMainIcon.setSelected(selected);
        if (selected){
            tvMainText.setTextColor(getResources().getColor(R.color.yellow));
        }else{
            tvMainText.setTextColor(getResources().getColor(R.color.black));
        }
    }
}
