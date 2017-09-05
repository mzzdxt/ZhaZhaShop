package com.coderwjq.shop.module.movie_rank.top100_rank;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.network.RetrofitClient;
import com.coderwjq.shop.utils.ErrorHanding;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieTop100Activity extends AppCompatActivity implements ILoadingView {
    private static final String TAG = "MovieTop100Activity";
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

    private int offset;
    private MyPullToRefreshListener mRefreshListener;
    private TopHundredMovieAdapter mMovieAdapter;
    private TextView mTvContent;
    private TextView mTvCreate;

    public static void invoke(Context context) {
        Intent starter = new Intent(context, MovieTop100Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_top100);
        ButterKnife.bind(this);

        mTvTitle.setText("TOP100榜单");

        initPullToRefresh();
        initAdapter();

        getTopHundredMovie();
    }

    private void initAdapter() {
        mMovieAdapter = new TopHundredMovieAdapter();
        mRvBaseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRvBaseRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mRvBaseRecyclerView.setAdapter(mMovieAdapter);

        View headerView = getLayoutInflater().inflate(R.layout.layout_fixboard_header, mRvBaseRecyclerView, false);
        mTvContent = (TextView) headerView.findViewById(R.id.tv_content);
        mTvCreate = (TextView) headerView.findViewById(R.id.tv_createDate);
        mMovieAdapter.addHeaderView(headerView);

        mMovieAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getTopHundredMovie();
            }
        }, mRvBaseRecyclerView);
    }

    private void initPullToRefresh() {
        mRefreshListener = new MyPullToRefreshListener(this, mSwipe);
        mSwipe.setOnPullRefreshListener(mRefreshListener);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                offset = 0;
                mMovieAdapter.setNewData(new ArrayList<TopHundredMovieBean.DataBean.MoviesBean>());
                getTopHundredMovie();
            }
        });
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
                    getTopHundredMovie();
                }
            });
        }
    }

    public void getTopHundredMovie() {
        RetrofitClient.getInstance()
                .apiMovieRankService()
                .getTopHundredMovie(10, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopHundredMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        showLoading();
                    }

                    @Override
                    public void onNext(@NonNull TopHundredMovieBean topHundredMovieBean) {
                        addContent(topHundredMovieBean.getData().getCreated(), topHundredMovieBean.getData().getContent());
                        addTopHundredMovie(topHundredMovieBean.getData().getMovies());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showError(ErrorHanding.handleError(e));
                    }

                    @Override
                    public void onComplete() {
                        showContent();
                    }
                });
    }

    public void addTopHundredMovie(List<TopHundredMovieBean.DataBean.MoviesBean> movies) {
        if (movies.size() > 0) {
            offset += 10;
            mMovieAdapter.addData(movies);
            mMovieAdapter.loadMoreComplete();
        } else {
            mMovieAdapter.loadMoreEnd();
        }

    }

    public void addContent(String created, String content) {
        mTvCreate.setText(created);
        mTvContent.setText(content);
    }
}
