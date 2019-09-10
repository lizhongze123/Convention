package com.gz0101.hzwy.baselibrary.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gz0101.hzwy.baselibrary.R;

public class GZDialog extends Dialog {
    public static final int ID_LEFT = R.id.left;
    public static final int ID_RIGHT = R.id.right;
    public static final int ID_SINGLE = R.id.single;
    private View mRootView;
    private TextView mContent;
    private TextView mLeftBtn;
    private TextView mRightBtn;
    private TextView mSingleBtn;

    private GZDialog(@NonNull Context context) {
        this(context, R.style.AppDialog);
    }

    private GZDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.AppDialog);
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.base_dialog, null, false);
        setContentView(mRootView);
        mContent = findViewById(R.id.content);
        mLeftBtn = findViewById(R.id.left);
        mRightBtn = findViewById(R.id.right);
        mSingleBtn = findViewById(R.id.single);
    }

    public void setContentText(String str) {
        mContent.setText(str);
    }

    public void setLeftButtonText(String str) {
        mLeftBtn.setText(str);
    }

    public void setRightButtonText(String str) {
        mRightBtn.setText(str);
    }

    public void setSingleButtonText(String str) {
        mSingleBtn.setText(str);
    }

    public void setSingleButtonListener(View.OnClickListener listener) {
        if (listener != null) {
            mSingleBtn.setOnClickListener(listener);
        }
    }

    public void setLeftButtonListener(View.OnClickListener listener) {
        if (listener != null) {
            mLeftBtn.setOnClickListener(listener);
        }
    }

    public void setRightButtonListener(View.OnClickListener listener) {
        if (listener != null) {
            mRightBtn.setOnClickListener(listener);
        }
    }

    public void setType(int type) {
        if (type == Builder.TYPE_NORMAL) {
            mLeftBtn.setVisibility(View.VISIBLE);
            mRightBtn.setVisibility(View.VISIBLE);
            mSingleBtn.setVisibility(View.GONE);
        } else if (type == Builder.TYPE_SINGLE) {
            mLeftBtn.setVisibility(View.GONE);
            mRightBtn.setVisibility(View.GONE);
            mSingleBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
            attributes.width = (int) (width * 0.5f);
            window.setAttributes(attributes);
        }
        super.show();
    }

    public static class Builder{
        public static final int TYPE_NORMAL = 0;
        public static final int TYPE_SINGLE = 1;

        private String leftBtnStr;
        private String rightBtnStr;
        private String singleBtnStr;
        private String content;
        private int type;

        private View.OnClickListener leftListener;
        private View.OnClickListener rightListener;
        private View.OnClickListener singleListener;

        private Activity activity;
        private boolean isLeftDefault;
        private boolean isRightDefault;
        private boolean isSingleDefault;

        private DefaultListener defaultListener;

        public Builder(Activity activity) {
            this.activity = activity;
            if (activity == null) {
                throw new NullPointerException();
            }
            defaultListener = new DefaultListener();
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setContent(int res) {
            this.content = activity.getString(res);
            return this;
        }

        public Builder setLeftButtonText(CharSequence text) {
            this.leftBtnStr = text.toString();
            return this;
        }

        public Builder setRightButtonText(CharSequence text) {
            this.rightBtnStr = text.toString();
            return this;
        }

        public Builder setSingleButtonText(CharSequence text) {
            this.singleBtnStr = text.toString();
            return this;
        }

        public Builder setLeftButtonText(int res) {
            this.leftBtnStr = activity.getString(res);
            return this;
        }

        public Builder setRightButtonText(int res) {
            this.rightBtnStr = activity.getString(res);
            return this;
        }

        public Builder setSingleButtonText(int res) {
            this.singleBtnStr = activity.getString(res);
            return this;
        }

        public Builder setLeftButtonText(CharSequence text, View.OnClickListener listener) {
            this.leftBtnStr = text.toString();
            this.leftListener = listener;
            return this;
        }

        public Builder setRightButtonText(CharSequence text, View.OnClickListener listener) {
            this.rightBtnStr = text.toString();
            this.rightListener = listener;
            return this;
        }

        public Builder setSingleButtonText(CharSequence text, View.OnClickListener listener) {
            this.singleBtnStr = text.toString();
            this.singleListener = listener;
            return this;
        }

        public Builder setLeftButtonText(int res, View.OnClickListener listener) {
            this.leftBtnStr = activity.getString(res);
            this.leftListener = listener;
            return this;
        }

        public Builder setRightButtonText(int res, View.OnClickListener listener) {
            this.rightBtnStr = activity.getString(res);
            this.rightListener = listener;
            return this;
        }

        public Builder setSingleButtonText(int res, View.OnClickListener listener) {
            this.singleBtnStr = activity.getString(res);
            this.singleListener = listener;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setLeftDefault(){
            isLeftDefault = true;
            return this;
        }

        public Builder setRightDefault(){
            isRightDefault = true;
            return this;
        }

        public Builder setSingleDefault() {
            isSingleDefault = true;
            return this;
        }

        public GZDialog build() {
            GZDialog dialog = new GZDialog(activity);
            defaultListener.setDialog(dialog);
            if (!TextUtils.isEmpty(content)) {
                dialog.setContentText(content);
            }
            if (!TextUtils.isEmpty(leftBtnStr)) {
                dialog.setLeftButtonText(leftBtnStr);
            }
            if (!TextUtils.isEmpty(rightBtnStr)) {
                dialog.setRightButtonText(rightBtnStr);
            }
            if (!TextUtils.isEmpty(singleBtnStr)) {
                dialog.setSingleButtonText(singleBtnStr);
            }
            if (leftListener != null) {
                dialog.setLeftButtonListener(leftListener);
            }
            if (rightListener != null) {
                dialog.setRightButtonListener(rightListener);
            }
            if (singleListener != null) {
                dialog.setSingleButtonListener(singleListener);
            }
            if (isLeftDefault) {
                dialog.setLeftButtonListener(defaultListener);
            }
            if (isRightDefault) {
                dialog.setRightButtonListener(defaultListener);
            }
            if (isSingleDefault) {
                dialog.setSingleButtonListener(defaultListener);
            }
            dialog.setType(type);
            return dialog;
        }
    }

    private static class DefaultListener implements View.OnClickListener {
        private Dialog dialog;

        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }

        public void setDialog(Dialog dialog) {
            this.dialog = dialog;
        }
    }
}
