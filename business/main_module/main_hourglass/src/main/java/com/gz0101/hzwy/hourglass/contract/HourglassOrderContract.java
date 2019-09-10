package com.gz0101.hzwy.hourglass.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;

import java.util.List;

public interface HourglassOrderContract {
    interface HourglassOrderView extends BaseView {
    }

    interface HourglassOrderPresenter extends BasePresenter<HourglassOrderView> {

    }
}
