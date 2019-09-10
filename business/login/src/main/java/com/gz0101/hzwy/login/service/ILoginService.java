package com.gz0101.hzwy.login.service;

import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.login.bean.LoginBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {
    //注册
    @POST("/api/register")
    Call<BaseRespone<LoginBean>> userRegister(@Body Map<String, String> param);

    //登陆
    @POST("/api/login")
    Call<BaseRespone<LoginBean>> userLogin(@Body Map<String, String> param);
}
