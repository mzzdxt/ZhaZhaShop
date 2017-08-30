package com.coderwjq.shop.module.discover;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.module.discover.adapter.DiscoverAdapter;
import com.coderwjq.shop.module.discover.model.DiscoverBean;
import com.coderwjq.shop.module.discover.model.DiscoverHeaderBean;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ToastUtil;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @Created by coderwjq on 2017/5/25 14:50.
 * @Desc
 */

public class FragmentDiscover extends BaseFragment implements DiscoverContract.IDiscoverView {

    @BindView(R.id.rv_discover)
    RecyclerView mRvDiscover;
    @BindView(R.id.ssrl_refresh)
    SuperSwipeRefreshLayout mSsrlRefresh;
    @BindView(R.id.pl_container)
    ProgressLayout mPlContainer;
    private DiscoverAdapter mDiscoverAdapter;
    private DiscoverPresenter mDiscoverPresenter;
    private MyPullToRefreshListener mRefreshListener;

    private int offset = 0;
    private int limit = 10;
    private View mHeaderView;

    public static FragmentDiscover getInstance() {
        return new FragmentDiscover();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView() {
        mDiscoverPresenter = new DiscoverPresenter(getActivity(), this);

        mDiscoverAdapter = new DiscoverAdapter();
        mRvDiscover.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvDiscover.setAdapter(mDiscoverAdapter);

        mHeaderView = mContext.getLayoutInflater().inflate(R.layout.item_discover_header, (ViewGroup) mRvDiscover.getParent(), false);
        mDiscoverAdapter.setHeaderView(mHeaderView);

        // pull to refresh widget
        mRefreshListener = new MyPullToRefreshListener(mContext, mSsrlRefresh);
        mSsrlRefresh.setOnPullRefreshListener(mRefreshListener);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                offset = 0;
                // 请求新数据
                mDiscoverPresenter.getDiscoverData(offset, limit);
                mDiscoverPresenter.getDiscoverHeader("7.8.0");
            }
        });
        mRefreshListener.onRefresh();

        mDiscoverAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mDiscoverPresenter.getDiscoverData(offset, limit);
            }
        }, mRvDiscover);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showLoading() {
        if (!mPlContainer.isContent()) {
            mPlContainer.showLoading();

            if (mPlContainer.getVisibility() == View.INVISIBLE) {
                mPlContainer.setVisibility(View.VISIBLE);
            }
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
        mDiscoverAdapter.loadMoreEnd();

        mPlContainer.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset = 0;
                mDiscoverPresenter.getDiscoverData(offset, limit);
                mDiscoverPresenter.getDiscoverHeader("7.8.0");
            }
        });
    }

    @Override
    public void addDiscoverHeaderData(final List<DiscoverHeaderBean.DataBean> data) {
        ImageView[] imageViews = new ImageView[]{((ImageView) mHeaderView.findViewById(R.id.iv_header1)),
                ((ImageView) mHeaderView.findViewById(R.id.iv_header2)),
                ((ImageView) mHeaderView.findViewById(R.id.iv_header3)),
                ((ImageView) mHeaderView.findViewById(R.id.iv_header4))};
        TextView[] tv_content = new TextView[]{((TextView) mHeaderView.findViewById(R.id.tv_header1)),
                ((TextView) mHeaderView.findViewById(R.id.tv_header2)),
                ((TextView) mHeaderView.findViewById(R.id.tv_header3)),
                ((TextView) mHeaderView.findViewById(R.id.tv_header4))};
        for (int i = 0; i < data.size(); i++) {
            GlideManager.loadImage(mContext, data.get(i).getImage().getUrl(), imageViews[i]);
            tv_content[i].setText(data.get(i).getTitle());
            final int finalI = i;
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort(getActivity(), data.get(finalI).getUrl());
//                    BaseWebViewActivity.start(mContext,data.get(finalI).getUrl());
                }
            });
        }
    }

    @Override
    public void addDiscoverData(List<DiscoverBean.DataBean.FeedsBean> feeds) {
        if (feeds.size() > 0) {
            offset += 10;

            mDiscoverAdapter.addData(feeds);
            mDiscoverAdapter.loadMoreComplete();
        } else {
            // no data
            mDiscoverAdapter.loadMoreEnd();
        }
    }
}
