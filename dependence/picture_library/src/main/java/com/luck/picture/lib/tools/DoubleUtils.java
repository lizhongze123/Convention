package com.luck.picture.lib.tools;

import android.os.SystemClock;

/**
 * author：luck
 * project：PictureSelector
 * package：com.luck.picture.lib.tool
 * email：893855882@qq.com
 * data：2017/5/25
 */

public class DoubleUtils {
    /**
     * Prevent continuous click, jump two pages
     */
    private static long lastClickTime;
    private final static long TIME = 800;

    public static boolean isFastDoubleClick() {
        long time1 = System.currentTimeMillis();//获取的是系统时间，是距离1970年1月1日开始计算的一个值
        long time2 = SystemClock.currentThreadTimeMillis();//当前线程运行了多少时间
        long time = android.os.SystemClock.elapsedRealtime();//系统启动后过了多长时间,包含休眠时间
        if (time - lastClickTime < TIME) {
            //lastClickTime = time;
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
