package com.gz0101.hzwy.receipt.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.receipt.R;
import com.gz0101.hzwy.receipt.adapter.MainAdapter;
import com.gz0101.hzwy.receipt.bean.PlaceItem;
import com.gz0101.hzwy.receipt.constant.PlaceConstant;
import com.gz0101.hzwy.receipt.contract.MainContract;
import com.gz0101.hzwy.receipt.presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterConstance.ACTIVITY_URL_PLACE)
public class PlaceReceiptActivity extends BaseActivity<MainPresenterImpl> implements View.OnClickListener,
        MainAdapter.OnClickLocationListener , MainContract.MainView {
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    private List<PlaceItem> mList;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_place);
    }

    @Override
    protected void onCreate() {
        TextView title = findViewById(R.id.title);
        title.setText(R.string.module_name_place);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.iv_back).setOnClickListener(this);
        mAdapter = new MainAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickLocationListener(this);
        mList = new ArrayList<>();
    }

    @Override
    protected MainPresenterImpl createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onRefreshData(List<PlaceItem> list) {
        if (mList != null) {
            mList.clear();
            if (list != null) {
                mList.addAll(list);
            }
            mAdapter.setList(mList);
        }
    }

    public void addNewPlace(View view) {
        startActivity(new Intent(this, AddNewPlaceActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().getAllPlaceData();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }

    @Override
    public void onClickLocation(int position, PlaceItem item) {
        Intent intent = new Intent(this, AddNewPlaceActivity.class);
        intent.putExtra(PlaceConstant.KEY_PLACE, item);
        startActivity(intent);
    }

    @Override
    public void onSelectClick(int position, PlaceItem item) {
        if (item == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("key", item);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
