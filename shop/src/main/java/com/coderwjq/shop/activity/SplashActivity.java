package com.coderwjq.shop.activity;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.code19.library.DensityUtil;
import com.coderwjq.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    @BindView(R.id.civ_icon)
    CircleImageView mCivIcon;
    @BindView(R.id.tv_welcome)
    AppCompatTextView mTvWelcome;
    @BindView(R.id.tv_author)
    AppCompatTextView mTvAuthor;
    @BindView(R.id.iv_background)
    ImageView mIvBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initBackgroundBlur();
        initIconAnimation();
    }

    private void initBackgroundBlur() {
        Glide.with(this).load(R.drawable.icon_coderwjq)
                .bitmapTransform(new BlurTransformation(SplashActivity.this, 6))
                .into(mIvBackground);
    }

    /**
     * 初始化文字透明动画
     */
    private void initTextAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTvWelcome.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LoginActivity.invoke(SplashActivity.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mTvWelcome.startAnimation(alphaAnimation);
    }

    /**
     * 初始化开始动画
     */
    private void initIconAnimation() {
        int screenH = DensityUtil.getScreenH(this);

        SpringForce springForce = new SpringForce()
                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_VERY_LOW)
                .setFinalPosition(0);
        SpringAnimation springAnimation = new SpringAnimation(mCivIcon, DynamicAnimation.TRANSLATION_Y)
                .setSpring(springForce)
                .setStartValue(screenH);

        springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                initTextAnimation();
            }
        });

        springAnimation.start();
    }
}
