package com.gz0101.hzwy.main.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.gz0101.hzwy.main.R;

public class GoodsNoHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;

    public GoodsNoHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.text);
    }

    public void setText(String message) {
        if (!TextUtils.isEmpty(message)) {
            mTextView.setText(message);
        }
    }
}
