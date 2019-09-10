package com.gz0101.hzwy.baselibrary.toast;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.gz0101.hzwy.baselibrary.base.BaseApplication;


public class ToastManager {

    private static volatile ToastManager instance;

    private ToastManager() {

    }

    public static ToastManager getInstance() {
        if (instance == null) {
            synchronized (ToastManager.class) {
                if (instance == null) {
                    instance = new ToastManager();
                }
            }
        }
        return instance;
    }

    private Toast toast;

    public void showText(String str, int duration, int gravity, int xOffset, int yOffset) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), "", duration);
        }
        toast.setText(str);
        toast.setDuration(duration);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }

    public void showView(View view, int duration, int gravity, int xOffset, int yOffset) {
        if (view == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), "", duration);
        }
        toast.setView(view);
        toast.setDuration(duration);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.show();
    }

    public void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
