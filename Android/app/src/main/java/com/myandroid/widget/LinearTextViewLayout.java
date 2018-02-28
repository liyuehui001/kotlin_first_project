package com.myandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.lyh.com.android.R;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 小黑 on 2017/12/4.
 */

public class LinearTextViewLayout extends LinearLayout {
    private String strTextDesc;
    private TextView textDesc,textResult;


    public LinearTextViewLayout(Context context) {
        super(context);
        _initView(context);
    }

    public LinearTextViewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        _initView(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.LinearTextViewLayout);
        strTextDesc = typedArray.getString(R.styleable.LinearTextViewLayout_strTextDesc);

        if (strTextDesc!=null || !strTextDesc.equals("")){
            textDesc.setText(strTextDesc);
        }

    }

    public LinearTextViewLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _initView(context);
    }

    private void _initView(Context ctx){
        LayoutInflater.from(ctx).inflate(R.layout.layout_textview_linearlayout,this,true);
        textDesc = (TextView)findViewById(R.id.textDesc);
        textResult = (TextView)findViewById(R.id.textResult);
    }

    public void setStrTextResult(String str){
        if (str!=null || !str.equals("")){
            textResult.setText(str);
        }
    }



}
