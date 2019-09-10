package com.gz0101.hzwy.login.model;


import com.gz0101.hzwy.baselibrary.constance.RequestParamConstance;
import com.gz0101.hzwy.baselibrary.request.RequestParamUtil;
import com.gz0101.hzwy.baselibrary.request.RetrofitClient;
import com.gz0101.hzwy.baselibrary.request.callback.CallBackFactory;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.login.bean.LoginBean;
import com.gz0101.hzwy.login.service.ILoginService;

import retrofit2.Call;

public class RegisterModel {
    private static volatile RegisterModel instance;

    private RegisterModel() {

    }

    public static RegisterModel getInstance() {
        if (instance == null) {
            synchronized (RegisterModel.class) {
                if (instance == null) {
                    instance = new RegisterModel();
                }
            }
        }
        return instance;
    }

    public void userRegister(String phoneNum, String verification, String pwd, int type, ICallBack<BaseRespone<LoginBean>> callBack) {
        Call<BaseRespone<LoginBean>> call =  RetrofitClient.getInstance(ILoginService.class).userRegister(RequestParamUtil.createLocalParams(RequestParamConstance.PHONE_NUM, phoneNum, RequestParamConstance.CAPTCHA, verification, RequestParamConstance.PASSWORD, pwd, RequestParamConstance.TYPE, type + ""));
        call.enqueue(CallBackFactory.getInstance().callback(callBack));
    }

}
