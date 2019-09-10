package com.gz0101.hzwy.common.presenter;

import com.gz0101.hzwy.common.contract.TakePictureContract;

import java.lang.ref.SoftReference;

public class TakePictureImpl implements TakePictureContract.TakeCameraPresenter {

    private SoftReference<TakePictureContract.TakeCameraView> mView;

    @Override
    public void attachView(TakePictureContract.TakeCameraView view) {
        mView = new SoftReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}

