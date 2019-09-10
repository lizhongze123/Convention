package com.gz0101.hzwy.hourglass.model;

import com.gz0101.hzwy.baselibrary.constance.RequestParamConstance;
import com.gz0101.hzwy.baselibrary.request.RequestParamUtil;
import com.gz0101.hzwy.baselibrary.request.RetrofitClient;
import com.gz0101.hzwy.baselibrary.request.callback.CallBackFactory;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.PageRespone;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.baselibrary.request.service.ILocalService;
import com.gz0101.hzwy.hourglass.service.IHourglassService;

import java.util.List;

import retrofit2.Call;

public class HourglassModel {
    private static volatile HourglassModel instance;

    private HourglassModel() {

    }

    public static HourglassModel getInstance() {
        if (instance == null) {
            synchronized (HourglassModel.class) {
                if (instance == null) {
                    instance = new HourglassModel();
                }
            }
        }
        return instance;
    }

    public void getData(int page, ICallBack<PageRespone<List<ShopListBean>>> callBack) {
        Call<PageRespone<List<ShopListBean>>> call = RetrofitClient.getInstance(ILocalService.class).getShopList(RequestParamUtil.createLocalParams(RequestParamConstance.TYPE, 1 + "", RequestParamConstance.PAGE, page + ""));
        call.enqueue(CallBackFactory.getInstance().callback(callBack));
    }
}
