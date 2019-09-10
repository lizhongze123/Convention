package com.gz0101.hzwy.baselibrary.mvp;


import com.gz0101.hzwy.baselibrary.constance.RequestParamConstance;
import com.gz0101.hzwy.baselibrary.request.RequestParamUtil;
import com.gz0101.hzwy.baselibrary.request.RetrofitClient;
import com.gz0101.hzwy.baselibrary.request.callback.CallBackFactory;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.baselibrary.request.service.ILocalService;

import retrofit2.Call;

public class BaseModel {

    private static volatile BaseModel instance;

    private BaseModel() {
    }

    public static BaseModel getInstance() {
        if (instance == null) {
            synchronized (BaseModel.class) {
                if (instance == null) {
                    instance = new BaseModel();
                }
            }
        }
        return instance;
    }

    public void obtainVerify(String phoneNum, int mode, ICallBack<BaseRespone> callBack) {
        Call<BaseRespone> call = RetrofitClient.getInstance(ILocalService.class).noteVerify(RequestParamUtil.createLocalParams(RequestParamConstance.PHONE_NUM, phoneNum, RequestParamConstance.MODE, mode + ""));
        call.enqueue(CallBackFactory.getInstance().callback(callBack));
    }

}
