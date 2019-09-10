package com.gz0101.hzwy.baselibrary.base;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gz0101.hzwy.baselibrary.BuildConfig;
import com.gz0101.hzwy.baselibrary.cache.SpHelp;
import com.gz0101.hzwy.baselibrary.db.DbHelp;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static List<Activity> activityList;
    public static BaseActivity mTopActivity;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        registerActivityLife();
        initRouter();
        initSp();
        DbHelp.getInstance().initDb();
    }

    private void initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void initSp() {
        SpHelp.getInstance().init();
    }

    private void registerActivityLife() {
        activityList = new ArrayList<>();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityList.remove(activity);
            }
        });
    }

    public static BaseActivity getTopActivity() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = activityList.size() - 1; i >= 0; i--) {
                if (activityList.get(i) instanceof BaseActivity) {
                    mTopActivity = (BaseActivity) activityList.get(i);
                    break;
                }
            }
        }
        return mTopActivity;
    }

    @Override
    public void onTerminate() {
        ARouter.getInstance().destroy();
        super.onTerminate();
    }
}
