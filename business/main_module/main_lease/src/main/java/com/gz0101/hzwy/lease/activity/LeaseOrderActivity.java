package com.gz0101.hzwy.lease.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.lease.R;
import com.gz0101.hzwy.lease.bean.LeaseItem;
import com.gz0101.hzwy.lease.constants.LeaseConstant;
import com.gz0101.hzwy.lease.contract.OrderContract;
import com.gz0101.hzwy.lease.presenter.OrderPresenterImpl;
import com.gz0101.hzwy.receipt.AddressHelper;
import com.gz0101.hzwy.receipt.bean.PlaceItem;

import java.util.ArrayList;
import java.util.Locale;

public class LeaseOrderActivity extends BaseActivity<OrderPresenterImpl> implements OrderContract.MainView, View.OnClickListener {
    private static final int REQUEST_CODE = 0x124;
    private static final int MASK_REDUCE = -12432;
    private static final int MASK_INCREASE = -22432;
    private ArrayList<LeaseItem> mList;

    private TextView mGetSite;
    private TextView mName;
    private TextView mPhone;
    private TextView mLocation;
    private TextView mGetTime;
    private TextView mReturnTime;
    private TextView mDepositAmount;
    private TextView mRentAmount;
    private TextView mTotalValue;

    private LinearLayout mGoodContent;
    private View[] mItemView;
    private TextView[] mItemCount;
    private View[] mReduceView;
    private View[] mIncreaseView;
    private Button mSendBtn;
    private ScrollView mScrollView;
    private TextView mEmptyView;
    private TextView mSelectNo;
    private boolean isInit;
    private PlaceItem mItem;

    @Override
    protected void layout() {
        StatusBarUtil.setStatusBarLightMode(this);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        setContentView(R.layout.activity_lease_order);
    }

    @Override
    protected void onCreate() {
        mList = getIntent().getParcelableArrayListExtra(LeaseConstant.KEY_GOODS_LIST);
        if (mList == null || mList.isEmpty()) {
            ToastHelp.showShort("error");
            finish();
        }
        mSelectNo = findViewById(R.id.no_address);
        mGetSite = findViewById(R.id.get_type);
        mName = findViewById(R.id.name);
        mPhone = findViewById(R.id.tel);
        mLocation = findViewById(R.id.loc);
        mGetTime = findViewById(R.id.get_time);
        mReturnTime = findViewById(R.id.to_time);
        mDepositAmount = findViewById(R.id.lease_param);
        mRentAmount = findViewById(R.id.lease_param_0);
        mTotalValue = findViewById(R.id.good_sum);
        mGoodContent = findViewById(R.id.content);
        mSendBtn = findViewById(R.id.send);
        mScrollView = findViewById(R.id.srcoll);
        mEmptyView = findViewById(R.id.empty_view);
        mSendBtn.setOnClickListener(this);
        mGetSite.setOnClickListener(this);
        mSelectNo.setOnClickListener(this);
        mName.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        mLocation.setOnClickListener(this);
        mGetTime.setOnClickListener(this);
        mReturnTime.setOnClickListener(this);
        mDepositAmount.setOnClickListener(this);
        mRentAmount.setOnClickListener(this);
        mTotalValue.setOnClickListener(this);
        mGoodContent.setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.get_type_back).setOnClickListener(this);
        findViewById(R.id.user_message_back).setOnClickListener(this);
        findViewById(R.id.get_time_back).setOnClickListener(this);
        findViewById(R.id.to_time_back).setOnClickListener(this);
        findViewById(R.id.delete_all).setOnClickListener(this);
        mItemView = new View[mList.size()];
        mItemCount = new TextView[mList.size()];
        mReduceView = new View[mList.size()];
        mIncreaseView = new View[mList.size()];
        for (int i=0 ;i < mList.size(); i++) {
            mItemView[i] = LayoutInflater.from(this).inflate(R.layout.item_lease_order, mGoodContent, false);
            initContentView(mItemView[i], mList.get(i), i);
        }
    }

    private void initContentView(View rootView, LeaseItem item, int index) {
        if (rootView == null || item == null || index < 0 || index > mList.size()) {
            return;
        }
        Glide.with(this).load(item.getIconUrl()).into((ImageView)(rootView.findViewById(R.id.image)));
        ((TextView)(rootView.findViewById(R.id.name))).setText(item.getName());
        ((TextView)(rootView.findViewById(R.id.price))).setText(String.format(Locale.CHINA, "￥%.2f", item.getPrice()));
        mItemCount[index] = rootView.findViewById(R.id.count);
        mItemCount[index].setText(String.valueOf(item.getCount()));
        mReduceView[index] = rootView.findViewById(R.id.reduce);
        mIncreaseView[index] = rootView.findViewById(R.id.increase);
        mReduceView[index].setId(index);
        mIncreaseView[index].setId(index);
        mReduceView[index].setTag(MASK_REDUCE);
        mIncreaseView[index].setTag(MASK_INCREASE);
        mGoodContent.addView(mItemView[index]);
        mReduceView[index].setOnClickListener(this);
        mIncreaseView[index].setOnClickListener(this);
        updateBottomViews();
    }

    @Override
    protected OrderPresenterImpl createPresenter() {
        return new OrderPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    private void updateAddress() {
        if (!AddressHelper.getInstance().isHasAddress(mItem)) {
            mItem = null;
        }
        mSelectNo.setVisibility(mItem != null?View.GONE:View.VISIBLE);
        if (mItem != null) {
            mName.setText(mItem.getName());
            mPhone.setText(mItem.getPhone());
            String location = mItem.getProvince() + mItem.getCity() + mItem.getTown()
                    + mItem.getStreet() + mItem.getArea();
            mLocation.setText(location);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isInit) {
            return;
        }
        isInit = true;
        mItem = AddressHelper.getInstance().getDefaultAddress();
        updateAddress();
        mScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0, 0);
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (v.getTag() instanceof Integer) {
            Integer value = (Integer) v.getTag();
            if (value == MASK_REDUCE) {
                onClickReduce(id);
            } else if (value == MASK_INCREASE) {
                onClickIncrease(id);
            }
            return;
        }
        if (id == R.id.get_type || id == R.id.get_type_back) {
            onClickType();
        } else if(id == R.id.user_message_back || id == R.id.no_address) {
            onClickUser();
        } else if(id == R.id.get_time || id == R.id.get_time_back) {
            onClickGetTime();
        } else if(id == R.id.to_time || id == R.id.to_time_back) {
            onClickToTime();
        } else if(id == R.id.delete_all) {
            onClickDeleteAll();
        } else if(id == R.id.send) {
            onClickSend();
        } else if (id == R.id.back) {
            finish();
        }
    }

    private void onClickReduce(int index) {
        int count = mList.get(index).getCount();
        mList.get(index).setCount(count - 1);
        if (count == 1) {
            mGoodContent.removeView(mItemView[index]);
        }  else {
            mItemCount[index].setText(String.valueOf(count - 1));
        }
        updateBottomViews();
    }

    private void onClickIncrease(int index) {
        int count = mList.get(index).getCount();
        mList.get(index).setCount(count + 1);
        if (count < 9999) {
            mItemCount[index].setText(String.valueOf(count + 1));
        }
        updateBottomViews();
    }

    private void onClickType() {

    }

    private void onClickUser() {
        ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_PLACE).navigation(this, REQUEST_CODE);
    }

    private void onClickGetTime() {

    }

    private void onClickToTime() {

    }

    private void onClickDeleteAll() {

    }

    private void onClickSend() {

    }

    private void updateBottomViews() {
        float sum = 0;
        for (LeaseItem item : mList) {
            sum += item.getCount() * item.getPrice();
        }
        mSendBtn.setEnabled(sum > 0);
        mEmptyView.setVisibility(sum > 0?View.GONE:View.VISIBLE);
        mTotalValue.setText(String.format(Locale.CHINA,"￥%.2f", sum));
        mDepositAmount.setText(String.format(Locale.CHINA,"￥%.2f", sum));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK || data == null) {
            updateAddress();
            return;
        }
        mItem = data.getParcelableExtra("key");
        updateAddress();
    }
}
