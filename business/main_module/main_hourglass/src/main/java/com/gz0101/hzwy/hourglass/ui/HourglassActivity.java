package com.gz0101.hzwy.hourglass.ui;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.hourglass.HourglassConstance;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.adapter.HourglassMainAdapter;
import com.gz0101.hzwy.hourglass.contract.HourglassContract;
import com.gz0101.hzwy.hourglass.presenter.HourglassPresenterImpl;

import java.util.List;

@Route(path = RouterConstance.ACTIVITY_URL_HOURGLASS)
public class HourglassActivity extends BaseActivity<HourglassPresenterImpl> implements HourglassContract.HourglassView, View.OnClickListener {

    LinearLayout findLL;
    RecyclerView recyclerView;
    TextView title;
    HourglassMainAdapter adapter;


    @Override
    protected void layout() {
        setContentView(R.layout.activity_hourglass);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
        loadData(false, false);
    }

    @Override
    protected HourglassPresenterImpl createPresenter() {
        return new HourglassPresenterImpl();
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title = findViewById(R.id.title);
        title.setText(R.string.module_name);
        findLL = findViewById(R.id.hourglass_find_ll);
        recyclerView = findViewById(R.id.hourglass_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HourglassMainAdapter(this);
        adapter.setOnItemClickListener(new HourglassMainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ShopListBean bean) {
                jump2DetailActivity(bean);
            }
        });
        recyclerView.setAdapter(adapter);

        findLL.setOnClickListener(this);
    }


    @Override
    protected void destroy() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.hourglass_find_ll) {
            jump2SearchActivity();
            return;
        }
    }

    private void jump2SearchActivity() {
        startActivity(new Intent(this, HourglassSearchActivity.class));
    }

    private void jump2DetailActivity(ShopListBean bean) {
        Intent intent = new Intent(this, HourglassCommodityActivity.class);
        intent.putExtra(HourglassConstance.SHOP_BEAN,bean);
        startActivity(intent);
    }

    @Override
    public void showErrorPage(boolean isNoData) {

    }

    @Override
    public void showData(List<ShopListBean> datas, boolean isMore) {
        if (adapter != null) {
            if (isMore)
                adapter.loadMore(datas);
            else
                adapter.refresh(datas);
        }
    }

    @Override
    public void stopRefresh() {

    }

    private void loadData(boolean isMore, boolean isRefresh) {
        if (getPresenter() != null)
            getPresenter().loadData(isMore, isRefresh);
    }
}
