package com.coderwjq.shop.base;

/**
 * @Created by coderwjq on 2017/5/26 9:18.
 * @Desc
 */

public interface ILoadingView {
    void showLoading();

    void showContent();

    void showError(String error);
}
