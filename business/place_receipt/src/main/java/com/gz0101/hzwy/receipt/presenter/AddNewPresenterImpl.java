package com.gz0101.hzwy.receipt.presenter;

import com.gz0101.hzwy.receipt.contract.AddNewContract;


import java.lang.ref.SoftReference;

public class AddNewPresenterImpl implements AddNewContract.MainPresenter {
    private SoftReference<AddNewContract.MainView> mView;

    @Override
    public void attachView(AddNewContract.MainView view) {
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
