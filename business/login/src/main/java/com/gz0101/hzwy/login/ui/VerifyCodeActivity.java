package com.gz0101.hzwy.login.ui;

import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.login.R;
import com.gz0101.hzwy.login.contract.VerifyCodeContract;
import com.gz0101.hzwy.login.presenter.VerifyCodePresenterImpl;

public class VerifyCodeActivity extends BaseActivity<VerifyCodePresenterImpl> implements VerifyCodeContract.VerifyCodeView {
    @Override
    protected void layout() {
        setContentView(R.layout.activity_verify_code);
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected VerifyCodePresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected void destroy() {

    }
}
