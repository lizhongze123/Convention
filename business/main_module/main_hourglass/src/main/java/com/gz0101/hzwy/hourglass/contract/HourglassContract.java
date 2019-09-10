package com.gz0101.hzwy.hourglass.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;

import java.util.List;

public interface HourglassContract {
    interface HourglassView extends BaseView {
        void showErrorPage(boolean isNoData);

        void showData(List<ShopListBean> datas, boolean isMore);

        void stopRefresh();
    }

    interface HourglassPresenter extends BasePresenter<HourglassView> {
        void loadData(boolean isMore, boolean isRefresh);
    }
}
