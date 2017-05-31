package com.coderwjq.shop.base;

/**
 * @Created by coderwjq on 2017/5/25 15:20.
 * @Desc
 */

public interface BaseConstant {
    //底部4个Tab
    int RB_MOVIE = 0;
    int RB_CINEMA = 1;
    int RB_DISCOVER = 2;
    int RB_MINE = 3;

    //首页热映的2种item
    int TYPE_HOT_HEADLINE = 0; // 第一个热门
    int TYPE_HOT_NORMAL = 1; // 正常
    int TYPE_HOT_PRE_SELL = 2; // 预售

    //待映5种item
    int TYPE_WAIT_DIVIDER = 0; // 悬浮item
    int TYPE_WAIT_HEADLINES = 1; // 有专访的item
    int TYPE_WAIT_TRAILER = 2; // 预告片
    int TYPE_WAIT_RECENT = 3; // 近期受期待
    int TYPE_WAIT_NORMAL = 4; // 正常
}
