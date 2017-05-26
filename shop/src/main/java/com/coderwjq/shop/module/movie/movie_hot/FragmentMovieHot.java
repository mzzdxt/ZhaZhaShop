package com.coderwjq.shop.module.movie.movie_hot;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @Created by coderwjq on 2017/5/25 16:01.
 * @Desc
 */

public class FragmentMovieHot extends BaseFragment implements HotMovieListContact.IHotMovieListView {
    private static final String TAG = "FragmentMovieHot";
    @BindView(R.id.rv_hot_movie)
    RecyclerView mRvHotMovie;
    @BindView(R.id.ssrl_refresh)
    SuperSwipeRefreshLayout mSsrlRefresh;
    @BindView(R.id.pl_container)
    ProgressLayout mPlContainer;
    private HotMovieListAdapter mHotMovieListAdapter;
    private MyPullToRefreshListener mRefreshListener;
    private int mCurrentIndex;
    private HotMovieListPresenter mPresenter;

    public static FragmentMovieHot getInstance() {
        return new FragmentMovieHot();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot_movie;
    }

    @Override
    protected void initView() {
        mRvHotMovie.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        mPresenter = new HotMovieListPresenter(mContext, this);
        mHotMovieListAdapter = new HotMovieListAdapter(mContext);
        mRefreshListener = new MyPullToRefreshListener(mContext, mSsrlRefresh);
    }

    @Override
    protected void initListener() {
        mRvHotMovie.setAdapter(mHotMovieListAdapter);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                mCurrentIndex = 1;
                mPresenter.getHotMovieList(12);
            }
        });
        mSsrlRefresh.setOnPullRefreshListener(mRefreshListener);
        mPresenter.getHotMovieList(12);
    }

    @Override
    public void showLoading() {
        if (!mPlContainer.isContent()) {
            mPlContainer.showLoading();
        }
    }

    @Override
    public void showContent() {
        if (!mPlContainer.isContent()) {
            mPlContainer.showContent();
        }
        mRefreshListener.refreshDone();
    }

    @Override
    public void showError(String error) {
        mRefreshListener.refreshDone();
        mPlContainer.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getHotMovieList(12);
            }
        });
    }

    @Override
    public void addHotMovieList(List<HotMovieBean.DataBean.HotBean> hot) {
        Log.d(TAG, "addHotMovieList() called with: hot = [" + hot + "]");
        mHotMovieListAdapter.setNewData(hot);
    }

    @Override
    public void addMovieIds(List<Integer> movieIds) {

    }
}
