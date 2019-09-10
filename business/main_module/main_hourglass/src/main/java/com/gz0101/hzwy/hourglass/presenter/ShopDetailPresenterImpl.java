package com.gz0101.hzwy.hourglass.presenter;



import com.gz0101.hzwy.hourglass.contract.ShopDetailContract;

import java.lang.ref.SoftReference;

public class ShopDetailPresenterImpl implements ShopDetailContract.ShopDetailPresenter {

    private SoftReference<ShopDetailContract.ShopDetailView > mView;

    @Override
    public void attachView(ShopDetailContract.ShopDetailView  view) {
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

