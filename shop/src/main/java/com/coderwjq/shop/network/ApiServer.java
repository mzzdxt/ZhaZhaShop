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
     * 获取首页热映列表
     *
     * @param ci    未知，默认为20
     * @param limit 每页返回的数据条目数
     * @sample http://api.maoyan.com/mmdb/movie/v3/list/hot.json?ci=20&limit=20
     */
    @GET("/mmdb/movie/v3/list/hot.json")
    Observable<HotMovieBean> getHotMovieList(@Query("ci") int ci,
                                             @Query("limit") int limit);

    /**
     * 获取首页热映列表加载更多的数据
     *
     * @param ci
     * @param headline
     * @param movieIds
     * @return
     * @sample http://api.maoyan.com/mmdb/movie/list/info.json?ci=20&headline=0&movieIds=345719,248818,346116,38977,248683,1205521,345992,248700,346648,346103,672114,633
     */
    @GET("/mmdb/movie/list/info.json")
    Observable<HotMovieBean> getMoreHotMovieList(@Query("ci") int ci,
                                                 @Query("headline") int headline,
                                                 @Query("movieIds") String movieIds);
}
