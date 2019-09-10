package com.gz0101.hzwy.main.presenter;

import com.gz0101.hzwy.main.contract.HomeMainContract;

import java.lang.ref.SoftReference;

public class HomeMainPresenterImpl implements HomeMainContract.HomeMainPresenter {
    private SoftReference<HomeMainContract.HomeMainView> mView;

    @Override
    public void attachView(HomeMainContract.HomeMainView view) {
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
