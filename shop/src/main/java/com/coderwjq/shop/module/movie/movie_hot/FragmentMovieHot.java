package com.coderwjq.shop.module.movie.movie_hot;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Created by coderwjq on 2017/5/25 16:01.
 * @Desc
 */

public class FragmentMovieHot extends BaseFragment implements HotMovieListContact.IHotMovieListView {
    public static final int GROUP_ITEM_COUNT = 12;
    private static final String TAG = "FragmentMovieHot";
    @BindView(R.id.rv_hot_movie)
    RecyclerView mRvHotMovie;
    @BindView(R.id.ssrl_refresh)
    SuperSwipeRefreshLayout mSsrlRefresh;
    @BindView(R.id.pl_container)
    ProgressLayout mPlContainer;
    private HotMovieListAdapter mHotMovieListAdapter;
    private MyPullToRefreshListener mRefreshListener;
    private int mCurrentIndex = 1;
    private HotMovieListPresenter mPresenter;
    private List<List<Integer>> mMovieIdsList;
    private int mGroupCount;

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
                mPresenter.getHotMovieList(GROUP_ITEM_COUNT);
            }
        });
        // 设置下拉刷新监听
        mSsrlRefresh.setOnPullRefreshListener(mRefreshListener);
        // 设置加载更多监听
        mHotMovieListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mCurrentIndex <= mGroupCount) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < mMovieIdsList.get(mCurrentIndex).size(); i++) {
                        stringBuilder.append(mMovieIdsList.get(mCurrentIndex).get(i));
                        stringBuilder.append(",");
                    }
                    String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
                    Log.e(TAG, "onLoadMoreRequested: " + substring);
                    mPresenter.getMoreHotMovieList(0, substring);
                } else {
                    mHotMovieListAdapter.loadMoreEnd();
                }
            }
        }, mRvHotMovie);
        mPresenter.getHotMovieList(GROUP_ITEM_COUNT);
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
                mPresenter.getHotMovieList(GROUP_ITEM_COUNT);
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
        // 对获取的数据进行分组
        mGroupCount = movieIds.size() / GROUP_ITEM_COUNT;
        mMovieIdsList = new ArrayList<>();

        // 前groupCount组
        for (int i = 0; i < mGroupCount; i++) {
            List<Integer> content = new ArrayList<>();
            for (int j = i * GROUP_ITEM_COUNT; j < (i + 1) * GROUP_ITEM_COUNT; j++) {
                content.add(movieIds.get(j));
            }
            mMovieIdsList.add(content);
        }

        // 最后一组
        ArrayList<Integer> content = new ArrayList<>();
        for (int i = mGroupCount * GROUP_ITEM_COUNT; i < movieIds.size(); i++) {
            content.add(movieIds.get(i));
        }
        mMovieIdsList.add(content);
    }

    @Override
    public void addMoreMovies(HotMovieBean.DataBean.HotBean hotMovieBeanList) {
        mHotMovieListAdapter.addData(hotMovieBeanList);
    }

    @Override
    public void addMoreError(Throwable e) {
        Log.e(TAG, "addMoreError: " + e.getMessage().toString());
        mHotMovieListAdapter.loadMoreFail();
    }

    @Override
    public void loadMoreCompleted() {
        mCurrentIndex++;
        mHotMovieListAdapter.loadMoreComplete();
    }
}
