package com.coderwjq.shop.module.movie.movie_hot;

import com.coderwjq.shop.network.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Created by coderwjq on 2017/5/25 19:55.
 * @Desc
 */

public class HotMovieListManager {

    /**
     * 获取热门电影列表
     *
     * @param limit
     * @return
     */
    public Observable<HotMovieBean> getHotMovieList(int limit) {
        return RetrofitClient
                .getInstance()
                .apiServer()
                .getHotMovieList(20, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取热门电影列表更多数据
     *
     * @param headline
     * @param movieIds
     * @return
     */
    public Observable<HotMovieBean.DataBean.HotBean> getMoreHotMovieList(int headline, String movieIds) {
        return RetrofitClient
                .getInstance()
                .apiServer()
                .getMoreHotMovieList(20, headline, movieIds)
                .flatMap(new Function<HotMovieBean, Observable<HotMovieBean.DataBean.MoviesBean>>() {
                    @Override
                    public Observable<HotMovieBean.DataBean.MoviesBean> apply(@NonNull HotMovieBean hotMovieBean) throws Exception {
                        if (hotMovieBean.getData().getMovies().size() > 0) {
                            return Observable.fromIterable(hotMovieBean.getData().getMovies());
                        } else {
                            return Observable.error(new RuntimeException());
                        }
                    }
                }).map(new Function<HotMovieBean.DataBean.MoviesBean, HotMovieBean.DataBean.HotBean>() {
                    @Override
                    public HotMovieBean.DataBean.HotBean apply(@NonNull HotMovieBean.DataBean.MoviesBean moviesBean) throws Exception {
                        HotMovieBean.DataBean.HotBean hotBean = new HotMovieBean.DataBean.HotBean();
                        hotBean.setBoxInfo(moviesBean.getBoxInfo());
                        hotBean.setCat(moviesBean.getCat());
                        hotBean.setCivilPubSt(moviesBean.getCivilPubSt());
                        hotBean.setId(moviesBean.getId());
                        hotBean.setShowInfo(moviesBean.getShowInfo());//播放场次
                        hotBean.setSc(moviesBean.getSc());//得分
                        hotBean.setNm(moviesBean.getNm());//片名
                        hotBean.setVer(moviesBean.getVer());//3D标签
                        hotBean.setScm(moviesBean.getScm());//描述
                        hotBean.setPreSale(moviesBean.getPreSale());//是否预售
                        hotBean.setWish(moviesBean.getWish());//期待观影人数
                        hotBean.setImg(moviesBean.getImg());
                        return hotBean;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
