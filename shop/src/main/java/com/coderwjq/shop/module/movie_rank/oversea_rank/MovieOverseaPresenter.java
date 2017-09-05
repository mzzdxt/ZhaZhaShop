package com.coderwjq.shop.module.movie_rank.oversea_rank;

import android.app.Activity;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/9/5 10:02.
 */

public class MovieOverseaPresenter extends BasePresenter<MovieOverseaContract.IMovieOverseaView> implements MovieOverseaContract.IMovieOverseaPresenter {

    private final MovieOverseaManager mManager;

    public MovieOverseaPresenter(Activity activity, MovieOverseaContract.IMovieOverseaView view) {
        super(activity, view);
        mManager = new MovieOverseaManager();
    }

    @Override
    public void getOverseaMovie(String area) {
        Observable.merge(mManager.getOverseaComingMovie(area),
                mManager.getOverseaHotMovie(area))
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof OverseaHotMovieBean) {
                            mView.addOverseaHotMovie(((OverseaHotMovieBean) o).getData().getHot());
                        }

                        if (o instanceof OverseaComingMovieBean) {
                            mView.addOverseaComingMovie(((OverseaComingMovieBean) o).getData().getComing());
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
