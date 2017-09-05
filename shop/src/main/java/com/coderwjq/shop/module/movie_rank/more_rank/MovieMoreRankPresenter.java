package com.coderwjq.shop.module.movie_rank.more_rank;

import android.app.Activity;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/9/5 16:14.
 */

public class MovieMoreRankPresenter extends BasePresenter<MovieMoreRankContract.IMovieMoreRankView> implements MovieMoreRankContract.IMovieMoreRankPresenter {

    private final MovieMoreRankManager mManager;

    public MovieMoreRankPresenter(Activity activity, MovieMoreRankContract.IMovieMoreRankView view) {
        super(activity, view);
        mManager = new MovieMoreRankManager();
    }

    @Override
    public void getOverseaHotMovieList(String area, int limit, int offset) {
        mManager.getOverseaHotMovie(area, limit, offset)
                .subscribe(new Observer<OverseaHotMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull OverseaHotMovieBean data) {
                        mView.addOverseaHotMovieList(data.getData().getHot());
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
    public void getOverseaComingMovieList(String area, int limit, int offset) {
        mManager.getOverseaComingMovie(area, limit, offset)
                .subscribe(new Observer<OverseaComingMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull OverseaComingMovieBean data) {
                        mView.addOverseaComingMovieList(data.getData().getComing());
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
