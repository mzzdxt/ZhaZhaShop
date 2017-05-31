package com.coderwjq.shop.module.movie.movie_wait;

/**
 * @Created by coderwjq on 2017/5/31 10:38.
 * @Desc
 */

public interface IWaitMoviePresenter {
    /**
     * 获得推荐电影的预告片列表数据
     */
    void getTrailerRecommendMovie();

    /**
     * 获取最近期望值最高的电影列表
     *
     * @param offset
     * @param limit
     */
    void getRecentExpectMovie(int offset, int limit);

    /**
     * 获取待映的列表数据
     *
     * @param ci
     * @param limit
     */
    void getWaitMovieList(int ci, int limit);
}
