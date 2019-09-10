package com.gz0101.hzwy.lease.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gz0101.hzwy.lease.R;

public class LeaseNoHolder extends RecyclerView.ViewHolder {
    private TextView mTextView;

    public LeaseNoHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
    }

    public void setText(String message) {
        if (!TextUtils.isEmpty(message)) {
            mTextView.setText(message);
        }
    }
}
