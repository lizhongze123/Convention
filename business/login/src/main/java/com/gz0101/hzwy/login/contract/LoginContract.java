package com.gz0101.hzwy.login.contract;

import com.gz0101.hzwy.baselibrary.mvp.BasePresenter;
import com.gz0101.hzwy.baselibrary.mvp.BaseView;

public interface LoginContract {
    interface LoignView extends BaseView {
        void changeNoteVerifyState(String hint);

        void changeLoginBtnState(boolean enable);

        void startNoteVerifyChange();

        void jump2Main();
    }

    interface LoginPresenter extends BasePresenter<LoignView> {
        void obtainVerify(String phone, int mode);

        void userLogin(String phone, String pwd, String loginType);

    }
}
