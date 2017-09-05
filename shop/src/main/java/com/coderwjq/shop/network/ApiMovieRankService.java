package com.coderwjq.shop.network;

import com.coderwjq.shop.module.movie_rank.expecting_rank.MostExpectMovieBean;
import com.coderwjq.shop.module.movie_rank.favourable_rank.FavourableMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;
import com.coderwjq.shop.module.movie_rank.top100_rank.TopHundredMovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by coderwjq on 2017/9/4 17:15.
 */

public interface ApiMovieRankService {
    @GET("mmdb/movieboard/fixedboard/7.json")
    Observable<FavourableMovieBean> getHotGoodCommentMovie(@Query("limit") int limit,
                                                           @Query("offset") int offset);

    @GET("mmdb/movieboard/fixedboard/6.json")
    Observable<MostExpectMovieBean> getMostExpectMovie(@Query("limit") int limit,
                                                       @Query("offset") int offset);

    @GET("mmdb/movieboard/fixedboard/4.json")
    Observable<TopHundredMovieBean> getTopHundredMovie(@Query("limit") int limit,
                                                       @Query("offset") int offset);

    /**
     * 海外热映电影
     *
     * @param area
     * @param limit
     * @param offset
     * @return
     */
    @GET("mmdb/movie/oversea/hot.json")
    Observable<OverseaHotMovieBean> getOverseaHotMovie(@Query("area") String area,
                                                       @Query("limit") int limit,
                                                       @Query("offset") int offset);

    /**
     * 海外待映电影
     *
     * @param area
     * @param limit
     * @param offset
     * @return
     */
    @GET("mmdb/movie/oversea/coming.json")
    Observable<OverseaComingMovieBean> getOverseaComingMovie(@Query("area") String area,
                                                             @Query("limit") int limit,
                                                             @Query("offset") int offset);
}
