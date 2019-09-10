package com.gz0101.hzwy.main.presenter;

import com.gz0101.hzwy.main.contract.HomeOrderContract;

import java.lang.ref.SoftReference;

public class HomeOrderPresenterImpl implements HomeOrderContract.HomeOrderPresenter {
    private SoftReference<HomeOrderContract.HomeOrderView> mView;

    @Override
    public void attachView(HomeOrderContract.HomeOrderView view) {
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
