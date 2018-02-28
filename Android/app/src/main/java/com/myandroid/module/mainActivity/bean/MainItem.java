package com.myandroid.module.mainActivity.bean;

import java.util.ArrayList;

/**
 * Created by 小黑 on 2017/11/28.
 */

public class MainItem {
    private String username;
    private String id;
    private String time;
    private String text;
    private String zannum;
    private String headImgUrl;
    private String viewtype;
    private ArrayList<String> image_urls;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getZannum() {
        return zannum;
    }

    public void setZannum(String zannum) {
        this.zannum = zannum;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getViewtype() {
        return viewtype;
    }

    public void setViewtype(String viewtype) {
        this.viewtype = viewtype;
    }

    public ArrayList<String> getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(ArrayList<String> image_urls) {
        this.image_urls = image_urls;
    }
}
