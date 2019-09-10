package com.gz0101.hzwy.main.presenter;

import com.gz0101.hzwy.main.contract.HomeSettingContract;

import java.lang.ref.SoftReference;

public class HomeSettingPresenterImpl implements HomeSettingContract.HomeSettingPresenter {
    private SoftReference<HomeSettingContract.HomeSettingView> mView;

    @Override
    public void attachView(HomeSettingContract.HomeSettingView view) {
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
