package com.coderwjq.shop.network;

import com.coderwjq.shop.module.movie_rank.expecting_rank.MostExpectMovieBean;
import com.coderwjq.shop.module.movie_rank.favourable_rank.FavourableMovieBean;

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
}
