package com.coderwjq.shop.module.player.video_list;

import android.app.Activity;
import android.util.Log;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/9/1 18:17.
 */

public class VideoListPresenter extends BasePresenter<VideoListContract.IVideoListView> implements VideoListContract.IVideoListPresenter {
    private static final String TAG = "VideoListPresenter";

    private final VideoListManager mVideoListManager;

    public VideoListPresenter(Activity activity, VideoListContract.IVideoListView view) {
        super(activity, view);

        mVideoListManager = new VideoListManager();
    }

    @Override
    public void getMovieList(int movieId, int offset) {
        mVideoListManager.getVideoList(movieId, offset)
                .subscribe(new Observer<VideoListBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull VideoListBean videoListBean) {
                        Log.d(TAG, "onNext() called with: videoListBean = [" + videoListBean + "]");
                        mView.addVideoList(videoListBean.getData());
                        mView.addTotalCount(videoListBean.getPaging().getTotal());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError() called with: e = [" + e + "]");
                        mView.showError(ErrorHanding.handleError(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete() called");
                        mView.showContent();
                    }
                });
    }

    @Override
    public void getVideoMovieInfo(int movieId) {
        mVideoListManager.getVideoMovieInfo(movieId)
                .subscribe(new Observer<VideoMovieInfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull VideoMovieInfoBean videoMovieInfoBean) {
                        mView.addVideoMovieInfo(videoMovieInfoBean.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showError(ErrorHanding.handleError(e));
                    }

                    @Override
                    public void onComplete() {
                        mView.showContent();
                    }
                });
    }

    @Override
    public void getMoreVideoList(int movieId, int offset) {
        mVideoListManager.getVideoList(movieId, offset)
                .subscribe(new Observer<VideoListBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull VideoListBean videoListBean) {
                        mView.addVideoMoreList(videoListBean.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showLoadMoreError(ErrorHanding.handleError(e));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
