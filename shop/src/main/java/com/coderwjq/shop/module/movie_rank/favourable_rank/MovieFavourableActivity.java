package com.coderwjq.shop.module.movie_rank.favourable_rank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class MovieFavourableActivity extends AppCompatActivity implements ILoadingView {

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
    @BindView(R.id.rv_hot_good_comment_list)
    RecyclerView mRvHotGoodCommentList;
    @BindView(R.id.swipe)
    SuperSwipeRefreshLayout mSwipe;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;

    private int offset;
    private FavourableMovieAdapter mMovieAdapter;
    private View mHeaderView;
    private MyPullToRefreshListener mRefreshListener;
    private TextView tvCreateDate;
    private TextView tvContent;

    public static void invoke(Context context) {
        Intent starter = new Intent(context, MovieFavourableActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_favourable);
        ButterKnife.bind(this);

        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initAdapter();
    }

    private void initAdapter() {
        mMovieAdapter = new FavourableMovieAdapter();
        mRvHotGoodCommentList.setLayoutManager(new LinearLayoutManager(this));
        mRvHotGoodCommentList.setAdapter(mMovieAdapter);

        mHeaderView = getLayoutInflater().inflate(R.layout.layout_fixboard_header, mRvHotGoodCommentList, false);
        mMovieAdapter.setHeaderView(mHeaderView);

        getHotGoodCommentList(offset);

        mRefreshListener = new MyPullToRefreshListener(this, mSwipe);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                offset = 0;
                mMovieAdapter.setNewData(new ArrayList<FavourableMovieBean.DataBean.MoviesBean>());
                getHotGoodCommentList(offset);
            }
        });
        mSwipe.setOnPullRefreshListener(mRefreshListener);
    }

    public void getHotGoodCommentList(int offset) {
        RetrofitClient
                .getInstance()
                .apiMovieRankService()
                .getHotGoodCommentMovie(10, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FavourableMovieBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        showLoading();
                    }

                    @Override
                    public void onNext(@NonNull FavourableMovieBean favourableMovieBean) {
                        mMovieAdapter.setNewData(favourableMovieBean.getData().getMovies());

                        mTvTitle.setText(favourableMovieBean.getData().getTitle());

                        tvCreateDate = (TextView) mHeaderView.findViewById(R.id.tv_createDate);
                        tvContent = (TextView) mHeaderView.findViewById(R.id.tv_content);
                        tvCreateDate.setText(favourableMovieBean.getData().getCreated());
                        tvContent.setText(favourableMovieBean.getData().getContent());
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
                    offset = 0;
                    getHotGoodCommentList(offset);
                }
            });
        }
    }
}
