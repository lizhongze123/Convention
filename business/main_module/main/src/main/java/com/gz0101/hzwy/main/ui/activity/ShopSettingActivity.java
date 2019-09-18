package com.gz0101.hzwy.main.ui.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.main.R;
import com.gz0101.hzwy.main.contract.MainContract;
import com.gz0101.hzwy.main.presenter.MainPresenterImpl;

@Route(path = RouterConstance.ACTIVITY_URL_SHOP_DATA)
public class ShopSettingActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView, View.OnClickListener {

    ImageView ivAvatar;
    LinearLayout llType;
    TextView tvType;
    LinearLayout llAddress;
    TextView tvAddress;
    LinearLayout llMobile;
    TextView tvMobile;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_shop_data);
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
        findViewById(R.id.iv_back).setOnClickListener(this);
        ivAvatar = findViewById(R.id.iv_avatar);
        llType = findViewById(R.id.ll_type);
        tvType = findViewById(R.id.tv_type);
        llAddress = findViewById(R.id.ll_address);
        tvAddress = findViewById(R.id.tv_address);
        llMobile = findViewById(R.id.ll_mobile);
        tvMobile = findViewById(R.id.tv_mobile);
        ivAvatar.setOnClickListener(this);
        llType.setOnClickListener(this);
        llAddress.setOnClickListener(this);
        llMobile.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
            return;
        }
        if (v.getId() == R.id.iv_avatar) {

            return;
        }
        if (v.getId() == R.id.ll_type) {

            return;
        }
        if (v.getId() == R.id.ll_address) {

            return;
        }
        if (v.getId() == R.id.ll_mobile) {

            return;
        }
    }
}
