package com.gz0101.hzwy.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.gz0101.hzwy.main.R;
import com.gz0101.hzwy.main.holder.GoodsHolder;
import com.gz0101.hzwy.main.holder.GoodsNoHolder;
import com.gz0101.hzwy.main.listener.MyItemClickListener;
import com.gz0101.hzwy.main.model.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsManagementAdapter extends RecyclerView.Adapter {

    final static int TYPE_NO = 0;
    final static int TYPE_NORMAL = 1;
    private Context mContext;
    private List<GoodsBean> mList;
    private MyItemClickListener mListener;

    public GoodsManagementAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<GoodsBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public List<GoodsBean> getList() {
        return mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NO:
                return new GoodsNoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_no, parent, false)).;
            default:
                return new GoodsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_goods, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_NO) {
            GoodsNoHolder viewHolder = (GoodsNoHolder) holder;
            viewHolder.setText(mContext.getString(R.string.no_goods));
            return;
        }
        final GoodsBean item = mList.get(position);
        final GoodsHolder viewHolder = (GoodsHolder) holder;
        viewHolder.tvName.setText(item.name);
        viewHolder.tvPrice.setText(item.price + "");
        Glide.with(mContext).load(item.avatar).into(viewHolder.ivAvatar);
        viewHolder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 1 : mList.size() == 0 ? 1 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList == null || mList.size() == 0) {
            return TYPE_NO;
        }
        return TYPE_NORMAL;
    }


    public void setCountListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }



    public List<GoodsBean> filterList() {
        List<GoodsBean> list = new ArrayList<>();
        for (GoodsBean item : mList) {
            if (item.getCount() > 0) {
                list.add(item);
            }
        }
        return list;
    }
}
