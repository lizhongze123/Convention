package com.gz0101.hzwy.receipt.presenter;


import com.gz0101.hzwy.receipt.AddressHelper;
import com.gz0101.hzwy.receipt.bean.PlaceItem;
import com.gz0101.hzwy.receipt.contract.MainContract;

import java.lang.ref.SoftReference;
import java.util.List;

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

    public void getAllPlaceData() {
        if (mView.get() == null) {
            return;
        }
        List<PlaceItem> list = AddressHelper.getInstance().geAllAddress();
        mView.get().onRefreshData(list);
    }
}
