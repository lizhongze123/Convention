package com.gz0101.hzwy.baselibrary.request.respone.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShopCategoryBean implements Serializable {
    @SerializedName("tag_id")
    private int tagId;
    private String name;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShopCategoryBean{" +
                "tagId=" + tagId +
                "name=" + name + "}";
    }
}
