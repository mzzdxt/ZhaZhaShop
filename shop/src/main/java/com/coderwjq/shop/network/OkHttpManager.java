package com.coderwjq.shop.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @Created by coderwjq on 2017/5/25 20:00.
 * @Desc
 */

public class OkHttpManager {
    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getInstance() {
        if (mOkHttpClient == null) {
            synchronized (OkHttpManager.class) {
                if (mOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                    // 设置超时时间
                    builder.connectTimeout(15, TimeUnit.SECONDS); // 15S连接超时
                    builder.readTimeout(20, TimeUnit.SECONDS); // 20s读取超时
                    builder.writeTimeout(20, TimeUnit.SECONDS); // 20s写入超时
                    // 错误重连
                    builder.retryOnConnectionFailure(true);
                    mOkHttpClient = builder.build();
                }
            }
        }
        return mOkHttpClient;
    }
}
