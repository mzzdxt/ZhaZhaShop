package com.coderwjq.shop.network;

import com.coderwjq.shop.module.player.video_comment.VideoCommentListBean;
import com.coderwjq.shop.module.player.video_list.VideoListBean;
import com.coderwjq.shop.module.player.video_list.VideoMovieInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by coderwjq on 2017/9/1 18:12.
 */

public interface ApiMovieVideoService {
    /**
     * 视频预告片
     *
     * @param movieId
     * @param limit
     * @param offset
     * @return
     */
    @GET("mmdb/v1/movie/{movieId}/videos.json")
    Observable<VideoListBean> getVideoList(@Path("movieId") int movieId,
                                           @Query("limit") int limit,
                                           @Query("offset") int offset);

    /**
     * 视频信息
     *
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/videos/movieInfo.json")
    Observable<VideoMovieInfoBean> getVideoMovieInfo(@Path("movieId") int movieId);

    /**
     * 视频评论
     *
     * @param movieId
     * @param feature
     * @param limit
     * @param offset
     * @return
     */
    @GET("mmdb/comments/feature/v2/{movieId}.json")
    Observable<VideoCommentListBean> getVideoComment(@Path("movieId") int movieId,
                                                     @Query("feature") String feature,
                                                     @Query("limit") int limit,
                                                     @Query("offset") int offset);
}
