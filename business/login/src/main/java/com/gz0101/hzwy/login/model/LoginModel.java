package com.gz0101.hzwy.login.model;

import com.gz0101.hzwy.baselibrary.constance.RequestParamConstance;
import com.gz0101.hzwy.baselibrary.request.RetrofitClient;
import com.gz0101.hzwy.baselibrary.request.RequestParamUtil;
import com.gz0101.hzwy.baselibrary.request.callback.CallBackFactory;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.login.bean.LoginBean;
import com.gz0101.hzwy.login.service.ILoginService;

import retrofit2.Call;

public class LoginModel {
    private static volatile LoginModel instance;

    private LoginModel() {

    }

    public static LoginModel getInstance() {
        if (instance == null) {
            synchronized (LoginModel.class) {
                if (instance == null) {
                    instance = new LoginModel();
                }
            }
        }
        return instance;
    }

    public void userLogin(String phoneNum, String pwd, String type, String code, ICallBack<BaseRespone<LoginBean>> callBack) {
        Call<BaseRespone<LoginBean>> call = RetrofitClient.getInstance(ILoginService.class).userLogin(RequestParamUtil.createLocalParams(RequestParamConstance.PHONE_NUM, phoneNum, RequestParamConstance.SECRET, pwd, RequestParamConstance.MODE, type, RequestParamConstance.CODE, code));
        call.enqueue(CallBackFactory.getInstance().callback(callBack));
    }

}
