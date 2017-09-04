package com.coderwjq.shop.module.movie_search;

import android.app.Activity;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.module.movie_search.model.ClassifySearchBean;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/9/4 14:25.
 */

public class SearchPresenter extends BasePresenter<SearchContract.ISearchView> implements SearchContract.ISearchPresenter {

    private final SearchManager mManager;

    public SearchPresenter(Activity activity, SearchContract.ISearchView view) {
        super(activity, view);
        mManager = new SearchManager();
    }


    @Override
    public void getMovieTypeList() {
        mManager.getMovieTypeList()
                .subscribe(new Observer<MovieTypeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull MovieTypeBean movieTypeBean) {
                        mView.addMovieType(movieTypeBean.getData().get(0).getTagList());
                        mView.addMovieNation(movieTypeBean.getData().get(1).getTagList());
                        mView.addMoviePeriod(movieTypeBean.getData().get(2).getTagList());
                        mView.addMoviePoint(movieTypeBean.getData().get(3).getTagList());
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
    public void getClassifySearchList(int offset, int catId, int sourceId, int yearId, int sortId) {
        mManager.getClassifySearchList(offset, catId, sourceId, yearId, sortId)
                .subscribe(new Observer<ClassifySearchBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoadingData();
                    }

                    @Override
                    public void onNext(@NonNull ClassifySearchBean classifySearchBean) {
                        mView.addClassifySearchData(classifySearchBean.getList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showError(ErrorHanding.handleError(e));
                    }

                    @Override
                    public void onComplete() {
                        mView.finishLoadingData();
                        mView.showContent();
                    }
                });
    }
}
