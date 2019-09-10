package com.gz0101.hzwy.hourglass.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;
import com.gz0101.hzwy.baselibrary.request.respone.bean.GoodsListBean;

import java.util.List;

public interface HourglassCommodityContract {
    interface HourglassCommodityView extends BaseView {
        void showErrorPage(boolean isNoData);

        void showData(List<GoodsListBean> listBeanList);
    }

    interface HourglassCommodityPresenter extends BasePresenter<HourglassCommodityView> {
        void loadData(int shopId);
    }
}
