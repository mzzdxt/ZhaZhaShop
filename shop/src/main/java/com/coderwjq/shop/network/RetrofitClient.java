package com.coderwjq.shop.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Created by coderwjq on 2017/5/25 20:00.
 * @Desc
 */

public class RetrofitClient {
    private static RetrofitClient mInstance;
    private static Retrofit mRetrofit;

    private RetrofitClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.BASE_URL)
                .client(OkHttpManager.getInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient();
                }
            }
        }
        return mInstance;
    }

    private <T> T create(Class<T> clz) {
        return mRetrofit.create(clz);
    }

    public ApiServer apiServer() {
        return RetrofitClient.mInstance.create(ApiServer.class);
    }

    public ApiMovieDetailService apiMovieDetailService() {
        return RetrofitClient.mInstance.create(ApiMovieDetailService.class);
    }

    public ApiMovieVideoService apiMovieVideoService() {
        return RetrofitClient.mInstance.create(ApiMovieVideoService.class);
    }

    public ApiMovieStarService apiMovieStarService() {
        return RetrofitClient.mInstance.create(ApiMovieStarService.class);
    }

    public ApiMovieSearch apiMovieSearch() {
        return RetrofitClient.mInstance.create(ApiMovieSearch.class);
    }
}
