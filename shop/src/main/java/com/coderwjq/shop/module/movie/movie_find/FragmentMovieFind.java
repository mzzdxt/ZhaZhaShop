package com.coderwjq.shop.module.movie.movie_find;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coderwjq.shop.R;
import com.coderwjq.shop.activity.MainActivity;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.module.movie.movie_find.adapter.FindMovieAwardAdapter;
import com.coderwjq.shop.module.movie.movie_find.adapter.FindMovieGridAdapter;
import com.coderwjq.shop.module.movie.movie_find.adapter.FindMovieNationAdapter;
import com.coderwjq.shop.module.movie.movie_find.adapter.FindMoviePeriodAdapter;
import com.coderwjq.shop.module.movie.movie_find.adapter.FindMovieTypeAdapter;
import com.coderwjq.shop.module.movie.movie_find.model.AwardsMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.GridMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Created by coderwjq on 2017/5/25 16:01.
 * @Desc view层
 */

public class FragmentMovieFind extends BaseFragment implements FindMovieContract.IFindMovieView {
    private static final String TAG = "FragmentMovieFind";

    @BindView(R.id.rv_movie_type)
    RecyclerView mRvMovieType;
    @BindView(R.id.rv_movie_nation)
    RecyclerView mRvMovieNation;
    @BindView(R.id.rv_movie_period)
    RecyclerView mRvMoviePeriod;
    @BindView(R.id.rv_find_movie_grid)
    RecyclerView mRvFindMovieGrid;
    @BindView(R.id.rv_movie_awards)
    RecyclerView mRvMovieAwards;
    @BindView(R.id.tv_all_awards)
    TextView mTvAllAwards;
    @BindView(R.id.ssrl_refresh)
    SuperSwipeRefreshLayout mSsrlRefresh;
    @BindView(R.id.pl_container)
    ProgressLayout mPlContainer;
    @BindView(R.id.nsv_view)
    NestedScrollView mNsvView;
    Unbinder unbinder;
    private MyPullToRefreshListener mRefreshListener;
    private FindMoviePresenter mFindMoviePresenter;
    private FindMovieTypeAdapter mFindMovieTypeAdapter;
    private FindMovieNationAdapter mFindMovieNationAdapter;
    private FindMoviePeriodAdapter mFindMoviePeriodAdapter;
    private FindMovieGridAdapter mFindMovieGridAdapter;
    private FindMovieAwardAdapter mFindMovieAwardAdapter;

    public static FragmentMovieFind getInstance() {
        return new FragmentMovieFind();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_movie;
    }

    @Override
    protected void initView() {
        mFindMoviePresenter = new FindMoviePresenter(getActivity(), this);

        mRefreshListener = new MyPullToRefreshListener(getActivity(), mSsrlRefresh);
        mSsrlRefresh.setOnPullRefreshListener(mRefreshListener);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                mFindMoviePresenter.getFindMovieData();
            }
        });
        mRefreshListener.onRefresh();

        // 类型
        mRvMovieType.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // 地区
        mRvMovieNation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // 年代
        mRvMoviePeriod.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // 表格数据
        mRvFindMovieGrid.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // 获奖电影
        mRvMovieAwards.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mFindMovieTypeAdapter = new FindMovieTypeAdapter();
        mRvMovieType.setAdapter(mFindMovieTypeAdapter);

        View headerViewType = mContext.getLayoutInflater().inflate(R.layout.layout_normal_tv, (ViewGroup) mRvMovieType.getParent(), false);
        ((TextView) headerViewType.findViewById(R.id.tv_content)).setText("类型");
        mFindMovieTypeAdapter.setHeaderView(headerViewType, 0, LinearLayout.HORIZONTAL);

        mFindMovieNationAdapter = new FindMovieNationAdapter();
        mRvMovieNation.setAdapter(mFindMovieNationAdapter);
        View headerViewNation = mContext.getLayoutInflater().inflate(R.layout.layout_normal_tv, (ViewGroup) mRvMovieType.getParent(), false);
        ((TextView) headerViewNation.findViewById(R.id.tv_content)).setText("地区");
        mFindMovieNationAdapter.setHeaderView(headerViewNation, 0, LinearLayout.HORIZONTAL);

        mFindMoviePeriodAdapter = new FindMoviePeriodAdapter();
        mRvMoviePeriod.setAdapter(mFindMoviePeriodAdapter);
        View headerViewPeriod = mContext.getLayoutInflater().inflate(R.layout.layout_normal_tv, (ViewGroup) mRvMovieType.getParent(), false);
        ((TextView) headerViewPeriod.findViewById(R.id.tv_content)).setText("年代");
        mFindMoviePeriodAdapter.setHeaderView(headerViewPeriod, 0, LinearLayout.HORIZONTAL);

        mFindMovieGridAdapter = new FindMovieGridAdapter();
        mRvFindMovieGrid.setAdapter(mFindMovieGridAdapter);
        mRvFindMovieGrid.setNestedScrollingEnabled(false);

        mFindMovieAwardAdapter = new FindMovieAwardAdapter();
        mRvMovieAwards.setAdapter(mFindMovieAwardAdapter);

        mNsvView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int dySum;

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Activity attachedActivity = getActivity();

                int dy = scrollY - oldScrollY;

                if (dy >= 0) {
                    // 向下滑动,隐藏底部tabbar
                    if (dySum < 0) {
                        dySum = 0;
                    } else {
                        dySum += dy;

                        // 判断用户手指在屏幕上的滑动是否是一个ACTION_MOVE动作的这个距离常量叫做TouchSlop
                        if (dySum >= ViewConfiguration.get(attachedActivity).getScaledTouchSlop()) {
                            if (attachedActivity instanceof MainActivity && !(((MainActivity) attachedActivity).isBottomMenuAnimShowing())) {
                                ((MainActivity) attachedActivity).hideBottomBar();
                                dySum = 0;
                            }
                        }
                    }
                } else {
                    // 向上滑动,显示底部tabbar
                    if (dySum > 0) {
                        dySum = 0;
                    } else {
                        dySum += dy;

                        if (dySum <= -ViewConfiguration.get(attachedActivity).getScaledTouchSlop()) {
                            if (attachedActivity instanceof MainActivity && !(((MainActivity) attachedActivity).isBottomMenuAnimShowing())) {
                                ((MainActivity) attachedActivity).showBottomBar();
                                dySum = 0;
                            }
                        }
                    }


                }

            }
        });
    }

    @Override
    public void showLoading() {
        if (!isAdded()) {
            return;
        }

        if (!mPlContainer.isContent()) {
            mPlContainer.showLoading();

            if (mPlContainer.getVisibility() == View.INVISIBLE) {
                mPlContainer.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void showContent() {
        if (!isAdded()) {
            return;
        }

        mRefreshListener.refreshDone();

        if (!mPlContainer.isContent()) {
            mPlContainer.showContent();
        }
    }

    @Override
    public void showError(String error) {
        if (!isAdded()) {
            return;
        }

        mRefreshListener.refreshDone();

        mPlContainer.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFindMoviePresenter.getFindMovieData();
            }
        });
    }

    @Override
    public void addMovieType(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        mFindMovieTypeAdapter.setNewData(tagList);
    }

    @Override
    public void addMovieNation(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        mFindMovieNationAdapter.setNewData(tagList);
    }

    @Override
    public void addMoviePeriod(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        mFindMoviePeriodAdapter.setNewData(tagList);
    }

    @Override
    public void addMovieGrid(List<GridMovieBean.DataBean> data) {
        mFindMovieGridAdapter.setNewData(data);
    }

    @Override
    public void addAwardsMovie(List<AwardsMovieBean.DataBean> data) {
        mFindMovieAwardAdapter.setNewData(data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
