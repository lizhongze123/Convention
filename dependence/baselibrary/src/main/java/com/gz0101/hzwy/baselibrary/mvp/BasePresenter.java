package com.gz0101.hzwy.baselibrary.mvp;


public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
