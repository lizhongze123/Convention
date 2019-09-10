package com.gz0101.hzwy.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    private View rootView;

    private boolean isVisible = false;
    private boolean isPrepared = false;

    private P presenter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            if (isPrepared) {
                loadData();
                isPrepared = false;
                isVisible = false;
            }
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPrepared = false;
        isVisible = false;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        isVisible = getUserVisibleHint();
        if (isVisible) {
            loadData();
            isPrepared = false;
            isVisible = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = loadView(inflater, container);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        destroy();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoading();
        }
    }

    @Override
    public void dismissLoading() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).dismissLoading();
        }
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract P createPresenter();

    //加载页面布局，初始化控件
    protected abstract View loadView(LayoutInflater inflater, ViewGroup container);

    //请求数据填充
    public abstract void loadData();

    public abstract String getTAG();

    public abstract void destroy();

}
