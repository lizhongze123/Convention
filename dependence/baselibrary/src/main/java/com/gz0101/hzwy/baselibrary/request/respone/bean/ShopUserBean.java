package com.gz0101.hzwy.baselibrary.request.respone.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopUserBean implements Serializable {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("nickname")
    private String nickName;
    private String avatar;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ShopUserBean{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
