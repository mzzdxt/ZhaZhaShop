package com.coderwjq.shop.module.movieDetail;

import android.app.Activity;
import android.util.Log;

import com.coderwjq.shop.base.BasePresenter;
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
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/8/31 9:07.
 */

public class MovieDetailPresenter extends BasePresenter<MovieDetailContract.IMovieDetailView> implements MovieDetailContract.IMovieDetailPresenter {
    private static final String TAG = "MovieDetailPresenter";

    private MovieDetailManager mMovieDetailManager;

    public MovieDetailPresenter(Activity activity, MovieDetailContract.IMovieDetailView view) {
        super(activity, view);
        mMovieDetailManager = new MovieDetailManager();
    }


    @Override
    public void getMovieBasicData(int movieId) {
        // merge最多支持4个
        Observable.merge(
                mMovieDetailManager.getMovieBasicData(movieId),
                mMovieDetailManager.getMovieTipsBean(movieId),
                mMovieDetailManager.getMovieStarList(movieId),
                mMovieDetailManager.getMovieBox(movieId))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof MovieBasicDataBean) {
                            mView.addMovieBasicData(((MovieBasicDataBean) o).getData().getMovie());
                        }

                        if (o instanceof MovieTipsBean) {
                            mView.addMovieTips(((MovieTipsBean) o).getData());
                        }

                        if (o instanceof MovieStarBean) {
                            mView.addMovieStarList((MovieStarBean) o);
                        }

                        if (o instanceof MovieMoneyBoxBean) {
                            mView.addMovieMoneyBox((MovieMoneyBoxBean) o);
                        }
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
    public void getMovieData(int movieId) {
        Observable.merge(
                mMovieDetailManager.getMovieAwards(movieId),
                mMovieDetailManager.getMovieResource(movieId),
                mMovieDetailManager.getMovieCommentTag(movieId),
                mMovieDetailManager.getMovieLongComment(movieId))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof MovieAwardsBean) {
                            mView.addMovieAwards(((MovieAwardsBean) o).getData());
                        }

                        if (o instanceof MovieResourceBean) {
                            mView.addMovieResource(((MovieResourceBean) o).getData());
                        }

                        if (o instanceof MovieCommentTagBean) {
                            mView.addMovieCommentTag(((MovieCommentTagBean) o).getData());
                        }

                        if (o instanceof MovieLongCommentBean) {
                            mView.addMovieLongComment(((MovieLongCommentBean) o).getData());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getMovieMoreData(int movieId) {
        Observable.merge(
                mMovieDetailManager.getMovieProComment(movieId),
                mMovieDetailManager.getMovieRelatedInformation(movieId),
                mMovieDetailManager.getRelatedMovie(movieId),
                mMovieDetailManager.getMovieTopic(movieId))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof MovieProCommentBean) {
                            mView.addMovieProComment((MovieProCommentBean) o);
                        }

                        if (o instanceof MovieRelatedInformationBean) {
                            mView.addMovieRelatedInformation(((MovieRelatedInformationBean) o).getData().getNewsList());
                        }

                        if (o instanceof RelatedMovieBean) {
                            mView.addRelatedMovie(((RelatedMovieBean) o).getData());
                        }

                        if (o instanceof MovieTopicBean) {
                            mView.addMovieTopic(((MovieTopicBean) o).getData());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
