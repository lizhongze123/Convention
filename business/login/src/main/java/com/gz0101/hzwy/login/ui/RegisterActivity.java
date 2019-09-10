package com.gz0101.hzwy.login.ui;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.util.FormatUtil;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.baselibrary.util.help.TimerHelp;
import com.gz0101.hzwy.login.AppConstance;
import com.gz0101.hzwy.login.R;
import com.gz0101.hzwy.login.contract.RegisterContract;
import com.gz0101.hzwy.login.presenter.RegisterPresenterImpl;
import com.gz0101.hzwy.login.widget.RoleSelectDialog;

public class RegisterActivity extends BaseActivity<RegisterPresenterImpl> implements RegisterContract.RegisterView, CompoundButton.OnCheckedChangeListener, View.OnClickListener {


    TextView registerBtn;
    TextView obtinVerifyBtn;
    EditText pwdEt;
    EditText verifyEt;
    EditText phoneEt;
    ImageView deletePhoneBtn;
    CheckBox hidePwdCb;

    TextView titleTv;
    private TimerHelp mNoteTimer;

    private String type;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_register);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
        initData();
        initEditChangeListener();
    }

    private void initData() {
        if (getIntent() != null) {
            type = getIntent().getStringExtra("type");
            if (AppConstance.LOGIN_JUMP_TYPE_FORGER_PWD.equals(type)) {
                if (registerBtn != null)
                    registerBtn.setText("重置");
                if (titleTv != null)
                    titleTv.setText("忘记密码");
            } else {
                if (registerBtn != null)
                    registerBtn.setText("注册");
                if (titleTv != null)
                    titleTv.setText("用户注册");
            }
        }
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        registerBtn = findViewById(R.id.tv_register);
        obtinVerifyBtn = findViewById(R.id.tv_verify_note);
        pwdEt = findViewById(R.id.et_register_pwd);
        verifyEt = findViewById(R.id.et_register_verify);
        phoneEt = findViewById(R.id.et_register_phone);
        deletePhoneBtn = findViewById(R.id.iv_register_phone_delete);
        hidePwdCb = findViewById(R.id.cb_register_hide_pwd);
        titleTv = findViewById(R.id.title);

        obtinVerifyBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        deletePhoneBtn.setOnClickListener(this);
        hidePwdCb.setOnCheckedChangeListener(this);
    }

    private void initEditChangeListener() {
        if (phoneEt != null) {
            phoneEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (verifyEt != null && pwdEt != null) {
                        changeRegisterBtnStates(!TextUtils.isEmpty(s.toString()) && !TextUtils.isEmpty(verifyEt.getText().toString()) && !TextUtils.isEmpty(pwdEt.getText().toString()));
                    }
                    if (deletePhoneBtn != null) {
                        deletePhoneBtn.setVisibility(TextUtils.isEmpty(s.toString()) ? View.INVISIBLE : View.VISIBLE);
                    }
                }
            });
        }
        if (verifyEt != null) {
            verifyEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (phoneEt != null && pwdEt != null) {
                        changeRegisterBtnStates(!TextUtils.isEmpty(s.toString()) && !TextUtils.isEmpty(phoneEt.getText().toString()) && !TextUtils.isEmpty(pwdEt.getText().toString()));
                    }
                }
            });
        }
        if (pwdEt != null) {
            pwdEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (phoneEt != null && verifyEt != null) {
                        changeRegisterBtnStates(!TextUtils.isEmpty(s.toString()) && !TextUtils.isEmpty(phoneEt.getText().toString()) && !TextUtils.isEmpty(verifyEt.getText().toString()));
                    }
                }
            });
        }
    }

    @Override
    protected RegisterPresenterImpl createPresenter() {
        return new RegisterPresenterImpl();
    }

    @Override
    protected void destroy() {
        if (mNoteTimer != null) {
            mNoteTimer.destroy();
            mNoteTimer = null;
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_register_phone_delete) {
            if (phoneEt != null) {
                phoneEt.setText("");
            }
            return;
        }

        if (v.getId() == R.id.tv_register) {
            if (this.type.equals(AppConstance.LOGIN_JUMP_TYPE_FORGER_PWD)) {
                ToastHelp.showShort("暂无接口");
                return;
            }
            showRoleDialog();
            return;
        }
        if (v.getId() == R.id.tv_verify_note) {
            if (getPresenter() != null) {
                getPresenter().obtainVerify(phoneEt.getText().toString(), type.equals(AppConstance.LOGIN_JUMP_TYPE_FORGER_PWD) ? 1 : 2);
            }
            return;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (hidePwdCb != null)
            hidePwdCb.setSelected(isChecked);
        if (pwdEt != null) {
            if (isChecked) {
                pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            pwdEt.setSelection(pwdEt.length());
        }
    }

    @Override
    public void changeRegisterBtnStates(boolean enable) {
        if (registerBtn != null) {
            registerBtn.setEnabled(enable);
        }
    }

    @Override
    public void changeNoteVerifyState(String hint) {
        if (getString(R.string.login_verification_code).equals(hint)) {
            mNoteTimer.cancel();
        }
        if (obtinVerifyBtn != null) {
            obtinVerifyBtn.setText(hint);
            obtinVerifyBtn.setTextColor(getString(R.string.login_verification_code).equals(hint) ? getResources().getColor(R.color.color_ffffff) : getResources().getColor(R.color.color_7a7a7a));
            obtinVerifyBtn.setEnabled(getString(R.string.login_verification_code).equals(hint));
        }
    }

    @Override
    public void startNoteVerifyChange() {
        if (mNoteTimer == null) {
            mNoteTimer = new TimerHelp(1000, 0);
        }
        mNoteTimer.execute(new TimerHelp.ExecuteTask() {
            @Override
            public void update(int count) {
                String hint = "";
                if ((60 - count) >= 0) {
                    hint = String.format(getString(R.string.login_note_verify_count_down), 60 - count);
                } else {
                    hint = getString(R.string.login_verification_code);
                }
                changeNoteVerifyState(hint);
            }
        });
        changeNoteVerifyState(String.format(getString(R.string.login_note_verify_count_down), 60));
    }

    @Override
    public void jump2Main() {
        if (Boolean.parseBoolean(getResources().getString(R.string.isModule))) {
            ToastHelp.showShort("开发模式");
        } else {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_MAIN).navigation();
            finish();
        }
    }

    @Override
    public void jump2Loign() {
        finish();
    }

    private void showRoleDialog() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("roleselect");
        if (fragment instanceof RoleSelectDialog) {
            ((RoleSelectDialog) fragment).dismiss();
        }
        new RoleSelectDialog()
                .setListener(new RoleSelectDialog.DialogMerchantBtnListener() {

                    @Override
                    public void onMerchantClick() {
                        register(2);
                    }
                }, new RoleSelectDialog.DialogUserBtnListener() {
                    @Override
                    public void onUserClick() {
                        register(1);
                    }
                }).show(getSupportFragmentManager(), "roleselect");
    }

    private void register(int type) {
        if (phoneEt == null || TextUtils.isEmpty(phoneEt.getText().toString())) {
            ToastHelp.showShort(R.string.login_phone_null_hint);
            return;
        }
        if (phoneEt == null || !FormatUtil.isPhoneNum(phoneEt.getText().toString())) {
            ToastHelp.showShort(R.string.login_phone_error_hint);
            return;
        }
        if (verifyEt == null || TextUtils.isEmpty(verifyEt.getText().toString())) {
            ToastHelp.showShort(R.string.login_verification_null_hint);
            return;
        }
        if (pwdEt == null || TextUtils.isEmpty(pwdEt.getText().toString())) {
            ToastHelp.showShort(R.string.login_pwd_null_hint);
            return;
        }
        if (pwdEt == null || pwdEt.getText().toString().length() < 6) {
            ToastHelp.showShort(R.string.login_pwd_error_hint);
            return;
        }
        if (getPresenter() != null) {
            getPresenter().userRegister(phoneEt.getText().toString(), verifyEt.getText().toString(), pwdEt.getText().toString(), type);
        }
    }
}
