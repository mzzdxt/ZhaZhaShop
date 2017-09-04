package com.coderwjq.shop.network;

import com.coderwjq.shop.module.movie_star.model.MovieStarHonor;
import com.coderwjq.shop.module.movie_star.model.MovieStarInfoBean;
import com.coderwjq.shop.module.movie_star.model.RelatedInformationBean;
import com.coderwjq.shop.module.movie_star.model.StarMoviesBean;
import com.coderwjq.shop.module.movie_star.model.StarRelatedPeople;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by coderwjq on 2017/9/4 9:14.
 */

public interface ApiMovieStarService {

    /**
     * @param starId
     * @return 明星资料
     */
    @GET("mmdb/v6/celebrity/{starId}.json")
    Observable<MovieStarInfoBean> getMovieStarInfoBean(@Path("starId") int starId);

    /**
     * @param starId
     * @return 明星荣誉
     */
    @GET("mmdb/celebrity/{starId}/honors.json")
    Observable<MovieStarHonor> getMovieStarHonors(@Path("starId") int starId);

    /**
     * @param starId
     * @param limit
     * @param offset
     * @return 参演电影
     */
    @GET("mmdb/celebrity/{starId}/rank/movies.json")
    Observable<StarMoviesBean> getStarMovies(@Path("starId") int starId,
                                             @Query("limit") int limit,
                                             @Query("offset") int offset);

    /**
     * @param starId
     * @return 相关资讯
     */
    @GET("sns/news/v3/type/1/target/{starId}/top.json")
    Observable<RelatedInformationBean> getRelatedInformation(@Path("starId") int starId);

    /**
     * @param starId
     * @return 相关明星
     */
    @GET("mmdb/celebrity/{starId}/relationship.json")
    Observable<StarRelatedPeople> getStarRelatedPeople(@Path("starId") int starId);

}
