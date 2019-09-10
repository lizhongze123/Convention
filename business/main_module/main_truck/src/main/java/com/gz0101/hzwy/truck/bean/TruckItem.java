package com.gz0101.hzwy.truck.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TruckItem implements Parcelable {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_TAKE_REST = 1;


    private String iconUrl;
    private String name;
    private String type;
    private int status;

    public TruckItem(){}

    public TruckItem(String iconUrl, String name, String type, int status) {
        this.iconUrl = iconUrl;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    protected TruckItem(Parcel in) {
        iconUrl = in.readString();
        name = in.readString();
        type = in.readString();
        status = in.readInt();
    }

    public static final Creator<TruckItem> CREATOR = new Creator<TruckItem>() {
        @Override
        public TruckItem createFromParcel(Parcel in) {
            return new TruckItem(in);
        }

        @Override
        public TruckItem[] newArray(int size) {
            return new TruckItem[size];
        }
    };

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iconUrl);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeInt(status);
    }
}
