package com.myandroid.module.mainActivity.fragment;

import android.content.Intent;
import android.lyh.com.android.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.myandroid.base.AndroidBaseActivity;
import com.myandroid.base.AndroidBaseFragment;
import com.myandroid.module.loginActivity.bean.UserInfoDto;
import com.myandroid.module.mainActivity.MainContract;
import com.myandroid.module.mainActivity.MainPresenter;
import com.myandroid.module.showImageView.ShowImageViewActivity;
import com.myandroid.utils.CommonUtil;
import com.myandroid.utils.ImageLoaderUtil;
import com.myandroid.widget.LinearTextViewLayout;
import com.staticVar.IUrl;
import com.staticVar.VarString;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.WeakHashMap;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 小黑 on 2017/11/26.
 */

public class FragmentInfo extends AndroidBaseFragment<MainPresenter> implements MainContract,View.OnClickListener {

    public final static String GETINFOSUCCESS = "GETINFOSUCCESS";
    public final static String GETINFOFAIL = "GETINFOFAIL";

    private UserInfoDto userInfoDto;
    private View view;
    private LinearTextViewLayout userName,registerTime,followNum,fanNum;
    private ImageView ivHeadImage;
    private PopupWindow popupwindow;

    private String imageUrl="";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        mPresenter =

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_info,container,false);
        mPresenter = (MainPresenter) ((AndroidBaseActivity)getActivity()).getPresenter();

        _initView();

        WeakHashMap<String,String> map = new WeakHashMap<>();
        this.getUserInfo(map);

        return view;
    }

    private void _initView() {
        userName = (LinearTextViewLayout)view.findViewById(R.id.userName);
        registerTime = (LinearTextViewLayout)view.findViewById(R.id.registerTime);
        followNum = (LinearTextViewLayout)view.findViewById(R.id.followNum);
        fanNum = (LinearTextViewLayout)view.findViewById(R.id.fanNum);
        ivHeadImage = (ImageView)view.findViewById(R.id.ivHeadImage);

        ivHeadImage.setOnClickListener(this);

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
        map.put("type","3");
        map.put("userid","45");
        mPresenter.getUserInfo(map);
    }

    @Override
    public void uploadFile(String filePath,HashMap<String,String> params) {
        mPresenter.uploadFile(filePath,params);
    }

    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(FragmentInfo.GETINFOSUCCESS)
            }
    )
    public void getinfosuccess(UserInfoDto response){
        userInfoDto = response;

        _addData();
    }

    private void _addData() {
        userName.setStrTextResult(userInfoDto.getUsername());
        registerTime.setStrTextResult(userInfoDto.getRegisterTime());
        followNum.setStrTextResult(userInfoDto.getFollowNum());
        fanNum.setStrTextResult(userInfoDto.getFanNum());

        if (userInfoDto.getHeadImgUrl()!=null && !userInfoDto.equals("")){
            imageUrl = IUrl.BASE_URL+userInfoDto.getHeadImgUrl();
            ImageLoaderUtil.getImageLoader(getContext()).displayImage(IUrl.BASE_URL+userInfoDto.getHeadImgUrl(), ivHeadImage, ImageLoaderUtil.getPhotoImageOption());
        }
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        switch (viewid){
            case R.id.ivHeadImage:
                _showPopupWindow();
                break;
            case R.id.showBigHeadImg:
                popupwindow.dismiss();
                showBigImage();
                break;
            case R.id.changeHeadimg:
                popupwindow.dismiss();
                showFileChooser();
                break;
        }
    }

    private void showBigImage() {
        Intent intent = new Intent(getActivity(), ShowImageViewActivity.class);
        intent.putExtra(VarString.imageUrl,imageUrl);
        startActivity(intent);
    }

    private void _showPopupWindow() {

        View myview = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow_headimg,null);
        popupwindow = new PopupWindow(myview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        popupwindow.setAnimationStyle(R.style.showPopupAnimation);
        TextView showBigHeadImg = (TextView) myview.findViewById(R.id.showBigHeadImg);
        TextView changeHeadimg = (TextView) myview.findViewById(R.id.changeHeadimg);
        changeHeadimg.setOnClickListener(this);
        showBigHeadImg.setOnClickListener(this);
        popupwindow.showAtLocation(myview, Gravity.BOTTOM,0,0);
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
                        HashMap<String,String> map = new HashMap<>();
                        map.put("userid","45");
                        uploadFile(path,map);
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
