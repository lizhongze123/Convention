package com.gz0101.hzwy.city.picker.Interface;

import com.gz0101.hzwy.city.picker.bean.CityBean;
import com.gz0101.hzwy.city.picker.bean.DistrictBean;
import com.gz0101.hzwy.city.picker.bean.ProvinceBean;

public abstract class OnCityItemClickListener {
    
    /**
     * 当选择省市区三级选择器时，需要覆盖此方法
     * @param province
     * @param city
     * @param district
     */
    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
        
    }
    
    /**
     * 取消
     */
    public void onCancel() {
        
    }
}
