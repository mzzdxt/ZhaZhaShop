package com.coderwjq.shop.module.player.video_list;

import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/9/1 18:19.
 */

public class VideoListManager {
    /**
     * @param movieId
     * @param offset
     * @return 视频列表数据
     */
    public Observable<VideoListBean> getVideoList(int movieId, int offset) {
        return RetrofitClient.getInstance()
                .apiMovieVideoService()
                .getVideoList(movieId, 10, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param movieId
     * @return 视频信息数据
     */
    public Observable<VideoMovieInfoBean> getVideoMovieInfo(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieVideoService()
                .getVideoMovieInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
