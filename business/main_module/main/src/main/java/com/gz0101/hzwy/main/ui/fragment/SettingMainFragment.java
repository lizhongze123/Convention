package com.gz0101.hzwy.main.ui.fragment;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gz0101.hzwy.baselibrary.base.BaseFragment;
import com.gz0101.hzwy.baselibrary.constance.RouterConstance;
import com.gz0101.hzwy.baselibrary.widget.SwitchView;
import com.gz0101.hzwy.main.R;
import com.gz0101.hzwy.main.contract.HomeSettingContract;
import com.gz0101.hzwy.main.presenter.HomeSettingPresenterImpl;

public class SettingMainFragment extends BaseFragment<HomeSettingPresenterImpl> implements HomeSettingContract.HomeSettingView,View.OnClickListener {
    private static final String TAG="SettingMainFragment";

    View taskView;
    private SwitchView mSwitchView;
    private ImageView ivAvatar;
    private LinearLayout llData,llGoods,llCar,llAddress,llBankCard,llSetting;

    @Override
    protected HomeSettingPresenterImpl createPresenter() {
        return new HomeSettingPresenterImpl();
    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container) {
        taskView = inflater.inflate(R.layout.fragment_setting_main,null);
        initView();
        return taskView;
    }

    private void initView() {
        ivAvatar = taskView.findViewById(R.id.iv_avatar);
        mSwitchView = taskView.findViewById(R.id.switch_view);
        llData = taskView.findViewById(R.id.ll_data);
        llGoods = taskView.findViewById(R.id.ll_goods);
        llCar = taskView.findViewById(R.id.ll_car);
        llAddress = taskView.findViewById(R.id.ll_address);
        llBankCard = taskView.findViewById(R.id.ll_bankCard);
        llSetting = taskView.findViewById(R.id.ll_setting);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mSwitchView.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent),ContextCompat.getColor(getContext(),R.color.color_70acfa));
        }
        ivAvatar.setOnClickListener(this);
        llData.setOnClickListener(this);
        llCar.setOnClickListener(this);
        llAddress.setOnClickListener(this);
        llBankCard.setOnClickListener(this);
        llSetting.setOnClickListener(this);
        String icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562434907946&di=df269b6b64c79cca65ecdd1eb280189a&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201702%2F23%2F20170223093051_Jy8XP.jpeg";
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(ivAvatar.getContext()).load(icon).apply(requestOptions).into(ivAvatar);
    }

    @Override
    public void loadData() {

    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_avatar) {
            ARouter.getInstance().build(RouterConstance.ACTIVITY_URL_TAKE_PICTURE).navigation();
            return;
        }
        if (view.getId() == R.id.ll_data) {

            return;
        }
        if (view.getId() == R.id.ll_goods) {

            return;
        }
        if (view.getId() == R.id.ll_car) {

            return;
        }
        if (view.getId() == R.id.ll_address) {

            return;
        }
        if (view.getId() == R.id.ll_bankCard) {

            return;
        }
        if (view.getId() == R.id.ll_setting) {

            return;
        }
    }
}
