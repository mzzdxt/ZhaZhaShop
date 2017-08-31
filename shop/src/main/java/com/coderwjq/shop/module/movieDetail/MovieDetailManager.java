package com.coderwjq.shop.module.movieDetail;

import com.coderwjq.shop.module.movieDetail.model.MovieAwardsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieBasicDataBean;
import com.coderwjq.shop.module.movieDetail.model.MovieCommentTagBean;
import com.coderwjq.shop.module.movieDetail.model.MovieLongCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieMoneyBoxBean;
import com.coderwjq.shop.module.movieDetail.model.MovieProCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieRelatedInformationBean;
import com.coderwjq.shop.module.movieDetail.model.MovieResourceBean;
import com.coderwjq.shop.module.movieDetail.model.MovieStarBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTipsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTopicBean;
import com.coderwjq.shop.module.movieDetail.model.RelatedMovieBean;
import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/8/31 9:09.
 */

public class MovieDetailManager {
    /**
     * 获取电影基本信息
     *
     * @param movieId
     * @return
     */
    public Observable<MovieBasicDataBean> getMovieBasicData(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieBasicData(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取电影观影提示
     *
     * @param movieId
     * @return
     */
    Observable<MovieTipsBean> getMovieTipsBean(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieTipsBean(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获得演员列表
     *
     * @param movieId
     * @return
     */
    Observable<MovieStarBean> getMovieStarList(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieStarList(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取票房数据
     *
     * @param movieId
     * @return
     */
    Observable<MovieMoneyBoxBean> getMovieBox(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieBox(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 电影获奖情况
     *
     * @param movieId
     * @return
     */
    Observable<MovieAwardsBean> getMovieAwards(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieAwards(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取影片资料
     *
     * @param movieId
     * @return
     */
    Observable<MovieResourceBean> getMovieResource(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieResource(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取评论标签
     *
     * @param movieId ci默认20,代表广东
     * @return
     */
    Observable<MovieCommentTagBean> getMovieCommentTag(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieCommentTag(movieId, 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取长评数据
     *
     * @param movieId
     * @return
     */
    Observable<MovieLongCommentBean> getMovieLongComment(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieLongComment(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取专业评论数据
     *
     * @param movieId
     * @return 默认只获取前3条
     */
    Observable<MovieProCommentBean> getMovieProComment(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieProComment(movieId, 0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取相关资讯
     *
     * @param movieId
     * @return
     */
    Observable<MovieRelatedInformationBean> getMovieRelatedInformation(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieRelatedInformation(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取相关电影
     *
     * @param movieId
     * @return
     */
    Observable<RelatedMovieBean> getRelatedMovie(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getRelatedMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取相关话题
     *
     * @param movieId
     * @return
     */
    Observable<MovieTopicBean> getMovieTopic(int movieId) {
        return RetrofitClient.getInstance()
                .apiMovieDetailService()
                .getMovieTopic(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
