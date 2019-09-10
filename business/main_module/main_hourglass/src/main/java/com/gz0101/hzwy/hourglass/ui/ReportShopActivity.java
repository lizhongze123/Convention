package com.gz0101.hzwy.hourglass.ui;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.AppConstant;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.util.PermissionUtil;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.hourglass.R;
import com.gz0101.hzwy.hourglass.adapter.ReportShopAdapter;
import com.gz0101.hzwy.hourglass.contract.ReportShopContract;
import com.gz0101.hzwy.hourglass.presenter.ReportShopPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class ReportShopActivity extends BaseActivity<ReportShopPresenterImpl> implements ReportShopContract.ReportShopView, View.OnClickListener {

    private static final String TAG = "ReportShopActivity";

    RelativeLayout reasonLL;
    TextView reasonTv;
    EditText detailEt;
    RecyclerView recyclerView;

    ReportShopAdapter adapter;
    GridLayoutManager manager;

    private static final int REQUEST_PICTURE = 101;
    final String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void layout() {
        setContentView(R.layout.activity_report_shop);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        ((TextView) findViewById(R.id.title)).setText(getResources().getString(R.string.report_title));
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reasonLL = findViewById(R.id.rl_report_problem);
        reasonTv = findViewById(R.id.tv_report_reason);
        detailEt = findViewById(R.id.et_report_detail);


        recyclerView = findViewById(R.id.rv_report);

        adapter = new ReportShopAdapter(this);
        adapter.setOnClickListener(new ReportShopAdapter.OnClickListener() {
            @Override
            public void addImage() {
                checkPermission(permissions);
            }
        });
        manager = new GridLayoutManager(this, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return manager.getSpanCount();
            }
        });

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        reasonLL.setOnClickListener(this);
    }

    @Override
    protected ReportShopPresenterImpl createPresenter() {
        return new ReportShopPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onClick(View v) {

    }

    private void checkPermission(final String[] args) {
        PermissionUtil.checkPermission(args, new PermissionUtil.ICheckPermissionCallBack() {
            @Override
            public void onSuccess() {
                jump2TakePicture();
            }

            @Override
            public void onFailed() {
                ActivityCompat.requestPermissions(ReportShopActivity.this, args, AppConstant.REQUEST_PERMISSION_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isSuccess = true;
        if (requestCode == AppConstant.REQUEST_PERMISSION_CODE) {
            for (int i : grantResults) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    isSuccess = false;
                    break;
                }
            }
            if (isSuccess) {
                jump2TakePicture();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<String> datas = new ArrayList<>();
        if (requestCode == REQUEST_PICTURE) {
            if (resultCode == 104) {
                List<String> list = data.getStringArrayListExtra("path");
                datas.addAll(list);
            }
        }
        if (adapter != null)
            adapter.setDatas(datas);
    }

    private void jump2TakePicture() {
        if (Boolean.parseBoolean(getResources().getString(R.string.isModule))) {
            ToastHelp.showShort("开发模式");
        } else {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_TAKE_PICTURE).navigation(this, REQUEST_PICTURE);
        }
    }
}
