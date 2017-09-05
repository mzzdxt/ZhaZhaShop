package com.coderwjq.shop.module.movie_rank.oversea_rank;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaComingMovieBean;
import com.coderwjq.shop.module.movie_rank.oversea_rank.model.OverseaHotMovieBean;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/5 10:04.
 */

public class MovieOverseaContract {
    public interface IMovieOverseaView extends ILoadingView {
        void addOverseaHotMovie(List<OverseaHotMovieBean.DataBean.HotBean> hot);

        void addOverseaComingMovie(List<OverseaComingMovieBean.DataBean.ComingBean> coming);
    }

    public interface IMovieOverseaPresenter {
        void getOverseaMovie(String area);
    }
}
