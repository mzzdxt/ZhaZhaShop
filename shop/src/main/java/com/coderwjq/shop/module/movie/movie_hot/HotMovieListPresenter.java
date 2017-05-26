package com.coderwjq.shop.module.movie.movie_hot;

import android.app.Activity;
import android.util.Log;

import com.coderwjq.shop.base.BasePresenter;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @Created by coderwjq on 2017/5/26 9:17.
 * @Desc
 */

public class HotMovieListPresenter extends BasePresenter<HotMovieListContact.IHotMovieListView> implements HotMovieListContact.IHotMoviePresenter {
    private static final String TAG = "HotMovieListPresenter";
    private final HotMovieListManager mHotMovieListManager;

    public HotMovieListPresenter(Activity activity, HotMovieListContact.IHotMovieListView view) {
        super(activity, view);
        mHotMovieListManager = new HotMovieListManager();
    }

    @Override
    public void getHotMovieList(int limit) {
        mView.showLoading();

        mHotMovieListManager.getHotMovieList(limit)
                .subscribe(new Consumer<HotMovieBean>() {
                    @Override
                    public void accept(@NonNull HotMovieBean hotMovieBean) throws Exception {
                        Log.e(TAG, "accept: " + hotMovieBean.toString());
                        mView.addHotMovieList(hotMovieBean.getData().getHot());
                        mView.addMovieIds(hotMovieBean.getData().getMovieIds());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: " + throwable.getLocalizedMessage().toString());
                        mView.showError(throwable.getMessage().toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.showContent();
                    }
                });
    }
}
