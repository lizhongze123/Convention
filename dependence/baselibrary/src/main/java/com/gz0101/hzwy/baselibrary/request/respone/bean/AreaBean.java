package com.gz0101.hzwy.baselibrary.request.respone.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AreaBean implements Serializable {
    @SerializedName("area_id")
    private int areaId;
    @SerializedName("area_name")
    private String areaName;
    @SerializedName("name_relation")
    private List<Object> nameRelation;

    @Override
    public String toString() {
        return "AreaBean{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", nameRelation=" + nameRelation +
                '}';
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<Object> getNameRelation() {
        return nameRelation;
    }

    public void setNameRelation(List<Object> nameRelation) {
        this.nameRelation = nameRelation;
    }
}
