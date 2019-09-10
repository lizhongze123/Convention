package com.gz0101.hzwy.truck.presenter;

import com.gz0101.hzwy.truck.contract.MainContract;

import java.lang.ref.SoftReference;

public class MainPresenterImpl implements MainContract.MainPresenter {

    private SoftReference<MainContract.MainView> mView;

    @Override
    public void attachView(MainContract.MainView view) {
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
