package com.gz0101.hzwy.material.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gz0101.hzwy.material.R;
import com.gz0101.hzwy.material.bean.MaterialRightBean;

public class MaterialRightHolder extends RecyclerView.ViewHolder {
    public ImageView reduce;
    public ImageView increase;
    public TextView count;
    public TextView price;
    public ImageView icon;
    public TextView name;

    public MaterialRightHolder(View itemView) {
        super(itemView);
        price = itemView.findViewById(R.id.price);
        reduce = itemView.findViewById(R.id.reduce);
        increase = itemView.findViewById(R.id.increase);
        count = itemView.findViewById(R.id.count);
        icon = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
    }

    public void setContent(MaterialRightBean bean) {
        if (bean == null) {
            return;
        }
        name.setText(bean.getName() == null?"":bean.getName());
        count.setText(String.valueOf(bean.getCount()));
        Glide.with(icon.getContext()).load(bean.getIconUrl()).into(icon);
    }

}
