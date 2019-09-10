package com.gz0101.hzwy.baselibrary.cache;

import android.text.TextUtils;

import com.google.gson.Gson;

import retrofit2.http.PUT;

public class UserCache {

    private static volatile UserCache instance;

    private UserCache() {

    }

    public static UserCache getInstance() {
        if (instance == null) {
            synchronized (UserCache.class) {
                if (instance == null) {
                    instance = new UserCache();
                }
            }
        }
        return instance;
    }

    public final static String UER_INFO = "user_info";
    private UserInfo user;

    private void init() {
        String userStr = SpHelp.getInstance().getString(UER_INFO);
        if (TextUtils.isEmpty(userStr)) {
            user = new Builder().build();
        } else {
            user = new Gson().fromJson(userStr, UserInfo.class);
        }
    }

    public void clearUser() {
        this.user = new Builder().build();
        SpHelp.getInstance().save(UER_INFO, new Gson().toJson(user));
    }

    public void saveUser(UserInfo user) {
        this.user = user;
        SpHelp.getInstance().save(UER_INFO, new Gson().toJson(user));
    }

    public void saveCreatedTime(long time) {
        user.setCreatedTime(time);
        SpHelp.getInstance().save(UER_INFO, new Gson().toJson(user));
    }

    public void saveIsReal(int isReal) {
        user.setIsRealName(isReal);
        SpHelp.getInstance().save(UER_INFO, new Gson().toJson(user));
    }

    public void savePhone(String phone) {
        user.setPhone(phone);
        SpHelp.getInstance().save(UER_INFO, new Gson().toJson(user));
    }

    public String getPhone() {
        if (user == null) {
            init();
        }
        if (user == null || TextUtils.isEmpty(user.getPhone())) {
            return "";
        } else {
            return user.getPhone();
        }
    }

    public int getUserId() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return 0;
        } else {
            return user.getUserId();
        }
    }

    public String getToken() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return "";
        } else {
            return user.getToken();
        }
    }

    public String getNickName() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return "";
        } else {
            return user.getNickName();
        }
    }

    public String getAvater() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return "";
        } else {
            return user.getAvatar();
        }
    }

    public boolean getIsReal() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return false;
        } else {
            return user.getIsRealName() == 1;
        }
    }

    public int getStatus() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return 0;
        } else {
            return user.getStatus();
        }
    }

    public int getType() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return 0;
        } else {
            return user.getType();
        }
    }

    public String getEmail() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return "";
        } else {
            return user.getEmail();
        }
    }

    public long getCreateTime() {
        if (user == null) {
            init();
        }
        if (user == null || user.getUserId() <= 0) {
            return 0;
        } else {
            return user.getCreatedTime();
        }
    }


    public static class UserInfo {
        private int userId;
        private String nickName;
        private String token;
        private String phone;
        private int type;
        private String avatar;
        private String email;
        private int status;
        private long createdTime;
        private int isRealName;


        private UserInfo(Builder builder) {
            this.token = builder.token;
            this.phone = builder.phone;
            this.userId = builder.userId;
            this.nickName = builder.nickName;
            this.avatar = builder.avatar;
            this.type = builder.type;
            this.email = builder.email;
            this.status = builder.status;

        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "userId=" + userId +
                    ", nickName='" + nickName + '\'' +
                    ", token='" + token + '\'' +
                    ", phone='" + phone + '\'' +
                    ", type='" + type + '\'' +
                    ", avatar=" + avatar +
                    ", email=" + email +
                    ", status=" + status +
                    ", createdTime=" + createdTime +
                    ", isRealName=" + isRealName +
                    '}';
        }

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public int getIsRealName() {
            return isRealName;
        }

        public void setIsRealName(int isRealName) {
            this.isRealName = isRealName;
        }
    }

    public static class Builder {
        private int userId;
        private String nickName;
        private String token;
        private String phone;
        private int type;
        private String avatar;
        private String email;
        private int status;

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }

    }

}
