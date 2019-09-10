package com.gz0101.hzwy.common.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;
import com.gz0101.hzwy.common.bean.AddressListBean;

import java.util.List;

public interface AddressListContract {
    interface AddressListView extends BaseView {
        void showData(List<AddressListBean> datas);

        void showErrorPage();
    }

    interface AddressListPresenter extends BasePresenter<AddressListView> {
        void getData();
    }
}
