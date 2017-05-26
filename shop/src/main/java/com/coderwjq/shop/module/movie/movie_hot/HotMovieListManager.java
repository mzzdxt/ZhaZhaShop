package com.coderwjq.shop.module.movie.movie_hot;

import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Created by coderwjq on 2017/5/25 19:55.
 * @Desc
 */

public class HotMovieListManager {

    /**
     * 获取热门电影列表
     *
     * @param limit
     * @return
     */
    public Observable<HotMovieBean> getHotMovieList(int limit) {
        return RetrofitClient
                .getInstance()
                .apiServer()
                .getHotMovieList(20, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
