package com.gz0101.hzwy.common.presenter;


import com.gz0101.hzwy.common.contract.AddressEditContract;

import java.lang.ref.SoftReference;

public class AddressEditPresenterImpl implements AddressEditContract.AddressEditPresenter {

    private SoftReference<AddressEditContract.AddressEditView> mView;

    private int curPage = 1;

    @Override
    public void attachView(AddressEditContract.AddressEditView view) {
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

