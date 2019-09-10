package com.gz0101.hzwy.common.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.util.StatusBarUtil;
import com.gz0101.hzwy.common.Constant;
import com.gz0101.hzwy.common.R;
import com.gz0101.hzwy.common.bean.AddressListBean;
import com.gz0101.hzwy.common.contract.AddressEditContract;
import com.gz0101.hzwy.common.presenter.AddressEditPresenterImpl;

public class AddressEditActivity extends BaseActivity<AddressEditPresenterImpl> implements AddressEditContract.AddressEditView, View.OnClickListener {

    TextView titleTv;
    EditText nameEt;
    EditText phoneEt;
    EditText addressEt;
    ImageView selectAddressIv;
    EditText detailAddress;
    TextView deleteTv;
    TextView saveTv;
    Switch defaultS;
    private AddressListBean bean;

    @Override
    protected void layout() {
        setContentView(R.layout.activity_address_edit);
        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_ffffff));
    }

    @Override
    protected void onCreate() {
        initView();
        initData();
    }

    private void initData() {
        bean = (AddressListBean) getIntent().getSerializableExtra(Constant.ADDRESS_LIST_BEAN);
        boolean isAdd = getIntent().getBooleanExtra(Constant.IS_ADD, true);
        titleTv.setText(isAdd ? getResources().getString(R.string.address_edit_title_add) : getResources().getString(R.string.address_edit_title_edit));
        deleteTv.setVisibility(isAdd ? View.INVISIBLE : View.VISIBLE);
        if (!isAdd) {
            if (bean == null)
                return;
            nameEt.setText(bean.getContactName());
            phoneEt.setText(bean.getContactPhone());
            defaultS.setChecked(bean.getIsDefault() == 1);
        }
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        titleTv = findViewById(R.id.title);
        nameEt = findViewById(R.id.et_address_edit_name);
        phoneEt = findViewById(R.id.et_address_edit_phone);
        saveTv = findViewById(R.id.tv_address_edit_save);
        deleteTv = findViewById(R.id.tv_address_edit_detele);
        addressEt = findViewById(R.id.et_address_edit_address);
        detailAddress = findViewById(R.id.et_address_edit_detail);
        selectAddressIv = findViewById(R.id.iv_address_edit_select_address);

        defaultS = findViewById(R.id.s_address_edit);
        detailAddress = findViewById(R.id.et_address_edit_detail);
        saveTv.setOnClickListener(this);
        deleteTv.setOnClickListener(this);
        selectAddressIv.setOnClickListener(this);
    }

    @Override
    protected AddressEditPresenterImpl createPresenter() {
        return new AddressEditPresenterImpl();
    }

    @Override
    protected void destroy() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_address_edit_save) {
            return;
        }
        if (v.getId() == R.id.tv_address_edit_detele) {
            return;
        }
    }
}
