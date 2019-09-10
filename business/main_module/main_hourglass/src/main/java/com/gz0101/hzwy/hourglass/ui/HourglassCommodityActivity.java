package com.gz0101.hzwy.hourglass.ui;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.request.respone.bean.GoodsListBean;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.hourglass.HourglassConstance;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.adapter.CommodityAdapter;
import com.gz0101.hzwy.hourglass.contract.HourglassCommodityContract;
import com.gz0101.hzwy.hourglass.presenter.HourglassCommodityPresenterImpl;

import java.util.List;

public class HourglassCommodityActivity extends BaseActivity<HourglassCommodityPresenterImpl> implements HourglassCommodityContract.HourglassCommodityView, View.OnClickListener {

    TextView titleTv;
    ImageView shopDetailBtn;
    EditText findEt;
    RecyclerView goodsRv;

    TextView selectedGoodsCountTv;
    TextView selectedGoodsListBtn;
    Button payBtn;
    TextView selectedGoodsPriceTv;

    LinearLayout selectedGoodsListLL;
    ImageView selectedGoodClearIv;

    CommodityAdapter adapter;
    ShopListBean bean;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_hourglass_commodity);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
        initData();
    }

    private void initData() {
        if (getIntent() == null)
            return;
        bean = (ShopListBean) getIntent().getSerializableExtra(HourglassConstance.SHOP_BEAN);
        if (bean == null)
            return;
        if (titleTv != null)
            titleTv.setText(bean.getShopName());
        if (getPresenter() != null)
            getPresenter().loadData(bean.getShopId());

    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleTv = findViewById(R.id.tv_commodity_title);
        shopDetailBtn = findViewById(R.id.iv_commodity_home);
        findEt = findViewById(R.id.et_commodity_find);
        goodsRv = findViewById(R.id.rv_commodity_all);

        selectedGoodsCountTv = findViewById(R.id.tv_commodity_good_count);
        selectedGoodsListBtn = findViewById(R.id.tv_commodity_goods_list);
        payBtn = findViewById(R.id.btn_commodity_pay);
        selectedGoodsPriceTv = findViewById(R.id.tv_commodity_goods_value);

        selectedGoodsListLL = findViewById(R.id.ll_hourglass_commodity_bottom_content);
        selectedGoodClearIv = findViewById(R.id.iv_commodity_clear);

        adapter = new CommodityAdapter(this);
        goodsRv.setLayoutManager(new LinearLayoutManager(this));
        goodsRv.setAdapter(adapter);

        shopDetailBtn.setOnClickListener(this);
        payBtn.setOnClickListener(this);
    }

    @Override
    protected HourglassCommodityPresenterImpl createPresenter() {
        return new HourglassCommodityPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void showErrorPage(boolean isNoData) {

    }

    @Override
    public void showData(List<GoodsListBean> listBeanList) {
        if (adapter != null)
            adapter.setList(listBeanList);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_commodity_home) {
            jumpShopDetail();
            return;
        }
        if (v.getId() == R.id.btn_commodity_pay) {
            jump2OrderActivity();
            return;
        }
    }

    private void jump2OrderActivity() {
        Intent intent = new Intent(this, HourglassOrderActivity.class);
        startActivity(intent);
    }

    private void jumpShopDetail() {
        Intent intent = new Intent(this, ShopDetailActivity.class);
        intent.putExtra(HourglassConstance.SHOP_BEAN, bean);
        startActivity(intent);
    }
}
