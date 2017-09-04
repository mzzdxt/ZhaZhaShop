package com.coderwjq.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coderwjq.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {
    private static final String TARGET_ID = "id";
    private static final String URL = "url";
    private static final String TITLE = "title";
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
    @BindView(R.id.wv_webview)
    WebView mWvWebview;
    private String mUrl;
    private String mTitle;
    private int mTargetId;

    public static void invoke(Context context, int id) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(TARGET_ID, id);
        context.startActivity(starter);
    }

    public static void invoke(Context context, String url) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(URL, url);
        context.startActivity(starter);
    }

    public static void invoke(Context context, String url, String title) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra(URL, url);
        starter.putExtra(TITLE, title);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        handleIntent();
        setupWebView();

        initListener();

        if (mTargetId != 0) {
            String url = "http://m.maoyan.com/topic/" + mTargetId + "?_v_=yes&f=android";
            mWvWebview.loadUrl(url);
        }

        if (mTitle != null) {
            mTvTitle.setText(mTitle);
        }

        if (mUrl != null) {
            mWvWebview.loadUrl(mUrl);
        }
    }

    private void initListener() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupWebView() {
        WebSettings settings = mWvWebview.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }
        };

        mWvWebview.setWebViewClient(webViewClient);
    }

    private void handleIntent() {
        mTargetId = getIntent().getIntExtra(TARGET_ID, 0);
        mUrl = getIntent().getStringExtra(URL);
        mTitle = getIntent().getStringExtra(TITLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWvWebview.canGoBack()) {
            mWvWebview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
