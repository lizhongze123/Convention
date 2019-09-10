package com.gz0101.hzwy.receipt.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gz0101.hzwy.receipt.R;
import com.gz0101.hzwy.receipt.bean.PlaceItem;
import com.gz0101.hzwy.receipt.holder.MainHolder;
import com.gz0101.hzwy.receipt.holder.NoHolder;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter  {
    private static final int TYPE_NO = -0x12;

    private List<PlaceItem> mList;
    private OnClickLocationListener mListener;

    public void setList(List<PlaceItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NO) {
            return new NoHolder(getView(parent, R.layout.item_no_place));
        } else {
            return new MainHolder(getView(parent, R.layout.item_place));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NO) {
            return;
        }
        final PlaceItem placeItem = mList.get(holder.getAdapterPosition());
        final MainHolder viewHolder = (MainHolder) holder;
        viewHolder.setContent(placeItem);
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClickLocation(viewHolder.getAdapterPosition(), placeItem);
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onSelectClick(viewHolder.getAdapterPosition(), placeItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null || mList.size() == 0 ? 1 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList == null || mList.size() == 0) {
            return TYPE_NO;
        }
        return 0;
    }

    private View getView(ViewGroup parent, int res) {
        return LayoutInflater.from(parent.getContext()).inflate(res, parent, false);
    }

    public interface OnClickLocationListener {
        void onClickLocation(int position, PlaceItem item);
        void onSelectClick(int position, PlaceItem item);
    }

    public void setOnClickLocationListener(OnClickLocationListener listener) {
        mListener = listener;
    }
}
