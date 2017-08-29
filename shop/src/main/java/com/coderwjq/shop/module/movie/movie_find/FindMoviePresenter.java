package com.coderwjq.shop.module.movie.movie_find;

import android.app.Activity;
import android.util.Log;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/8/29 14:12.
 */

public class FindMoviePresenter extends BasePresenter<FindMovieContract.IFindMovieView> implements FindMovieContract.IFindMoviePresenter {
    private static final String TAG = "FindMoviePresenter";

    private FindMovieManager mFindMovieManager;

    public FindMoviePresenter(Activity activity, FindMovieContract.IFindMovieView view) {
        super(activity, view);

        mFindMovieManager = new FindMovieManager();
    }

    @Override
    public void getFindMovieData() {
        Observable.merge(mFindMovieManager.getMovieTypeList(),
                mFindMovieManager.getGridMovieList(),
                mFindMovieManager.getAwardsMovieList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        if (o instanceof MovieTypeBean) {
                            mView.addMovieType(((MovieTypeBean) o).getData().get(0).getTagList());
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
