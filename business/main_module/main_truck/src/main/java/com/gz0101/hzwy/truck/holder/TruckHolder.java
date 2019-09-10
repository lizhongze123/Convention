package com.gz0101.hzwy.truck.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gz0101.hzwy.truck.R;
import com.gz0101.hzwy.truck.bean.TruckItem;

public class TruckHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private TextView mNameText;
    private TextView mTruckType;
    private TextView mTruckStatus;
    public TruckHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image);
        mNameText = itemView.findViewById(R.id.name);
        mTruckType = itemView.findViewById(R.id.type);
        mTruckStatus = itemView.findViewById(R.id.status);
    }

    public void setTruckItem(TruckItem item) {
        if (item == null) {
            return;
        }
        if (mImageView != null && !TextUtils.isEmpty(item.getIconUrl())) {
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(mImageView.getContext()).load(item.getIconUrl()).apply(requestOptions).into(mImageView);
        }
        if (mNameText != null && !TextUtils.isEmpty(item.getName())) {
            mNameText.setText(item.getName());
        }
        if (mTruckType != null && !TextUtils.isEmpty(item.getType())) {
            mTruckType.setText(item.getType());
        }
        if (mTruckStatus == null) {
            return;
        }
        switch (item.getStatus()) {
            case TruckItem.STATUS_NORMAL:
                mTruckStatus.setVisibility(View.GONE);
                break;
            case TruckItem.STATUS_TAKE_REST:
                mTruckStatus.setVisibility(View.VISIBLE);
                break;
        }
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getNameText() {
        return mNameText;
    }

    public TextView getTruckType() {
        return mTruckType;
    }
}
