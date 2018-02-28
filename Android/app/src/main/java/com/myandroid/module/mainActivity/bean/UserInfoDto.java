package com.myandroid.module.mainActivity.bean;

import com.myandroid.bean.HttpResponse;

/**
 * Created by 小黑 on 2017/12/5.
 */

public class UserInfoDto extends HttpResponse {
    private String username;
    private String registerTime;
    private String headImgUrl;
    private String followNum;
    private String funNum;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
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

    public String getFunNum() {
        return funNum;
    }

    public void setFunNum(String funNum) {
        this.funNum = funNum;
    }
}
