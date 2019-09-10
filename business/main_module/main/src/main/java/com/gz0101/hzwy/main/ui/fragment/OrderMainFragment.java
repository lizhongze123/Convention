package com.gz0101.hzwy.main.ui.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gz0101.hzwy.baselibrary.base.BaseFragment;
import com.gz0101.hzwy.main.R;
import com.gz0101.hzwy.main.contract.HomeOrderContract;
import com.gz0101.hzwy.main.presenter.HomeOrderPresenterImpl;

public class OrderMainFragment extends BaseFragment<HomeOrderPresenterImpl> implements HomeOrderContract.HomeOrderView {
    private static final String TAG = "OrderMainFragment";
    View taskView;

    @Override
    protected HomeOrderPresenterImpl createPresenter() {
        return new HomeOrderPresenterImpl();
    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container) {
        taskView = inflater.inflate(R.layout.fragment_order_main, null);
        return taskView;
    }

    @Override
    public void loadData() {

    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void destroy() {

    }
}
