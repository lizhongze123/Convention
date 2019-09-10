package com.gz0101.hzwy.login.presenter;


import com.gz0101.hzwy.login.contract.CheckPhoneContract;

import java.lang.ref.SoftReference;

public class CheckPhonePresenterImpl implements CheckPhoneContract.CheckPhoneNumPresenter {

    private SoftReference<CheckPhoneContract.CheckPhoneNumView> mView;

    @Override
    public void attachView(CheckPhoneContract.CheckPhoneNumView view) {
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

