package com.gz0101.hzwy.baselibrary.base;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;

import com.gz0101.hzwy.baselibrary.R;
import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        layout();
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        onCreate();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        if (wm != null && loadingView != null) {
            wm.removeView(loadingView);
            loadingView = null;
            wm = null;
        }
        destroy();
        super.onDestroy();
    }


    @Override
    public void showLoading() {
        if (isShow) {
            return;
        }
        if (loadingView == null) {
            loadingView = LayoutInflater.from(this).inflate(R.layout.base_loading_view, null);
            loadingView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
        if (wm == null) {
            wm = getWindowManager();
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
        layoutParams.format = PixelFormat.TRANSLUCENT;
        layoutParams.x = 5;
        layoutParams.y = 5;

        wm.addView(loadingView, layoutParams);
        isShow = true;
    }

    @Override
    public void dismissLoading() {
        if (!isShow) {
            return;
        }
        if (wm != null && loadingView != null) {
            wm.removeView(loadingView);
            loadingView = null;
            wm = null;
        }
        isShow = false;
    }

    private boolean isShow = false;
    private View loadingView;
    private WindowManager wm;

    protected abstract void layout();

    protected abstract void onCreate();

    protected abstract P createPresenter();

    protected abstract void destroy();

    public P getPresenter() {
        return presenter;
    }
}
