package com.gz0101.hzwy.baselibrary.request.respone.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodsListBean {
    @SerializedName("goods_id")
    private int goodsId;
    @SerializedName("classify_id")
    private int eclassifyId;
    private String title;
    @SerializedName("cover_path")
    private String coverPath;
    @SerializedName("selling_price")
    private String sellingPrice;
    @SerializedName("inventory_control")
    private int inventoryControl;
    private int inventory;
    @SerializedName("sold_quantity")
    private int soldQuantity;
    private String description;
    private int sort;
    private int status;
    private ShopListBean shop;
    private AreaBean area;
    @Expose
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getEclassifyId() {
        return eclassifyId;
    }

    public void setEclassifyId(int eclassifyId) {
        this.eclassifyId = eclassifyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getInventoryControl() {
        return inventoryControl;
    }

    public void setInventoryControl(int inventoryControl) {
        this.inventoryControl = inventoryControl;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ShopListBean getShop() {
        return shop;
    }

    public void setShop(ShopListBean shop) {
        this.shop = shop;
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "GoodsListBean{" +
                "goodsId=" + goodsId +
                ", eclassifyId=" + eclassifyId +
                ", title='" + title + '\'' +
                ", coverPath='" + coverPath + '\'' +
                ", selling_price='" + sellingPrice + '\'' +
                ", inventoryControl=" + inventoryControl +
                ", inventory=" + inventory +
                ", soldQuantity=" + soldQuantity +
                ", description='" + description + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", shop=" + shop +
                ", area=" + area +
                '}';
    }
}
