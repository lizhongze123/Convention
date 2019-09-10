package com.gz0101.hzwy.lease.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gz0101.hzwy.lease.R;
import com.gz0101.hzwy.lease.bean.LeaseItem;
import com.gz0101.hzwy.lease.holder.LeaseHolder;
import com.gz0101.hzwy.lease.holder.LeaseNoHolder;
import com.gz0101.hzwy.lease.listener.CountListener;

import java.util.ArrayList;
import java.util.List;

public class LeaseAdapter extends RecyclerView.Adapter {
    final static int TYPE_NO = 0;
    final static int TYPE_NORMAL = 1;
    private Context mContext;
    private List<LeaseItem> mList;
    private CountListener mListener;

    public LeaseAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<LeaseItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public List<LeaseItem> getList() {
        return mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NO:
                return new LeaseNoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_lease_no, parent, false));
            default:
                return new LeaseHolder(LayoutInflater.from(mContext).inflate(R.layout.item_lease_main, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_NO) {
            LeaseNoHolder viewHolder = (LeaseNoHolder) holder;
            viewHolder.setText(mContext.getString(R.string.main_no));
            return;
        }
        final LeaseItem item = mList.get(position);
        final LeaseHolder viewHolder = (LeaseHolder) holder;
        viewHolder.mName.setText(item.getName());
        viewHolder.mPrice.setText("test");
        viewHolder.mCount.setText(String.valueOf(item.getCount()));
        Glide.with(mContext).load(item.getIconUrl()).into(viewHolder.mImageView);
        viewHolder.mReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = item.getCount();
                if (count <= 0) {
                    return;
                }
                item.setCount(count - 1);
                viewHolder.mCount.setText(String.valueOf(item.getCount()));
                if (mListener != null) {
                    mListener.onClickCount(true);
                }
            }
        });
        viewHolder.mInCrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = item.getCount();
                if (count >= 9999) {
                    return;
                }
                item.setCount(count + 1);
                viewHolder.mCount.setText(String.valueOf(item.getCount()));
                if (mListener != null) {
                    mListener.onClickCount(true);
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

    public int getCount(){
        int count = 0;
        for (LeaseItem item:mList) {
            count += item.getCount();
        }
        return count;
    }

    public float getValue() {
        float value = 0.0f;
        for (LeaseItem item:mList) {
            value += item.getCount() * item.getPrice();
        }
        return value;
    }

    public void setCountListener(CountListener listener) {
        mListener = listener;
    }



    public List<LeaseItem> filterList() {
        List<LeaseItem> list = new ArrayList<>();
        for (LeaseItem item : mList) {
            if (item.getCount() > 0) {
                list.add(item);
            }
        }
        return list;
    }
}
