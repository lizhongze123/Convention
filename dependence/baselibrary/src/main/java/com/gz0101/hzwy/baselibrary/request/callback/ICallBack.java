package com.gz0101.hzwy.baselibrary.request.callback;

import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;
import com.gz0101.hzwy.baselibrary.toast.ToastHelp;

public abstract class ICallBack<T extends BaseRespone> {

    public abstract void onSuccess(T t);

    public abstract void onFailure(String str);

    public void onUnifyDispose(T t) {
        //code码统一处理

        onSuccess(t);
    }
}
