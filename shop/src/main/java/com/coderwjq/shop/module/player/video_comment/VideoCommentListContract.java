package com.coderwjq.shop.module.player.video_comment;

import com.coderwjq.shop.base.ILoadingView;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/1 18:17.
 */

public class VideoCommentListContract {
    public interface IVideoCommentListView extends ILoadingView {
        void addVideoCommentList(List<VideoCommentListBean.DataBean.CommentsBean> comments);

        void addVideoCommentCount(int total);

        void addMoreVideoComment(List<VideoCommentListBean.DataBean.CommentsBean> comments);

        void loadMoreError(String message);
    }

    public interface IVideoCommentListPresenter {
        void getVideoCommentList(int movieId, int offset);

        void getMoreVideoComment(int movieId, int offset);
    }
}
