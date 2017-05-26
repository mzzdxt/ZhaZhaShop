package com.coderwjq.shop.module.movie.movie_hot;

import com.coderwjq.shop.base.ILoadingView;

import java.util.List;

/**
 * @Created by coderwjq on 2017/5/26 9:23.
 * @Desc
 */

public class HotMovieListContact {
    /**
     * 负责界面展示
     */
    public interface IHotMovieListView extends ILoadingView {
        void addHotMovieList(List<HotMovieBean.DataBean.HotBean> hot);

        void addMovieIds(List<Integer> movieIds);
    }

    /**
     * 负责业务逻辑
     */
    public interface IHotMoviePresenter {
        void getHotMovieList(int limit);
    }
}
