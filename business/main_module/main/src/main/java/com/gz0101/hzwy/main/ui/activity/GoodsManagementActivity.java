package com.gz0101.hzwy.main.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.baselibrary.widget.GZDialog;
import com.gz0101.hzwy.main.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 商品管理
 * */
@Route(path = RouterConstance.ACTIVITY_URL_LEASE)
public class GoodsManagementActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView,
        CountListener, View.OnClickListener, Animation.AnimationListener {

    private RecyclerView recyclerView;
    private Button btnAdd;
    private LeaseAdapter mAdapter;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_goods_management);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        StatusBarUtil.setStatusBarLightMode(this, StatusBarUtil.setStatusBarLightMode(this));
    }

    @Override
    protected void onCreate() {
        initViews();
        updateViews();
    }

    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    private void initViews() {
        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.rv);

        btnAdd.setOnClickListener(this);
        mAdapter = new LeaseAdapter(this);

        mMainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBottomRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainRecyclerView.setAdapter(mAdapter);
        mBottomRecyclerView.setAdapter(mPreviewAdapter);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.menu).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        mAdapter.setCountListener(this);
        mPreviewAdapter.setCountListener(this);
        mAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        mAnimation.setInterpolator(new AccelerateInterpolator());
        mAnimation.setDuration(200);
        mAnimation.setAnimationListener(this);
        List<LeaseItem> list = new ArrayList<>();
        String iconUrl = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1728992939,164060872&fm=26&gp=0.jpg";
        for (int i=0; i < 10; i++) {
            list.add(new LeaseItem(i,"白色展会旅具" + i,10.0f, iconUrl, 0));
        }
        mAdapter.setList(list);
    }

    private void updateViews(){
        String goodValue = String.format(staticGoodsValue, String.format(Locale.CHINA,"%.2f", mGoodsValue));
        String goodCount = String.format(staticSumAll, mGoodsCount);
        mGoToPayBtn.setText(goodCount);
        mGoToPayBtn.setEnabled(mGoodsCount > 0);
        mGoodsValueTextView.setText(goodValue);
        mGoodListTextView.setEnabled(mGoodsCount > 0);
        mGoodCountTextView.setEnabled(mGoodsCount > 0);
        mGoodCountTextView.setText(String.valueOf(mGoodsCount));
        if (mGoodsCount <= 0) {
            onClickGoodListClose();
        }
    }

    @Override
    public void onClickCount(boolean type) {
        mGoodsValue = mAdapter.getValue();
        mGoodsCount = mAdapter.getCount();
        updateViews();
        if (!type) {
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.good_list) {
            if (isOpenPreview) {
                onClickGoodListClose();
            } else {
                onClickGoodListOpen();
            }
        } else if(isOpenPreview && v.getId() == R.id.bottom_content) {
            onClickGoodListClose();
        } else if(v.getId() == R.id.pay) {
            goToLeaseDetail();
        } else if (v.getId() == R.id.back) {
            finish();
        } else if (v.getId() == R.id.menu) {
            startActivity(new Intent(this, LeaseInstructionActivity.class));
        } else if (v.getId() == R.id.clear) {
            showDialog();
        } else if (v.getId() == GZDialog.ID_LEFT) {
            onClickClearShop();
        }
    }

    private void showDialog() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    private void onClickClearShop() {
        mDialog.dismiss();
        for (LeaseItem bean:mAdapter.getList()) {
            bean.setCount(0);
        }
        mAdapter.notifyDataSetChanged();
        mPreviewAdapter.notifyDataSetChanged();
        onClickGoodListClose();
        mGoodsValue = mAdapter.getValue();
        mGoodsCount = mAdapter.getCount();
        updateViews();
    }

    private void goToLeaseDetail() {
        Intent intent = new Intent(this, LeaseOrderActivity.class);
        ArrayList<LeaseItem> leaseItems = (ArrayList<LeaseItem>) mAdapter.filterList();
        intent.putParcelableArrayListExtra(LeaseConstant.KEY_GOODS_LIST,leaseItems);
        startActivity(intent);
    }

    private void onClickGoodListOpen(){
        mPreviewAdapter.setList(mAdapter.filterList());
        mBottomContent.startAnimation(mAnimation);
        mBottomContent.setVisibility(View.VISIBLE);
    }

    private void onClickGoodListClose(){
        mBottomContent.setVisibility(View.GONE);
        mBottomContent.setBackgroundColor(Color.TRANSPARENT);
        isOpenPreview = false;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        mBottomContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mBottomContent.setBackgroundColor(Color.parseColor("#5A000000"));
        isOpenPreview = true;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
