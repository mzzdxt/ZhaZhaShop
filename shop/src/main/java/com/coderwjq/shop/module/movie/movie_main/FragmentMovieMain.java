package com.coderwjq.shop.module.movie.movie_main;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseFragment;
import com.coderwjq.shop.module.movie.movie_find.FragmentMovieFind;
import com.coderwjq.shop.module.movie.movie_hot.FragmentMovieHot;
import com.coderwjq.shop.module.movie.movie_wait.FragmentMovieWait;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Created by coderwjq on 2017/5/25 14:44.
 * @Desc
 */

public class FragmentMovieMain extends BaseFragment implements ViewPager.OnPageChangeListener {

    private static final String TAG = "FragmentMovieMain";
    @BindView(R.id.view_indicator)
    View mViewIndicator;
    @BindView(R.id.tv_hot_movie)
    TextView mTvHotMovie;
    @BindView(R.id.tv_wait_movie)
    TextView mTvWaitMovie;
    @BindView(R.id.tv_find_movie)
    TextView mTvFindMovie;
    @BindView(R.id.tv_area_name)
    TextView mTvAreaName;
    @BindView(R.id.vp_movie)
    ViewPager mVpMovie;
    private FragmentMovieHot mFragmentMovieHot;
    private FragmentMovieWait mFragmentMovieWait;
    private FragmentMovieFind mFragmentMovieFind;
    private ArrayList<BaseFragment> mFragmentList;
    private MovieMainAdapter mMovieMainAdapter;
    private int mSelectedColor;
    private int mNormalColor;
    private FrameLayout.LayoutParams mParams;

    public static FragmentMovieMain getInstance() {
        return new FragmentMovieMain();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        initFragment();
        initAdapter();
        initColorResource();
    }

    private void initColorResource() {
        mSelectedColor = getResources().getColor(R.color.holo_blue_dark, null);
        mNormalColor = getResources().getColor(R.color.white, null);
    }

    private void initAdapter() {
        mMovieMainAdapter = new MovieMainAdapter(getFragmentManager(), mFragmentList);
        mVpMovie.setAdapter(mMovieMainAdapter);
    }

    private void initFragment() {
        mFragmentMovieHot = FragmentMovieHot.getInstance();
        mFragmentMovieWait = FragmentMovieWait.getInstance();
        mFragmentMovieFind = FragmentMovieFind.getInstance();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mFragmentMovieHot);
        mFragmentList.add(mFragmentMovieWait);
        mFragmentList.add(mFragmentMovieFind);
    }

    @Override
    protected void initListener() {
        mVpMovie.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e(TAG, "onPageScrolled: " + position);
        boolean lessThanHalf = positionOffset < 0.5;

        // alpha变化范围：-255~255
        float alpha = (1 - positionOffset * 2) * 255;
        int alphaWhite = Color.argb((int) alpha, 255, 255, 255); // 白渐变到透明
        int alphaBlue = Color.argb((int) alpha, 0, 153, 204); // 红渐变到透明

        int alphaWhiteReverse = Color.argb((int) -alpha, 255, 255, 255); // 白渐变到透明
        int alphaBlueReverse = Color.argb((int) -alpha, 0, 153, 204); // 红渐变到透明

        //获取滑块的Layout
        mParams = (FrameLayout.LayoutParams) mViewIndicator.getLayoutParams();
        //判断滑动的距离
        if (positionOffset == 0) { // 停止滚动
            mParams.setMargins(mViewIndicator.getWidth() * position, 0, 0, 0);
        } else {
            mParams.setMargins((int) (mViewIndicator.getWidth() * (position + positionOffset)), 0, 0, 0);
        }
        //通过LayoutParams设置滑块的位置
        mViewIndicator.setLayoutParams(mParams);

        switch (position) {
            case 0:
                if (lessThanHalf) {
                    mTvHotMovie.setTextColor(alphaBlue);
                    mTvWaitMovie.setTextColor(alphaWhite);
                } else {
                    mTvHotMovie.setTextColor(alphaWhiteReverse);
                    mTvWaitMovie.setTextColor(alphaBlueReverse);
                }
                break;
            case 1:
                if (lessThanHalf) {
                    mTvWaitMovie.setTextColor(alphaBlue);
                    mTvFindMovie.setTextColor(alphaWhite);
                } else {
                    mTvWaitMovie.setTextColor(alphaWhiteReverse);
                    mTvFindMovie.setTextColor(alphaBlueReverse);
                }
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mTvHotMovie.setTextColor(mSelectedColor);
                mTvWaitMovie.setTextColor(mNormalColor);
                mTvFindMovie.setTextColor(mNormalColor);
                break;
            case 1:
                mTvHotMovie.setTextColor(mNormalColor);
                mTvWaitMovie.setTextColor(mSelectedColor);
                mTvFindMovie.setTextColor(mNormalColor);
                break;
            case 2:
                mTvHotMovie.setTextColor(mNormalColor);
                mTvWaitMovie.setTextColor(mNormalColor);
                mTvFindMovie.setTextColor(mSelectedColor);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.tv_hot_movie, R.id.tv_wait_movie, R.id.tv_find_movie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_hot_movie:
                mVpMovie.setCurrentItem(0);
                break;
            case R.id.tv_wait_movie:
                mVpMovie.setCurrentItem(1);
                break;
            case R.id.tv_find_movie:
                mVpMovie.setCurrentItem(2);
                break;
        }
    }
}
