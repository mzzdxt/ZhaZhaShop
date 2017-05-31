package com.coderwjq.shop.module.movie.movie_wait;

import android.app.Activity;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.module.movie.movie_wait.bean.ExpectMovieBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.TrailerRecommendBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.WaitMovieBean;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Created by coderwjq on 2017/5/31 10:36.
 * @Desc
 */

public class WaitMoviePresenter extends BasePresenter<IWaitMovieView> implements IWaitMoviePresenter {

    private final WaitMovieManager mWaitMovieManager;

    public WaitMoviePresenter(Activity activity, IWaitMovieView view) {
        super(activity, view);
        mWaitMovieManager = new WaitMovieManager();
    }

    @Override
    public void getTrailerRecommendMovie() {
        mWaitMovieManager.getTrailerRecommendMovie()
                .subscribe(new Observer<TrailerRecommendBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TrailerRecommendBean trailerRecommendBean) {
                        mView.addTrailerRecommendMovieList(trailerRecommendBean.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRecentExpectMovie(int offset, int limit) {
        mWaitMovieManager.getRecentExpectMovie(offset, limit)
                .subscribe(new Observer<ExpectMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ExpectMovieBean expectMovieBean) {
                        mView.addRecentExpectMovieList(expectMovieBean.getData().getComing());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {
                        mView.showContent();
                    }
                });
    }

    @Override
    public void getWaitMovieList(int ci, int limit) {
        mWaitMovieManager.getWaitMovieList(ci, limit)
                .subscribe(new Observer<WaitMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WaitMovieBean waitMovieBean) {
                        mView.addWaitMovieList(waitMovieBean.getData().getComing());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {
                        mView.showContent();
                    }
                });
    }
}
