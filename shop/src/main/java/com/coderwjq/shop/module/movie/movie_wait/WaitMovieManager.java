package com.coderwjq.shop.module.movie.movie_wait;

import com.coderwjq.shop.module.movie.movie_wait.bean.ExpectMovieBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.TrailerRecommendBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.WaitMovieBean;
import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Created by coderwjq on 2017/5/31 10:51.
 * @Desc
 */

public class WaitMovieManager {
    /**
     * 预告片推荐
     *
     * @return
     */
    public Observable<TrailerRecommendBean> getTrailerRecommendMovie() {
        return RetrofitClient
                .getInstance()
                .apiServer()
                .getTrailerRecommend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 最近最受期待
     *
     * @param offset
     * @param limit
     * @return
     */
    public Observable<ExpectMovieBean> getRecentExpectMovie(int offset, int limit) {
        return RetrofitClient
                .getInstance()
                .apiServer()
                .getExpectMovieList(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 待映列表数据
     *
     * @param ci
     * @param limit
     * @return
     */
    public Observable<WaitMovieBean> getWaitMovieList(int ci, int limit) {
        return RetrofitClient
                .getInstance()
                .apiServer()
                .getWaitMovieList(ci, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
