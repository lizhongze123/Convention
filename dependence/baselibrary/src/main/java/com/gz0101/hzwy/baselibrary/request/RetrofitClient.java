package com.gz0101.hzwy.baselibrary.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gz0101.hzwy.baselibrary.BuildConfig;
import com.gz0101.hzwy.baselibrary.cache.UserCache;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String baseUrl = "http://v1.haojin.org";
    private static Retrofit retrofit;
    private static final int DEFAULT_TIMEOUT = 20;
    private static Map<Class, Object> sServices = new HashMap<>();

    public static <T> T getInstance(Class<T> clazz) {
        if (retrofit == null) {
            synchronized (RetrofitClient.class) {
                if (retrofit == null) {
                    retrofit = createRetrofit();
                }
            }
        }
        if (sServices.get(clazz) == null) {
            T service = retrofit.create(clazz);
            sServices.put(clazz, service);
            return service;
        }
        return (T) sServices.get(clazz);
    }

    private static Retrofit createRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("okhttp:", "response:" + message);
                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptor());

        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit;
    }


    static class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Log.e("TAG", "url=" + chain.request().url());
            okhttp3.Request request = chain.request();
            HttpUrl httpUrl = request.url().newBuilder()
                    .build();

            request = new okhttp3.Request.Builder()
                    .method(request.method(), request.body())
                    .addHeader("x-access-sign", "585c84aca67b58e000364309783e31e6")
                    .addHeader("Authorization", UserCache.getInstance().getToken())
                    .url(httpUrl)
                    .build();
            return chain.proceed(request);
        }
    }

}
