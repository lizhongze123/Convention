package com.gz0101.hzwy.hourglass.ui;


import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.contract.HourglassSearchContract;
import com.gz0101.hzwy.hourglass.presenter.HourglassSearchPresenterImpl;

public class HourglassSearchActivity extends BaseActivity<HourglassSearchPresenterImpl> implements HourglassSearchContract.HourglassSearchView {


    @Override
    protected void layout() {
        setContentView(R.layout.activity_hourglass_search);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected HourglassSearchPresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected void destroy() {

    }
}
