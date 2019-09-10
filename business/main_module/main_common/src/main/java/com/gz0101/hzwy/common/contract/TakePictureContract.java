package com.gz0101.hzwy.common.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;

public interface TakePictureContract {
    interface TakeCameraView extends BaseView {
    }

    interface TakeCameraPresenter extends BasePresenter<TakeCameraView> {
    }
}
