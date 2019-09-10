package com.gz0101.hzwy.login.ui;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.util.PermissionUtil;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.baselibrary.util.help.TimerHelp;
import com.gz0101.hzwy.login.AppConstance;
import com.gz0101.hzwy.login.R;
import com.gz0101.hzwy.login.contract.LoginContract;
import com.gz0101.hzwy.login.presenter.LoginPresenterImpl;

@Route(path = RouterConstance.ACTIVITY_URL_LOGIN)
public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements LoginContract.LoignView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static final int REQUEST_PHONE_PERMISSION_CODE = 100;
    ImageView wechatLoginIv;
    TextView agreeTv;
    TextView registerTv;
    TextView forgetPwdTv;
    TextView loginTv;
    LinearLayout pwdInputLl;
    EditText pwdEt;
    CheckBox hidePwdIv;
    LinearLayout verifyInputLl;
    EditText verifyEt;
    TextView obtainVerifyTv;
    EditText phoneEt;
    ImageView phoneDelIv;

    RelativeLayout loginTypeVerRl;
    View loginTypeVerV;

    RelativeLayout loginTypePwdRl;
    View loginTypePwdV;

    String loginType = AppConstance.LOGIN_TYPE_VER;
    private TimerHelp mNoteTimer;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_login);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_f8f8f8));
    }

    @Override
    protected void onCreate() {
        initView();
        initEditChangeListener();
    }

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    protected void destroy() {
        if (mNoteTimer != null) {
            mNoteTimer.destroy();
            mNoteTimer = null;
        }
    }

    private void initView() {
        wechatLoginIv = findViewById(R.id.iv_login_wechat);
        agreeTv = findViewById(R.id.tv_login_agree);
        registerTv = findViewById(R.id.tv_login_register);
        forgetPwdTv = findViewById(R.id.tv_login_forget_pwd);
        loginTv = findViewById(R.id.tv_login);
        pwdInputLl = findViewById(R.id.ll_login_pwd);
        pwdEt = findViewById(R.id.et_login_pwd);
        hidePwdIv = findViewById(R.id.iv_login_hide_pwd);
        verifyInputLl = findViewById(R.id.ll_login_verify);
        verifyEt = findViewById(R.id.et_login_verify);
        obtainVerifyTv = findViewById(R.id.tv_login_obtain_verify);
        phoneEt = findViewById(R.id.et_login_phone);
        phoneDelIv = findViewById(R.id.iv_login_phone_delete);

        loginTypeVerRl = findViewById(R.id.rl_login_type_verify);
        loginTypeVerV = findViewById(R.id.v_login_type_verify_bottom);

        loginTypePwdRl = findViewById(R.id.rl_login_type_pwd);
        loginTypePwdV = findViewById(R.id.v_login_type_pwd_bottom);

        loginTypePwdRl.setOnClickListener(this);
        loginTypeVerRl.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        forgetPwdTv.setOnClickListener(this);
        wechatLoginIv.setOnClickListener(this);
        obtainVerifyTv.setOnClickListener(this);
        hidePwdIv.setOnCheckedChangeListener(this);
        phoneDelIv.setOnClickListener(this);
        agreeTv.setOnClickListener(this);
        loginTv.setOnClickListener(this);
        loginTypeVerRl.setSelected(true);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                    if (loginType.equals(AppConstance.LOGIN_TYPE_VER)) {
                        if (verifyEt != null)
                            changeLoginBtnState(!TextUtils.isEmpty(s.toString()) && !TextUtils.isEmpty(verifyEt.getText().toString()));
                    } else {
                        if (pwdEt != null)
                            changeLoginBtnState(!TextUtils.isEmpty(s.toString()) && !TextUtils.isEmpty(pwdEt.getText().toString()));
                    }

                    if (phoneDelIv != null) {
                        phoneDelIv.setVisibility(TextUtils.isEmpty(s.toString()) ? View.INVISIBLE : View.VISIBLE);
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
                    if (phoneEt != null) {
                        changeLoginBtnState(!TextUtils.isEmpty(s.toString()) && !TextUtils.isEmpty(phoneEt.getText().toString()));
                    }
                }

            });
        }

        pwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (phoneEt != null) {
                    changeLoginBtnState(!TextUtils.isEmpty(s.toString()) && !TextUtils.isEmpty(phoneEt.getText().toString()));
                }
            }
        });
    }

    @Override
    public void changeNoteVerifyState(String hint) {
        if (getString(R.string.login_verification_code).equals(hint)) {
            mNoteTimer.cancel();
        }
        if (obtainVerifyTv != null) {
            obtainVerifyTv.setText(hint);
            obtainVerifyTv.setTextColor(getString(R.string.login_verification_code).equals(hint) ? getResources().getColor(R.color.color_ffffff) : getResources().getColor(R.color.color_7a7a7a));
            obtainVerifyTv.setEnabled(getString(R.string.login_verification_code).equals(hint));
        }
    }

    @Override
    public void changeLoginBtnState(boolean enable) {
        if (loginTv != null) {
            loginTv.setEnabled(enable);
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
                String hint;
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

    private void verLogin() {
        loginType = AppConstance.LOGIN_TYPE_VER;
        loginTypeVerRl.setSelected(true);
        loginTypePwdRl.setSelected(false);
        loginTypeVerV.setVisibility(View.VISIBLE);
        loginTypePwdV.setVisibility(View.INVISIBLE);
        pwdInputLl.setVisibility(View.GONE);
        verifyInputLl.setVisibility(View.VISIBLE);
    }

    private void pwdLogin() {
        loginType = AppConstance.LOGIN_TYPE_PWD;
        loginTypeVerRl.setSelected(false);
        loginTypePwdRl.setSelected(true);
        loginTypeVerV.setVisibility(View.INVISIBLE);
        loginTypePwdV.setVisibility(View.VISIBLE);
        pwdInputLl.setVisibility(View.VISIBLE);
        verifyInputLl.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_login_type_verify) {
            verLogin();
            return;
        }

        if (v.getId() == R.id.rl_login_type_pwd) {
            pwdLogin();
            return;
        }

        if (v.getId() == R.id.iv_login_phone_delete) {
            if (phoneEt != null) {
                phoneEt.setText("");
            }
            return;
        }
        if (v.getId() == R.id.tv_login_register) {
            jump2Register(AppConstance.LOGIN_JUMP_TYPE_REGISTER);
            return;
        }

        if (v.getId() == R.id.tv_login_obtain_verify) {
            if (getPresenter() != null)
                getPresenter().obtainVerify(phoneEt.getText().toString(), 1);
            return;
        }
        if (v.getId() == R.id.tv_login) {
            requestPermission(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_PHONE_PERMISSION_CODE);
            return;
        }
        if (v.getId() == R.id.iv_login_wechat) {
            ToastHelp.showShort("敬请期待");
            return;
        }
        if (v.getId() == R.id.tv_login_forget_pwd) {
            jump2Register(AppConstance.LOGIN_JUMP_TYPE_FORGER_PWD);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (hidePwdIv != null)
            hidePwdIv.setSelected(isChecked);
        if (pwdEt != null) {
            if (isChecked) {
                pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            pwdEt.setSelection(pwdEt.length());
        }
    }

    private void jump2Register(String type) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    private void requestPermission(final String[] permissions, final int requestCode) {
        PermissionUtil.checkPermission(permissions, new PermissionUtil.ICheckPermissionCallBack() {
            @Override
            public void onSuccess() {
                if (getPresenter() != null && phoneEt != null) {
                    if (AppConstance.LOGIN_TYPE_PWD.equals(loginType) && pwdEt != null) {
                        getPresenter().userLogin(phoneEt.getText().toString(), pwdEt.getText().toString(), loginType);
                    } else if (AppConstance.LOGIN_TYPE_VER.equals(loginType) && verifyEt != null) {
                        getPresenter().userLogin(phoneEt.getText().toString(), verifyEt.getText().toString(), loginType);
                    }
                }
            }

            @Override
            public void onFailed() {
                ActivityCompat.requestPermissions(LoginActivity.this, permissions, requestCode);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PHONE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (getPresenter() != null && phoneEt != null) {
                    if (AppConstance.LOGIN_TYPE_PWD.equals(loginType) && pwdEt != null) {
                        getPresenter().userLogin(phoneEt.getText().toString(), pwdEt.getText().toString(), loginType);
                    } else if (AppConstance.LOGIN_TYPE_VER.equals(loginType) && verifyEt != null) {
                        getPresenter().userLogin(phoneEt.getText().toString(), verifyEt.getText().toString(), loginType);
                    }
                }
            } else {
                ToastHelp.showShort(R.string.login_login_permission);
            }
        }
    }
}
