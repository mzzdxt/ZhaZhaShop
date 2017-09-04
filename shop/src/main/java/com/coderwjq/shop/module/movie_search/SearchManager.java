package com.coderwjq.shop.module.movie_search;

import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.module.movie_search.model.ClassifySearchBean;
import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by coderwjq on 2017/9/4 14:24.
 */

public class SearchManager {
    public Observable<MovieTypeBean> getMovieTypeList() {
        return RetrofitClient
                .getInstance()
                .apiMovieSearch()
                .getMovieTypeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ClassifySearchBean> getClassifySearchList(int offset, int catId, int sourceId, int yearId, int sortId) {
        return RetrofitClient
                .getInstance()
                .apiMovieSearch()
                .getClassifySearchList(10, offset, catId, sourceId, yearId, sortId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
