package com.coderwjq.shop.module.movieDetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.coderwjq.shop.module.movieDetail.adapter.MoviePhotosAdapter;
import com.coderwjq.shop.module.movieDetail.model.MovieAwardsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieBasicDataBean;
import com.coderwjq.shop.module.movieDetail.model.MovieCommentTagBean;
import com.coderwjq.shop.module.movieDetail.model.MovieLongCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieMoneyBoxBean;
import com.coderwjq.shop.module.movieDetail.model.MoviePhotosBean;
import com.coderwjq.shop.module.movieDetail.model.MovieProCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieRelatedInformationBean;
import com.coderwjq.shop.module.movieDetail.model.MovieResourceBean;
import com.coderwjq.shop.module.movieDetail.model.MovieStarBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTipsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTopicBean;
import com.coderwjq.shop.module.movieDetail.model.RelatedMovieBean;
import com.coderwjq.shop.utils.ErrorHanding;
import com.coderwjq.shop.utils.FastBlurUtil;
import com.coderwjq.shop.utils.GlideManager;
import com.coderwjq.shop.utils.ImgSizeUtil;
import com.coderwjq.shop.utils.StringUtil;
import com.coderwjq.shop.utils.ToastUtil;
import com.coderwjq.shop.utils.UIUtils;
import com.coderwjq.shop.view.ExpandTextView;
import com.coderwjq.shop.view.ProgressLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coderwjq on 2017/8/31 8:59.
 */

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.IMovieDetailView {
    public static final String MOVIE_ID = "movie_id";
    private static final String TAG = "MovieDetailActivity";
    @BindView(R.id.iv_blur_bg)
    ImageView mIvBlurBg;
    @BindView(R.id.iv_movie_img)
    ImageView mIvMovieImg;
    @BindView(R.id.fl_movie_img)
    FrameLayout mFlMovieImg;
    @BindView(R.id.tv_movie_name)
    TextView mTvMovieName;
    @BindView(R.id.iv_win_awards)
    ImageView mIvWinAwards;
    @BindView(R.id.tv_movie_english_name)
    TextView mTvMovieEnglishName;
    @BindView(R.id.tv_people_judge)
    TextView mTvPeopleJudge;
    @BindView(R.id.tv_movie_score)
    TextView mTvMovieScore;
    @BindView(R.id.tv_snum)
    TextView mTvSnum;
    @BindView(R.id.ll_score)
    LinearLayout mLlScore;
    @BindView(R.id.tv_movie_type)
    TextView mTvMovieType;
    @BindView(R.id.tv_src_dur)
    TextView mTvSrcDur;
    @BindView(R.id.tv_pubDesc)
    TextView mTvPubDesc;
    @BindView(R.id.rl_movie_info)
    RelativeLayout mRlMovieInfo;
    @BindView(R.id.expandText_movie_Desc)
    ExpandTextView mExpandTextMovieDesc;
    @BindView(R.id.rv_movie_tips)
    RecyclerView mRvMovieTips;
    @BindView(R.id.ll_tips)
    LinearLayout mLlTips;
    @BindView(R.id.rv_movie_star)
    RecyclerView mRvMovieStar;
    @BindView(R.id.rv_movie_photos)
    RecyclerView mRvMoviePhotos;
    @BindView(R.id.iv_movie_music)
    ImageView mIvMovieMusic;
    @BindView(R.id.tv_music_name)
    TextView mTvMusicName;
    @BindView(R.id.tv_music_num)
    TextView mTvMusicNum;
    @BindView(R.id.ll_movie_music)
    LinearLayout mLlMovieMusic;
    @BindView(R.id.rl_money_box)
    RelativeLayout mRlMoneyBox;
    @BindView(R.id.tv_lastDayRank)
    TextView mTvLastDayRank;
    @BindView(R.id.tv_firstWeekBox)
    TextView mTvFirstWeekBox;
    @BindView(R.id.tv_sumBox)
    TextView mTvSumBox;
    @BindView(R.id.tv_sum_box_content)
    TextView mTvSumBoxContent;
    @BindView(R.id.ll_money_box)
    LinearLayout mLlMoneyBox;
    @BindView(R.id.rv_movie_awards)
    RecyclerView mRvMovieAwards;
    @BindView(R.id.rv_movie_resource)
    RecyclerView mRvMovieResource;
    @BindView(R.id.rv_movie_pro_comment)
    RecyclerView mRvMovieProComment;
    @BindView(R.id.ll_pro_comment)
    LinearLayout mLlProComment;
    @BindView(R.id.flowLayout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.tv_long_comment)
    TextView mTvLongComment;
    @BindView(R.id.tv_no_long_comment)
    TextView mTvNoLongComment;
    @BindView(R.id.rv_long_comment)
    RecyclerView mRvLongComment;
    @BindView(R.id.iv_related_information)
    ImageView mIvRelatedInformation;
    @BindView(R.id.tv_related_information_title)
    TextView mTvRelatedInformationTitle;
    @BindView(R.id.ll_related_information_content)
    LinearLayout mLlRelatedInformationContent;
    @BindView(R.id.tv_related_information_author)
    TextView mTvRelatedInformationAuthor;
    @BindView(R.id.tv_related_information_view_count)
    TextView mTvRelatedInformationViewCount;
    @BindView(R.id.tv_related_information_comment_count)
    TextView mTvRelatedInformationCommentCount;
    @BindView(R.id.ll_all_related_information)
    LinearLayout mLlAllRelatedInformation;
    @BindView(R.id.ll_related_information)
    LinearLayout mLlRelatedInformation;
    @BindView(R.id.iv_related_topic)
    ImageView mIvRelatedTopic;
    @BindView(R.id.tv_related_topic_title)
    TextView mTvRelatedTopicTitle;
    @BindView(R.id.tv_related_topic_author)
    TextView mTvRelatedTopicAuthor;
    @BindView(R.id.tv_related_topic_view_count)
    TextView mTvRelatedTopicViewCount;
    @BindView(R.id.tv_related_topic_comment_count)
    TextView mTvRelatedTopicCommentCount;
    @BindView(R.id.ll_all_related_topic)
    LinearLayout mLlAllRelatedTopic;
    @BindView(R.id.ll_related_topic)
    LinearLayout mLlRelatedTopic;
    @BindView(R.id.tv_related_movie)
    TextView mTvRelatedMovie;
    @BindView(R.id.rv_related_movie)
    RecyclerView mRvRelatedMovie;
    @BindView(R.id.ll_related_movie)
    LinearLayout mLlRelatedMovie;
    @BindView(R.id.sc_movie_detail)
    NestedScrollView mScMovieDetail;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
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

    private int mMovieId;
    private MovieDetailPresenter mMovieDetailPresenter;
    private String mTitle;
    private String mMovieName;

    public static void invoke(Context context, int movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, movieId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        handleIntent();
        initPresenter();
        initListener();
        initData();
    }

    private void initData() {
        mMovieDetailPresenter.getMovieBasicData(mMovieId);
        mMovieDetailPresenter.getMovieData(mMovieId);
        mMovieDetailPresenter.getMovieMoreData(mMovieId);
    }

    private void initListener() {
        // 下拉刷新
        mSwipe.setColorSchemeResources(R.color.colorPrimary);
        mSwipe.setProgressViewOffset(false, UIUtils.dp2px(this, 20), UIUtils.dp2px(this, 80));
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMovieDetailPresenter.getMovieBasicData(mMovieId);
            }
        });

        // 动态设置状态栏
        LinearLayout.LayoutParams statusBarParams = (LinearLayout.LayoutParams) mStatusBarBg.getLayoutParams();
        // 获取状态栏高度
        int statusBarHeight = getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
        statusBarParams.height = statusBarHeight;
        mStatusBarBg.setLayoutParams(statusBarParams);

        // 将title布局向上移动状态栏的高度
        FrameLayout.LayoutParams titleParams = (FrameLayout.LayoutParams) mLlTitle.getLayoutParams();
        titleParams.setMargins(0, -statusBarHeight, 0, 0);
        mLlTitle.setLayoutParams(titleParams);

        FrameLayout.LayoutParams progressParams = (FrameLayout.LayoutParams) mProgressLayout.getLayoutParams();
        progressParams.setMargins(0, -statusBarHeight, 0, 0);
        mProgressLayout.setLayoutParams(progressParams);

        // 根据scrollView滚动修改标题栏背景
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRlMovieInfo.getLayoutParams();
        mLlTitle.measure(0, 0);
        final int movieInfoHeight = layoutParams.height - mLlTitle.getMeasuredHeight();
        mScMovieDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float scale = (float) v.getScrollY() / movieInfoHeight;

                //变化范围0-255 表示从透明到纯色背景
                float alpha = scale * 255 >= 255 ? 255 : scale * 255;

                mLlTitle.setBackgroundColor(Color.argb((int) alpha, 0, 153, 204));
                mTvTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                mTvSubTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            }
        });
    }

    private void handleIntent() {
        mMovieId = getIntent().getIntExtra(MOVIE_ID, 0);
    }

    private void initPresenter() {
        mMovieDetailPresenter = new MovieDetailPresenter(this, this);
    }

    @Override
    public void showLoading() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showLoading();
        }
    }

    @Override
    public void showContent() {
        mSwipe.setRefreshing(false);

        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(String error) {
        mSwipe.setRefreshing(false);

        mProgressLayout.showError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMovieDetailPresenter.getMovieBasicData(mMovieId);
                mMovieDetailPresenter.getMovieData(mMovieId);
                mMovieDetailPresenter.getMovieMoreData(mMovieId);
            }
        });
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }

    @Override
    protected void onStart() {
        overridePendingTransition(0, 0);
        super.onStart();
    }

    @Override
    public void addMovieBasicData(MovieBasicDataBean.DataBean.MovieBean movie) {
        setTitle(movie);
        setMovieInfo(movie);
        mExpandTextMovieDesc.setText(movie.getDra());
        setMoviePhoto(movie);
        setMovieMusic(movie);
    }

    private void setMovieMusic(final MovieBasicDataBean.DataBean.MovieBean movie) {
        if (movie.getMusicNum() != 0) {
            mLlMovieMusic.setVisibility(View.VISIBLE);
            mTvMusicName.setText(movie.getMusicName());
            mTvMusicNum.setText(String.format("%s", movie.getMusicNum()));
            GlideManager.loadImage(this, movie.getAlbumImg(), mIvMovieMusic);
            mLlMovieMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showShort(MovieDetailActivity.this, movie.getId() + "");
//                    MovieSoundTrackActivity.start(mContext, movie.getId());
                }
            });
        }
    }

    private void setMoviePhoto(MovieBasicDataBean.DataBean.MovieBean movie) {
        // 处理视频数据
        final List<MoviePhotosBean> photosBeanList = new ArrayList<>();
        if (movie.getVideoImg() != null) {
            MoviePhotosBean bean = new MoviePhotosBean();
            bean.setVideo(true);
            bean.setVideoImg(movie.getVideoImg());
            bean.setMovieTitle(movie.getNm() + " " + movie.getVideoName());
            bean.setUrl(movie.getVideourl());
            bean.setMovieId(movie.getId());
            bean.setVideoNum(movie.getVnum());
            photosBeanList.add(bean);
        }

        // 处理图片数据
        Observable.fromIterable(movie.getPhotos())
                .map(new Function<String, MoviePhotosBean>() {
                    @Override
                    public MoviePhotosBean apply(@NonNull String s) throws Exception {
                        MoviePhotosBean bean = new MoviePhotosBean();
                        bean.setVideo(false);
                        bean.setUrl(s);
                        return bean;
                    }
                })
                .toList()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MoviePhotosBean>>() {
                    @Override
                    public void accept(@NonNull List<MoviePhotosBean> moviePhotosBeen) throws Exception {
                        photosBeanList.addAll(moviePhotosBeen);
                        MoviePhotosAdapter moviePhotosAdapter = new MoviePhotosAdapter();
                        mRvMoviePhotos.setAdapter(moviePhotosAdapter);
                        mRvMoviePhotos.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        moviePhotosAdapter.setNewData(photosBeanList);
                    }
                });
    }

    private void setMovieInfo(final MovieBasicDataBean.DataBean.MovieBean movie) {
        String imgUrl = ImgSizeUtil.resetPicUrl(movie.getImg(), ".webp@321w_447h_1e_1c_1l");
        String originalUrl = ImgSizeUtil.resetPicUrl(movie.getImg(), "");
        GlideManager.loadImage(this, imgUrl, mIvMovieImg);
        mFlMovieImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(MovieDetailActivity.this, movie.getVideoName());
//                MovieVideoActivity.start(mContext, movie.getId(), 0, movie.getNm() + " " + movie.getVideoName(), movie.getVideourl());
            }
        });

        if (movie.getAwardUrl().equals("")) {
            mIvWinAwards.setVisibility(View.GONE);
        }

        mTvMovieName.setText(movie.getNm());
        mTvMovieEnglishName.setText(movie.getEnm());
        mTvMovieScore.setText(String.format("%s", movie.getSc()));
        mTvSnum.setText(String.format("(%s人评)", StringUtil.changeNumToCN(movie.getSnum())));
        mTvMovieType.setText(movie.getCat());
        mTvSrcDur.setText(String.format("%s%s", movie.getSrc(), movie.getDur() == 0 ? "" : "/" + movie.getDur() + "分钟"));
        mTvPubDesc.setText(movie.getPubDesc());

        if (movie.getSc() == 0) {
            mTvSnum.setVisibility(View.GONE);
            mTvPeopleJudge.setVisibility(View.GONE);
            mTvMovieScore.setText(String.format("%s人想看", movie.getWish()));
        }

        //模糊背景图
        Observable.just(originalUrl)
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
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                        return FastBlurUtil.doBlur(bitmap, 130, false);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        mIvBlurBg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + ErrorHanding.handleError(e), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setTitle(MovieBasicDataBean.DataBean.MovieBean movie) {
        mTvTitle.setTextColor(getResources().getColor(android.R.color.transparent, null));
        mTvSubTitle.setTextColor(getResources().getColor(android.R.color.transparent, null));
        mTitle = movie.getNm();
        mTvTitle.setText(mTitle);
        mTvSubTitle.setText(movie.getEnm());
        mMovieName = movie.getNm();
    }

    @Override
    public void addMovieTips(MovieTipsBean.DataBean tips) {

    }

    @Override
    public void addMovieStarList(MovieStarBean movieStarBean) {

    }

    @Override
    public void addMovieMoneyBox(MovieMoneyBoxBean moneyBoxBean) {

    }

    @Override
    public void addMovieAwards(List<MovieAwardsBean.DataBean> movieAwards) {

    }

    @Override
    public void addMovieResource(List<MovieResourceBean.DataBean> movieResources) {

    }

    @Override
    public void addMovieCommentTag(List<MovieCommentTagBean.DataBean> commentTags) {

    }

    @Override
    public void addMovieLongComment(MovieLongCommentBean.DataBean movieLongComments) {

    }

    @Override
    public void addMovieRelatedInformation(List<MovieRelatedInformationBean.DataBean.NewsListBean> newsListBean) {

    }

    @Override
    public void addRelatedMovie(List<RelatedMovieBean.DataBean> relatedMovies) {

    }

    @Override
    public void addMovieTopic(MovieTopicBean.DataBean movieTopicBean) {

    }

    @Override
    public void addMovieProComment(MovieProCommentBean data) {

    }
}
