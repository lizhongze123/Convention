package com.gz0101.hzwy.baselibrary.util;

import android.content.pm.PackageManager;

import com.gz0101.hzwy.baselibrary.base.BaseApplication;


public class PermissionUtil {

    public static void checkPermission(String permission, ICheckPermissionCallBack callBack) {
        boolean result = BaseApplication.getInstance().getApplicationContext().checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        if (callBack != null) {
            if (result) {
                callBack.onSuccess();
            } else {
                callBack.onFailed();
            }
        }
    }

    public static void checkPermission(String[] permissions, ICheckPermissionCallBack callBack) {
        boolean result = true;
        if (null != permissions && permissions.length > 0) {
            for (int i = 0; i < permissions.length; i++) {
                boolean permissionResult = BaseApplication.getInstance().getApplicationContext().checkCallingOrSelfPermission(permissions[i]) == PackageManager.PERMISSION_GRANTED;
                result = result & permissionResult;
            }
        }
        if (callBack != null) {
            if (result) {
                callBack.onSuccess();
            } else {
                callBack.onFailed();
            }
        }
    }

    public interface ICheckPermissionCallBack {

        void onSuccess();

        void onFailed();

    }

}
