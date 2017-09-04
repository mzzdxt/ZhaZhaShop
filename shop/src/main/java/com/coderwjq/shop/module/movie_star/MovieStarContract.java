package com.coderwjq.shop.module.movie_star;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movie_star.model.MovieStarHonor;
import com.coderwjq.shop.module.movie_star.model.MovieStarInfoBean;
import com.coderwjq.shop.module.movie_star.model.RelatedInformationBean;
import com.coderwjq.shop.module.movie_star.model.StarMoviesBean;
import com.coderwjq.shop.module.movie_star.model.StarRelatedPeople;

/**
 * Created by coderwjq on 2017/9/4 9:12.
 */

public class MovieStarContract {
    public interface IMovieStarView extends ILoadingView {

        void addMovieStarInfo(MovieStarInfoBean.DataBean data);

        void addMovieStarHonor(MovieStarHonor data);

        void addStarMovie(StarMoviesBean.DataBean data);

        void addRelatedInfo(RelatedInformationBean data);

        void addRelatedPeople(StarRelatedPeople data);
    }

    public interface IMovieStarPresenter {
        void getMovieStarInfo(int starId);
    }
}
