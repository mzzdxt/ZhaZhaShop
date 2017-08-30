package com.coderwjq.shop.module.movie.movie_find;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movie.movie_find.model.AwardsMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.GridMovieBean;
import com.coderwjq.shop.module.movie.movie_find.model.MovieTypeBean;

import java.util.List;

/**
 * Created by coderwjq on 2017/8/29 14:12.
 */

public class FindMovieContract {

    public interface IFindMovieView extends ILoadingView {
        void addMovieType(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMovieNation(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMoviePeriod(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMovieGrid(List<GridMovieBean.DataBean> data);

        void addAwardsMovie(List<AwardsMovieBean.DataBean> data);
    }

    public interface IFindMoviePresenter {
        void getFindMovieData();
    }
}
