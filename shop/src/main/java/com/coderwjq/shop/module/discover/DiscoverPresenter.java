package com.coderwjq.shop.module.discover;

import android.app.Activity;

import com.coderwjq.shop.base.BasePresenter;
import com.coderwjq.shop.module.discover.model.DiscoverBean;
import com.coderwjq.shop.module.discover.model.DiscoverHeaderBean;
import com.coderwjq.shop.utils.ErrorHanding;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by coderwjq on 2017/8/30 17:52.
 */

public class DiscoverPresenter extends BasePresenter<DiscoverContract.IDiscoverView> implements DiscoverContract.IDiscoverPresenter {

    private final DiscoverManager mDiscoverManager;

    public DiscoverPresenter(Activity activity, DiscoverContract.IDiscoverView view) {
        super(activity, view);
        mDiscoverManager = new DiscoverManager();
    }

    @Override
    public void getDiscoverHeader(String utm_term) {
        mDiscoverManager.getDiscoverHeaderData(utm_term)
                .subscribe(new Observer<DiscoverHeaderBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull DiscoverHeaderBean discoverHeaderBean) {
                        mView.addDiscoverHeaderData(discoverHeaderBean.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showError(ErrorHanding.handleError(e));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getDiscoverData(int offset, int limit) {
        mDiscoverManager.getDiscoverData(offset, limit)
                .subscribe(new Observer<DiscoverBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull DiscoverBean discoverBean) {
                        mView.addDiscoverData(discoverBean.getData().getFeeds());
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
