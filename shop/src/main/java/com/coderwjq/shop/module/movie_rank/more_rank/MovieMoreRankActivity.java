package com.coderwjq.shop.module.movie_rank.more_rank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderwjq.shop.R;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieMoreRankActivity extends AppCompatActivity implements MovieMoreRankContract.IMovieMoreRankView {
    private static final String TAG = "MovieMoreRankActivity";

    private static final String FIELD_AREA = "area";
    private static final String FIELD_TYPE = "type";

    private static final String TYPE_HOT = "hot";
    private static final String TYPE_COMING = "coming";
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_title_right_icon)
    ImageView mIvTitleRightIcon;
    @BindView(R.id.rl_right_icon)
    RelativeLayout mRlRightIcon;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
    @BindView(R.id.rv_base_recyclerView)
    RecyclerView mRvBaseRecyclerView;
    @BindView(R.id.swipe)
    SuperSwipeRefreshLayout mSwipe;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;
    private MovieMoreRankPresenter mPresenter;
    private String mArea;
    private String mType;
    private int offset;
    private int limit = 10;
    private MyPullToRefreshListener mRefreshListener;
    private BaseMultiItemQuickAdapter mAdapter;

    public static void invoke(Context context, String area, String type) {
        Intent starter = new Intent(context, MovieMoreRankActivity.class);
        starter.putExtra(FIELD_AREA, area);
        starter.putExtra(FIELD_TYPE, type);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_more_rank);
        ButterKnife.bind(this);

        mPresenter = new MovieMoreRankPresenter(this, this);

        handleIntent();

        initPullToRefresh();
        initViews();
        initAdapter();
    }

    private void initAdapter() {
        switch (mType) {
            case TYPE_HOT:
                mAdapter = new OverseaMovieHotListAdapter();
                break;
            case TYPE_COMING:
                mAdapter = new OverseaMovieComingListAdapter();
                break;
        }

        mRvBaseRecyclerView.setAdapter(mAdapter);
        mRvBaseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRvBaseRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData();
            }
        }, mRvBaseRecyclerView);

        getData();
    }

    private void getData() {
        switch (mType) {
            case TYPE_HOT:
                mPresenter.getOverseaHotMovieList(mArea, limit, offset);
                break;
            case TYPE_COMING:
                mPresenter.getOverseaComingMovieList(mArea, limit, offset);
                break;
        }
    }

    private String switchAreaToName() {
        switch (mArea) {
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

    private void initViews() {
        mTvTitle.setText(mType.equals(TYPE_HOT) ? switchAreaToName() + "热映电影" : switchAreaToName() + "待映电影");

        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void handleIntent() {
        mArea = getIntent().getStringExtra(FIELD_AREA);
        mType = getIntent().getStringExtra(FIELD_TYPE);
    }

    private void initPullToRefresh() {
        mRefreshListener = new MyPullToRefreshListener(this, mSwipe);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                offset = 0;

                getData();
            }
        });
        mSwipe.setOnPullRefreshListener(mRefreshListener);
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
                    offset = 0;

                    getData();
                }
            });
        }
    }

    @Override
    public void addOverseaHotMovieList(List<OverseaHotMovieBean.DataBean.HotBean> hot) {
        if (hot.size() > 0) {
            offset += 10;
            mAdapter.addData(hot);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }

    @Override
    public void addOverseaComingMovieList(List<OverseaComingMovieBean.DataBean.ComingBean> coming) {
        if (coming.size() > 0) {
            offset += 10;
            mAdapter.addData(coming);
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreEnd();
        }
    }
}
