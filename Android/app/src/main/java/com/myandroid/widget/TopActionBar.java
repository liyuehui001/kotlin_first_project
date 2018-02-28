package com.myandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.lyh.com.android.R;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 小黑 on 2017/11/3.
 */

public class TopActionBar extends LinearLayout {
    private String Title;
    private Drawable leftIcon;
    private Drawable rightFirstIcon;
    private Drawable rightSecondIcon;
    private MyOnClickListener listener;

    private TextView tvReturn,tvRightFirst,tvRightSecond,tvTitle;



    public TopActionBar(Context context) {
        super(context);
    }

    public TopActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_top_actionbar,this,true);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.TopActionBar);
        Title = ta.getString(R.styleable.TopActionBar_title_title);
        leftIcon = ta.getDrawable(R.styleable.TopActionBar_left_icon);
        rightFirstIcon =  ta.getDrawable(R.styleable.TopActionBar_right_first_icon);
        rightSecondIcon =  ta.getDrawable(R.styleable.TopActionBar_right_second_icon);

        ta.recycle();

        _init();

        _setProp();

    }

    private void _setProp() {
        if (leftIcon!=null){
            tvReturn.setBackground(leftIcon);
        }

        if (rightFirstIcon!=null){
            tvRightFirst.setBackground(rightFirstIcon);
        }

        if (rightSecondIcon!=null){
            tvRightSecond.setBackground(rightSecondIcon);
        }

        if (Title!=null){
            tvTitle.setText(Title);
        }
    }

    public void _init() {
        tvReturn =(TextView) findViewById(R.id.tvReturn);
        tvRightFirst =(TextView)findViewById(R.id.tvRightFirst);
        tvRightSecond =(TextView)findViewById(R.id.tvRightSecond);
        tvTitle = (TextView)findViewById(R.id.tvTitle);

        tvReturn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onReturnClick();
                }
            }
        });

        tvRightFirst.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnRightFirstClick();
                }
            }
        });

        tvRightSecond.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.OnRightSecondClick();
                }
            }
        });
    }

    public TopActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextView getLeftTextView(){
        return tvReturn;
    }
    public void setTopBarBackground(int bgColor){
        setBackgroundColor(bgColor);
    }

    public TextView getRightFirstTextView(){
        return tvRightFirst;
    }
    public TextView getRightSecondTextView(){
        return tvRightSecond;
    }
    public TextView getTitleTextView(){
        return tvTitle;
    }

    public void setOnMyClickListener(MyOnClickListener listener){
        this.listener = listener;
    }

    public interface MyOnClickListener{
        void onReturnClick();
        void OnRightFirstClick();
        void OnRightSecondClick();
    }




}
