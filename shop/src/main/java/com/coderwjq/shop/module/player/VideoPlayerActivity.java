package com.coderwjq.shop.module.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coderwjq.shop.R;
import com.coderwjq.shop.module.player.model.MovieMusicBean;
import com.coderwjq.shop.module.player.rxbus.CommentCountPostBean;
import com.coderwjq.shop.module.player.rxbus.VideoPostBean;
import com.coderwjq.shop.module.player.video_comment.VideoCommentFragment;
import com.coderwjq.shop.module.player.video_list.VideoListFragment;
import com.coderwjq.shop.utils.UIUtils;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String TAG = "VideoPlayerActivity";

    private static final String MOVIE_ID = "movie_id";
    private static final String VIDEO_URL = "video_url";
    private static final String VIDEO_NAME = "video_name";
    private static final String VIDEO_ID = "video_id";
    private static final String IS_MV = "is_mv";
    private static final String MV_DATA = "mv_data";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard mVideoplayer;
    @BindView(R.id.view_indicator)
    View mViewIndicator;
    @BindView(R.id.tv_video_info)
    TextView mTvVideoInfo;
    @BindView(R.id.tv_video_comment)
    TextView mTvVideoComment;
    @BindView(R.id.tv_video_comment_count)
    TextView mTvVideoCommentCount;
    @BindView(R.id.rl_video_comment)
    RelativeLayout mRlVideoComment;
    @BindView(R.id.vp_video_comment)
    ViewPager mVpVideoComment;
    private int mVideoId;
    private int mMovieId;
    private boolean mIsMv;
    private MovieMusicBean.DataBean.ItemsBean.VideoTagVOBean mVideoBean;
    private FrameLayout.LayoutParams mIndecatorParams;

    public static void invoke(Context context, int movieId, int videoId, String videoName, String url) {
        Intent starter = new Intent(context, VideoPlayerActivity.class);
        starter.putExtra(MOVIE_ID, movieId);
        starter.putExtra(VIDEO_ID, videoId);
        starter.putExtra(VIDEO_NAME, videoName);
        starter.putExtra(VIDEO_URL, url);
        context.startActivity(starter);
    }

    public static void invoke(Context context, int movieId, int videoId, String videoName, String url, boolean isMV, MovieMusicBean.DataBean.ItemsBean.VideoTagVOBean dataBean) {
        Intent starter = new Intent(context, VideoPlayerActivity.class);
        starter.putExtra(MOVIE_ID, movieId);
        starter.putExtra(VIDEO_ID, videoId);
        starter.putExtra(VIDEO_NAME, videoName);
        starter.putExtra(VIDEO_URL, url);
        starter.putExtra(IS_MV, isMV);
        Bundle bundle = new Bundle();
        bundle.putParcelable(MV_DATA, dataBean);
        starter.putExtra("bundle", bundle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        String videoUrl = getIntent().getStringExtra(VIDEO_URL);
        String videoName = getIntent().getStringExtra(VIDEO_NAME);
        mVideoId = getIntent().getIntExtra(VIDEO_ID, 0);
        mMovieId = getIntent().getIntExtra(MOVIE_ID, 0);
        mIsMv = getIntent().getBooleanExtra(IS_MV, false);
        if (getIntent().getBundleExtra("bundle") != null) {
            mVideoBean = getIntent().getBundleExtra("bundle").getParcelable(MV_DATA);
        }

        mVideoplayer.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, videoName);

        setUpViewPager();
        RxBus.get().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 释放视频播放器资源
        mVideoplayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }

        super.onBackPressed();
    }

    private void setUpViewPager() {
        mIndecatorParams = (FrameLayout.LayoutParams) mViewIndicator.getLayoutParams();
        mIndecatorParams.width = UIUtils.getDeviceWidth(this) / 2;
        mIndecatorParams.setMargins(0, 0, 0, 0);
        mViewIndicator.setLayoutParams(mIndecatorParams);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(VideoListFragment.getInstance(mMovieId, mIsMv, mVideoBean));
        fragments.add(VideoCommentFragment.getInstance(mVideoId));

        mVpVideoComment.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        mVpVideoComment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0) {
                    // 停止滑动
                    mIndecatorParams.setMargins(position * mViewIndicator.getWidth(), 0, 0, 0);
                } else {
                    // 正在滑动
                    mIndecatorParams.setMargins((int) (mViewIndicator.getWidth() * (position + positionOffset)), 0, 0, 0);
                }

                mViewIndicator.setLayoutParams(mIndecatorParams);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mTvVideoInfo.setTextColor(getResources().getColor(R.color.colorPrimary, null));
                        mTvVideoComment.setTextColor(getResources().getColor(R.color.text_gray_deep, null));
                        break;
                    case 1:
                        mTvVideoComment.setTextColor(getResources().getColor(R.color.colorPrimary, null));
                        mTvVideoInfo.setTextColor(getResources().getColor(R.color.text_gray_deep, null));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTvVideoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVpVideoComment.setCurrentItem(0);
            }
        });

        mTvVideoComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVpVideoComment.setCurrentItem(1);
            }
        });
    }

    @Subscribe
    public void ChangeVideo(VideoPostBean videoPostBean) {
        mVideoplayer.setUp(videoPostBean.getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, videoPostBean.getVideoName());
        mVideoplayer.startVideo();

    }

    @Subscribe
    public void CommentCount(CommentCountPostBean count) {
        mTvVideoCommentCount.setVisibility(View.VISIBLE);
        mTvVideoCommentCount.setText(String.format("%s", count.getCommentCount()));
    }
}
