package com.coderwjq.shop.module.player.video_comment;

import android.os.Bundle;

import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;

/**
 * Created by coderwjq on 2017/9/1 17:36.
 */

public class VideoCommentFragment extends BaseFragment {
    private static final String VIDEO_ID = "video_id";

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
