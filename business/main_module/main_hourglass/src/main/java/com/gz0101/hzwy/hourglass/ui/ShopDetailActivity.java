package com.gz0101.hzwy.hourglass.ui;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.util.PermissionUtil;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.hourglass.HourglassConstance;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.contract.ShopDetailContract;
import com.gz0101.hzwy.hourglass.presenter.ShopDetailPresenterImpl;

public class ShopDetailActivity extends BaseActivity<ShopDetailPresenterImpl> implements ShopDetailContract.ShopDetailView, View.OnClickListener {

    ImageView shopImg;
    TextView shopNametV;

    TextView shopTypeTv;
    TextView shopAddressTv;
    TextView shopPhoneTv;
    LinearLayout shopPhoneLL;
    TextView reportBtn;

    ShopListBean bean;


    @Override
    protected void layout() {
        setContentView(R.layout.activity_shop_detail);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
        initData();
    }

    private void initView() {
        ((TextView) findViewById(R.id.title)).setText(getResources().getString(R.string.shop_detail_title));
        findViewById(R.id.iv_back).setOnClickListener(this);
        shopImg = findViewById(R.id.iv_shop_detail);
        shopNametV = findViewById(R.id.tv_shop_detail_name);
        shopTypeTv = findViewById(R.id.tv_shop_detail_type);
        shopAddressTv = findViewById(R.id.tv_shop_detail_address);
        shopPhoneTv = findViewById(R.id.tv_shop_detail_phone);
        shopPhoneLL = findViewById(R.id.ll_shop_detail_phone);
        reportBtn = findViewById(R.id.btn_shop_detail);
        reportBtn.setOnClickListener(this);
        shopPhoneLL.setOnClickListener(this);
    }

    private void initData() {
        if (getIntent() == null)
            return;
        bean = (ShopListBean) getIntent().getSerializableExtra(HourglassConstance.SHOP_BEAN);
        if (bean == null)
            return;

        if (shopNametV != null)
            shopNametV.setText(bean.getShopName());
        if (shopTypeTv != null)
            shopTypeTv.setText(bean.getCategory().getName());
        if (shopAddressTv != null)
            shopAddressTv.setText(bean.getShopAddress());
        if (shopPhoneTv != null)
            shopPhoneTv.setText(bean.getShopPhone());
        if (shopImg != null && !TextUtils.isEmpty(bean.getShopLogo())) {
            Glide.with(this).load(bean.getShopLogo()).into(shopImg);
        }
    }

    @Override
    protected ShopDetailPresenterImpl createPresenter() {
        return new ShopDetailPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
            return;
        }
        if (v.getId() == R.id.btn_shop_detail) {
            jump2ReportActivity();
            return;
        }
        if (v.getId() == R.id.ll_shop_detail_phone) {
            requestPermission();
            return;
        }
    }

    private void jump2ReportActivity() {
        Intent intent = new Intent(this, ReportShopActivity.class);
        intent.putExtra(HourglassConstance.SHOP_ID, bean.getShopId());
        startActivity(intent);
    }

    private void requestPermission() {
        PermissionUtil.checkPermission(Manifest.permission.CALL_PHONE, new PermissionUtil.ICheckPermissionCallBack() {
            @Override
            public void onSuccess() {
                call();
            }

            @Override
            public void onFailed() {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                ToastHelp.showShort(R.string.shop_detail_permission_call_error);
            }
        }
    }

    private void call() {
        String phone = bean == null ? "" : bean.getShopPhone();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

}
