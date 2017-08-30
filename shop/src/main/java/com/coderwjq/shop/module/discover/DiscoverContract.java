package com.coderwjq.shop.module.discover;

import com.coderwjq.shop.base.ILoadingView;
import com.coderwjq.shop.module.discover.model.DiscoverBean;
import com.coderwjq.shop.module.discover.model.DiscoverHeaderBean;

import java.util.List;

/**
 * Created by coderwjq on 2017/8/30 17:41.
 */

public class DiscoverContract {
    public interface IDiscoverView extends ILoadingView {
        void addDiscoverHeaderData(List<DiscoverHeaderBean.DataBean> data);

        void addDiscoverData(List<DiscoverBean.DataBean.FeedsBean> feeds);
    }

    public interface IDiscoverPresenter {
        void getDiscoverHeader(String utm_term);

        void getDiscoverData(int offset, int limit);
    }
}
