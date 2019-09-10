package com.gz0101.hzwy.receipt.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gz0101.hzwy.baselibrary.base.BaseActivity;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;
import com.gz0101.hzwy.baselibrary.widget.SwitchView;
import com.gz0101.hzwy.city.picker.Interface.OnCityItemClickListener;
import com.gz0101.hzwy.city.picker.bean.CityBean;
import com.gz0101.hzwy.city.picker.bean.DistrictBean;
import com.gz0101.hzwy.city.picker.bean.ProvinceBean;
import com.gz0101.hzwy.city.picker.style.cityjd.JDCityConfig;
import com.gz0101.hzwy.city.picker.style.cityjd.JDCityPicker;
import com.gz0101.hzwy.receipt.AddressHelper;
import com.gz0101.hzwy.receipt.R;
import com.gz0101.hzwy.receipt.bean.PlaceItem;
import com.gz0101.hzwy.receipt.constant.PlaceConstant;
import com.gz0101.hzwy.receipt.presenter.AddNewPresenterImpl;

public class AddNewPlaceActivity extends BaseActivity<AddNewPresenterImpl> implements View.OnClickListener {
    private EditText mName;
    private EditText mPhone;
    private TextView mArea;
    private EditText mDetail;
    private PlaceItem mItem;
    private SwitchView mSwitchView;
    private JDCityPicker mCityPicker;
    private String mProvince = "";
    private String mCity = "";
    private String mTown = "";
    private String mStreet = "";

    @Override
    protected void layout() {
        setContentView(R.layout.activity_add_new);
    }

    @Override
    protected void onCreate() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        mSwitchView = findViewById(R.id.switch_view);
        mName = findViewById(R.id.name);
        mPhone = findViewById(R.id.tel);
        mArea = findViewById(R.id.area);
        mDetail = findViewById(R.id.detail);
        mCityPicker = new JDCityPicker();
        mItem = getIntent().getParcelableExtra(PlaceConstant.KEY_PLACE);
        int res = mItem == null ? R.string.add_new_place : R.string.modify_place;
        findViewById(R.id.btn).setVisibility(mItem == null ? View.GONE : View.VISIBLE);
        TextView title = findViewById(R.id.title);
        title.setText(res);
        updateViews();
        JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();

        jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
        mCityPicker.init(this);
        mCityPicker.setConfig(jdCityConfig);
        mCityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                mProvince = province.getName();
                mCity = city.getName();
                mTown = district.getName();
                updateLocation();
            }

            @Override
            public void onCancel() {
            }
        });
    }

    private void updateLocation() {
        String location = mProvince + " " + mCity + " " + mTown + " " + mStreet;
        mArea.setText(location);
    }

    @Override
    protected AddNewPresenterImpl createPresenter() {
        return null;
    }

    @Override
    protected void destroy() {

    }

    private void updateViews() {
        mProvince = "北京市";
        mCity = "北京市";
        mTown = "海淀区";
        if (mItem == null) {
            updateLocation();
            return;
        }
        if (TextUtils.isEmpty(mItem.getProvince())) {
            mItem.setProvince(mProvince);
        } else {
            mProvince = mItem.getProvince();
        }
        if (TextUtils.isEmpty(mItem.getCity())) {
            mItem.setCity(mCity);
        } else  {
            mCity = mItem.getCity();
        }
        if (TextUtils.isEmpty(mItem.getTown())) {
            mItem.setTown(mTown);
        } else  {
            mTown = mItem.getTown();
        }
        mName.setText(mItem.getName() == null ? "" : mItem.getName());
        mPhone.setText(mItem.getPhone() == null ? "" : mItem.getPhone());
        mDetail.setText(mItem.getArea() == null ? "" : mItem.getArea());
        String location = mItem.getProvince() + " " + mItem.getCity() + " " + mItem.getTown() + " " + mItem.getStreet();
        mArea.setText(location);
        mSwitchView.setOpened(mItem.isDefault());
    }

    public void saveThisPlace(View view) {
        String name = mName.getText().toString();
        String phone = mPhone.getText().toString();
        String area = mDetail.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(mCity)
                || TextUtils.isEmpty(area) || TextUtils.isEmpty(mProvince) || TextUtils.isEmpty(mTown)) {
            ToastHelp.showShort("请填入完整的地址信息");
            return;
        }
        boolean isNewAdd = mItem == null;
        if (mItem == null) {
            mItem = new PlaceItem();
        }
        mItem.setName(name);
        mItem.setPhone(phone);
        mItem.setProvince(mProvince);
        mItem.setCity(mCity);
        mItem.setTown(mTown);
        mItem.setStreet(mStreet);
        mItem.setDefault(mSwitchView.isOpened());
        mItem.setArea(area);
        if (isNewAdd) {
            checkResult(AddressHelper.getInstance().addAddress(mItem));
        } else {
            checkResult(AddressHelper.getInstance().updateAddress(mItem));
        }
    }

    private void checkResult(boolean result) {
        if (result) {
            ToastHelp.showShort("操作成功");
            setResult(RESULT_OK);
            finish();
        } else {
            ToastHelp.showShort("添加失败");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }

    public void onClickDelete(View view) {
        AddressHelper.getInstance().deleteAddress(mItem.getId());
        ToastHelp.showShort("刪除成功！");
        finish();
    }

    public void onClickArea(View view) {
        mCityPicker.showCityPicker();
    }
}
