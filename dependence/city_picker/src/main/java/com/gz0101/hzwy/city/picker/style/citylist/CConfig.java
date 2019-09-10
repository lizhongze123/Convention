package com.gz0101.hzwy.city.picker.style.citylist;

import com.gz0101.hzwy.city.picker.style.citylist.bean.CityInfoBean;

public class CConfig {

    private static CityInfoBean sCityInfoBean;

    public static void setCity(CityInfoBean city) {
        sCityInfoBean = city;
    }

    public static CityInfoBean getCitySelected() {

        return sCityInfoBean;
    }
}
