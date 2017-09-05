package com.coderwjq.shop.module.movie_rank.oversea_rank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coderwjq.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieOverseaActivity extends AppCompatActivity {

    private static final String TAG = "MovieOverseaActivity";

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
    @BindView(R.id.tab_oversea)
    TabLayout mTabOversea;
    @BindView(R.id.fl_container_oversea_movie)
    FrameLayout mFlContainerOverseaMovie;

    private MovieOverseaFragment fg1;
    private MovieOverseaFragment fg2;
    private MovieOverseaFragment fg3;

    private String TAG_FG_1 = "movie_oversea_fg1";
    private String TAG_FG_2 = "movie_oversea_fg2";
    private String TAG_FG_3 = "movie_oversea_fg3";

    public static void invoke(Context context) {
        Intent starter = new Intent(context, MovieOverseaActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_oversea);
        ButterKnife.bind(this);

        initView();
        initFragments();
        initTablayout();
    }

    private void initTablayout() {
        mTabOversea.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabOversea.addTab(mTabOversea.newTab().setText("美国"), true);
        mTabOversea.addTab(mTabOversea.newTab().setText("韩国"));
        mTabOversea.addTab(mTabOversea.newTab().setText("日本"));
    }

    private void switchTabFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                fg1.setUserVisibleHint(true);
                fg2.setUserVisibleHint(false);
                fg3.setUserVisibleHint(false);
                transaction.show(fg1).hide(fg2).hide(fg3);
                break;
            case 1:
                fg2.setUserVisibleHint(true);
                fg1.setUserVisibleHint(false);
                fg3.setUserVisibleHint(false);
                transaction.show(fg2).hide(fg1).hide(fg3);
                break;
            case 2:
                fg3.setUserVisibleHint(true);
                fg2.setUserVisibleHint(false);
                fg1.setUserVisibleHint(false);
                transaction.show(fg3).hide(fg2).hide(fg1);
                break;
        }
        transaction.commit();
    }

    private void initFragments() {
        fg1 = MovieOverseaFragment.getInstance("NA");
        fg2 = MovieOverseaFragment.getInstance("KR");
        fg3 = MovieOverseaFragment.getInstance("JP");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container_oversea_movie, fg1, TAG_FG_1)
                .add(R.id.fl_container_oversea_movie, fg2, TAG_FG_2)
                .add(R.id.fl_container_oversea_movie, fg3, TAG_FG_3)
                .commit();

        switchTabFragment(0);
    }

    private void initView() {
        mTvTitle.setText("海外电影");
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
