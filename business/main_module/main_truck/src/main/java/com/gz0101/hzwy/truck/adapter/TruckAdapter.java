package com.gz0101.hzwy.truck.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gz0101.hzwy.truck.R;
import com.gz0101.hzwy.truck.activity.TruckDetailActivity;
import com.gz0101.hzwy.truck.bean.TruckItem;
import com.gz0101.hzwy.truck.constants.TruckConstant;
import com.gz0101.hzwy.truck.holder.TruckHolder;

import java.util.List;

public class TruckAdapter extends RecyclerView.Adapter<TruckHolder> {
    static final int TYPE_TRUCK = 0x01;
    private Context mContext;
    private List<TruckItem> mList;

    public TruckAdapter(Context context) {
        if (!(context instanceof Activity)) {
            throw new IllegalStateException();
        }
        mContext = context;
    }

    public void setTruckListData(List<TruckItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TruckHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TRUCK:
                return new TruckHolder(getView(parent, R.layout.truck_item_holder));
            default:
                return new TruckHolder(getView(parent, R.layout.truck_item_holder));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final TruckHolder holder, int position) {
        final TruckItem truckItem = mList.get(position);
        holder.setTruckItem(truckItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTruckDetail(truckItem, holder);
            }
        });
    }

    private void openTruckDetail(TruckItem truckItem, TruckHolder holder) {

        Intent intent = new Intent(mContext, TruckDetailActivity.class);
        intent.putExtra(TruckConstant.KEY_DETAIL, truckItem);
        if (Build.VERSION.SDK_INT >= 21) {
            Pair imageView = new Pair<>(holder.getImageView(), ViewCompat.getTransitionName(holder.getImageView()));
            Pair nameText = new Pair<>(holder.getNameText(), ViewCompat.getTransitionName(holder.getNameText()));
            Pair typeText = new Pair<>(holder.getTruckType(), ViewCompat.getTransitionName(holder.getTruckType()));
            mContext.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, imageView, nameText, typeText).toBundle());
        } else {
            mContext.startActivity(intent);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_TRUCK;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0:mList.size();
    }

    private View getView(ViewGroup parent, int resID) {
        if (parent == null) {
            return null;
        }
        return LayoutInflater.from(mContext).inflate(resID, parent, false);
    }

    public void search(String content) {

    }

}
