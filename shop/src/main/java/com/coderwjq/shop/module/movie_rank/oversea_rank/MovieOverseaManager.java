package com.coderwjq.shop.module.movie_rank.oversea_rank;

import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;
import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/9/5 10:02.
 */

public class MovieOverseaManager {
    public Observable<OverseaHotMovieBean> getOverseaHotMovie(String area) {
        return RetrofitClient
                .getInstance()
                .apiMovieRankService()
                .getOverseaHotMovie(area, 10, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OverseaComingMovieBean> getOverseaComingMovie(String area) {
        return RetrofitClient
                .getInstance()
                .apiMovieRankService()
                .getOverseaComingMovie(area, 10, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
