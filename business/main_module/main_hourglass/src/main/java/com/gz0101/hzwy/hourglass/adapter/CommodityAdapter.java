package com.gz0101.hzwy.hourglass.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gz0101.hzwy.baselibrary.request.respone.bean.GoodsListBean;
import com.gz0101.hzwy.hourglass.R;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder> {
    private Context mContext;
    private List<GoodsListBean> mList;

    public CommodityAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<GoodsListBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public List<GoodsListBean> getList() {
        return mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_commodity, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsListBean bean = mList.get(position);
        if (!TextUtils.isEmpty(bean.getCoverPath()))
            Glide.with(mContext).load(bean.getCoverPath()).into(holder.roundedImageView);
        holder.name.setText(bean.getTitle());
        holder.price.setText(bean.getSellingPrice());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;
        TextView name;
        TextView price;
        ImageView reduce;
        TextView count;
        ImageView increase;

        public ViewHolder(View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.riv_item_commoditys);
            name = itemView.findViewById(R.id.tv_item_commodity_name);
            price = itemView.findViewById(R.id.tv_item_commodity_price);
            reduce = itemView.findViewById(R.id.iv_item_commodity_reduce);
            count = itemView.findViewById(R.id.tv_item_commodity_count);
            increase = itemView.findViewById(R.id.iv_item_commodity_increase);
        }

    }
}
