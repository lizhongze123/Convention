package com.gz0101.hzwy.hourglass.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;

import java.util.List;

public interface ShopDetailContract {
    interface ShopDetailView extends BaseView {
    }

    interface ShopDetailPresenter extends BasePresenter<ShopDetailView> {
    }
}
