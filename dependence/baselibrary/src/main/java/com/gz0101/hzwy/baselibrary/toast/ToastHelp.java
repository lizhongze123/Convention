package com.gz0101.hzwy.baselibrary.toast;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.gz0101.hzwy.baselibrary.base.BaseApplication;
import com.gz0101.hzwy.baselibrary.util.UnitUtil;

public class ToastHelp {

    public static void showShort(int resId) {
        String str = BaseApplication.getInstance().getResources().getString(resId);
        ToastManager.getInstance().showText(str, Toast.LENGTH_SHORT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void showShort(String str) {
        ToastManager.getInstance().showText(str, Toast.LENGTH_SHORT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void showShort(int resId, int gravity, int xOffSet, int yOffSet) {
        String str = BaseApplication.getInstance().getResources().getString(resId);
        ToastManager.getInstance().showText(str, Toast.LENGTH_SHORT, gravity, xOffSet, yOffSet);
    }

    public static void showShort(String str, int gravity, int xOffSet, int yOffSet) {
        ToastManager.getInstance().showText(str, Toast.LENGTH_SHORT, gravity, xOffSet, yOffSet);
    }

    public static void showShort(View view) {
        ToastManager.getInstance().showView(view, Toast.LENGTH_SHORT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void showShort(View view, int gravity, int xOffSet, int yOffSet) {
        ToastManager.getInstance().showView(view, Toast.LENGTH_SHORT, gravity, xOffSet, yOffSet);
    }

    public static void showLong(int resId) {
        String str = BaseApplication.getInstance().getResources().getString(resId);
        ToastManager.getInstance().showText(str, Toast.LENGTH_LONG, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void showLong(String str) {
        ToastManager.getInstance().showText(str, Toast.LENGTH_LONG, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void showLong(int resId, int gravity, int xOffSet, int yOffSet) {
        String str = BaseApplication.getInstance().getResources().getString(resId);
        ToastManager.getInstance().showText(str, Toast.LENGTH_LONG, gravity, xOffSet, yOffSet);
    }

    public static void showLong(String str, int gravity, int xOffSet, int yOffSet) {
        ToastManager.getInstance().showText(str, Toast.LENGTH_LONG, gravity, xOffSet, yOffSet);
    }

    public static void showLong(View view) {
        ToastManager.getInstance().showView(view, Toast.LENGTH_LONG, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void showLong(View view, int gravity, int xOffSet, int yOffSet) {
        ToastManager.getInstance().showView(view, Toast.LENGTH_LONG, gravity, xOffSet, yOffSet);
    }

    public static void show(int resId, int duration) {
        String str = BaseApplication.getInstance().getResources().getString(resId);
        ToastManager.getInstance().showText(str, duration, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void show(int resId, int duration, int gravity, int xOffSet, int yOffSet) {
        String str = BaseApplication.getInstance().getResources().getString(resId);
        ToastManager.getInstance().showText(str, duration, gravity, xOffSet, yOffSet);
    }

    public static void show(String str, int duration) {
        ToastManager.getInstance().showText(str, duration, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void show(String str, int duration, int gravity, int xOffSet, int yOffSet) {
        ToastManager.getInstance().showText(str, duration, gravity, xOffSet, yOffSet);
    }

    public static void show(View view, int duration) {
        ToastManager.getInstance().showView(view, duration, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int) UnitUtil.dp2px(50f));
    }

    public static void show(View view, int duration, int gravity, int xOffSet, int yOffSet) {
        ToastManager.getInstance().showView(view, duration, gravity, xOffSet, yOffSet);
    }

    public static void cancel() {
        ToastManager.getInstance().cancel();
    }

}
