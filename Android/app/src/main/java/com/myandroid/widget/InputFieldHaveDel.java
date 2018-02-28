package com.myandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.lyh.com.android.R;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 小黑 on 2017/11/2.
 */

public class InputFieldHaveDel extends RelativeLayout {

    private String text;
    private boolean isPassword;

    private TextView tvText;
    private ImageView ivDel;
    private EditText etInput;

    public InputFieldHaveDel(Context context) {
        super(context);
    }

    public InputFieldHaveDel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_input_field,this,true);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.InputFieldHaveDel);
        _init(typedArray);
    }



    public InputFieldHaveDel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void _init(TypedArray typedArray) {
        text = typedArray.getString(R.styleable.InputFieldHaveDel_text);
        isPassword = typedArray.getBoolean(R.styleable.InputFieldHaveDel_isPassword,false);


        tvText = (TextView)findViewById(R.id.tvText);
        tvText.setText(text);

        ivDel = (ImageView)findViewById(R.id.ivDel);
        etInput = (EditText)findViewById(R.id.etInput);

        etInput.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

        if (isPassword){
            etInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }


        ivDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.setText("");
            }
        });
    }

    public EditText getEditText(){
        return etInput;
    }

}
