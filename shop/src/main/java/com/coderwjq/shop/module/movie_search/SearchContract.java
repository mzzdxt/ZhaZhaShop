package com.coderwjq.shop.module.movie_search;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;
import com.coderwjq.shop.module.movie_search.model.ClassifySearchBean;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/4 14:24.
 */

public class SearchContract {
    public interface ISearchView extends ILoadingView {
        void addMovieType(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMovieNation(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMoviePeriod(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMoviePoint(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addClassifySearchData(List<ClassifySearchBean.ListBean> list);

        void showLoadingData();

        void finishLoadingData();
    }

    public interface ISearchPresenter {
        void getMovieTypeList();

        void getClassifySearchList(int offset, int catId, int sourceId, int yearId, int sortId);
    }
}
