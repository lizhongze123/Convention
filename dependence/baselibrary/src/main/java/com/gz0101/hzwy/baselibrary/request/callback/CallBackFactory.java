package com.gz0101.hzwy.baselibrary.request.callback;


import com.gz0101.hzwy.baselibrary.request.respone.BaseRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallBackFactory<T extends BaseRespone> {
    private static volatile CallBackFactory instance;

    private CallBackFactory() {

    }

    public static CallBackFactory getInstance() {
        if (instance == null) {
            synchronized (CallBackFactory.class) {
                if (instance == null) {
                    instance = new CallBackFactory();
                }
            }
        }
        return instance;
    }

    public Callback<T> callback(final ICallBack<T> paramCallback) {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    paramCallback.onUnifyDispose(response.body());
                } else {
                    paramCallback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                paramCallback.onFailure(t.getMessage());
            }
        };
    }
}
