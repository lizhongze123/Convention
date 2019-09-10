package com.gz0101.hzwy.lease.activity;

import android.graphics.Color;
import android.view.View;

import com.gz0101.hzwy.lease.R;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;

public class LeaseInstructionActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void layout() {
        setContentView(R.layout.activity_instruciton);
        StatusBarUtil.setStatusBarLightMode(this);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
    }

    @Override
    protected void onCreate() {
        findViewById(R.id.back).setOnClickListener(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        }
    }
}
