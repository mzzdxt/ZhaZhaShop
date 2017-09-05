package com.coderwjq.shop.module.movie_rank.more_rank;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/5 16:14.
 */

public class MovieMoreRankContract {

    public interface IMovieMoreRankView extends ILoadingView {
        void addOverseaHotMovieList(List<OverseaHotMovieBean.DataBean.HotBean> hot);

        void addOverseaComingMovieList(List<OverseaComingMovieBean.DataBean.ComingBean> coming);
    }

    public interface IMovieMoreRankPresenter {
        void getOverseaHotMovieList(String area, int limit, int offset);

        void getOverseaComingMovieList(String area, int limit, int offset);
    }
}
