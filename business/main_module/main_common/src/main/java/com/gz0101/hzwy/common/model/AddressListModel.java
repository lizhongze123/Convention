package com.gz0101.hzwy.common.model;

import com.gz0101.hzwy.baselibrary.request.RequestParamUtil;
import com.gz0101.hzwy.baselibrary.request.RetrofitClient;
import com.gz0101.hzwy.baselibrary.request.callback.CallBackFactory;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.common.bean.AddressListBean;
import com.gz0101.hzwy.common.service.ICommonService;

import java.util.List;

import retrofit2.Call;

public class AddressListModel {
    private static volatile AddressListModel instance;

    private AddressListModel() {

    }

    public static AddressListModel getInstance() {
        if (instance == null) {
            synchronized (AddressListModel.class) {
                if (instance == null) {
                    instance = new AddressListModel();
                }
            }
        }
        return instance;
    }

    public void getData(ICallBack<BaseRespone<List<AddressListBean>>> callBack) {
        Call<BaseRespone<List<AddressListBean>>> call = RetrofitClient.getInstance(ICommonService.class).getAddressList(RequestParamUtil.createLocalParams());
        call.enqueue(CallBackFactory.getInstance().callback(callBack));
    }
}
