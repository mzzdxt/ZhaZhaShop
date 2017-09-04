package com.coderwjq.shop.module.movie_star;

import com.coderwjq.shop.module.movie_star.model.MovieStarHonor;
import com.coderwjq.shop.module.movie_star.model.MovieStarInfoBean;
import com.coderwjq.shop.module.movie_star.model.RelatedInformationBean;
import com.coderwjq.shop.module.movie_star.model.StarMoviesBean;
import com.coderwjq.shop.module.movie_star.model.StarRelatedPeople;
import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/9/4 9:14.
 */

public class MovieStarManager {
    public Observable<MovieStarInfoBean> getMovieStarInfoBean(int starId) {
        return RetrofitClient.getInstance()
                .apiMovieStarService()
                .getMovieStarInfoBean(starId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StarMoviesBean> getStarMovies(int starId) {
        return RetrofitClient.getInstance()
                .apiMovieStarService()
                .getStarMovies(starId, 20, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MovieStarHonor> getMovieStarHonor(int starId) {
        return RetrofitClient.getInstance()
                .apiMovieStarService()
                .getMovieStarHonors(starId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<RelatedInformationBean> getRelatedInformation(int starId) {
        return RetrofitClient.getInstance()
                .apiMovieStarService()
                .getRelatedInformation(starId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StarRelatedPeople> getStarRelatedPeople(int starId) {
        return RetrofitClient.getInstance()
                .apiMovieStarService()
                .getStarRelatedPeople(starId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
