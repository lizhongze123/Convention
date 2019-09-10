package com.gz0101.hzwy.login.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;

public interface CheckPhoneContract {
    interface CheckPhoneNumView extends BaseView {
    }

    interface CheckPhoneNumPresenter extends BasePresenter<CheckPhoneNumView> {

    }
}
