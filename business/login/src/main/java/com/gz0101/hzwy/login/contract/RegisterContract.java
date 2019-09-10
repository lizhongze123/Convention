package com.gz0101.hzwy.login.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;

public interface RegisterContract {
    interface RegisterView extends BaseView {
        void changeRegisterBtnStates(boolean enable);

        void changeNoteVerifyState(String hint);

        void startNoteVerifyChange();

        void jump2Main();

        void jump2Loign();
    }

    interface RegisterPresenter extends BasePresenter<RegisterView> {
        void obtainVerify(String phoneNum, int type);

        void userRegister(String phoneNum, String verification, String pwd, int type);
    }
}
