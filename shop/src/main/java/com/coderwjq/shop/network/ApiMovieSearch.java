package com.coderwjq.shop.network;

import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.module.movie_search.model.ClassifySearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by coderwjq on 2017/9/4 14:27.
 */

public interface ApiMovieSearch {
    /**
     * 根据类型查找影片
     *
     * @return
     */
    @GET("mmdb/search/movie/tag/types.json")
    Observable<MovieTypeBean> getMovieTypeList();

    /**
     * 根据分类查找影片
     *
     * @param limit
     * @param offset
     * @param catId
     * @param sourceId
     * @param yearId
     * @param sortId
     * @return
     */
    @GET("mmdb/search/movie/tag/list.json")
    Observable<ClassifySearchBean> getClassifySearchList(@Query("limit") int limit,
                                                         @Query("offset") int offset,
                                                         @Query("catId") int catId,
                                                         @Query("sourceId") int sourceId,
                                                         @Query("yearId") int yearId,
                                                         @Query("sortId") int sortId);
}
