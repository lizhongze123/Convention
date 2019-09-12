package com.gz0101.hzwy.main.ui.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.base.BaseFragment;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.main.R;
import com.gz0101.hzwy.main.contract.MainContract;
import com.gz0101.hzwy.main.presenter.MainPresenterImpl;
import com.gz0101.hzwy.main.ui.fragment.HomeMainFragment;
import com.gz0101.hzwy.main.ui.fragment.OrderMainFragment;
import com.gz0101.hzwy.main.ui.fragment.SettingMainFragment;

import java.util.HashMap;

@Route(path = RouterConstance.ACTIVITY_URL_SHOP_DATA)
public class ShopDataActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView {

    RadioGroup radioGroup;
    String currentTab;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_93BEFA));
    }

    @Override
    protected void onCreate() {
        initView();
    }


    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void destroy() {

    }


    private void initView() {

    }


}
