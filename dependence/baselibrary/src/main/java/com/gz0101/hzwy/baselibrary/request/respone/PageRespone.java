package com.gz0101.hzwy.baselibrary.request.respone;

import com.google.gson.annotations.SerializedName;

public class PageRespone<T> extends BaseRespone<T> {
    private int total;
    @SerializedName("current_page")
    private int currentPage;
    @SerializedName("per_page")
    private int perPage;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + getCode() +
                ", message='" + getMessage() + '\'' +
                ", total='" + total + '\'' +
                ", currentPage='" + currentPage + '\'' +
                ", perPage='" + perPage + '\'' +
                ", data=" + getData() +
                '}';
    }
}
