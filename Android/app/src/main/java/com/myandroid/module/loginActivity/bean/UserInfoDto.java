package com.myandroid.module.loginActivity.bean;

import com.myandroid.bean.HttpResponse;

/**
 * Created by 小黑 on 2017/11/7.
 */

public class UserInfoDto extends HttpResponse{
    private String username;
    private String headImgUrl;
    private String followNum;
    private String fanNum;
    private String registerTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getFollowNum() {
        return followNum;
    }

    public void setFollowNum(String followNum) {
        this.followNum = followNum;
    }

    public String getFanNum() {
        return fanNum;
    }

    public void setFanNum(String fanNum) {
        this.fanNum = fanNum;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
