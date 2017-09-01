package com.coderwjq.shop.module.player.rxbus;

/**
 * Created by Administrator on 2017/2/17.
 */

public class CommentCountPostBean {
    private int commentCount;

    public CommentCountPostBean(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
