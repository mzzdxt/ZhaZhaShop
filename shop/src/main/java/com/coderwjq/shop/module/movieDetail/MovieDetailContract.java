package com.coderwjq.shop.module.movieDetail;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movieDetail.model.MovieAwardsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieBasicDataBean;
import com.coderwjq.shop.module.movieDetail.model.MovieCommentTagBean;
import com.coderwjq.shop.module.movieDetail.model.MovieLongCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieMoneyBoxBean;
import com.coderwjq.shop.module.movieDetail.model.MovieProCommentBean;
import com.coderwjq.shop.module.movieDetail.model.MovieRelatedInformationBean;
import com.coderwjq.shop.module.movieDetail.model.MovieResourceBean;
import com.coderwjq.shop.module.movieDetail.model.MovieStarBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTipsBean;
import com.coderwjq.shop.module.movieDetail.model.MovieTopicBean;
import com.coderwjq.shop.module.movieDetail.model.RelatedMovieBean;

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
