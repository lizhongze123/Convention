package com.gz0101.hzwy.hourglass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.utils.TextUtils;
import com.bumptech.glide.Glide;
import com.gz0101.hzwy.baselibrary.request.respone.bean.ShopListBean;
import com.gz0101.hzwy.hourglass.R;

import java.util.ArrayList;
import java.util.List;

public class HourglassMainAdapter extends RecyclerView.Adapter<HourglassMainAdapter.ViewHolder> {
    private OnItemClickListener clickListener;

    private Context mContext;

    private List<ShopListBean> datas = new ArrayList<>();

    public HourglassMainAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void refresh(List<ShopListBean> list) {
        if (datas == null)
            datas = new ArrayList<>();
        if (datas.size() > 0)
            datas.clear();
        if (list != null)
            datas.addAll(list);
        notifyDataSetChanged();
    }

    public void loadMore(List<ShopListBean> list) {
        if (datas == null)
            datas = new ArrayList<>();
        if (list != null)
            datas.addAll(datas.size(), list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourglass_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ShopListBean bean = datas.get(position);
        if (bean == null)
            return;
        if (!TextUtils.isEmpty(bean.getShopLogo()))
            Glide.with(mContext)
                    .load(bean.getShopLogo())
                    .into(holder.imageView);

        holder.address.setText(bean.getShopAddress());
        holder.name.setText(bean.getShopName());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null)
                    clickListener.onItemClick(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ShopListBean shopListBean);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView address;
        ImageView imageView;
        LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.hourglass_item_name_tv);
            address = itemView.findViewById(R.id.hourglass_item_address_tv);
            imageView = itemView.findViewById(R.id.hourglass_item_iv);
            ll = itemView.findViewById(R.id.hourglass_item_ll);
        }
    }
}
