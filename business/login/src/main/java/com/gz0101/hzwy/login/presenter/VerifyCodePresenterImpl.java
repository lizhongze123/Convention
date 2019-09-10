package com.gz0101.hzwy.login.presenter;



import com.gz0101.hzwy.login.contract.VerifyCodeContract;

import java.lang.ref.SoftReference;

public class VerifyCodePresenterImpl implements VerifyCodeContract.VerifyCodePresenter {

    private SoftReference<VerifyCodeContract.VerifyCodeView> mView;

    @Override
    public void attachView(VerifyCodeContract.VerifyCodeView view) {
        mView = new SoftReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

}

