package com.gz0101.hzwy.main.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gz0101.hzwy.main.R;

public class GoodsHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvPrice;
    public ImageView ivAvatar;
    public ImageView ivEdit;
    public RelativeLayout rl;

    public GoodsHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvPrice = itemView.findViewById(R.id.tv_price);
        ivAvatar = itemView.findViewById(R.id.iv_avatar);
        ivEdit = itemView.findViewById(R.id.iv_edit);
        rl = itemView.findViewById(R.id.rl);
    }
}
