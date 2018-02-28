package com.myandroid.module.mainActivity.bean;

import com.myandroid.bean.HttpResponse;

import java.util.ArrayList;

/**
 * Created by 小黑 on 2017/11/28.
 */

public class FragmentMainDto extends HttpResponse{
    private ArrayList<MainItem> mainlists;

    public ArrayList<MainItem> getLists() {
        return mainlists;
    }

    public void setLists(ArrayList<MainItem> mainlists) {
        this.mainlists = mainlists;
    }
}
