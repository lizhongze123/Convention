package com.gz0101.hzwy.login.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gz0101.hzwy.login.R;

import java.util.ArrayList;
import java.util.List;

public class VerifyView extends RelativeLayout {
    private Context context;
    private TextView verifyTv1;
    private TextView verifyTv2;
    private TextView verifyTv3;
    private TextView verifyTv4;
    private TextView verifyTv5;
    private TextView verifyTv6;
    private EditText verifyEt;
    private List<String> verify = new ArrayList<>();
    private InputMethodManager imm;

    public VerifyView(Context context) {
        this(context, null);
    }

    public VerifyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        loadView();
    }


    private void loadView() {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.view_verify, this);
        initView(view);
        initEvent();
    }

    private void initView(View view) {
        verifyTv1 = view.findViewById(R.id.tv_verify_1);
        verifyTv2 = view.findViewById(R.id.tv_verify_2);
        verifyTv3 = view.findViewById(R.id.tv_verify_3);
        verifyTv4 = view.findViewById(R.id.tv_verify_4);
        verifyTv5 = view.findViewById(R.id.tv_verify_5);
        verifyTv6 = view.findViewById(R.id.tv_verify_6);

        verifyEt = findViewById(R.id.et_verify);
    }

    private void initEvent() {
        //验证码输入
        verifyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    verifyEt.setText("");
                    if (verify.size() < 6) {
                        verify.add(editable.toString());
                        showCode();
                    }
                }
            }
        });
        // 监听验证码删除按键
        verifyEt.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN && verify.size() > 0) {
                    verify.remove(verify.size() - 1);
                    showCode();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 显示输入的验证码
     */
    private void showCode() {
        String code1 = "";
        String code2 = "";
        String code3 = "";
        String code4 = "";
        String code5 = "";
        String code6 = "";
        if (verify.size() >= 1) {
            code1 = verify.get(0);
        }
        if (verify.size() >= 2) {
            code2 = verify.get(1);
        }
        if (verify.size() >= 3) {
            code3 = verify.get(2);
        }
        if (verify.size() >= 4) {
            code4 = verify.get(3);
        }
        if (verify.size() >= 5) {
            code5 = verify.get(4);
        }
        if (verify.size() >= 6) {
            code6 = verify.get(5);
        }
        verifyTv1.setText(code1);
        verifyTv2.setText(code2);
        verifyTv3.setText(code3);
        verifyTv4.setText(code4);
        verifyTv5.setText(code5);
        verifyTv6.setText(code6);

        setColor();//设置高亮颜色
        callBack();//回调
    }

    /**
     * 设置高亮颜色
     */
    private void setColor() {
        verifyTv1.setEnabled(false);
        verifyTv2.setEnabled(false);
        verifyTv3.setEnabled(false);
        verifyTv4.setEnabled(false);
        verifyTv5.setEnabled(false);
        verifyTv6.setEnabled(false);
        if (verify.size() == 0) {
            verifyTv1.setEnabled(true);
        }
        if (verify.size() == 1) {
            verifyTv2.setEnabled(true);
        }
        if (verify.size() == 2) {
            verifyTv3.setEnabled(true);
        }
        if (verify.size() >= 3) {
            verifyTv4.setEnabled(true);
        }
        if (verify.size() >= 5) {
            verifyTv5.setEnabled(true);
        }
        if (verify.size() >= 5) {
            verifyTv6.setEnabled(true);
        }
    }

    /**
     * 回调
     */
    private void callBack() {
        if (onInputListener == null) {
            return;
        }
        if (verify.size() == 4) {
            onInputListener.onSucess();
        } else {
            onInputListener.onInput();
        }
    }

    /**
     * 显示键盘
     */
    public void showSoftInput() {
        //显示软键盘
        if (imm != null && verifyEt != null) {
            verifyEt.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imm.showSoftInput(verifyEt, 0);
                }
            }, 200);
        }
    }

    private OnInputListener onInputListener;

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    //定义回调
    public interface OnInputListener {
        void onSucess();

        void onInput();
    }
}
