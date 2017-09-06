package com.coderwjq.shop.module.movie.movie_wait;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.coderwjq.shop.R;
import com.coderwjq.shop.activity.MainActivity;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.module.movie.movie_wait.adapter.RecentExpectAdapter;
import com.coderwjq.shop.module.movie.movie_wait.adapter.TrailerRecommendAdapter;
import com.coderwjq.shop.module.movie.movie_wait.adapter.WaitMovieAdapter;
import com.coderwjq.shop.module.movie.movie_wait.bean.ExpectMovieBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.TrailerRecommendBean;
import com.coderwjq.shop.module.movie.movie_wait.bean.WaitMovieBean;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @Created by coderwjq on 2017/5/25 16:01.
 * @Desc 待映列表
 */

public class FragmentMovieWait extends BaseFragment implements IWaitMovieView {

    @BindView(R.id.rv_wait_movie)
    RecyclerView mRvWaitMovie;
    @BindView(R.id.swipe_refresh)
    SuperSwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.pl_container)
    ProgressLayout mPlContainer;
    private WaitMoviePresenter mPresenter;
    private MyPullToRefreshListener mRefreshListener;
    private TrailerRecommendAdapter mRecommendAdapter;
    private WaitMovieAdapter mWaitMovieAdapter;
    private RecentExpectAdapter mRecentExpectAdapter;

    public static FragmentMovieWait getInstance() {
        return new FragmentMovieWait();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wait_movie;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter = new WaitMoviePresenter(mContext, this);
        mRefreshListener = new MyPullToRefreshListener(mContext, mSwipeRefresh);
    }

    @Override
    protected void initListener() {
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                mPresenter.getTrailerRecommendMovie();
                mPresenter.getRecentExpectMovie(0, 50);
                mPresenter.getWaitMovieList(20, 12);
            }
        });
        mSwipeRefresh.setOnPullRefreshListener(mRefreshListener);

        mWaitMovieAdapter = new WaitMovieAdapter();
        mRvWaitMovie.setLayoutManager(new LinearLayoutManager(mContext));
        mRvWaitMovie.setAdapter(mWaitMovieAdapter);

        // 预告片
        View trailerRecommendView = mContext.getLayoutInflater().inflate(R.layout.layout_wait_movie_trailer_recommend, (ViewGroup) mRvWaitMovie.getParent(), false);
        RecyclerView rvTrailerRecommend = (RecyclerView) trailerRecommendView.findViewById(R.id.rv_wait_movie_trailer_recommend);
        mRecommendAdapter = new TrailerRecommendAdapter();
        rvTrailerRecommend.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvTrailerRecommend.setAdapter(mRecommendAdapter);
        mWaitMovieAdapter.addHeaderView(trailerRecommendView);

        // 最近期待
        View recentExpectView = mContext.getLayoutInflater().inflate(R.layout.layout_recent_expect, (ViewGroup) mRvWaitMovie.getParent(), false);
        RecyclerView rvRecentExpect = (RecyclerView) recentExpectView.findViewById(R.id.rv_wait_movie_recent_expect);
        mRecentExpectAdapter = new RecentExpectAdapter();
        rvRecentExpect.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvRecentExpect.setAdapter(mRecentExpectAdapter);
        mWaitMovieAdapter.addHeaderView(recentExpectView);

        // 更新数据
        mPresenter.getTrailerRecommendMovie();
        mPresenter.getRecentExpectMovie(0, 50);
        mPresenter.getWaitMovieList(20, 12);

        mRvWaitMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int dySum;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Activity attachedActivity = getActivity();

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
        if (!mPlContainer.isContent()) {
            mPlContainer.showLoading();
        }
    }

    @Override
    public void showContent() {
        mRefreshListener.refreshDone();
        if (!mPlContainer.isContent()) {
            mPlContainer.showContent();
        }
    }

    @Override
    public void showError(String error) {
        mRefreshListener.refreshDone();
        mPlContainer.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getWaitMovieList(20, 12);
            }
        });
    }

    @Override
    public void addTrailerRecommendMovieList(List<TrailerRecommendBean.DataBean> data) {
        mRecommendAdapter.setNewData(data);
    }

    @Override
    public void addRecentExpectMovieList(List<ExpectMovieBean.DataBean.ComingBean> coming) {
        mRecentExpectAdapter.setNewData(coming);
    }

    @Override
    public void addWaitMovieList(List<WaitMovieBean.DataBean.ComingBean> coming) {
        mWaitMovieAdapter.setNewData(coming);
    }
}
