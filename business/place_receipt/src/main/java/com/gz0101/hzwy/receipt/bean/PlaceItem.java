package com.gz0101.hzwy.receipt.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceItem implements Parcelable {
    private long id;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String town;
    private String street;
    private String area;
    private boolean isDefault;

    public PlaceItem() {}

    public PlaceItem(long id, String name, String phone, String province, String city, String town, String street, String area, boolean isDefault) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.town = town;
        this.street = street;
        this.area = area;
        this.isDefault = isDefault;
    }

    protected PlaceItem(Parcel in) {
        id = in.readLong();
        name = in.readString();
        phone = in.readString();
        province = in.readString();
        city = in.readString();
        town = in.readString();
        street = in.readString();
        area = in.readString();
        isDefault = in.readByte() != 0;
    }

    public static final Creator<PlaceItem> CREATOR = new Creator<PlaceItem>() {
        @Override
        public PlaceItem createFromParcel(Parcel in) {
            return new PlaceItem(in);
        }

        @Override
        public PlaceItem[] newArray(int size) {
            return new PlaceItem[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone == null?"":phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getArea() {
        return area == null?"":area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province == null?"":province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city == null?"":city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town == null?"":town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street == null?"":street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(province);
        dest.writeString(city);
        dest.writeString(town);
        dest.writeString(street);
        dest.writeString(area);
        dest.writeByte((byte) (isDefault ? 1 : 0));
    }
}
