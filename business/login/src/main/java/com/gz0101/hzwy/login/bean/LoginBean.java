package com.gz0101.hzwy.login.bean;


import com.google.gson.annotations.SerializedName;

public class LoginBean {
    @SerializedName("user_id")
    private int userId;
    private int type;
    private String phone;
    @SerializedName("nickname")
    private String nickName;
    private String avatar;
    private String email;
    private int status;
    private String authorization;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "userId:" + userId +
                ",type:" + type +
                ",phone:" + phone +
                ",nickName:" + nickName +
                ",email:" + email +
                ",avatar:" + avatar +
                ",status:" + status +
                ",authorization" + authorization +
                "}";
    }
}
