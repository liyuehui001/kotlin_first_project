package com.myandroid.module.mainActivity.fragment;

import android.content.Intent;
import android.lyh.com.android.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.myandroid.base.AndroidBaseActivity;
import com.myandroid.base.AndroidBaseFragment;
import com.myandroid.bean.HttpResponse;
import com.myandroid.module.mainActivity.MainContract;
import com.myandroid.module.mainActivity.MainPresenter;
import com.myandroid.utils.CommonUtil;
import com.myandroid.utils.ToastUtils;
import com.staticVar.RequestStr;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.WeakHashMap;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 小黑 on 2017/11/26.
 */

public class FragmentMsg extends AndroidBaseFragment<MainPresenter> implements MainContract {

    private View view;
    private Button btnUploadFile;
    public static final String uploadsuccess="uploadsuccess";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_msg,container,false);
        mPresenter = (MainPresenter) ((AndroidBaseActivity)getActivity()).getPresenter();
        _init();

        return view;
    }

    private void _init() {
        btnUploadFile = (Button)view.findViewById(R.id.btnUploadFile);

        btnUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();

            }
        });
    }

    @Override
    protected MainPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void getList(WeakHashMap<String, String> map) {

    }
    @Override
    public void getUserInfo(WeakHashMap<String, String> map) {

    }

    @Override
    public void uploadFile(String filePath,HashMap<String,String> params) {
        mPresenter.uploadFile(filePath ,params);
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(FragmentMain.getListSuccess)
            }
    )
    public void uploadsuccess(HttpResponse response){
        if (response.getResultCode().equals(RequestStr.requestCode.requestSuccess)){
            ToastUtils.showToast("上传成功");
            return;
        }
        ToastUtils.showToast(response.getMessage());
    }

    private static final int FILE_SELECT_CODE = 0;
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getActivity(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    private static final String TAG = "ChooseFile";
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path
                    String path = null;
                    try {
                        path = CommonUtil.getPath(getActivity(), uri);
                        HashMap<String,String> params = new HashMap<>();
                        params.put("userid","45");//将数据放进去
                        uploadFile(path,params);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




}
