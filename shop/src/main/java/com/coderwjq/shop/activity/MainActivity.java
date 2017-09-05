package com.coderwjq.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseConstant;
import com.coderwjq.shop.module.cinema.FragmentCinema;
import com.coderwjq.shop.module.discover.FragmentDiscover;
import com.coderwjq.shop.module.mine.FragmentMine;
import com.coderwjq.shop.module.movie.movie_main.FragmentMovieMain;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rg_main)
    RadioGroup mRgMain;
    private FragmentMovieMain mFragmentMovieMain;
    private FragmentCinema mFragmentCinema;
    private FragmentDiscover mFragmentDiscover;
    private FragmentMine mFragmentMine;
    private int mBottomBarHeight;

    public static void invoke(Activity srcActivity, boolean finishSelf) {
        srcActivity.startActivity(new Intent(srcActivity, MainActivity.class));
        if (finishSelf) {
            srcActivity.finish();
        }
    }

    public int getBottomBarHeight() {
        return mBottomBarHeight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRadioGroupListener();

        initFrament();
        switchFragment(BaseConstant.RB_MOVIE);

        mRgMain.measure(0, 0);
        mBottomBarHeight = mRgMain.getMeasuredHeight();
    }

    private void initRadioGroupListener() {
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_movie:
                        switchFragment(BaseConstant.RB_MOVIE);
                        break;
                    case R.id.rb_cinema:
                        switchFragment(BaseConstant.RB_CINEMA);
                        break;
                    case R.id.rb_discover:
                        switchFragment(BaseConstant.RB_DISCOVER);
                        break;
                    case R.id.rb_mine:
                        switchFragment(BaseConstant.RB_MINE);
                        break;
                }
            }
        });
    }

    private void switchFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case BaseConstant.RB_MOVIE:
                transaction.show(mFragmentMovieMain)
                        .hide(mFragmentCinema)
                        .hide(mFragmentDiscover)
                        .hide(mFragmentMine);
                break;
            case BaseConstant.RB_CINEMA:
                transaction.show(mFragmentCinema)
                        .hide(mFragmentMovieMain)
                        .hide(mFragmentDiscover)
                        .hide(mFragmentMine);
                break;
            case BaseConstant.RB_DISCOVER:
                transaction.show(mFragmentDiscover)
                        .hide(mFragmentCinema)
                        .hide(mFragmentMovieMain)
                        .hide(mFragmentMine);
                break;
            case BaseConstant.RB_MINE:
                transaction.show(mFragmentMine)
                        .hide(mFragmentCinema)
                        .hide(mFragmentDiscover)
                        .hide(mFragmentMovieMain);
                break;
        }
        transaction.commit();
    }

    /**
     * 初始化所有Fragment
     */
    private void initFrament() {
        mFragmentMovieMain = FragmentMovieMain.getInstance();
        mFragmentCinema = FragmentCinema.getInstance();
        mFragmentDiscover = FragmentDiscover.getInstance();
        mFragmentMine = FragmentMine.getInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container_main, mFragmentMovieMain)
                .add(R.id.fl_container_main, mFragmentCinema)
                .add(R.id.fl_container_main, mFragmentDiscover)
                .add(R.id.fl_container_main, mFragmentMine)
                .commit();
    }
}
