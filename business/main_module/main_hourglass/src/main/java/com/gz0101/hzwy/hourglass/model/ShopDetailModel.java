package com.gz0101.hzwy.hourglass.model;

import com.gz0101.hzwy.baselibrary.constance.RequestParamConstance;
import com.gz0101.hzwy.baselibrary.request.RequestParamUtil;
import com.gz0101.hzwy.baselibrary.request.RetrofitClient;
import com.gz0101.hzwy.baselibrary.request.callback.CallBackFactory;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.PageRespone;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.baselibrary.request.service.ILocalService;

import java.util.List;

import retrofit2.Call;

public class ShopDetailModel {
    private static volatile ShopDetailModel instance;

    private ShopDetailModel() {

    }

    public static ShopDetailModel getInstance() {
        if (instance == null) {
            synchronized (ShopDetailModel.class) {
                if (instance == null) {
                    instance = new ShopDetailModel();
                }
            }
        }
        return instance;
    }

}
