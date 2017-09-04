package com.coderwjq.shop.module.movie_search;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coderwjq.shop.R;
import com.coderwjq.shop.base.BaseSingleChoiceAdapter;
import com.coderwjq.shop.base.BaseSingleChoiceBean;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.module.movie_search.adapter.ClassifySearchListAdapter;
import com.coderwjq.shop.module.movie_search.model.ClassifySearchBean;
import com.coderwjq.shop.view.MyPullToRefreshListener;
import com.coderwjq.shop.view.ProgressLayout;
import com.coderwjq.shop.view.SuperSwipeRefreshLayout;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.coderwjq.shop.R.id.swipe;

public class SearchActivity extends AppCompatActivity implements SearchContract.ISearchView {
    private static final String TAG = "SearchActivity";

    private static final String TYPE_ID = "type_id";
    private static final String NATION_ID = "nation_id";
    private static final String PERIOD_ID = "period_id";
    private static final String TYPE_TITLE = "type_title";
    private static final String NATION_TITLE = "nation_title";
    private static final String PERIOD_TITLE = "period_title";

    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.rl_search)
    RelativeLayout mRlSearch;
    @BindView(R.id.rv_search_list)
    RecyclerView mRvSearchList;
    @BindView(swipe)
    SuperSwipeRefreshLayout mSwipe;
    @BindView(R.id.tv_classify_title)
    TextView mTvClassifyTitle;
    @BindView(R.id.progressLayout)
    ProgressLayout mProgressLayout;
    private SearchPresenter mPresenter;

    /**
     * 初次显示的数据
     */
    private int searchId;
    private int nationId;
    private int periodId;
    private String typeTitle;
    private String nationTitle;
    private String periodTitle;
    private String sortTitle = "好评";

    private int offset; // 偏移量
    private int catId; // 电影分类
    private int sourceId; // 地区Id
    private int yearId; // 年代Id
    private int sortId = 3; // 好评、最新、热门
    private int mDistanceY = 0; // 滑动距离
    private int showTitleHeight; // 开始显示的高度
    private int titleHeight; // 顶部标题的高度
    private boolean isFirst = true; // 判断显示标题栏
    private BaseSingleChoiceAdapter singleChoiceAdapter1;
    private BaseSingleChoiceAdapter singleChoiceAdapter2;
    private BaseSingleChoiceAdapter singleChoiceAdapter3;
    private BaseSingleChoiceAdapter singleChoiceAdapter4;
    private ClassifySearchListAdapter classifySearchListAdapter;
    private MyPullToRefreshListener pullToRefreshListener;
    private KProgressHUD mHud;

    public static void invokeWithType(Context context, int id, String typeTitle) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(TYPE_ID, id);
        starter.putExtra(TYPE_TITLE, typeTitle);
        context.startActivity(starter);
    }

    public static void invokeWithNation(Context context, int id, String nationTitle) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(NATION_ID, id);
        starter.putExtra(NATION_TITLE, nationTitle);
        context.startActivity(starter);
    }

    public static void invokeWithPeriod(Context context, int id, String periodTitle) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(PERIOD_ID, id);
        starter.putExtra(PERIOD_TITLE, periodTitle);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mHud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在拼命加载中...")
                .setDimAmount(0.5f);

        handleIntent();

        mPresenter = new SearchPresenter(this, this);

        initRecylerViews();

        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 获取电影分类信息
        mPresenter.getMovieTypeList();
        // 根据条件进行筛选电影
        mPresenter.getClassifySearchList(offset, catId, sourceId, yearId, sortId);

    }

    private void initRecylerViews() {
        final View header = getLayoutInflater().inflate(R.layout.layout_classify_search_header, (ViewGroup) mRvSearchList.getParent(), false);
        RecyclerView rvSearchType = (RecyclerView) header.findViewById(R.id.rv_search_type);
        RecyclerView rvSearchNation = (RecyclerView) header.findViewById(R.id.rv_search_nation);
        RecyclerView rvSearchPeriod = (RecyclerView) header.findViewById(R.id.rv_search_period);
        RecyclerView rvSearchPoint = (RecyclerView) header.findViewById(R.id.rv_search_point);

        //影片类型
        singleChoiceAdapter1 = new BaseSingleChoiceAdapter();
        rvSearchType.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSearchType.setAdapter(singleChoiceAdapter1);
        singleChoiceAdapter1.setOnItemClickListener(new BaseSingleChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(BaseSingleChoiceBean bean) {
                offset = 0;
                catId = bean.id;
                typeTitle = bean.content;
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(offset, catId, sourceId, yearId, sortId);
            }
        });

        //地区
        singleChoiceAdapter2 = new BaseSingleChoiceAdapter();
        rvSearchNation.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSearchNation.setAdapter(singleChoiceAdapter2);
        singleChoiceAdapter2.setOnItemClickListener(new BaseSingleChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(BaseSingleChoiceBean bean) {
                offset = 0;
                sourceId = bean.id;
                nationTitle = bean.content;
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(offset, catId, sourceId, yearId, sortId);
            }
        });

        //年代
        singleChoiceAdapter3 = new BaseSingleChoiceAdapter();
        rvSearchPeriod.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSearchPeriod.setAdapter(singleChoiceAdapter3);
        singleChoiceAdapter3.setOnItemClickListener(new BaseSingleChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(BaseSingleChoiceBean bean) {
                offset = 0;
                periodTitle = bean.content;
                yearId = bean.id;
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(offset, catId, sourceId, yearId, sortId);
            }
        });

        // 评分
        singleChoiceAdapter4 = new BaseSingleChoiceAdapter();
        rvSearchPoint.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSearchPoint.setAdapter(singleChoiceAdapter4);
        singleChoiceAdapter4.setOnItemClickListener(new BaseSingleChoiceAdapter.OnItemClickListener() {
            @Override
            public void click(BaseSingleChoiceBean bean) {
                offset = 0;
                sortTitle = bean.content;
                sortId = bean.id;
                classifySearchListAdapter.setNewData(new ArrayList<ClassifySearchBean.ListBean>());
                mPresenter.getClassifySearchList(offset, catId, sourceId, yearId, sortId);
            }
        });

        // 筛选结果
        classifySearchListAdapter = new ClassifySearchListAdapter();
        mRvSearchList.setLayoutManager(new LinearLayoutManager(this));
        mRvSearchList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 滑动监听，显示title
        mRvSearchList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                showTitleHeight = header.getHeight();
                titleHeight = mTvClassifyTitle.getHeight();
                if (showTitleHeight < mDistanceY) {
                    //显示
                    mTvClassifyTitle.setVisibility(View.VISIBLE);
                    if (isFirst) {
                        //第一次显示的时候才滑出来
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTvClassifyTitle, "translationY", -titleHeight, 0);
                        objectAnimator.setDuration(500);
                        objectAnimator.start();
                        isFirst = false;
                        String titleContent =
                                (typeTitle.equals("全部") ? "" : typeTitle + "·") +
                                        (nationTitle.equals("全部") ? "" : nationTitle + "·") +
                                        (periodTitle.equals("全部") ? "" : periodTitle + "·") +
                                        (sortTitle);
                        mTvClassifyTitle.setText(titleContent);

                    }
                } else {
                    //隐藏
                    mTvClassifyTitle.setVisibility(View.INVISIBLE);
                    isFirst = true;
                }
                mDistanceY += dy;
            }
        });

        mRvSearchList.setAdapter(classifySearchListAdapter);
        classifySearchListAdapter.addHeaderView(header);
        classifySearchListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getClassifySearchList(offset, catId, sourceId, yearId, sortId);
            }
        }, mRvSearchList);

        pullToRefreshListener = new MyPullToRefreshListener(this, mSwipe);
        mSwipe.setOnPullRefreshListener(pullToRefreshListener);
        pullToRefreshListener.setOnRefreshListener(new MyPullToRefreshListener.OnRefreshListener() {
            @Override
            public void refresh() {
                mPresenter.getClassifySearchList(offset, catId, sourceId, yearId, sortId);
            }
        });
    }

    private void handleIntent() {
        searchId = catId = getIntent().getIntExtra(TYPE_ID, 0);
        nationId = sourceId = getIntent().getIntExtra(NATION_ID, 0);
        periodId = yearId = getIntent().getIntExtra(PERIOD_ID, 0);

        typeTitle = getIntent().getStringExtra(TYPE_TITLE) == null ? "全部" : getIntent().getStringExtra(TYPE_TITLE);
        nationTitle = getIntent().getStringExtra(NATION_TITLE) == null ? "全部" : getIntent().getStringExtra(NATION_TITLE);
        periodTitle = getIntent().getStringExtra(PERIOD_TITLE) == null ? "全部" : getIntent().getStringExtra(PERIOD_TITLE);
    }

    @Override
    public void showLoading() {
        if (!mProgressLayout.isContent()) {
            mProgressLayout.showLoading();
        }
    }

    @Override
    public void showContent() {
        pullToRefreshListener.refreshDone();

        if (!mProgressLayout.isContent()) {
            mProgressLayout.showContent();
        }
    }

    @Override
    public void showError(String error) {
        pullToRefreshListener.refreshDone();

        if (!mProgressLayout.isContent()) {
            mProgressLayout.showError(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.getMovieTypeList();
                }
            });
        }
    }

    @Override
    public void addMovieType(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        final List<BaseSingleChoiceBean> choiceBeanList = new ArrayList<>();
        BaseSingleChoiceBean singleChoiceBean = new BaseSingleChoiceBean();
        singleChoiceBean.id = -1;
        singleChoiceBean.content = "全部";
        singleChoiceBean.isSelect = searchId == 0;
        choiceBeanList.add(0, singleChoiceBean);

        Observable.fromIterable(tagList)
                .map(new Function<MovieTypeBean.DataBean.TagListBean, BaseSingleChoiceBean>() {
                    @Override
                    public BaseSingleChoiceBean apply(@NonNull MovieTypeBean.DataBean.TagListBean tagListBean) throws Exception {
                        BaseSingleChoiceBean bean = new BaseSingleChoiceBean();
                        bean.id = tagListBean.getTagId();
                        bean.content = tagListBean.getTagName();
                        bean.isSelect = searchId == tagListBean.getTagId(); // 判断是否与传过来的ID相等
                        return bean;
                    }
                })
                .toList()
                .subscribe(new Consumer<List<BaseSingleChoiceBean>>() {
                    @Override
                    public void accept(@NonNull List<BaseSingleChoiceBean> baseSingleChoiceBeen) throws Exception {
                        choiceBeanList.addAll(baseSingleChoiceBeen);
                        singleChoiceAdapter1.addData(choiceBeanList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void addMovieNation(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        final List<BaseSingleChoiceBean> choiceBeanList2 = new ArrayList<>();
        BaseSingleChoiceBean singleChoiceBean = new BaseSingleChoiceBean();
        singleChoiceBean.id = -1;
        singleChoiceBean.content = "全部";
        singleChoiceBean.isSelect = nationId == 0;

        choiceBeanList2.add(0, singleChoiceBean);

        Observable.fromIterable(tagList)
                .map(new Function<MovieTypeBean.DataBean.TagListBean, BaseSingleChoiceBean>() {
                    @Override
                    public BaseSingleChoiceBean apply(@NonNull MovieTypeBean.DataBean.TagListBean tagListBean) throws Exception {
                        BaseSingleChoiceBean bean = new BaseSingleChoiceBean();
                        bean.id = tagListBean.getTagId();
                        bean.content = tagListBean.getTagName();
                        bean.isSelect = nationId == tagListBean.getTagId();//判断是否与传过来的ID相等

                        return bean;
                    }
                })
                .toList()
                .subscribe(new Consumer<List<BaseSingleChoiceBean>>() {
                    @Override
                    public void accept(@NonNull List<BaseSingleChoiceBean> baseSingleChoiceBeen) throws Exception {
                        choiceBeanList2.addAll(baseSingleChoiceBeen);
                        singleChoiceAdapter2.addData(choiceBeanList2);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void addMoviePeriod(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        final List<BaseSingleChoiceBean> choiceBeanList3 = new ArrayList<>();
        BaseSingleChoiceBean singleChoiceBean = new BaseSingleChoiceBean();
        singleChoiceBean.id = -1;
        singleChoiceBean.content = "全部";
        singleChoiceBean.isSelect = periodId == 0;
        choiceBeanList3.add(0, singleChoiceBean);

        Observable.fromIterable(tagList)
                .map(new Function<MovieTypeBean.DataBean.TagListBean, BaseSingleChoiceBean>() {
                    @Override
                    public BaseSingleChoiceBean apply(@NonNull MovieTypeBean.DataBean.TagListBean tagListBean) throws Exception {
                        BaseSingleChoiceBean bean = new BaseSingleChoiceBean();
                        bean.id = tagListBean.getTagId();
                        bean.content = tagListBean.getTagName();
                        bean.isSelect = periodId == tagListBean.getTagId();//判断是否与传过来的ID相等
                        return bean;
                    }
                })
                .toList()
                .subscribe(new Consumer<List<BaseSingleChoiceBean>>() {
                    @Override
                    public void accept(@NonNull List<BaseSingleChoiceBean> baseSingleChoiceBeen) throws Exception {
                        choiceBeanList3.addAll(baseSingleChoiceBeen);
                        singleChoiceAdapter3.addData(choiceBeanList3);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void addMoviePoint(List<MovieTypeBean.DataBean.TagListBean> tagList) {
        final List<BaseSingleChoiceBean> choiceBeanList4 = new ArrayList<>();

        for (int i = 0; i < tagList.size(); i++) {
            BaseSingleChoiceBean bean = new BaseSingleChoiceBean();
            bean.id = tagList.get(i).getTagId();
            bean.content = tagList.get(i).getTagName();
            bean.isSelect = i == 0;
            choiceBeanList4.add(bean);
        }
        singleChoiceAdapter4.addData(choiceBeanList4);
    }

    @Override
    public void addClassifySearchData(List<ClassifySearchBean.ListBean> list) {
        if (list.size() > 0) {
            offset += 10;
            classifySearchListAdapter.addData(list);
            classifySearchListAdapter.loadMoreComplete();
        } else {
            classifySearchListAdapter.loadMoreEnd();
        }
    }

    @Override
    public void showLoadingData() {
        if (!mHud.isShowing()) {
            mHud.show();
        }
    }

    @Override
    public void finishLoadingData() {
        if (mHud.isShowing()) {
            mHud.dismiss();
        }
    }
}
