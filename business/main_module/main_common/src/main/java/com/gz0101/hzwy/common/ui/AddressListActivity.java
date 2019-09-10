package com.gz0101.hzwy.common.ui;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.common.Constant;
import com.gz0101.hzwy.common.R;
import com.gz0101.hzwy.common.adapter.AddressListAdapter;
import com.gz0101.hzwy.common.bean.AddressListBean;
import com.gz0101.hzwy.common.contract.AddressListContract;
import com.gz0101.hzwy.common.presenter.AddressListPresenterImpl;

import java.util.List;

@Route(path = RouterConstance.ACTIVITY_URL_ADDRESS_LIST)
public class AddressListActivity extends BaseActivity<AddressListPresenterImpl> implements AddressListContract.AddressListView, View.OnClickListener, AddressListAdapter.OnClickEditListener {
    RecyclerView recyclerView;
    Button addBtn;

    AddressListAdapter adapter;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_address_list);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
        if (getPresenter() != null)
            getPresenter().getData();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_address_list);
        addBtn = findViewById(R.id.btn_address_list);
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.title)).setText(getResources().getString(R.string.address_list_title));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressListAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickEditListener(this);
    }

    @Override
    protected AddressListPresenterImpl createPresenter() {
        return new AddressListPresenterImpl();
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
        if (v.getId() == R.id.btn_address_list) {
            jump2AddressEdit(true, null);
            return;
        }
    }

    @Override
    public void showData(List<AddressListBean> datas) {
        if (adapter != null)
            adapter.setData(datas);
    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public void onClickEdit(AddressListBean bean) {
        jump2AddressEdit(false, bean);
    }

    private void jump2AddressEdit(boolean isAdd, AddressListBean bean) {
        Intent intent = new Intent(this, AddressEditActivity.class);
        if (!isAdd)
            intent.putExtra(Constant.ADDRESS_LIST_BEAN, bean);
        intent.putExtra(Constant.IS_ADD, isAdd);
        startActivity(intent);
    }
}
