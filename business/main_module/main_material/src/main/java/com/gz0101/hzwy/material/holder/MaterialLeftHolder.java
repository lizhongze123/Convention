package com.gz0101.hzwy.material.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gz0101.hzwy.material.R;

public class MaterialLeftHolder extends RecyclerView.ViewHolder {
    private View mRootView;
    private CheckBox checkBox;
    private TextView mTextView;

    public MaterialLeftHolder(View itemView) {
        super(itemView);
        mRootView = itemView.findViewById(R.id.root);
        checkBox = itemView.findViewById(R.id.tab);
        mTextView = itemView.findViewById(R.id.name);
    }

    public void setName(String name) {
        mTextView.setText(name);
    }

    public void setCheckStatus(boolean checked) {
        checkBox.setChecked(checked);
        mRootView.setBackgroundColor(checked?Color.WHITE:Color.parseColor("#F8F8F8"));
        mTextView.setTextColor(checked?Color.parseColor("#222222"):Color.parseColor("#7A7A7A"));
    }
}
