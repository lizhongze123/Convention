package com.gz0101.hzwy.hourglass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.gz0101.hzwy.hourglass.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportShopAdapter extends RecyclerView.Adapter<ReportShopAdapter.ViewHolder> {
    private List<String> datas = new ArrayList<>();
    private Context mContext;
    private OnClickListener listener;

    public ReportShopAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDatas(List<String> data) {
        if (datas == null)
            datas = new ArrayList<>();
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.listener = onClickListener;
    }

    public List<String> getDatas() {
        return datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_report_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (position == 0) {
            holder.addIv.setVisibility(View.VISIBLE);
            holder.pictureRl.setVisibility(View.INVISIBLE);
        } else {
            holder.addIv.setVisibility(View.INVISIBLE);
            holder.pictureRl.setVisibility(View.VISIBLE);
            File file = new File(datas.get(position-1));
            if (file.exists()) {
                Glide.with(mContext).load(file).into(holder.imageView);
            }
        }

        holder.addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.addImage();
            }
        });

        holder.deteleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas != null && datas.size() >= position) {
                    datas.remove(position - 1);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 1 : datas.size() + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView addIv;
        RelativeLayout pictureRl;
        ImageView imageView;
        ImageView deteleIv;

        public ViewHolder(View itemView) {
            super(itemView);
            addIv = itemView.findViewById(R.id.iv_item_report_add);
            pictureRl = itemView.findViewById(R.id.rl_item_report);
            imageView = itemView.findViewById(R.id.iv_item_report);
            deteleIv = itemView.findViewById(R.id.iv_item_report_detele);
        }
    }

    public interface OnClickListener {
        void addImage();
    }

}
