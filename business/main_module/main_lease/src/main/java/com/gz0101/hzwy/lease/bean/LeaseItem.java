package com.gz0101.hzwy.lease.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class LeaseItem implements Parcelable {
    private int id;
    private String name;
    private float price;
    private String iconUrl;
    private int count;

    public LeaseItem(){}

    public LeaseItem(int id, String name, float price, String iconUrl, int count) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.iconUrl = iconUrl;
        this.count = count;
    }

    protected LeaseItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readFloat();
        iconUrl = in.readString();
        count = in.readInt();
    }

    public static final Creator<LeaseItem> CREATOR = new Creator<LeaseItem>() {
        @Override
        public LeaseItem createFromParcel(Parcel in) {
            return new LeaseItem(in);
        }

        @Override
        public LeaseItem[] newArray(int size) {
            return new LeaseItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeString(iconUrl);
        dest.writeInt(count);
    }

    @Override
    public String toString() {
        String message = "id:%d, name:%s, price:%f, iconUrl:%s, count:%d";
        return String.format(Locale.CHINA, message, id, name, price, iconUrl, count);
    }
}
