package com.gz0101.hzwy.hzwy.ui;


import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.hzwy.R;
import com.gz0101.hzwy.hzwy.contract.SplashContract;
import com.gz0101.hzwy.hzwy.presenter.SplashPresenterImpl;

public class SplashActivity extends BaseActivity<SplashPresenterImpl> implements SplashContract.SplashView {


    @Override
    protected void layout() {
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate() {
        if (getPresenter() != null) {
            getPresenter().judgeJump();
        }
    }

    @Override
    protected SplashPresenterImpl createPresenter() {
        return new SplashPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void jump2Welcome() {

    }

    @Override
    public void jump2Main() {
        if (Boolean.parseBoolean(getResources().getString(R.string.isModule))) {
            ToastHelp.showShort("开发模式");
        } else {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_MAIN).navigation();
        }
        finish();
    }

    @Override
    public void jump2Login() {
        if (Boolean.parseBoolean(getResources().getString(R.string.isModule))) {
            ToastHelp.showShort("开发模式");
        } else {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_LOGIN).navigation();
        }
        finish();
    }
}
