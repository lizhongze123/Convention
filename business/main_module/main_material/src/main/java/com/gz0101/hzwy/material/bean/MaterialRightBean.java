package com.gz0101.hzwy.material.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MaterialRightBean implements Parcelable {
    private int id;
    private String name;
    private float price;
    private int count;
    private String iconUrl;

    public MaterialRightBean() {}

    public MaterialRightBean(int id, String name, float price, int count, String iconUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.iconUrl = iconUrl;
    }

    protected MaterialRightBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readFloat();
        count = in.readInt();
        iconUrl = in.readString();
    }

    public static final Creator<MaterialRightBean> CREATOR = new Creator<MaterialRightBean>() {
        @Override
        public MaterialRightBean createFromParcel(Parcel in) {
            return new MaterialRightBean(in);
        }

        @Override
        public MaterialRightBean[] newArray(int size) {
            return new MaterialRightBean[size];
        }
    };

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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
        dest.writeInt(count);
        dest.writeString(iconUrl);
    }
}
