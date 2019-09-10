package com.gz0101.hzwy.receipt.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;
import com.gz0101.hzwy.receipt.bean.PlaceItem;

import java.util.List;

public interface MainContract {

    interface MainView extends BaseView {
        void  onRefreshData(List<PlaceItem> list);
    }

    interface MainPresenter extends BasePresenter<MainView> {
    }

}
