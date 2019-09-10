package com.gz0101.hzwy.common.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz0101.hzwy.common.R;
import com.gz0101.hzwy.common.bean.AddressListBean;

import java.util.List;


public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.Holder> {

    private Context context;
    private List<AddressListBean> datas;
    private OnClickEditListener listener;

    public AddressListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<AddressListBean> datas) {
        if (datas != null) {
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

    public void setOnClickEditListener(OnClickEditListener onClickEditListener) {
        this.listener = onClickEditListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.item_address_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final AddressListBean bean = datas.get(position);
        holder.name.setText(bean.getContactName());
        holder.phone.setText(bean.getContactPhone());
        holder.isDefault.setVisibility(bean.getIsDefault() == 1 ? View.VISIBLE : View.INVISIBLE);
        holder.address.setText(bean.getContactAddress());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClickEdit(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.datas == null ? 0 : datas.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone;
        TextView isDefault;
        TextView address;
        ImageView edit;

        public Holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_address_list_item_name);
            phone = itemView.findViewById(R.id.tv_address_list_item_phone);
            isDefault = itemView.findViewById(R.id.tv_address_list_item_default);
            address = itemView.findViewById(R.id.tv_address_list_item_address);
            edit = itemView.findViewById(R.id.iv_address_list_item_edit);
        }
    }


    public interface OnClickEditListener {
        void onClickEdit(AddressListBean bean);
    }
}
