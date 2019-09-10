package com.gz0101.hzwy.lease.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz0101.hzwy.lease.R;

public class LeaseHolder extends RecyclerView.ViewHolder {
    public TextView mName;
    public TextView mPrice;
    public ImageView mImageView;
    public ImageView mReduce;
    public ImageView mInCrease;
    public TextView mCount;

    public LeaseHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image);
        mName = itemView.findViewById(R.id.name);
        mPrice = itemView.findViewById(R.id.price);
        mReduce = itemView.findViewById(R.id.reduce);
        mInCrease = itemView.findViewById(R.id.increase);
        mCount = itemView.findViewById(R.id.count);
    }
}
