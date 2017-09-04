package com.coderwjq.shop.module.movie_star;

import android.app.Activity;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.module.movie_star.model.MovieStarHonor;
import com.coderwjq.shop.module.movie_star.model.MovieStarInfoBean;
import com.coderwjq.shop.module.movie_star.model.RelatedInformationBean;
import com.coderwjq.shop.module.movie_star.model.StarMoviesBean;
import com.coderwjq.shop.module.movie_star.model.StarRelatedPeople;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/9/4 9:14.
 */

public class MovieStarPresenter extends BasePresenter<MovieStarContract.IMovieStarView> implements MovieStarContract.IMovieStarPresenter {

    private final MovieStarManager mManager;

    public MovieStarPresenter(Activity activity, MovieStarContract.IMovieStarView view) {
        super(activity, view);

        mManager = new MovieStarManager();
    }

    @Override
    public void getMovieStarInfo(int starId) {
        Observable.merge(mManager.getMovieStarInfoBean(starId),
                mManager.getMovieStarHonor(starId),
                mManager.getStarMovies(starId),
                mManager.getRelatedInformation(starId))
                .mergeWith(mManager.getStarRelatedPeople(starId))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof MovieStarInfoBean) {
                            // 明星资料
                            mView.addMovieStarInfo(((MovieStarInfoBean) o).getData());
                        }

                        if (o instanceof MovieStarHonor) {
                            // 明星荣誉
                            mView.addMovieStarHonor((MovieStarHonor) o);
                        }

                        if (o instanceof StarMoviesBean) {
                            // 参演电影
                            mView.addStarMovie(((StarMoviesBean) o).getData());
                        }

                        if (o instanceof RelatedInformationBean) {
                            // 相关资讯
                            mView.addRelatedInfo((RelatedInformationBean) o);
                        }

                        if (o instanceof StarRelatedPeople) {
                            // 相关影人
                            mView.addRelatedPeople((StarRelatedPeople) o);
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
}
