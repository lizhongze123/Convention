package com.gz0101.hzwy.truck.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;

public interface MainContract {

    interface MainView extends BaseView {
    }

    interface MainPresenter extends BasePresenter<MainView> {
    }

}
