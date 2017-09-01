package com.coderwjq.shop.module.movie_detail;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movie_detail.model.MovieAwardsBean;
import com.coderwjq.shop.module.movie_detail.model.MovieBasicDataBean;
import com.coderwjq.shop.module.movie_detail.model.MovieCommentTagBean;
import com.coderwjq.shop.module.movie_detail.model.MovieLongCommentBean;
import com.coderwjq.shop.module.movie_detail.model.MovieMoneyBoxBean;
import com.coderwjq.shop.module.movie_detail.model.MovieProCommentBean;
import com.coderwjq.shop.module.movie_detail.model.MovieRelatedInformationBean;
import com.coderwjq.shop.module.movie_detail.model.MovieResourceBean;
import com.coderwjq.shop.module.movie_detail.model.MovieStarBean;
import com.coderwjq.shop.module.movie_detail.model.MovieTipsBean;
import com.coderwjq.shop.module.movie_detail.model.MovieTopicBean;
import com.coderwjq.shop.module.movie_detail.model.RelatedMovieBean;

import java.util.List;

/**
 * Created by coderwjq on 2017/8/31 9:08.
 */

public class MovieDetailContract {
    public interface IMovieDetailView extends ILoadingView {
        void addMovieBasicData(MovieBasicDataBean.DataBean.MovieBean movie);

        void addMovieTips(MovieTipsBean.DataBean tips);

        void addMovieStarList(MovieStarBean movieStarBean);

        void addMovieMoneyBox(MovieMoneyBoxBean moneyBoxBean);

        void addMovieAwards(List<MovieAwardsBean.DataBean> movieAwards);

        void addMovieResource(List<MovieResourceBean.DataBean> movieResources);

        void addMovieCommentTag(List<MovieCommentTagBean.DataBean> commentTags);

        void addMovieLongComment(MovieLongCommentBean.DataBean movieLongComments);

        void addMovieRelatedInformation(List<MovieRelatedInformationBean.DataBean.NewsListBean> newsListBean);

        void addRelatedMovie(List<RelatedMovieBean.DataBean> relatedMovies);

        void addMovieTopic(MovieTopicBean.DataBean movieTopicBean);

        void addMovieProComment(MovieProCommentBean data);
    }

    public interface IMovieDetailPresenter {
        void getMovieBasicData(int movieId);

        void getMovieData(int movieId);

        void getMovieMoreData(int movieId);
    }
}
