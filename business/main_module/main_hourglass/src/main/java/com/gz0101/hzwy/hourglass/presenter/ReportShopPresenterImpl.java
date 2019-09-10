package com.gz0101.hzwy.hourglass.presenter;


import com.gz0101.hzwy.hourglass.contract.ReportShopContract;

import java.lang.ref.SoftReference;

public class ReportShopPresenterImpl implements ReportShopContract.ReportShopPresenter {

    private SoftReference<ReportShopContract.ReportShopView> mView;

    private int curPage = 1;

    @Override
    public void attachView(ReportShopContract.ReportShopView view) {
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

