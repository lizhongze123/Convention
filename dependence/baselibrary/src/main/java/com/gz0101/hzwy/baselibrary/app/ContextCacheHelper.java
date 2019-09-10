package com.gz0101.hzwy.baselibrary.app;

import android.content.Context;

public final class ContextCacheHelper {
    private Context mCacheContext;

    private ContextCacheHelper(){}

    private static class Holder {
        private static final ContextCacheHelper SELF = new ContextCacheHelper();
    }

    public static ContextCacheHelper getInstance() {
        return Holder.SELF;
    }

    public void setContext(Context context) {
        if (context == null) {
            return;
        }
        mCacheContext = context.getApplicationContext();
    }

    public Context getContext() {
        return mCacheContext;
    }

    public void clear() {
        mCacheContext = null;
    }
}
