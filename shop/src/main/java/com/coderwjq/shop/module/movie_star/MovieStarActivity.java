package com.coderwjq.shop.module.movie_star;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coderwjq.shop.R;
import com.coderwjq.shop.activity.WebViewActivity;
import com.coderwjq.shop.module.movie_star.adapter.StarMoviesAdapter;
import com.coderwjq.shop.module.movie_star.adapter.StarPhotosAdapter;
import com.coderwjq.shop.module.movie_star.adapter.StarRelatedPeopleAdapter;
import com.coderwjq.shop.module.movie_star.model.MovieStarHonor;
import com.coderwjq.shop.module.movie_star.model.MovieStarInfoBean;
import com.coderwjq.shop.module.movie_star.model.RelatedInformationBean;
import com.coderwjq.shop.module.movie_star.model.StarMoviesBean;
import com.coderwjq.shop.module.movie_star.model.StarRelatedPeople;
import com.coderwjq.shop.utils.ErrorHanding;
import com.coderwjq.shop.utils.FastBlurUtil;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;
import com.coderwjq.shop.utils.StringUtil;
import com.coderwjq.shop.view.ProgressLayout;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MovieStarActivity extends AppCompatActivity implements MovieStarContract.IMovieStarView {
    private static final String TAG = "MovieStarActivity";
    private static final String STAR_ID = "star_id";
    @BindView(R.id.iv_star_bg)
    ImageView mIvStarBg;
    @BindView(R.id.iv_star_avatar)
    ImageView mIvStarAvatar;
    @BindView(R.id.tv_star_name2)
    TextView mTvStarName2;
    @BindView(R.id.tv_star_english_name2)
    TextView mTvStarEnglishName2;
    @BindView(R.id.ll_star_name2)
    LinearLayout mLlStarName2;
    @BindView(R.id.tv_star_name)
    TextView mTvStarName;
    @BindView(R.id.tv_star_english_name)
    TextView mTvStarEnglishName;
    @BindView(R.id.ll_star_name)
    LinearLayout mLlStarName;
    @BindView(R.id.tv_star_rank)
    TextView mTvStarRank;
    @BindView(R.id.tv_star_fans_count)
    TextView mTvStarFansCount;
    @BindView(R.id.tv_star_major_movie_box)
    TextView mTvStarMajorMovieBox;
    @BindView(R.id.tv_star_title)
    TextView mTvStarTitle;
    @BindView(R.id.tv_star_birthday)
    TextView mTvStarBirthday;
    @BindView(R.id.tv_star_info_desc)
    TextView mTvStarInfoDesc;
    @BindView(R.id.tv_star_movies_count)
    TextView mTvStarMoviesCount;
    @BindView(R.id.rv_star_movies)
    RecyclerView mRvStarMovies;
    @BindView(R.id.ll_star_movie)
    LinearLayout mLlStarMovie;
    @BindView(R.id.tv_star_photos_count)
    TextView mTvStarPhotosCount;
    @BindView(R.id.rv_star_photos)
    RecyclerView mRvStarPhotos;
    @BindView(R.id.ll_star_photos)
    LinearLayout mLlStarPhotos;
    @BindView(R.id.tv_win_award_times)
    TextView mTvWinAwardTimes;
    @BindView(R.id.tv_nominate_times)
    TextView mTvNominateTimes;
    @BindView(R.id.tv_award_title)
    TextView mTvAwardTitle;
    @BindView(R.id.tv_award_content)
    TextView mTvAwardContent;
    @BindView(R.id.ll_honor)
    LinearLayout mLlHonor;
    @BindView(R.id.iv_related_information)
    ImageView mIvRelatedInformation;
    @BindView(R.id.tv_related_information_content)
    TextView mTvRelatedInformationContent;
    @BindView(R.id.ll_related_information)
    LinearLayout mLlRelatedInformation;
    @BindView(R.id.rv_related_stars)
    RecyclerView mRvRelatedStars;
    @BindView(R.id.ll_related_stars)
    LinearLayout mLlRelatedStars;
    @BindView(R.id.sc_star_detail)
    NestedScrollView mScStarDetail;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;
    @BindView(R.id.status_bar_bg)
    View mStatusBarBg;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.divider)
    View mDivider;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_subTitle)
    TextView mTvSubTitle;
    @BindView(R.id.iv_title_right_icon)
    ImageView mIvTitleRightIcon;
    @BindView(R.id.rl_right_icon)
    RelativeLayout mRlRightIcon;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
    @BindView(R.id.rl_top_container)
    RelativeLayout mRlTopContainer;
    private MovieStarPresenter mPresenter;
    private int mStarId;
    private int mStatusBarHeight;
    private FrameLayout.LayoutParams mTitleParams;

    public static void invoke(Context context, int starId) {
        Intent starter = new Intent(context, MovieStarActivity.class);
        starter.putExtra(STAR_ID, starId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_star);
        ButterKnife.bind(this);

        handleIntent();

        mPresenter = new MovieStarPresenter(this, this);

        initStatusBar();
        initDynamicTitle();
        initListener();

        mPresenter.getMovieStarInfo(mStarId);
    }

    private void initListener() {
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initDynamicTitle() {
        mRlTopContainer.measure(0, 0);
        mLlTitle.measure(0, 0);
        final int topHeight = mRlTopContainer.getMeasuredHeight() - mLlTitle.getMeasuredHeight();

        mScStarDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float scale = (float) v.getScrollY() / topHeight;

                //变化范围0-255 表示从透明到纯色背景
                float alpha = scale * 255 >= 255 ? 255 : scale * 255;

                mLlTitle.setBackgroundColor(Color.argb((int) alpha, 0, 153, 204));
                mTvTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                mTvSubTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            }
        });
    }

    private void initStatusBar() {
        // 设置状态栏view的高度
        LinearLayout.LayoutParams statusViewParams = (LinearLayout.LayoutParams) mStatusBarBg.getLayoutParams();
        // 获取状态栏高度
        mStatusBarHeight = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
        statusViewParams.height = mStatusBarHeight;
        mStatusBarBg.setLayoutParams(statusViewParams);

        mTitleParams = (FrameLayout.LayoutParams) mLlTitle.getLayoutParams();
        mTitleParams.setMargins(0, -mStatusBarHeight, 0, 0);
        mLlTitle.setLayoutParams(mTitleParams);

        FrameLayout.LayoutParams progressParams = (FrameLayout.LayoutParams) mProgressLayout.getLayoutParams();
        progressParams.setMargins(0, -mStatusBarHeight, 0, 0);
        mProgressLayout.setLayoutParams(progressParams);
    }

    private void handleIntent() {
        mStarId = getIntent().getIntExtra(STAR_ID, 0);
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
                    mPresenter.getMovieStarInfo(mStarId);
                }
            });
        }
    }

    @Override
    public void addMovieStarInfo(MovieStarInfoBean.DataBean info) {
        // 设置标题栏
        mTvTitle.setTextColor(getResources().getColor(android.R.color.transparent, null));
        mTvSubTitle.setTextColor(getResources().getColor(android.R.color.transparent, null));
        mTvTitle.setText(info.getCnm()); // 中文主标题
        mTvSubTitle.setText(info.getEnm()); // 英文副标题

        // 设置照片和姓名
        if (info.getBgImg() != null) {
            mTvStarName.setText(info.getCnm());
            mTvStarEnglishName.setText(info.getEnm());
            mLlStarName.setVisibility(View.VISIBLE);
            mLlStarName2.setVisibility(View.INVISIBLE);
            String imgUrl = ImgSizeUtil.resetPicUrl(info.getBgImg(), "@2250w_1380h_1e_1l");
            GlideManager.loadImage(this, imgUrl, mIvStarBg);
        } else {
            mTvStarName2.setText(info.getCnm());
            mTvStarEnglishName2.setText(info.getEnm());
            mLlStarName2.setVisibility(View.VISIBLE);
            mLlStarName.setVisibility(View.INVISIBLE);
            String avatarUrl = ImgSizeUtil.resetPicUrl(info.getAvatar(), "");
            GlideManager.loadImage(this, avatarUrl, mIvStarAvatar);
            // url -> bitmap -> null? -> blur -> show
            Observable.just(avatarUrl)
                    .map(new Function<String, Bitmap>() {
                        @Override
                        public Bitmap apply(@NonNull String s) throws Exception {
                            try {
                                URL url = new URL(s);
                                return BitmapFactory.decodeStream(url.openStream());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    })
                    .filter(new Predicate<Bitmap>() {
                        @Override
                        public boolean test(@NonNull Bitmap bitmap) throws Exception {
                            return bitmap != null;
                        }
                    })
                    .map(new Function<Bitmap, Bitmap>() {
                        @Override
                        public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                            return FastBlurUtil.doBlur(bitmap, 130, false);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Bitmap>() {
                        @Override
                        public void accept(@NonNull Bitmap bitmap) throws Exception {
                            mIvStarBg.setImageBitmap(bitmap);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Log.e(TAG, "accept: " + ErrorHanding.handleError(throwable), throwable);
                        }
                    });
        }

        // 设置票房和排名
        if (info.getSumBox() == 0) {
            mTvStarMajorMovieBox.setTextColor(getResources().getColor(R.color.text_gray, null));
            mTvStarMajorMovieBox.setText("暂无");
        } else {
            mTvStarMajorMovieBox.setText(StringUtil.changeMillionIntoBillion(info.getSumBox()));
        }
        mTvStarRank.setText(String.format("%s", info.getRank() == -1 ? "1000+" : info.getFollowCount()));
        mTvStarFansCount.setText(String.format("%s", info.getFollowCount()));

        // 设置个人资料
        mTvStarTitle.setText(info.getTitles());
        mTvStarBirthday.setText(String.format("生日:%s", info.getBirthday().equals("") ? "暂无" : info.getBirthday()));
        mTvStarInfoDesc.setText(String.format("简介:%s", info.getDesc().equals("") ? "暂无" : info.getDesc()));

        // 设置个人照片
        if (info.getPhotos().size() == 0) {
            mLlStarPhotos.setVisibility(View.GONE);
        } else {
            mTvStarPhotosCount.setText(String.format("全部(%s)", info.getPhotoNum()));
            StarPhotosAdapter starPhotosAdapter = new StarPhotosAdapter();
            mRvStarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mRvStarMovies.setAdapter(starPhotosAdapter);
            starPhotosAdapter.setNewData(info.getPhotos());
        }
    }

    @Override
    public void addMovieStarHonor(MovieStarHonor data) {
        Observable.just(data)
                .flatMap(new Function<MovieStarHonor, ObservableSource<MovieStarHonor.DataBean>>() {
                    @Override
                    public ObservableSource<MovieStarHonor.DataBean> apply(@NonNull MovieStarHonor movieStarHonor) throws Exception {
                        if (movieStarHonor.getData().getAward() != null) {
                            return Observable.just(movieStarHonor.getData());
                        }
                        return Observable.error(new Exception("empty data"));
                    }
                })
                .subscribe(new Consumer<MovieStarHonor.DataBean>() {
                    @Override
                    public void accept(@NonNull MovieStarHonor.DataBean dataBean) throws Exception {
                        mTvWinAwardTimes.setText(String.format("%s", dataBean.getAwardCount() == null ? "0" : dataBean.getAwardCount()));
                        mTvNominateTimes.setText(String.format("%s", dataBean.getNomCount() == null ? "0" : dataBean.getNomCount()));
                        mTvAwardTitle.setText(dataBean.getFestivalName());
                        mTvAwardContent.setText(dataBean.getPrizeDesc());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mLlHonor.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void addStarMovie(StarMoviesBean.DataBean moviesData) {
        if (moviesData.getMovies().size() == 0) {
            mLlStarMovie.setVisibility(View.GONE);
        } else {
            mTvStarMoviesCount.setText(String.format("全部(%s)", moviesData.getPaging().getTotal()));
            mRvStarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            StarMoviesAdapter starMoviesAdapter = new StarMoviesAdapter();
            mRvStarMovies.setAdapter(starMoviesAdapter);
            starMoviesAdapter.setNewData(moviesData.getMovies());
        }
    }

    @Override
    public void addRelatedInfo(RelatedInformationBean data) {
        Observable.just(data)
                .flatMap(new Function<RelatedInformationBean, ObservableSource<RelatedInformationBean.DataBean.NewsListBean>>() {
                    @Override
                    public ObservableSource<RelatedInformationBean.DataBean.NewsListBean> apply(@NonNull RelatedInformationBean relatedInformationBean) throws Exception {
                        if (relatedInformationBean.getData().getNewsList().size() > 0) {
                            return Observable.fromIterable(relatedInformationBean.getData().getNewsList());
                        }
                        return Observable.error(new Exception("empty data"));
                    }
                })
                .subscribe(new Consumer<RelatedInformationBean.DataBean.NewsListBean>() {
                    @Override
                    public void accept(@NonNull final RelatedInformationBean.DataBean.NewsListBean newsListBean) throws Exception {
                        GlideManager.loadImage(MovieStarActivity.this, newsListBean.getPreviewImages().get(0).getUrl(), mIvRelatedInformation);
                        mTvRelatedInformationContent.setText(newsListBean.getTitle());
                        mLlRelatedInformation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WebViewActivity.invoke(MovieStarActivity.this, StringUtil.getRealUrl(newsListBean.getUrl()));
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mLlRelatedInformation.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void addRelatedPeople(StarRelatedPeople relatedPeople) {
        Observable.just(relatedPeople)
                .flatMap(new Function<StarRelatedPeople, ObservableSource<StarRelatedPeople.DataBean>>() {
                    @Override
                    public ObservableSource<StarRelatedPeople.DataBean> apply(@NonNull StarRelatedPeople starRelatedPeople) throws Exception {
                        if (starRelatedPeople.getData().getRelations().size() > 0) {
                            return Observable.just(starRelatedPeople.getData());
                        }
                        return Observable.error(new Exception("empty data"));
                    }
                })
                .subscribe(new Consumer<StarRelatedPeople.DataBean>() {
                    @Override
                    public void accept(@NonNull StarRelatedPeople.DataBean dataBean) throws Exception {
                        StarRelatedPeopleAdapter starRelatedPeopleAdapter = new StarRelatedPeopleAdapter();
                        mRvRelatedStars.setLayoutManager(new LinearLayoutManager(MovieStarActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        mRvRelatedStars.setAdapter(starRelatedPeopleAdapter);
                        starRelatedPeopleAdapter.setNewData(dataBean.getRelations());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mLlRelatedStars.setVisibility(View.GONE);
                    }
                });

    }
}
