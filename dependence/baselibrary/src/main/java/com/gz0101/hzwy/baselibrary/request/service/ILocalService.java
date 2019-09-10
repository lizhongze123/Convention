package com.gz0101.hzwy.baselibrary.request.service;


import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.baselibrary.request.respone.PageRespone;
import com.gz0101.hzwy.baselibrary.request.respone.bean.GoodsListBean;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;

public interface ILocalService {
    //短信验证码
    @POST("/sms/captcha")
    Call<BaseRespone> noteVerify(@Body Map<String, String> param);

    //店铺分页
    @GET("/api/shop/paginate")
    Call<PageRespone<List<ShopListBean>>> getShopList(@QueryMap Map<String, String> param);

    //商品分页
    @GET("/api/goods/paginate")
    Call<PageRespone<List<GoodsListBean>>> getGoodsList(@QueryMap Map<String, String> param);

}
