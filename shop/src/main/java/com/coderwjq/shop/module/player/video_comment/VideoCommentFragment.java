package com.coderwjq.shop.module.player.video_comment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.module.player.rxbus.CommentCountPostBean;
import com.coderwjq.shop.view.ProgressLayout;
import com.hwangjr.rxbus.RxBus;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by coderwjq on 2017/9/1 17:36.
 */

public class VideoCommentFragment extends BaseFragment implements VideoCommentListContract.IVideoCommentListView {
    private static final String VIDEO_ID = "video_id";
    @BindView(R.id.rv_video_comment)
    RecyclerView mRvVideoComment;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;
    Unbinder unbinder;
    private VideoCommentListPresenter mPresenter;
    private int mMovieId;
    private int offset;
    private VideoCommentAdapter mVideoCommentAdapter;

    public static VideoCommentFragment getInstance(int videoId) {
        Bundle args = new Bundle();
        args.putInt(VIDEO_ID, videoId);
        VideoCommentFragment fragment = new VideoCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_comment_list;
    }

    @Override
    protected void initView() {
        mPresenter = new VideoCommentListPresenter(getActivity(), this);

        RxBus.get().register(this);
    }

    @Override
    protected void initData() {
        mMovieId = getArguments().getInt(VIDEO_ID, 0);
    }

    @Override
    protected void initListener() {
        mVideoCommentAdapter = new VideoCommentAdapter();
        mRvVideoComment.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvVideoComment.setAdapter(mVideoCommentAdapter);

        mVideoCommentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getMoreVideoComment(mMovieId, offset);
            }
        }, mRvVideoComment);

        mPresenter.getVideoCommentList(mMovieId, offset);
    }

    @Override
    public void showLoading() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showLoading();
        }
    }

    @Override
    public void showContent() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(String error) {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showError(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.getVideoCommentList(mMovieId, offset);
                }
            });
        }
    }

    @Override
    public void addVideoCommentList(List<VideoCommentListBean.DataBean.CommentsBean> comments) {
        offset += 10;
        mVideoCommentAdapter.setNewData(comments);
    }

    @Override
    public void addVideoCommentCount(int total) {
        RxBus.get().post(new CommentCountPostBean(total));
    }

    @Override
    public void addMoreVideoComment(List<VideoCommentListBean.DataBean.CommentsBean> comments) {
        if (comments.size() > 0) {
            offset += 10;
            mVideoCommentAdapter.addData(comments);
            mVideoCommentAdapter.loadMoreComplete();
        } else {
            mVideoCommentAdapter.loadMoreEnd();
        }
    }

    @Override
    public void loadMoreError(String message) {
        mVideoCommentAdapter.loadMoreFail();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        RxBus.get().unregister(this);
    }
}
