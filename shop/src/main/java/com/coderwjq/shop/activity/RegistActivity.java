package com.coderwjq.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderwjq.shop.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Created by coderwjq on 2017/5/17 17:18.
 * @Desc
 */

public class RegistActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    ImageView mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.til_password_confirm)
    TextInputLayout mTilPasswordConfirm;
    @BindView(R.id.btn_regist)
    Button mBtnRegist;
    private KProgressHUD mHud;

    public static void invoke(Activity srcActivity) {
        srcActivity.startActivity(new Intent(srcActivity, RegistActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        mTvTitle.setText("注册");
    }

    @OnClick({R.id.btn_back, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_regist:
                handleBtnRegistClick();
                break;
        }
    }

    private void handleBtnRegistClick() {
        mHud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在请求服务器...")
                .setDimAmount(0.5f);
        mHud.show();
        scheduleDismiss();
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHud.dismiss();
            }
        }, 2000);
    }
}
