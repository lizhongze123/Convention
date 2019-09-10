package com.gz0101.hzwy.login.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gz0101.hzwy.login.R;


public class RoleSelectDialog extends DialogFragment {

    LinearLayout left;
    LinearLayout right;
    private DialogMerchantBtnListener leftBtnListener;
    private DialogUserBtnListener rightBtnListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, com.gz0101.hzwy.baselibrary.R.style.AppDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_role_select, null);
        left = view.findViewById(R.id.ll_left_role_select_dialog);
        right = view.findViewById(R.id.ll_right_role_select_dialog);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (leftBtnListener != null)
                    leftBtnListener.onMerchantClick();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (rightBtnListener != null)
                    rightBtnListener.onUserClick();
            }
        });
        setCancelable(true);
        return view;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();
    }

    public RoleSelectDialog setListener(DialogMerchantBtnListener leftListener, DialogUserBtnListener rightListener) {
        this.leftBtnListener = leftListener;
        this.rightBtnListener = rightListener;
        return this;
    }

    public interface DialogMerchantBtnListener {
        void onMerchantClick();
    }

    public interface DialogUserBtnListener {
        void onUserClick();
    }

}
