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
import com.gz0101.hzwy.login.AppConstance;
import com.gz0101.hzwy.login.R;
import com.gz0101.hzwy.login.contract.LoginContract;
import com.gz0101.hzwy.login.model.LoginModel;

import java.lang.ref.SoftReference;

public class LoginPresenterImpl implements LoginContract.LoginPresenter {

    private SoftReference<LoginContract.LoignView> mView;

    @Override
    public void attachView(LoginContract.LoignView view) {
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
    public void obtainVerify(String phone, int mode) {
        if (TextUtils.isEmpty(phone)) {
            ToastHelp.showShort(R.string.login_input_phone_hint);
            return;
        }
        if (!FormatUtil.isPhoneNum(phone)) {
            ToastHelp.showShort(R.string.login_phone_error_hint);
            return;
        }

        if (mView != null && mView.get() != null) {
            mView.get().startNoteVerifyChange();
        }

        BaseModel.getInstance().obtainVerify(phone, mode, new ICallBack<BaseRespone>() {
            @Override
            public void onSuccess(BaseRespone baseRespone) {
                if (baseRespone.getCode() == ResponseCode.SUCCESS) {
                    ToastHelp.showShort(R.string.login_note_verify_success_hint);
                }
            }

            @Override
            public void onFailure(String str) {
                if (mView != null && mView.get() != null)
                    mView.get().changeNoteVerifyState(BaseApplication.getInstance().getResources().getString(R.string.login_verification_code));
                netError();
            }
        });
    }

    @Override
    public void userLogin(String phone, String pwd, String loginType) {
        if (TextUtils.isEmpty(phone)) {
            ToastHelp.showShort(R.string.login_input_phone_hint);
            return;
        }
        if (!FormatUtil.isPhoneNum(phone)) {
            ToastHelp.showShort(R.string.login_phone_error_hint);
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            if (loginType.equals(AppConstance.LOGIN_TYPE_VER))
                ToastHelp.showShort(R.string.loign_input_cvode_hint);
            else
                ToastHelp.showShort(R.string.login_input_pwd_hint);
            return;
        }

        if (mView != null && mView.get() != null) {
            mView.get().showLoading();
            mView.get().changeLoginBtnState(false);
        }

        LoginModel.getInstance().userLogin(phone, pwd, loginType, "", new ICallBack<BaseRespone<LoginBean>>() {
            @Override
            public void onSuccess(BaseRespone<LoginBean> loginBeanBaseRespone) {
                if (mView != null && mView.get() != null) {
                    mView.get().changeLoginBtnState(true);
                    mView.get().dismissLoading();
                }
                if (loginBeanBaseRespone.getCode() == ResponseCode.SUCCESS && loginBeanBaseRespone.getData() != null) {
                    checkUserInfo(loginBeanBaseRespone.getData());
                }
            }

            @Override
            public void onFailure(String str) {
                if (mView != null && mView.get() != null) {
                    mView.get().changeLoginBtnState(true);
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

        if (mView != null && mView.get() != null) {
            mView.get().jump2Main();
        }
    }

    private void netError() {
        if (mView != null && mView.get() != null) {
            mView.get().dismissLoading();
        }
        ToastHelp.showShort(R.string.base_net_error);
    }
}

