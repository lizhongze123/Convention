package com.gz0101.hzwy.main.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gz0101.hzwy.baselibrary.base.BaseFragment;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.main.R;
import com.gz0101.hzwy.main.contract.HomeMainContract;
import com.gz0101.hzwy.main.presenter.HomeMainPresenterImpl;


public class HomeMainFragment extends BaseFragment<HomeMainPresenterImpl> implements HomeMainContract.HomeMainView, View.OnClickListener {
    private final static String TAG = "HomeMainFragment";

    View taskView;
    LinearLayout materLl;
    LinearLayout hourglassLl;
    LinearLayout carLl;
    LinearLayout bagLl;

    ImageView scan;

    @Override
    protected HomeMainPresenterImpl createPresenter() {
        return new HomeMainPresenterImpl();
    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container) {
        taskView = inflater.inflate(R.layout.fragment_home_main, null);
        initView();
        return taskView;
    }

    private void initView() {
        materLl = taskView.findViewById(R.id.ll_home_function_mater);
        bagLl = taskView.findViewById(R.id.ll_home_function_bag);
        hourglassLl = taskView.findViewById(R.id.ll_home_function_hourglass);
        carLl = taskView.findViewById(R.id.ll_home_function_car);
        scan = taskView.findViewById(R.id.iv_home_scan);

        materLl.setOnClickListener(this);
        bagLl.setOnClickListener(this);
        hourglassLl.setOnClickListener(this);
        carLl.setOnClickListener(this);
        scan.setOnClickListener(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_home_scan) {
            //TODO 跳转扫码
            ToastHelp.showShort("敬请期待");
            return;
        }
        if (v.getId() == R.id.ll_home_function_mater) {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_MATERIAL).navigation();
            return;
        }
        if (v.getId() == R.id.ll_home_function_bag) {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_LEASE).navigation();
            return;
        }
        if (v.getId() == R.id.ll_home_function_hourglass) {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_HOURGLASS).navigation();
            return;
        }
        if (v.getId() == R.id.ll_home_function_car) {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_TRUCK).navigation();
            return;
        }
    }


}
