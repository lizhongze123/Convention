package com.gz0101.hzwy.material.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gz0101.hzwy.material.R;
import com.gz0101.hzwy.material.adapter.listener.MaterialRightListener;
import com.gz0101.hzwy.material.bean.MaterialRightBean;
import com.gz0101.hzwy.material.holder.MaterialRightHolder;

import java.util.ArrayList;
import java.util.List;

public class MaterialRightAdapter extends RecyclerView.Adapter<MaterialRightHolder> {
    private List<MaterialRightBean> mList;
    private MaterialRightListener mListener;

    public MaterialRightAdapter() {}

    public void setList(List<MaterialRightBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MaterialRightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MaterialRightHolder(getView(parent));
    }

    @Override
    public void onBindViewHolder(@NonNull final MaterialRightHolder holder, int position) {
        final MaterialRightBean bean = mList.get(position);
        holder.setContent(bean);
        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reduceItem(holder, bean);
            }
        });
        holder.increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseItem(holder, bean);
            }
        });
    }

    private void reduceItem(MaterialRightHolder holder, MaterialRightBean bean) {
        if (bean == null) {
            return;
        }
        int count = bean.getCount();
        if (count == 0) {
            return;
        }
        bean.setCount(count - 1);
        holder.count.setText(String.valueOf(bean.getCount()));
        if (mListener != null) {
            mListener.onClickCount(true);
        }
    }

    private void increaseItem(MaterialRightHolder holder, MaterialRightBean bean) {
        if (bean == null) {
            return;
        }
        int count = bean.getCount();
        if (count > 9999) {
            return;
        }
        bean.setCount(count + 1);
        holder.count.setText(String.valueOf(bean.getCount()));
        if (mListener != null) {
            mListener.onClickCount(true);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0:mList.size();
    }

    private View getView(ViewGroup group) {
        return LayoutInflater.from(group.getContext()).inflate(R.layout.item_right_item, group, false);
    }

    public int getCount(){
        int count = 0;
        for (MaterialRightBean item:mList) {
            count += item.getCount();
        }
        return count;
    }

    public float getValue() {
        float value = 0.0f;
        for (MaterialRightBean item:mList) {
            value += item.getCount() * item.getPrice();
        }
        return value;
    }

    public void setMaterialRightListener(MaterialRightListener listener) {
        mListener = listener;
    }

    public List<MaterialRightBean> filterList() {
        List<MaterialRightBean> list = new ArrayList<>();
        for (MaterialRightBean item : mList) {
            if (item.getCount() > 0) {
                list.add(item);
            }
        }
        return list;
    }
}
