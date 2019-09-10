package com.gz0101.hzwy.login.ui;

import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.login.R;
import com.gz0101.hzwy.login.contract.CheckPhoneContract;
import com.gz0101.hzwy.login.presenter.CheckPhonePresenterImpl;

public class CheckingPhoneActivity extends BaseActivity<CheckPhonePresenterImpl> implements CheckPhoneContract.CheckPhoneNumView {
    @Override
    protected void layout() {
        setContentView(R.layout.activity_checking_phone);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected CheckPhonePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected void destroy() {

    }
}
