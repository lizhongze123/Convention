package com.gz0101.hzwy.hourglass.presenter;

import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.PageRespone;
import com.gz0101.hzwy.baselibrary.request.respone.ResponseCode;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.contract.HourglassContract;
import com.gz0101.hzwy.hourglass.contract.HourglassOrderContract;
import com.gz0101.hzwy.hourglass.model.HourglassModel;

import java.lang.ref.SoftReference;
import java.util.List;

public class HourglassOrderPresenterImpl implements HourglassOrderContract.HourglassOrderPresenter {

    private SoftReference<HourglassOrderContract.HourglassOrderView> mView;

    private int curPage = 1;

    @Override
    public void attachView(HourglassOrderContract.HourglassOrderView view) {
        mView = new SoftReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}

