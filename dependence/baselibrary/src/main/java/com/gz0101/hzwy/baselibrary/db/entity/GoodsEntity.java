package com.gz0101.hzwy.baselibrary.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GoodsEntity {

    @Id(autoincrement = true)
    private Long id;
    private int shopId;
    private int goodsId;
    private int count;

    @Generated(hash = 1420937120)
    public GoodsEntity(Long id, int shopId, int goodsId, int count) {
        this.id = id;
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.count = count;
    }

    @Generated(hash = 223916156)
    public GoodsEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getShopId() {
        return this.shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
