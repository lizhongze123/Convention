package com.gz0101.hzwy.material.activity;

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
import com.gz0101.hzwy.material.R;
import com.gz0101.hzwy.material.adapter.MaterialLeftAdapter;
import com.gz0101.hzwy.material.adapter.MaterialPreviewAdapter;
import com.gz0101.hzwy.material.adapter.MaterialRightAdapter;
import com.gz0101.hzwy.material.adapter.listener.MaterialLeftCheckListener;
import com.gz0101.hzwy.material.adapter.listener.MaterialRightListener;
import com.gz0101.hzwy.material.bean.MaterialLeftBean;
import com.gz0101.hzwy.material.bean.MaterialRightBean;
import com.gz0101.hzwy.material.constant.MaterialConstant;
import com.gz0101.hzwy.material.contract.MainContract;
import com.gz0101.hzwy.material.presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Route(path = RouterConstance.ACTIVITY_URL_MATERIAL)
public class MaterialActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView,
        View.OnClickListener, MaterialLeftCheckListener, MaterialRightListener, Animation.AnimationListener {
    private RecyclerView mLeftRecyclerView;
    private RecyclerView mRightRecyclerView;
    private MaterialLeftAdapter mLeftAdapter;
    private MaterialRightAdapter mRightAdapter;
    private MaterialPreviewAdapter mPreviewAdapter;
    private List<MaterialLeftBean> mLeftList;
    private List<MaterialRightBean> mRightList;
    private Button mGoToPayBtn;
    private TextView mGoodsValueTextView;
    private EditText mTopSearchEditText;
    private TextView mGoodListTextView;
    private TextView mGoodCountTextView;

    private float mGoodsValue;
    private int mGoodsCount;

    private static String staticSumAll;
    private static String staticGoodsValue;

    private RecyclerView mBottomRecyclerView;
    private LinearLayout mBottomContent;
    private boolean isOpenPreview;

    private TranslateAnimation mAnimation;
    private GZDialog mDialog;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_material);
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
        mDialog = new GZDialog.Builder(this)
                .setContent(R.string.clear_shop_car_1)
                .setLeftButtonText(R.string.clear_1, this)
                .setRightDefault().build();
        mAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
        mAnimation.setInterpolator(new AccelerateInterpolator());
        mAnimation.setDuration(200);
        mAnimation.setAnimationListener(this);
        mBottomContent = findViewById(R.id.bottom_content);
        mGoodListTextView = findViewById(R.id.good_list);
        mGoodCountTextView = findViewById(R.id.good_count);
        mBottomRecyclerView = findViewById(R.id.recycler_bottom);
        mGoToPayBtn = findViewById(R.id.pay);
        mGoodsValueTextView = findViewById(R.id.goods_value);
        mTopSearchEditText = findViewById(R.id.find_edit);
        staticGoodsValue = getString(R.string.sum_all);
        staticSumAll = getString(R.string.to_pay);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        mLeftRecyclerView = findViewById(R.id.left_tab);
        mRightRecyclerView = findViewById(R.id.recycler_main);
        mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBottomRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLeftAdapter = new MaterialLeftAdapter();
        mLeftRecyclerView.setAdapter(mLeftAdapter);
        mRightAdapter = new MaterialRightAdapter();
        mLeftAdapter.setMaterialLeftCheckListener(this);
        mRightAdapter.setMaterialRightListener(this);
        mPreviewAdapter = new MaterialPreviewAdapter(this);
        mBottomRecyclerView.setAdapter(mPreviewAdapter);
        mRightRecyclerView.setAdapter(mRightAdapter);
        mGoToPayBtn.setOnClickListener(this);
        mGoodListTextView.setOnClickListener(this);
        mBottomContent.setOnClickListener(this);
        mPreviewAdapter.setMaterialRightListener(this);
        mLeftList = new ArrayList<>();
        mLeftList.add(new MaterialLeftBean(true, "智能门锁"));
        mLeftList.add(new MaterialLeftBean(false, "门五金"));
        mLeftList.add(new MaterialLeftBean(false, "机械门锁"));
        mLeftList.add(new MaterialLeftBean(false, "家具小拉手"));
        mLeftList.add(new MaterialLeftBean(false, "医疗整形"));
        mLeftAdapter.setList(mLeftList);
        mRightList = new ArrayList<>();
        String url = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1728992939,164060872&fm=26&gp=0.jpg";
        for (int i=0; i < 100;i ++) {
            mRightList.add(new MaterialRightBean(0, "藤原-" + i,10.0f, 0, url));
        }
        mRightAdapter.setList(mRightList);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        }
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
        for (MaterialRightBean bean:mRightList) {
            bean.setCount(0);
        }
        mRightAdapter.notifyDataSetChanged();
        mPreviewAdapter.notifyDataSetChanged();
        onClickGoodListClose();
        mGoodsValue = mRightAdapter.getValue();
        mGoodsCount = mRightAdapter.getCount();
        updateViews();
    }

    @Override
    public void onCheck(int position, MaterialLeftBean bean) {
        for (MaterialLeftBean item : mLeftList) {
            item.setChecked(bean.getName().equals(item.getName()));
        }
        mLeftAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickCount(boolean type) {
        mGoodsValue = mRightAdapter.getValue();
        mGoodsCount = mRightAdapter.getCount();
        updateViews();
        if (!type) {
            mRightAdapter.notifyDataSetChanged();
        }
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

    private void goToLeaseDetail() {
        Intent intent = new Intent(this, MaterialOrderActivity.class);
        ArrayList<MaterialRightBean> leaseItems = (ArrayList<MaterialRightBean>) mRightAdapter.filterList();
        intent.putParcelableArrayListExtra(MaterialConstant.KEY_GOODS_LIST,leaseItems);
        startActivity(intent);
    }

    private void onClickGoodListOpen(){
        mPreviewAdapter.setList(mRightAdapter.filterList());
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
