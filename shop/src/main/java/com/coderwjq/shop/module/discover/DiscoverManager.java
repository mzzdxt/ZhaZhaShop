package com.coderwjq.shop.module.discover;

import com.coderwjq.shop.module.discover.model.DiscoverBean;
import com.coderwjq.shop.module.discover.model.DiscoverHeaderBean;
import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/8/30 17:53.
 */

public class DiscoverManager {
    public Observable<DiscoverHeaderBean> getDiscoverHeaderData(String utm_term) {
        return RetrofitClient.getInstance()
                .apiServer().getDiscoverHeader("android", utm_term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DiscoverBean> getDiscoverData(int offset, int limit) {
        return RetrofitClient.getInstance()
                .apiServer().getDiscover(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
