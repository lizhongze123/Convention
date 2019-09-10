package com.gz0101.hzwy.hourglass.ui;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.contract.HourglassOrderContract;
import com.gz0101.hzwy.hourglass.presenter.HourglassOrderPresenterImpl;

public class HourglassOrderActivity extends BaseActivity<HourglassOrderPresenterImpl> implements HourglassOrderContract.HourglassOrderView, View.OnClickListener {

    TextView nameTv;
    TextView phoneTv;
    TextView addressTv;
    RelativeLayout addressRl;

    RecyclerView recyclerView;

    TextView goodsMoneyTv;
    TextView freightMoneyTv;

    EditText remarkEt;
    ImageView remarkDeleteIv;

    Button payBtn;
    TextView actaulMoneyTv;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_hour_order);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.title)).setText(getResources().getString(R.string.order_title));
        nameTv = findViewById(R.id.tv_order_name);
        phoneTv = findViewById(R.id.tv_order_phone);
        addressTv = findViewById(R.id.tv_order_address);
        addressRl = findViewById(R.id.rl_order_address);

        recyclerView = findViewById(R.id.rv_order_goods);

        goodsMoneyTv = findViewById(R.id.tv_order_goods_money);
        freightMoneyTv = findViewById(R.id.tv_order_freight_money);

        remarkEt = findViewById(R.id.et_order_remark);
        remarkDeleteIv = findViewById(R.id.iv_order_remark_detele);

        actaulMoneyTv = findViewById(R.id.tv_order_actaul_money);
        payBtn = findViewById(R.id.btn_order_submit);

        payBtn.setOnClickListener(this);
        addressRl.setOnClickListener(this);
        remarkDeleteIv.setOnClickListener(this);
    }

    @Override
    protected HourglassOrderPresenterImpl createPresenter() {
        return new HourglassOrderPresenterImpl();
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

        if (v.getId() == R.id.rl_order_address) {
            jump2AddressListActivity();
            return;
        }
        if (v.getId() == R.id.btn_order_submit) {
            return;
        }

        if (v.getId() == R.id.iv_order_remark_detele) {
            if (remarkEt != null) {
                remarkEt.setText("");
            }
            return;
        }
    }

    private void jump2AddressListActivity() {
        if (Boolean.parseBoolean(getResources().getString(R.string.isModule))) {
            ToastHelp.showShort("开发模式");
        } else {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_ADDRESS_LIST)
                    .navigation();

        }
    }
}
