package com.gz0101.hzwy.lease.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gz0101.hzwy.lease.R;
import com.gz0101.hzwy.lease.bean.LeaseItem;
import com.gz0101.hzwy.lease.holder.LeasePreviewHolder;
import com.gz0101.hzwy.lease.listener.CountListener;

import java.util.List;
import java.util.Locale;

public class LeasePreviewAdapter extends RecyclerView.Adapter<LeasePreviewHolder> {
    private Context mContext;
    private CountListener mListener;
    private List<LeaseItem> mList;

    public LeasePreviewAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<LeaseItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeasePreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeasePreviewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_lease_preview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final LeasePreviewHolder holder, int position) {
        final LeaseItem item = mList.get(position);
        holder.mName.setText(item.getName());
        holder.mCount.setText(String.valueOf(item.getCount()));
        holder.mPrice.setText(String.format(Locale.CHINA,"￥%.2f", item.getCount() * item.getPrice()));
        holder.mReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reduceGoods(item, holder.mCount, holder.mPrice);
            }
        });
        holder.mInCrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseGoods(item, holder.mCount, holder.mPrice);
            }
        });
    }

    private void reduceGoods(LeaseItem item, TextView count, TextView prices){
        if (item == null || count == null || prices == null) {
            return;
        }
        int number = item.getCount() - 1;
        item.setCount(number);
        if (number <= 0) {
            mList.remove(item);
            notifyDataSetChanged();
        } else {
            count.setText(String.valueOf(number));
            prices.setText(String.format(Locale.CHINA,"￥%.2f", item.getCount() * item.getPrice()));
        }
        if (mListener != null) {
            mListener.onClickCount(false);
        }
    }

    private void increaseGoods(LeaseItem item, TextView count, TextView prices){
        if (item == null || count == null || prices == null) {
            return;
        }
        int number = item.getCount() + 1;
        item.setCount(number);
        if (number <= 9999) {
            count.setText(String.valueOf(number));
            prices.setText(String.format(Locale.CHINA,"￥%.2f", item.getCount() * item.getPrice()));
        }
        if (number < 10000 && mListener != null) {
            mListener.onClickCount(false);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    public void setCountListener(CountListener listener) {
        mListener = listener;
    }

}
