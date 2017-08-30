package com.coderwjq.shop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    ImageView mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;
    @BindView(R.id.btn_regist)
    Button mBtnRegist;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    private KProgressHUD mHud;

    public static void invoke(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initHUD();
    }

    private void initHUD() {
        mHud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在登陆...")
                .setDimAmount(0.5f);
    }

    @OnClick({R.id.btn_back, R.id.btn_regist, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_regist:
                RegistActivity.invoke(LoginActivity.this);
                break;
            case R.id.btn_login:
                mHud.show();
                scheduleDismiss();
                break;
        }
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHud.dismiss();
                MainActivity.invoke(LoginActivity.this, true);
            }
        }, 500);
    }
}
