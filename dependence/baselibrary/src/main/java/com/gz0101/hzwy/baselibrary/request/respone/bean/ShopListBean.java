package com.gz0101.hzwy.baselibrary.request.respone.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopListBean implements Serializable {
    @SerializedName("shop_id")
    private int shopId;
    @SerializedName("shop_name")
    private String shopName;
    @SerializedName("shop_poster")
    private String shopPoster;
    @SerializedName("shop_phone")
    private String shopPhone;
    @SerializedName("shop_address")
    private String shopAddress;
    @SerializedName("shop_logo")
    private String shopLogo;
    @SerializedName("delivery_mode")
    private int deliveryMode;
    private String description;
    private int credit;
    @SerializedName("is_real")
    private int isReal;
    private int status;

    private ShopUserBean user;
    private AreaBean area;
    private ShopCategoryBean category;
    private ShopRealBean real;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPoster() {
        return shopPoster;
    }

    public void setShopPoster(String shopPoster) {
        this.shopPoster = shopPoster;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public int getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(int deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getIsReal() {
        return isReal;
    }

    public void setIsReal(int isReal) {
        this.isReal = isReal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ShopUserBean getUser() {
        return user;
    }

    public void setUser(ShopUserBean user) {
        this.user = user;
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public ShopCategoryBean getCategory() {
        return category;
    }

    public void setCategory(ShopCategoryBean category) {
        this.category = category;
    }

    public ShopRealBean getReal() {
        return real;
    }

    public void setReal(ShopRealBean real) {
        this.real = real;
    }

    @Override
    public String toString() {
        return "ShopListBean{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopPoster='" + shopPoster + '\'' +
                ", shopPhone='" + shopPhone + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopLogo='" + shopLogo + '\'' +
                ", deliveryMode=" + deliveryMode +
                ", description='" + description + '\'' +
                ", credit=" + credit +
                ", isReal=" + isReal +
                ", status=" + status +
                ", user=" + user +
                ", area=" + area +
                ", category=" + category +
                ", real=" + real +
                '}';
    }
}
