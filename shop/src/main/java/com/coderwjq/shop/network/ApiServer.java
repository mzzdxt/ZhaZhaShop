package com.coderwjq.shop.network;

import com.coderwjq.shop.module.discover.model.DiscoverBean;
import com.coderwjq.shop.module.discover.model.DiscoverHeaderBean;
import com.coderwjq.shop.module.movie.movie_find.model.AwardsMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.GridMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.module.movie.movie_hot.HotMovieBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.ExpectMovieBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.TrailerRecommendBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.WaitMovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    /**
     * 待映-预告片推荐
     *
     * @sample http://api.maoyan.com/mmdb/movie/lp/list.json
     */
    @GET("/mmdb/movie/lp/list.json")
    Observable<TrailerRecommendBean> getTrailerRecommend();

    /**
     * 待映-近期最受期待
     */
    @GET("mmdb/movie/v1/list/wish/order/coming.json")
    Observable<ExpectMovieBean> getExpectMovieList(@Query("offset") int offset,
                                                   @Query("limit") int limit);

    /**
     * 待映-列表
     *
     * @param ci
     * @param limit
     * @return
     */
    @GET("mmdb/movie/v2/list/rt/order/coming.json")
    Observable<WaitMovieBean> getWaitMovieList(@Query("ci") int ci,
                                               @Query("limit") int limit);

    /**
     * @return 电影分类
     */
    @GET("mmdb/search/movie/tag/types.json")
    Observable<MovieTypeBean> getMovieTypeList();


    /**
     * @return 找片-表格
     */
    @GET("mmdb/movieboard/fixedboard/v1/hot/list.json")
    Observable<GridMovieBean> getMovieGrid();

    /**
     * @param time
     * @return 找片-获奖
     */
    @GET("mmdb/movie/winning/film/{time}/list.json")
    Observable<AwardsMovieBean> getAwardsMovieList(@Path("time") String time);

    /**
     * @param offset
     * @param limit
     * @return 发现的内容列表
     */
    @GET("/sns/v5/feed.json")
    Observable<DiscoverBean> getDiscover(@Query("offset") int offset,
                                         @Query("limit") int limit);

    //发现列表的头部（今日Top10，影视快讯）
    //两个查询参数必须填，缺一不可，最新版猫眼的参数为utm_medium=android&utm_term=7.8.0
    //其中utm_term可以修改为其他参数，如utm_term=1
    @GET("/sns/v2/buttons.json")
    Observable<DiscoverHeaderBean> getDiscoverHeader(@Query("utm_medium") String utm_medium,
                                                     @Query("utm_term") String utm_term);
}
