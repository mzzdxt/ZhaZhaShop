package com.coderwjq.shop.module.player.video_list;

import com.coderwjq.shop.base.ILoadingView;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/1 18:17.
 */

public class VideoListContract {
    public interface IVideoListView extends ILoadingView {
        void addVideoList(List<VideoListBean.DataBean> data);

        void addVideoMovieInfo(VideoMovieInfoBean.DataBean videoMovieInfoBeanData);

        void addTotalCount(int total);

        void addVideoMoreList(List<VideoListBean.DataBean> videoMoreData);

        void showLoadMoreError(String message);
    }

    public interface IVideoListPresenter {
        void getMovieList(int movieId, int offset);

        void getVideoMovieInfo(int movieId);

        void getMoreVideoList(int movieId, int offset);
    }
}
