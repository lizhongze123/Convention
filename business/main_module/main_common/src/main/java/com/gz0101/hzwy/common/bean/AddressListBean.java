package com.gz0101.hzwy.common.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressListBean implements Serializable {
    @SerializedName("address_id")
    private int addressId;
    @SerializedName("contact_name")
    private String contactName;
    @SerializedName("contact_phone")
    private String contactPhone;
    @SerializedName("contact_address")
    private String contactAddress;
    @SerializedName("default")
    private int isDefault;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "AddressListBean{" +
                "addressId=" + addressId +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
