package com.coderwjq.shop.network;

import com.coderwjq.shop.module.movieDetail.model.MovieAwardsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieBasicDataBean;
import com.coderwjq.shop.module.movieDetail.model.MovieCommentTagBean;
import com.coderwjq.shop.module.movieDetail.model.MovieLongCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieMoneyBoxBean;
import com.coderwjq.shop.module.movieDetail.model.MovieProCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieRelatedInformationBean;
import com.coderwjq.shop.module.movieDetail.model.MovieResourceBean;
import com.coderwjq.shop.module.movieDetail.model.MovieStarBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTipsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTopicBean;
import com.coderwjq.shop.module.movieDetail.model.RelatedMovieBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by coderwjq on 2017/8/31 8:49.
 */

public interface ApiMovieDetailService {
    /**
     * 获取电影基本信息
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/v5/{movieId}.json")
    Observable<MovieBasicDataBean> getMovieBasicData(@Path("movieId") int movieId);

    /**
     * 获取电影观影提示
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/tips/{movieId}/list.json")
    Observable<MovieTipsBean> getMovieTipsBean(@Path("movieId") int movieId);

    /**
     * 获得演员列表
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/v7/movie/{movieId}/celebrities.json")
    Observable<MovieStarBean> getMovieStarList(@Path("movieId") int movieId);

    /**
     * 获取票房数据
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/feature/v1/mbox.json")
    Observable<MovieMoneyBoxBean> getMovieBox(@Path("movieId") int movieId);

    /**
     * 电影获奖情况
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/feature/awards.json")
    Observable<MovieAwardsBean> getMovieAwards(@Path("movieId") int movieId);

    /**
     * 获取影片资料
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/feature/v2/list.json")
    Observable<MovieResourceBean> getMovieResource(@Path("movieId") int movieId);

    /**
     * 获取评论标签
     *
     * @param movieId
     * @param ci      城市id,默认20,代表广东
     * @return
     */
    @GET("mmdb/comment/tag/movie/{movieId}.json")
    Observable<MovieCommentTagBean> getMovieCommentTag(@Path("movieId") int movieId,
                                                       @Query("ci") int ci);

    /**
     * 获取长评数据
     *
     * @param movieId
     * @return
     */
    @GET("sns/movie/{movieId}/filmReview/top.json")
    Observable<MovieLongCommentBean> getMovieLongComment(@Path("movieId") int movieId);

    /**
     * 获取专业评论数据
     *
     * @param movieId
     * @param offset
     * @param limit
     * @return
     */
    @GET("mmdb/comments/pro/movie/{movieId}.json")
    Observable<MovieProCommentBean> getMovieProComment(@Path("movieId") int movieId,
                                                       @Query("offset") int offset,
                                                       @Query("limit") int limit);

    /**
     * 获取相关资讯
     *
     * @param movieId
     * @return
     */
    @GET("sns/news/v3/type/0/target/{movieId}/top.json")
    Observable<MovieRelatedInformationBean> getMovieRelatedInformation(@Path("movieId") int movieId);

    /**
     * 获取相关电影
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/feature/relatedFilm.json")
    Observable<RelatedMovieBean> getRelatedMovie(@Path("movieId") int movieId);

    /**
     * 获取相关话题
     *
     * @param movieId
     * @return
     */
    @GET("sns/0/{movieId}/v2/hotTopics.json")
    Observable<MovieTopicBean> getMovieTopic(@Path("movieId") int movieId);
}
