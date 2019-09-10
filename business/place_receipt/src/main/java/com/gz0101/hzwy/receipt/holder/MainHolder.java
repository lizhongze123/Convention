package com.gz0101.hzwy.receipt.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gz0101.hzwy.receipt.R;
import com.gz0101.hzwy.receipt.bean.PlaceItem;

public class MainHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView phone;
    private TextView location;
    private TextView defaultLoc;
    public ImageView edit;

    public MainHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        phone = itemView.findViewById(R.id.tel);
        location = itemView.findViewById(R.id.loc);
        edit = itemView.findViewById(R.id.edit);
        defaultLoc = itemView.findViewById(R.id.default_loc);
    }

    public void setContent(PlaceItem item) {
        if (item == null) {
            return;
        }
        name.setText(item.getName() == null ? "" : item.getName());
        phone.setText(item.getPhone() == null ? "" : item.getPhone());
        String text = item.getProvince() + item.getCity()
                + item.getTown() + item.getStreet() + item.getArea();
        location.setText(text.trim());
        defaultLoc.setVisibility(item.isDefault() ? View.VISIBLE : View.GONE);
    }
}
