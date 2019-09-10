package com.gz0101.hzwy.common.service;

import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.common.bean.AddressListBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ICommonService {
    //获取收货地址列表
    @GET("/api/user/address/lists")
    Call<BaseRespone<List<AddressListBean>>> getAddressList(@QueryMap Map<String, String> params);


}
