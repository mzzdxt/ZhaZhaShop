package com.coderwjq.shop.module.movie_detail.model;

/**
 * Created by coderwjq on 2017/8/31 8:49.
 *
 * @desc 电影票房数据
 */

public class MovieMoneyBoxBean {

    /**
     * globalRelease : true
     * mbox : {"firstWeekBox":0,"firstWeekOverSeaBox":0,"lastDayRank":1,"sumBox":40467,"sumOverSeaBox":0}
     * url : http://m.maoyan.com/movie/334590/box?_v_=yes&utm_campaign=AmovieBmovieD100
     */

    private boolean globalRelease;
    private MboxBean mbox;
    private String url;

    public boolean isGlobalRelease() {
        return globalRelease;
    }

    public void setGlobalRelease(boolean globalRelease) {
        this.globalRelease = globalRelease;
    }

    public MboxBean getMbox() {
        return mbox;
    }

    public void setMbox(MboxBean mbox) {
        this.mbox = mbox;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class MboxBean {
        /**
         * firstWeekBox : 0
         * firstWeekOverSeaBox : 0
         * lastDayRank : 1
         * sumBox : 40467
         * sumOverSeaBox : 0
         */

        private int firstWeekBox;
        private int firstWeekOverSeaBox;
        private int lastDayRank;
        private int sumBox;
        private int sumOverSeaBox;

        public int getFirstWeekBox() {
            return firstWeekBox;
        }

        public void setFirstWeekBox(int firstWeekBox) {
            this.firstWeekBox = firstWeekBox;
        }

        public int getFirstWeekOverSeaBox() {
            return firstWeekOverSeaBox;
        }

        public void setFirstWeekOverSeaBox(int firstWeekOverSeaBox) {
            this.firstWeekOverSeaBox = firstWeekOverSeaBox;
        }

        public int getLastDayRank() {
            return lastDayRank;
        }

        public void setLastDayRank(int lastDayRank) {
            this.lastDayRank = lastDayRank;
        }

        public int getSumBox() {
            return sumBox;
        }

        public void setSumBox(int sumBox) {
            this.sumBox = sumBox;
        }

        public int getSumOverSeaBox() {
            return sumOverSeaBox;
        }

        public void setSumOverSeaBox(int sumOverSeaBox) {
            this.sumOverSeaBox = sumOverSeaBox;
        }
    }
}
