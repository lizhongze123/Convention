package com.gz0101.hzwy.lease.presenter;

import com.gz0101.hzwy.lease.contract.OrderContract;

import java.lang.ref.SoftReference;

public class OrderPresenterImpl implements OrderContract.MainPresenter {

    private SoftReference<OrderContract.MainView> mView;

    @Override
    public void attachView(OrderContract.MainView view) {
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
