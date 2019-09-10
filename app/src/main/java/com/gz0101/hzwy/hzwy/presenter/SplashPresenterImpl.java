package com.gz0101.hzwy.hzwy.presenter;

import android.text.TextUtils;

import com.gz0101.hzwy.baselibrary.cache.UserCache;
import com.gz0101.hzwy.hzwy.contract.SplashContract;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SplashPresenterImpl implements SplashContract.SplashPresenter {
    private SoftReference<SplashContract.SplashView> mView;

    @Override
    public void attachView(SplashContract.SplashView view) {
        mView = new SoftReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public void judgeJump() {
        //TODO 这个地方只是暂时这么写
        Observable.just(false)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        mView.get().jump2Login();
//                        if (aBoolean) {
//                            mView.get().jump2Welcome();
//                        } else {
//                            if (TextUtils.isEmpty(UserCache.getInstance().getToken())) {
//                                mView.get().jump2Login();
//                            } else {
//                                mView.get().jump2Main();
//                            }
//                        }
                    }
                });
    }
}
