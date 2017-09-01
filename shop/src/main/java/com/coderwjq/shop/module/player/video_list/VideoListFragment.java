package com.coderwjq.shop.module.player.video_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.module.player.model.MovieMusicBean;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by coderwjq on 2017/9/1 17:36.
 */

public class VideoListFragment extends BaseFragment implements VideoListContract.IVideoListView {
    private static final String TAG = "VideoListFragment";

    private static final String MOVIE_ID = "movie_id";
    private static final String IS_MV = "is_mv";
    private static final String MV_DATA = "mv_data";
    @BindView(R.id.tv_movie_name)
    TextView mTvMovieName;
    @BindView(R.id.tv_movie_score)
    TextView mTvMovieScore;
    @BindView(R.id.tv_score_wish)
    TextView mTvScoreWish;
    @BindView(R.id.tv_pub_time)
    TextView mTvPubTime;
    @BindView(R.id.view_divider)
    View mViewDivider;
    @BindView(R.id.tv_video_count)
    TextView mTvVideoCount;
    @BindView(R.id.rv_movie_video)
    RecyclerView mRvMovieVideo;
    @BindView(R.id.swipe)
    SuperSwipeRefreshLayout mSwipe;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;
    Unbinder unbinder;
    private MovieMusicBean.DataBean.ItemsBean.VideoTagVOBean mMvData;
    private boolean mIsMv;
    private int mMovieId;
    private VideoListPresenter mVideoListPresenter;
    private ArrayList<VideoListBean.DataBean> mVideoListBean;
    private VideoListAdapter mVideoListAdapter;
    private MyPullToRefreshListener mRefreshListener;
    private int offset;

    public static VideoListFragment getInstance(int movieId, boolean isMv, MovieMusicBean.DataBean.ItemsBean.VideoTagVOBean dataBean) {
        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieId);
        args.putBoolean(IS_MV, isMv);
        args.putParcelable(MV_DATA, dataBean);
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_list;
    }

    @Override
    protected void initView() {
        RxBus.get().register(this);

        mMovieId = getArguments().getInt(MOVIE_ID, 0);
        mIsMv = getArguments().getBoolean(IS_MV, false);
        mMvData = getArguments().getParcelable(MV_DATA);

        mVideoListPresenter = new VideoListPresenter(getActivity(), this);

        // 下拉刷新
        mRefreshListener = new MyPullToRefreshListener(getActivity(), mSwipe);
        mSwipe.setOnPullRefreshListener(mRefreshListener);
        mRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                offset = 0;
                mVideoListPresenter.getMovieList(mMovieId, offset);
            }
        });

        mRvMovieVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        mVideoListAdapter = new VideoListAdapter();
        mRvMovieVideo.setAdapter(mVideoListAdapter);

        // 加载更多
        mVideoListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mVideoListPresenter.getMoreVideoList(mMovieId, offset);
            }
        }, mRvMovieVideo);

        mVideoListPresenter.getMovieList(mMovieId, offset);
        mVideoListPresenter.getVideoMovieInfo(mMovieId);
    }

    @Override
    protected void initData() {
        mVideoListBean = new ArrayList<>();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RxBus.get().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
                    mVideoListPresenter.getMovieList(mMovieId, offset);
                }
            });
        }
    }

    @Override
    public void addVideoList(List<VideoListBean.DataBean> data) {
        Log.d(TAG, "addVideoList() called with: data = [" + data + "]");
        // 此处有严重bug...待修复
        offset += 10;
        mVideoListBean.addAll(data);
        // 将第一个数据设为选中状态,因为默认播放第一个视频
        if (!mIsMv) {
            mVideoListBean.get(0).isSelect = true;
            mVideoListBean.set(0, mVideoListBean.get(0));
        } else {
            VideoListBean.DataBean newData = new VideoListBean.DataBean();
            newData.isSelect = true;
            newData.setCount(mMvData.getCount());
            newData.setImg(mMvData.getImg());
            newData.setMovieId(mMvData.getMovieId());
            newData.setId(mMvData.getId());
            newData.setUrl(mMvData.getUrl());
            newData.setTl(mMvData.getTitle());
            newData.setTm(mMvData.getTime());
            mVideoListBean.add(0, newData);
        }
        mVideoListAdapter.setNewData(mVideoListBean);
    }

    @Override
    public void addVideoMovieInfo(VideoMovieInfoBean.DataBean videoMovieInfoBeanData) {
        mTvMovieName.setText(videoMovieInfoBeanData.getName());
        if (videoMovieInfoBeanData.getScore() == 0) {
            mTvMovieScore.setText(String.format("%s", videoMovieInfoBeanData.getWish()));
            mTvScoreWish.setText("人想看");
        } else {
            mTvMovieScore.setText(String.format("%s", videoMovieInfoBeanData.getScore()));
            mTvScoreWish.setText("分");
        }
        mTvPubTime.setText(videoMovieInfoBeanData.getPubdesc());
    }

    @Override
    public void addTotalCount(int total) {
        mTvVideoCount.setText(String.format("(%s)", total));
    }

    @Override
    public void addVideoMoreList(List<VideoListBean.DataBean> videoMoreData) {
        if (videoMoreData.size() > 0) {
            offset += 10;
            mVideoListAdapter.addData(videoMoreData);
            mVideoListAdapter.loadMoreComplete();
        } else {
            mVideoListAdapter.loadMoreEnd();
        }
    }

    @Override
    public void showLoadMoreError(String message) {
        mVideoListAdapter.loadMoreFail();
    }
}
