package com.gz0101.hzwy.baselibrary.request.respone.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ShopRealBean implements Serializable {
    @SerializedName("shop_id")
    private int shopId;
    private String corporate;
    @SerializedName("reg_name")
    private String regName;
    @SerializedName("reg_capital")
    private String regCapital;
    @SerializedName("reg_date")
    private String regDate;
    @SerializedName("reg_address")
    private String regAddress;
    @SerializedName("unified_credit_code")
    private String unifiedCreditCode;
    @SerializedName("business_scope")
    private String businessScope;
    @SerializedName("license_path")
    private String licensePath;
    private String rstatus;
    @SerializedName("denial_reason")
    private String denialReason;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getUnifiedCreditCode() {
        return unifiedCreditCode;
    }

    public void setUnifiedCreditCode(String unifiedCreditCode) {
        this.unifiedCreditCode = unifiedCreditCode;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getLicensePath() {
        return licensePath;
    }

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    public String getRstatus() {
        return rstatus;
    }

    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
    }

    public String getDenialReason() {
        return denialReason;
    }

    public void setDenialReason(String denialReason) {
        this.denialReason = denialReason;
    }

    @Override
    public String toString() {
        return "ShopRealBean{" +
                "shopId=" + shopId +
                ", corporate='" + corporate + '\'' +
                ", regName='" + regName + '\'' +
                ", regCapital='" + regCapital + '\'' +
                ", regDate=" + regDate +
                ", regAddress='" + regAddress + '\'' +
                ", unifiedCreditCode='" + unifiedCreditCode + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", licensePath='" + licensePath + '\'' +
                ", rstatus='" + rstatus + '\'' +
                ", denialReason='" + denialReason + '\'' +
                '}';
    }
}
