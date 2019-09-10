package com.gz0101.hzwy.truck.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gz0101.hzwy.truck.R;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.truck.bean.TruckItem;
import com.gz0101.hzwy.truck.constants.TruckConstant;

public class TruckDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TruckItem mItem;
    private TextView mNameTextView;
    private TextView mTruckType;
    private TextView mTruckCard;
    private TextView mTruckDesc;
    private ImageView mUserIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_detail);
        mItem = getIntent().getParcelableExtra(TruckConstant.KEY_DETAIL);
        if (mItem == null) {
            ToastHelp.showShort("获取货车信息失败");
            finish();
        }
        mNameTextView = findViewById(R.id.name);
        mTruckType = findViewById(R.id.car_type);
        mTruckCard = findViewById(R.id.car_card);
        mTruckDesc = findViewById(R.id.car_desc);
        mUserIcon = findViewById(R.id.image);
        findViewById(R.id.back).setOnClickListener(this);
        mNameTextView.setText(mItem.getName());
        mTruckType.setText(mItem.getType());
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(mUserIcon.getContext()).load(mItem.getIconUrl()).apply(requestOptions).into(mUserIcon);
    }

    public void onCall(View view) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        }
    }
}
