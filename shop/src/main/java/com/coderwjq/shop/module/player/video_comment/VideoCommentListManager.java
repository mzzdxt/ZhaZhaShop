package com.coderwjq.shop.module.player.video_comment;

import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/9/1 18:19.
 */

public class VideoCommentListManager {
    /**
     * @param movieId
     * @param offset
     * @return 评论数据
     */
    public Observable<VideoCommentListBean> getVideoComment(int movieId, int offset) {
        return RetrofitClient.getInstance()
                .apiMovieVideoService()
                .getVideoComment(movieId, "video", 10, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
