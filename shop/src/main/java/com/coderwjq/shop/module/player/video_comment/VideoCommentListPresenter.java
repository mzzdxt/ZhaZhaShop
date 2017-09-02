package com.coderwjq.shop.module.player.video_comment;

import android.app.Activity;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/9/1 18:17.
 */

public class VideoCommentListPresenter extends BasePresenter<VideoCommentListContract.IVideoCommentListView> implements VideoCommentListContract.IVideoCommentListPresenter {
    private static final String TAG = "VideoListPresenter";

    private final VideoCommentListManager mVideoCommentListManager;

    public VideoCommentListPresenter(Activity activity, VideoCommentListContract.IVideoCommentListView view) {
        super(activity, view);

        mVideoCommentListManager = new VideoCommentListManager();
    }


    @Override
    public void getVideoCommentList(int movieId, int offset) {
        mVideoCommentListManager.getVideoComment(movieId, offset)
                .subscribe(new Observer<VideoCommentListBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull VideoCommentListBean videoCommentListBean) {
                        mView.addVideoCommentList(videoCommentListBean.getData().getComments());
                        mView.addVideoCommentCount(videoCommentListBean.getData().getTotal());
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
    public void getMoreVideoComment(int movieId, int offset) {
        mVideoCommentListManager.getVideoComment(movieId, offset)
                .subscribe(new Observer<VideoCommentListBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull VideoCommentListBean videoCommentListBean) {
                        mView.addMoreVideoComment(videoCommentListBean.getData().getComments());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.loadMoreError(ErrorHanding.handleError(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
