package com.gz0101.hzwy.login.presenter;


import android.text.TextUtils;

import com.gz0101.hzwy.baselibrary.base.BaseApplication;
import com.gz0101.hzwy.baselibrary.cache.UserCache;
import com.gz0101.hzwy.baselibrary.mvp.BaseModel;
import com.gz0101.hzwy.baselibrary.request.callback.ICallBack;
import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.baselibrary.request.respone.ResponseCode;
import com.gz0101.hzwy.login.bean.LoginBean;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.util.FormatUtil;
import com.gz0101.hzwy.login.R;
import com.gz0101.hzwy.login.contract.RegisterContract;
import com.gz0101.hzwy.login.model.RegisterModel;

import java.lang.ref.SoftReference;

public class RegisterPresenterImpl implements RegisterContract.RegisterPresenter {

    private SoftReference<RegisterContract.RegisterView> mView;

    @Override
    public void attachView(RegisterContract.RegisterView view) {
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
    public void obtainVerify(String phoneNum, int type) {
        if (TextUtils.isEmpty(phoneNum)) {
            ToastHelp.showShort(R.string.login_phone_null_hint);
            return;
        }
        if (!FormatUtil.isPhoneNum(phoneNum)) {
            ToastHelp.showShort(R.string.login_phone_error_hint);
            return;
        }
        if (mView != null && mView.get() != null) {
            mView.get().startNoteVerifyChange();
        }


        BaseModel.getInstance().obtainVerify(phoneNum, type, new ICallBack<BaseRespone>() {
            @Override
            public void onSuccess(BaseRespone baseRespone) {
                if (baseRespone.getCode() == ResponseCode.SUCCESS) {
                    ToastHelp.showShort(R.string.login_note_verify_success_hint);
                }
            }

            @Override
            public void onFailure(String str) {
                if (mView != null && mView.get() != null) {
                    mView.get().changeNoteVerifyState(BaseApplication.getInstance().getResources().getString(R.string.login_verification_code));
                }
                netError();
            }
        });
    }

    @Override
    public void userRegister(String phoneNum, String verification, String pwd, int type) {


        if (mView != null && mView.get() != null) {
            mView.get().changeRegisterBtnStates(false);
            mView.get().showLoading();
        }

        RegisterModel.getInstance().userRegister(phoneNum, verification, pwd, type, new ICallBack<BaseRespone<LoginBean>>() {
            @Override
            public void onSuccess(BaseRespone<LoginBean> registerBeanBaseRespone) {
                if (mView != null && mView.get() != null) {
                    mView.get().changeRegisterBtnStates(true);
                    mView.get().dismissLoading();
                }
                if (registerBeanBaseRespone.getCode() == ResponseCode.SUCCESS && registerBeanBaseRespone.getData() != null) {
                    checkUserInfo(registerBeanBaseRespone.getData());
                }
            }

            @Override
            public void onFailure(String str) {
                if (mView != null && mView.get() != null) {
                    mView.get().changeRegisterBtnStates(true);
                }
                netError();
            }
        });
    }

    private void checkUserInfo(LoginBean userInfo) {
        UserCache.UserInfo user = new UserCache.Builder()
                .setToken(userInfo.getAuthorization())
                .setPhone(userInfo.getPhone())
                .setUserId(userInfo.getUserId())
                .setAvatar(userInfo.getAvatar())
                .setEmail(userInfo.getEmail())
                .setNickName(userInfo.getNickName())
                .setType(userInfo.getType())
                .setStatus(userInfo.getStatus())
                .build();
        UserCache.getInstance().saveUser(user);

        if (!TextUtils.isEmpty(userInfo.getAuthorization())) {
            if (mView != null && mView.get() != null) {
                mView.get().jump2Main();
            }
        } else {
            if (mView != null && mView.get() != null) {
                mView.get().jump2Loign();
            }
        }
    }

    private void netError() {
        if (mView != null && mView.get() != null) {
            mView.get().dismissLoading();
        }
        ToastHelp.showShort(R.string.base_net_error);
    }
}

