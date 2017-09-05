package com.coderwjq.shop.module.movie_rank.oversea_rank;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;
import com.coderwjq.shop.utils.ErrorHanding;
import com.coderwjq.shop.utils.UIUtils;
import com.coderwjq.shop.view.FloatingItemDecoration;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/9/5 10:05.
 */

public class MovieOverseaFragment extends BaseFragment implements MovieOverseaContract.IMovieOverseaView {
    private static final String TAG = "MovieOverseaFragment";

    private static final String FILED_AREA = "area";
    @BindView(R.id.rv_oversea_movie)
    RecyclerView mRvOverseaMovie;
    @BindView(R.id.swipe)
    SuperSwipeRefreshLayout mSwipe;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;
    private MovieOverseaPresenter mPresenter;
    private String mStrArea;
    private OverseaMovieAdapter mAdapter;
    private FloatingItemDecoration mDecoration;
    private HashMap<Integer, String> keys;
    private MyPullToRefreshListener mRefreshListener;

    public static MovieOverseaFragment getInstance(String area) {
        Bundle bundle = new Bundle();
        bundle.putString(FILED_AREA, area);

        MovieOverseaFragment fragment = new MovieOverseaFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_oversea_movie;
    }

    @Override
    protected void initView() {
        mPresenter = new MovieOverseaPresenter(getActivity(), this);

        mRefreshListener = new MyPullToRefreshListener(mContext, mSwipe);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                mAdapter.setNewData(new ArrayList<OverseaHotMovieBean.DataBean.HotBean>());
                mPresenter.getOverseaMovie(mStrArea);
            }
        });
        mSwipe.setOnPullRefreshListener(mRefreshListener);
    }

    @Override
    protected void initData() {
        mStrArea = getArguments().getString(FILED_AREA);

        mAdapter = new OverseaMovieAdapter();

        mRvOverseaMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDecoration = new FloatingItemDecoration(mContext);
        mDecoration.setmTitleHeight(UIUtils.dp2px(mContext, 27));
        mDecoration.setShowFloatingHeaderOnScrolling(false);

        mRvOverseaMovie.addItemDecoration(mDecoration);
        mRvOverseaMovie.setAdapter(mAdapter);

        keys = new HashMap<>();

        mPresenter.getOverseaMovie(mStrArea);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showLoading() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showLoading();
        }
    }

    @Override
    public void showContent() {
        mRefreshListener.refreshDone();

        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(String error) {
        mRefreshListener.refreshDone();

        if (!mProgressLayout.isContent()) {
            mProgressLayout.showError(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.setNewData(new ArrayList<OverseaHotMovieBean.DataBean.HotBean>());

                    mPresenter.getOverseaMovie(mStrArea);
                }
            });
        }
    }

    private String getArea(String area) {
        switch (area) {
            case "NA":
                return "美国";
            case "KR":
                return "韩国";
            case "JP":
                return "日本";
            default:
                return "";

        }
    }

    @Override
    public void addOverseaHotMovie(List<OverseaHotMovieBean.DataBean.HotBean> hot) {
        keys.put(0, String.format("%s热映", getArea(mStrArea)));
        mDecoration.setKeys(keys);

        if (hot.size() >= 10) {
            OverseaHotMovieBean.DataBean.HotBean footer = new OverseaHotMovieBean.DataBean.HotBean();
            footer.setFooterName("查看全部热映影片");
            footer.setType("hot");
            footer.setArea(mStrArea);
            hot.add(footer);
        }
        mAdapter.addData(hot);
    }

    @Override
    public void addOverseaComingMovie(List<OverseaComingMovieBean.DataBean.ComingBean> coming) {
        Observable.fromIterable(coming)
                .map(new Function<OverseaComingMovieBean.DataBean.ComingBean, OverseaHotMovieBean.DataBean.HotBean>() {
                    @Override
                    public OverseaHotMovieBean.DataBean.HotBean apply(@NonNull OverseaComingMovieBean.DataBean.ComingBean comingBean) throws Exception {
                        OverseaHotMovieBean.DataBean.HotBean hotBean = new OverseaHotMovieBean.DataBean.HotBean();
                        List<OverseaHotMovieBean.DataBean.HotBean.HeadLinesVOBean> headLineList = new ArrayList<>();
                        if (comingBean.getHeadLinesVO() != null) {
                            for (int i = 0; i < comingBean.getHeadLinesVO().size(); i++) {
                                OverseaHotMovieBean.DataBean.HotBean.HeadLinesVOBean headLinesVOBean =
                                        new OverseaHotMovieBean.DataBean.HotBean.HeadLinesVOBean();

                                headLinesVOBean.setMovieId(comingBean.getHeadLinesVO().get(i).getMovieId());
                                headLinesVOBean.setTitle(comingBean.getHeadLinesVO().get(i).getTitle());
                                headLinesVOBean.setType(comingBean.getHeadLinesVO().get(i).getType());
                                headLinesVOBean.setUrl(comingBean.getHeadLinesVO().get(i).getUrl());
                                headLineList.add(headLinesVOBean);
                            }
                        }
                        hotBean.setId(comingBean.getId());
                        hotBean.setImg(comingBean.getImg());
                        hotBean.setHeadLinesVO(headLineList);
                        hotBean.setStar(comingBean.getStar());
                        hotBean.setShowst(comingBean.getShowst());
                        hotBean.setWish(comingBean.getWish());
                        hotBean.setVideourl(comingBean.getVideourl());
                        hotBean.setVideoName(comingBean.getVideoName());
                        hotBean.setStar(comingBean.getStar());
                        hotBean.setNm(comingBean.getNm());
                        return hotBean;
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<OverseaHotMovieBean.DataBean.HotBean>>() {
                    @Override
                    public void accept(@NonNull List<OverseaHotMovieBean.DataBean.HotBean> hotBeen) throws Exception {
                        keys.put(mAdapter.getData().size(), String.format("%s待映", getArea(mStrArea)));

                        mDecoration.setKeys(keys);
                        if (hotBeen.size() >= 10) {
                            OverseaHotMovieBean.DataBean.HotBean footer = new OverseaHotMovieBean.DataBean.HotBean();
                            footer.setFooterName("查看全部待映影片");
                            footer.setType("coming");
                            footer.setArea(mStrArea);
                            hotBeen.add(footer);
                        }
                        mAdapter.addData(hotBeen);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: " + ErrorHanding.handleError(throwable), throwable);
                    }
                });
    }
}
