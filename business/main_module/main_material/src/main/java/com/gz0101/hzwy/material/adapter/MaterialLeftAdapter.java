package com.gz0101.hzwy.material.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gz0101.hzwy.material.R;
import com.gz0101.hzwy.material.adapter.listener.MaterialLeftCheckListener;
import com.gz0101.hzwy.material.bean.MaterialLeftBean;
import com.gz0101.hzwy.material.holder.MaterialLeftHolder;

import java.util.List;

public class MaterialLeftAdapter extends RecyclerView.Adapter<MaterialLeftHolder> {
    private List<MaterialLeftBean> mList;
    private MaterialLeftCheckListener mListener;

    public void setList(List<MaterialLeftBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MaterialLeftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MaterialLeftHolder(getView(parent));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialLeftHolder holder, final int position) {
        final MaterialLeftBean bean = mList.get(position);
        holder.setName(bean.getName());
        holder.setCheckStatus(bean.isChecked());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCheck(position, bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null?0:mList.size();
    }

    private View getView(ViewGroup group) {
        return LayoutInflater.from(group.getContext()).inflate(R.layout.item_material_left, group, false);
    }

    public void setMaterialLeftCheckListener(MaterialLeftCheckListener listener) {
        mListener = listener;
    }
}
