package com.coderwjq.shop.network;

import com.coderwjq.shop.module.movie.movie_hot.HotMovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Created by coderwjq on 2017/5/25 20:09.
 * @Desc
 */

public interface ApiServer {
    String BASE_URL = "http://api.maoyan.com";

    /**
     * @param ci    未知，默认为20
     * @param limit 每页返回的数据条目数
     * @desc 获取首页热映列表
     * @sample http://api.maoyan.com/mmdb/movie/v3/list/hot.json?ci=20&limit=20
     */
    @GET("/mmdb/movie/v3/list/hot.json")
    Observable<HotMovieBean> getHotMovieList(@Query("ci") int ci, @Query("limit") int limit);
}
