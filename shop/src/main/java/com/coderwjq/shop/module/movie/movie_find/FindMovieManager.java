package com.coderwjq.shop.module.movie.movie_find;

import com.coderwjq.shop.module.movie.movie_find.model.AwardsMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.GridMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.network.RetrofitClient;
import com.coderwjq.shop.utils.TimeUtils;

import io.reactivex.Observable;

/**
 * Created by coderwjq on 2017/8/29 14:16.
 */

public class FindMovieManager {
    /**
     * @return 电影类型数据
     */
    public Observable<MovieTypeBean> getMovieTypeList() {
        return RetrofitClient.getInstance()
                .apiServer().getMovieTypeList();
    }

    public Observable<GridMovieBean> getGridMovieList() {
        return RetrofitClient.getInstance()
                .apiServer().getMovieGrid();
    }

    public Observable<AwardsMovieBean> getAwardsMovieList() {
        return RetrofitClient.getInstance()
                .apiServer().getAwardsMovieList(TimeUtils.dateYMD(System.currentTimeMillis()));
    }
}
