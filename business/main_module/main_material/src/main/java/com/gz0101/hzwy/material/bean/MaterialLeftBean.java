package com.gz0101.hzwy.material.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MaterialLeftBean implements Parcelable {
    private boolean isChecked;
    private String name;

    public MaterialLeftBean(){}

    public MaterialLeftBean(boolean isChecked, String name) {
        this.isChecked = isChecked;
        this.name = name;
    }

    protected MaterialLeftBean(Parcel in) {
        isChecked = in.readByte() != 0;
        name = in.readString();
    }

    public static final Creator<MaterialLeftBean> CREATOR = new Creator<MaterialLeftBean>() {
        @Override
        public MaterialLeftBean createFromParcel(Parcel in) {
            return new MaterialLeftBean(in);
        }

        @Override
        public MaterialLeftBean[] newArray(int size) {
            return new MaterialLeftBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeString(name);
    }
}
