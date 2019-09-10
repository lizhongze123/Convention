package com.gz0101.hzwy.truck.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.truck.R;
import com.gz0101.hzwy.truck.adapter.TruckAdapter;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.truck.bean.TruckItem;
import com.gz0101.hzwy.truck.contract.MainContract;
import com.gz0101.hzwy.truck.presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterConstance.ACTIVITY_URL_TRUCK)
public class TruckActivity extends BaseActivity<MainPresenterImpl> implements MainContract.MainView, TextWatcher, View.OnClickListener {
    private EditText mEditText;
    private RecyclerView mRecyclerView;
    private TruckAdapter mAdapter;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_truck);
        StatusBarUtil.setStatusBarLightMode(this);
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
    }

    @Override
    protected void onCreate() {
        initViews();
    }

    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    private void initViews() {
        mEditText = findViewById(R.id.find_edit);
        mRecyclerView = findViewById(R.id.recycler_main);
        findViewById(R.id.back).setOnClickListener(this);
        mAdapter = new TruckAdapter(this);
        mEditText.addTextChangedListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        List<TruckItem> list = new ArrayList<>();
        String icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562434907946&di=df269b6b64c79cca65ecdd1eb280189a&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201702%2F23%2F20170223093051_Jy8XP.jpeg";
        list.add(new TruckItem(icon, "段政","车型：重型大货车", 0));
        list.add(new TruckItem(icon, "郭计虎","车型：重型大货车", 0));
        list.add(new TruckItem(icon, "贾睿睿","车型：半挂式货车", 0));
        list.add(new TruckItem(icon, "周杰伦","车型：重型大货车", 1));
        list.add(new TruckItem(icon, "江强","车型：半挂式货车", 1));
        mAdapter.setTruckListData(list);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        }
    }
}
