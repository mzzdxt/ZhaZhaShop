package com.coderwjq.shop.module.movie.movie_wait;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movie.movie_wait.bean.ExpectMovieBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.TrailerRecommendBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.WaitMovieBean;

import java.util.List;

/**
 * @Created by coderwjq on 2017/5/31 10:38.
 * @Desc
 */

public interface IWaitMovieView extends ILoadingView {
    void addTrailerRecommendMovieList(List<TrailerRecommendBean.DataBean> data);

    void addRecentExpectMovieList(List<ExpectMovieBean.DataBean.ComingBean> coming);

    void addWaitMovieList(List<WaitMovieBean.DataBean.ComingBean> coming);
}
