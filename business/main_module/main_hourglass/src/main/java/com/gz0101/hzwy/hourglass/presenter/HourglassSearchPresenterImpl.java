package com.gz0101.hzwy.hourglass.presenter;

import com.gz0101.hzwy.hourglass.contract.HourglassSearchContract;

import java.lang.ref.SoftReference;

public class HourglassSearchPresenterImpl implements HourglassSearchContract.HourglassSearchPresenter {

    private SoftReference<HourglassSearchContract.HourglassSearchView> mView;

    @Override
    public void attachView(HourglassSearchContract.HourglassSearchView view) {
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

