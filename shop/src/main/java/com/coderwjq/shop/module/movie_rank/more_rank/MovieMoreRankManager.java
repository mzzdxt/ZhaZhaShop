package com.coderwjq.shop.module.movie_rank.more_rank;

import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;
import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/9/5 16:14.
 */

public class MovieMoreRankManager {

    public Observable<OverseaHotMovieBean> getOverseaHotMovie(String area, int limit, int offset) {
        return RetrofitClient
                .getInstance()
                .apiMovieRankService()
                .getOverseaHotMovie(area, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<OverseaComingMovieBean> getOverseaComingMovie(String are, int limit, int offset) {
        return RetrofitClient
                .getInstance()
                .apiMovieRankService()
                .getOverseaComingMovie(are, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
