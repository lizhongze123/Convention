package com.gz0101.hzwy.hzwy.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;

public interface SplashContract {

    interface SplashView extends BaseView {
        void jump2Welcome();

        void jump2Main();

        void jump2Login();
    }

    interface SplashPresenter extends BasePresenter<SplashView> {
        void judgeJump();
    }
}
