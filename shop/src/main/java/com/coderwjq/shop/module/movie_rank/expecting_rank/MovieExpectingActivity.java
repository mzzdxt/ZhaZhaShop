package com.coderwjq.shop.module.movie_rank.expecting_rank;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieExpectingActivity extends AppCompatActivity implements ILoadingView {

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
    @BindView(R.id.rv_recent_expect)
    RecyclerView mRvRecentExpect;
    @BindView(R.id.swipe)
    SuperSwipeRefreshLayout mSwipe;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;

    private int offset;
    private MostExpectMovieAdapter mMovieAdapter;
    private TextView mTvContent;
    private TextView mTvCreateDate;
    private MyPullToRefreshListener mRefreshListener;

    public static void invoke(Context context) {
        Intent starter = new Intent(context, MovieExpectingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_expecting);
        ButterKnife.bind(this);

        initAdapter();

        mTvTitle.setText("最受期待榜单");

        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initAdapter() {
        mMovieAdapter = new MostExpectMovieAdapter();
        mRvRecentExpect.setAdapter(mMovieAdapter);
        mRvRecentExpect.setLayoutManager(new LinearLayoutManager(this));
        mRvRecentExpect.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        View headerView = getLayoutInflater().inflate(R.layout.layout_fixboard_header, mRvRecentExpect, false);
        mTvContent = (TextView) headerView.findViewById(R.id.tv_content);
        mTvCreateDate = (TextView) headerView.findViewById(R.id.tv_createDate);
        mMovieAdapter.addHeaderView(headerView);

        mMovieAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMostExpectMovie();
            }
        }, mRvRecentExpect);

        mRefreshListener = new MyPullToRefreshListener(this, mSwipe);
        mSwipe.setOnPullRefreshListener(mRefreshListener);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                offset = 0;
                mMovieAdapter.setNewData(new ArrayList<MostExpectMovieBean.DataBean.MoviesBean>());
                getMostExpectMovie();
            }
        });

        getMostExpectMovie();
    }

    public void getMostExpectMovie() {
        RetrofitClient.getInstance()
                .apiMovieRankService()
                .getMostExpectMovie(10, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MostExpectMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        showLoading();
                    }

                    @Override
                    public void onNext(@NonNull MostExpectMovieBean mostExpectMovieBean) {
                        if (mostExpectMovieBean.getData().getMovies().size() > 0) {
                            offset += 10;
                            mMovieAdapter.addData(mostExpectMovieBean.getData().getMovies());
                            mMovieAdapter.loadMoreComplete();
                        } else {
                            mMovieAdapter.loadMoreEnd();
                        }

                        mTvContent.setText(mostExpectMovieBean.getData().getContent());
                        mTvCreateDate.setText(mostExpectMovieBean.getData().getCreated());
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
                    getMostExpectMovie();
                }
            });
        }
    }
}
