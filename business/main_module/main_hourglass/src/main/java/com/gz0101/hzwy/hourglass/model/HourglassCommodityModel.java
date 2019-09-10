package com.gz0101.hzwy.hourglass.model;


import com.gz0101.hzwy.baselibrary.constance.RequestParamConstance;
import com.gz0101.hzwy.baselibrary.request.RequestParamUtil;
import com.gz0101.hzwy.baselibrary.request.RetrofitClient;
import com.gz0101.hzwy.baselibrary.request.callback.CallBackFactory;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.PageRespone;
import com.gz0101.hzwy.baselibrary.request.respone.bean.GoodsListBean;
import com.gz0101.hzwy.baselibrary.request.service.ILocalService;
import com.gz0101.hzwy.hourglass.service.IHourglassService;

import java.util.List;

import retrofit2.Call;

public class HourglassCommodityModel {
    private static volatile HourglassCommodityModel instance;

    private HourglassCommodityModel() {

    }

    public static HourglassCommodityModel getInstance() {
        if (instance == null) {
            synchronized (HourglassCommodityModel.class) {
                if (instance == null) {
                    instance = new HourglassCommodityModel();
                }
            }
        }
        return instance;
    }

    public void getData(int shopId, ICallBack<PageRespone<List<GoodsListBean>>> callBack) {
        Call<PageRespone<List<GoodsListBean>>> call = RetrofitClient.getInstance(ILocalService.class).getGoodsList(RequestParamUtil.createLocalParams(RequestParamConstance.TYPE, 1 + "", RequestParamConstance.SHOP_ID, shopId + ""));
        call.enqueue(CallBackFactory.getInstance().callback(callBack));
    }
}
