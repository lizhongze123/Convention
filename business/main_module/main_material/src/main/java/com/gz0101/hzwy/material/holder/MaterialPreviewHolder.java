package com.gz0101.hzwy.material.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz0101.hzwy.material.R;

public class MaterialPreviewHolder extends RecyclerView.ViewHolder {
    public TextView mName;
    public TextView mPrice;
    public ImageView mReduce;
    public ImageView mInCrease;
    public TextView mCount;
    public MaterialPreviewHolder(View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.name);
        mPrice = itemView.findViewById(R.id.price);
        mReduce = itemView.findViewById(R.id.reduce);
        mInCrease = itemView.findViewById(R.id.increase);
        mCount = itemView.findViewById(R.id.count);
    }
}
