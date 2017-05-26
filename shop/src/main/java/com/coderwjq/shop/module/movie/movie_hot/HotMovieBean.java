package com.coderwjq.shop.module.movie.movie_hot;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.coderwjq.shop.base.BaseConstant;

import java.util.List;

/**
 * @Created by coderwjq on 2017/5/26 8:48.
 * @Desc 热映电影的业务bean
 */

public class HotMovieBean {

    /**
     * data : {"coming":[],"hot":[{"boxInfo":"上映1天，累计票房6208万","cat":"喜剧,动作,奇幻","civilPubSt":0,"desc":"主演:约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹","dir":"乔阿吉姆·罗恩尼,艾斯彭·山德伯格","dur":129,"effectShowNum":0,"fra":"美国","frt":"2017-05-26","globalReleased":true,"headLineShow":false,"headLines":[],"headLinesVO":[{"movieId":246012,"title":"细数那些被杰克船长坑掉的海盗头子们","type":"专题","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20965"},{"movieId":246012,"title":"被迪斯尼嫌娘炮？德普：我演的都同性恋","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20997"}],"id":246012,"img":"http://p0.meituan.net/w.h/movie/ee5e691b425292f455c3eac5c628cfc7904509.png","late":false,"localPubSt":0,"mk":9,"newsHeadlines":[{"movieId":246012,"title":"细数那些被杰克船长坑掉的海盗头子们","type":"专题","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20965"},{"movieId":246012,"title":"被迪斯尼嫌娘炮？德普：我演的都同性恋","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20997"}],"nm":"加勒比海盗5：死无对证","pn":227,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":1,"pubDate":1495728000000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-26","sc":9,"scm":"亡灵要复仇，船长要发愁","showCinemaNum":147,"showInfo":"今天147家影院放映3074场","showNum":3074,"showTimeInfo":"2017-05-26 本周五上映","showst":3,"snum":10712,"star":"约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹","totalShowNum":8758,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","videoId":85205,"videoName":"杰克船长预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x48048034498937d40f1b44f0153009bc710.mp4","vnum":33,"weight":1,"wish":518761,"wishst":0},{"boxInfo":"上映22天，累计票房86021万","cat":"喜剧,动作,家庭","civilPubSt":0,"desc":"主演:阿米尔·汗,萨卡诗·泰瓦,法缇玛·萨那·纱卡","dir":"尼特什·提瓦瑞","dur":140,"effectShowNum":0,"fra":"印度","frt":"2016-12-23","globalReleased":true,"headLineShow":false,"id":588362,"img":"http://p0.meituan.net/w.h/movie/aeb864fa21d578d845b9cefc056e40cb2874891.jpg","late":false,"localPubSt":0,"mk":9.8,"nm":"摔跤吧！爸爸","pn":65,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":47,"pubDate":1493913600000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-05","sc":9.8,"scm":"为圆摔跤梦，女儿不心疼","showCinemaNum":142,"showInfo":"今天142家影院放映1070场","showNum":1070,"showTimeInfo":"2017-05-05上映","showst":3,"snum":486908,"star":"阿米尔·汗,萨卡诗·泰瓦,法缇玛·萨那·纱卡","totalShowNum":2009,"ver":"2D","videoId":84972,"videoName":"超长终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480447637b28ad74d02b5cb9f1c3dcd1d00.mp4","vnum":8,"weight":1,"wish":27412,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动作,战争,历史","civilPubSt":0,"desc":"主演:赵文卓,洪金宝,万茜","dir":"陈嘉上","dur":129,"effectShowNum":470,"globalReleased":false,"headLineShow":false,"id":338486,"img":"http://p1.meituan.net/w.h/movie/cc7402daf3cc47719e08ecd773930992712461.png","late":false,"localPubSt":0,"mk":0,"nm":"荡寇风云","pn":257,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":1,"pubDate":1495814400000,"pubShowNum":470,"recentShowDate":1495728000000,"recentShowNum":13,"rt":"2017-05-27","sc":0,"scm":"战神戚继光，海战谁更强","showCinemaNum":13,"showInfo":"今天13家影院放映13场","showNum":13,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":745,"star":"赵文卓,洪金宝,万茜","totalShowNum":906,"ver":"2D","videoId":85384,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480257f46beb00d4baa8e20a101d77a435d.mp4","vnum":9,"weight":1,"wish":24220,"wishst":0},{"boxInfo":"上映8天，累计票房12172万","cat":"恐怖,惊悚,科幻","civilPubSt":0,"desc":"主演:杰克·吉伦哈尔,丽贝卡·弗格森,瑞安·雷诺兹","dir":"丹尼尔·伊斯皮诺萨","dur":104,"effectShowNum":0,"fra":"美国","frt":"2017-03-24","globalReleased":true,"headLineShow":false,"id":345719,"img":"http://p1.meituan.net/w.h/movie/cc0e89d4db9c01c505bc0387d9e4522d370864.png","late":false,"localPubSt":0,"mk":7.6,"nm":"异星觉醒","pn":80,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":24,"pubDate":1495123200000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-19","sc":7.6,"scm":"宇宙外来物，样本来揭秘","showCinemaNum":124,"showInfo":"今天124家影院放映436场","showNum":436,"showTimeInfo":"2017-05-19上映","showst":3,"snum":43978,"star":"杰克·吉伦哈尔,丽贝卡·弗格森,瑞安·雷诺兹","totalShowNum":540,"ver":"2D/中国巨幕/全景声","videoId":85367,"videoName":"\u201c赶尽杀绝\u201d公映版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480b3dcf05d79a1466bad97282067a65caf.mp4","vnum":36,"weight":1,"wish":38378,"wishst":0},{"boxInfo":"喵，即将上映","cat":"喜剧,爱情,奇幻","civilPubSt":0,"desc":"主演:徐熙娣,林志玲,金世佳","dir":"蔡康永","dur":93,"effectShowNum":328,"globalReleased":false,"headLineShow":false,"id":246360,"img":"http://p1.meituan.net/w.h/movie/2dcbf613194280eec7345f0b19371dc9935368.jpg","late":false,"localPubSt":0,"mk":0,"nm":"\u201c吃吃\u201d的爱","pn":126,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":1,"pubDate":1495814400000,"pubShowNum":328,"recentShowDate":1495728000000,"recentShowNum":1,"rt":"2017-05-27","sc":0,"scm":"康永脑洞开，群星浪起来","showCinemaNum":1,"showInfo":"今天1家影院放映1场","showNum":1,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":702,"star":"徐熙娣,林志玲,金世佳","totalShowNum":544,"ver":"2D","videoId":85260,"videoName":"\u201c全靠演技\u201d版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480cdab28dee7b14583bab02c552f20da91.mp4","vnum":13,"weight":1,"wish":12333,"wishst":0},{"boxInfo":"喵，即将上映","cat":"喜剧,爱情","civilPubSt":0,"desc":"主演:郑恺,热依扎,郭晓东","dir":"钱江汉,黄志伟","dur":93,"effectShowNum":281,"globalReleased":false,"headLineShow":false,"id":247373,"img":"http://p0.meituan.net/w.h/movie/c08f83a2c77357e5fe63884ff7e86e56665346.jpg","late":false,"localPubSt":0,"mk":0,"nm":"临时演员","pn":123,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":0,"pubDate":1495814400000,"pubShowNum":281,"recentShowDate":1495728000000,"recentShowNum":1,"rt":"2017-05-27","sc":0,"scm":"狗仔太难敌，逢场作个戏","showCinemaNum":1,"showInfo":"今天1家影院放映1场","showNum":1,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":592,"star":"郑恺,热依扎,郭晓东","totalShowNum":509,"ver":"2D","videoId":85334,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4804be9bad1949642e1989b17a527f2a190.mp4","vnum":14,"weight":1,"wish":52366,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动画,冒险","civilPubSt":0,"desc":"主演:水田山葵,大原惠美,嘉数由美","dir":"高桥敦史","dur":101,"effectShowNum":232,"globalReleased":false,"headLineShow":false,"id":1183351,"img":"http://p0.meituan.net/w.h/movie/7a2659d853c1c26e58e1e77d7d6c851a748072.png","late":false,"localPubSt":0,"mk":0,"nm":"哆啦A梦：大雄的南极冰冰凉大冒险","pn":111,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":0,"pubDate":1496073600000,"pubShowNum":232,"recentShowDate":1495987200000,"recentShowNum":5,"rt":"2017-05-30","sc":0,"scm":"大雄又冒险，勇闯南极地","showCinemaNum":0,"showInfo":"2017-05-30 下周二上映","showNum":0,"showTimeInfo":"2017-05-30 下周二上映","showst":4,"snum":482,"star":"水田山葵,大原惠美,嘉数由美","totalShowNum":353,"ver":"2D","videoId":85478,"videoName":"哆啦A梦遇险冰冻南极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x48095ba0207d34e47c3b3b2c5280469d14f.mp4","vnum":4,"weight":1,"wish":29095,"wishst":0},{"boxInfo":"喵，即将上映","cat":"爱情,悬疑","civilPubSt":0,"desc":"主演:黄子韬,杨采钰,杜天皓","dir":"张荣吉","dur":105,"effectShowNum":197,"globalReleased":false,"headLineShow":false,"id":345193,"img":"http://p1.meituan.net/w.h/movie/5e7399e0aad9f915cb39891c2dcdcd3a465161.jpg","late":false,"localPubSt":0,"mk":9.1,"nm":"夏天19岁的肖像","pn":202,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":2,"pubDate":1495814400000,"pubShowNum":197,"recentShowDate":1495728000000,"recentShowNum":2,"rt":"2017-05-27","sc":9.1,"scm":"少年恋红颜，青春大冒险","showCinemaNum":2,"showInfo":"今天2家影院放映2场","showNum":2,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":79,"star":"黄子韬,杨采钰,杜天皓","totalShowNum":307,"ver":"2D","videoId":85386,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4800d1dd06fcd344305beefacbb7f08948c.mp4","vnum":12,"weight":1,"wish":49606,"wishst":0},{"boxInfo":"上映22天，累计票房67629万","cat":"动作,冒险,科幻","civilPubSt":0,"desc":"主演:克里斯·帕拉特,佐伊·索尔达娜,戴夫·巴蒂斯塔","dir":"詹姆斯·古恩","dur":136,"effectShowNum":0,"fra":"美国","frt":"2017-05-05","globalReleased":true,"headLineShow":false,"id":248683,"img":"http://p0.meituan.net/w.h/movie/fbe5f97c016c9f4520109dc70f458d4d83363.jpg","late":false,"localPubSt":0,"mk":9.1,"nm":"银河护卫队2","pn":195,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":48,"pubDate":1493913600000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-05","sc":9.1,"scm":"星爵身世迷，终于见爹地","showCinemaNum":84,"showInfo":"今天84家影院放映193场","showNum":193,"showTimeInfo":"2017-05-05上映","showst":3,"snum":238240,"star":"克里斯·帕拉特,佐伊·索尔达娜,戴夫·巴蒂斯塔","totalShowNum":228,"ver":"2D/3D/IMAX 3D/中国巨幕","videoId":85047,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4807011e019c4ef46239c91f6636435051b.mp4","vnum":48,"weight":1,"wish":154543,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动画,奇幻,家庭","civilPubSt":0,"desc":"主演:吕豆,禹祥,绿绮","dir":"刘骏,牛志远","dur":84,"effectShowNum":132,"globalReleased":false,"headLineShow":false,"id":1204621,"img":"http://p0.meituan.net/w.h/movie/ba3cde77f7b3e1a0bc90ad2de522983d947078.jpg","late":false,"localPubSt":0,"mk":0,"nm":"我的爸爸是国王","pn":25,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":0,"pubDate":1495814400000,"pubShowNum":132,"recentShowDate":1495814400000,"recentShowNum":0,"rt":"2017-05-27","sc":0,"scm":"公主带老爹，同闯新世界","showCinemaNum":0,"showInfo":"2017-05-27 本周六上映","showNum":0,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":493,"star":"吕豆,禹祥,绿绮","totalShowNum":294,"ver":"2D/3D","videoId":85437,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/1146x4800d53c438cd5a46f99ba6aa4134973e10.mp4","vnum":6,"weight":1,"wish":2988,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动作,冒险,奇幻","civilPubSt":0,"desc":"主演:盖尔·加朵,克里斯·派恩,萨伊德·塔格马奥","dir":"派蒂·杰金斯","dur":142,"effectShowNum":129,"fra":"美国","frt":"2017-06-02","globalReleased":false,"headLineShow":false,"id":247731,"img":"http://p1.meituan.net/w.h/movie/8edf9effb721474878e6f2d77b492f1b952198.jpg","late":false,"localPubSt":0,"mk":0,"nm":"神奇女侠","pn":209,"preSale":1,"preShow":true,"proScore":0,"proScoreNum":0,"pubDate":1496332800000,"pubShowNum":129,"recentShowDate":1496160000000,"recentShowNum":6,"rt":"2017-06-02","sc":0,"scm":"神力女超人，救世又圈粉","showCinemaNum":0,"showInfo":"2017-06-02 下周五上映","showNum":0,"showTimeInfo":"2017-06-02 下周五上映","showst":4,"snum":587,"star":"盖尔·加朵,克里斯·派恩,萨伊德·塔格马奥","totalShowNum":288,"ver":"3D/IMAX 3D/中国巨幕/全景声","videoId":85455,"videoName":"盖尔.加朵解读神奇女侠版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480cfe5fc36c0c24c72a4087e606ffc53c6.mp4","vnum":36,"weight":1,"wish":89906,"wishst":0},{"boxInfo":"上映8天，累计票房1981万","cat":"动作,冒险,科幻","civilPubSt":0,"desc":"主演:塞巴斯蒂安·斯萨克,艾琳娜·拉尼娜,安东·庞布施尼","dir":"萨里·奥德赛耶","dur":90,"effectShowNum":0,"fra":"俄罗斯","frt":"2017-02-23","globalReleased":true,"headLineShow":false,"id":346411,"img":"http://p0.meituan.net/w.h/movie/513f82152242db24230047c160601b87108614.jpg","late":false,"localPubSt":0,"mk":5.4,"nm":"守护者：世纪战元","pn":78,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":4,"pubDate":1495123200000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-19","sc":5.4,"scm":"邪恶老教授，爆炸惹争斗","showCinemaNum":41,"showInfo":"今天41家影院放映75场","showNum":75,"showTimeInfo":"2017-05-19上映","showst":3,"snum":15116,"star":"塞巴斯蒂安·斯萨克,艾琳娜·拉尼娜,安东·庞布施尼","totalShowNum":80,"ver":"2D/3D","videoId":85320,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4803ca9c91d5305422997f245c4a6677162.mp4","vnum":10,"weight":1,"wish":69195,"wishst":0}],"movieIds":[246012,588362,338486,345719,246360,247373,1183351,345193,248683,1204621,247731,346411,341753,248700,346103,1201630,345786,346648,672362,672114,338393,1205521,248818,337058,1203251,338409,344809,345672,38977,343351,1159320,345992,78610,672175,633,346116,1197452,1198613,490367,338499,1218,344225,341979,1204586,246319,1205313,341605,341669,343443,247885,349251,410606,346662],"stid":"576591972453269000","stids":[{"movieId":246012,"stid":"576591972453269000_a246012_c0"},{"movieId":588362,"stid":"576591972453269000_a588362_c1"},{"movieId":338486,"stid":"576591972453269000_a338486_c2"},{"movieId":345719,"stid":"576591972453269000_a345719_c3"},{"movieId":246360,"stid":"576591972453269000_a246360_c4"},{"movieId":247373,"stid":"576591972453269000_a247373_c5"},{"movieId":1183351,"stid":"576591972453269000_a1183351_c6"},{"movieId":345193,"stid":"576591972453269000_a345193_c7"},{"movieId":248683,"stid":"576591972453269000_a248683_c8"},{"movieId":1204621,"stid":"576591972453269000_a1204621_c9"},{"movieId":247731,"stid":"576591972453269000_a247731_c10"},{"movieId":346411,"stid":"576591972453269000_a346411_c11"}],"total":53}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HotMovieBean{" +
                "data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * coming : []
         * hot : [{"boxInfo":"上映1天，累计票房6208万","cat":"喜剧,动作,奇幻","civilPubSt":0,"desc":"主演:约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹","dir":"乔阿吉姆·罗恩尼,艾斯彭·山德伯格","dur":129,"effectShowNum":0,"fra":"美国","frt":"2017-05-26","globalReleased":true,"headLineShow":false,"headLines":[],"headLinesVO":[{"movieId":246012,"title":"细数那些被杰克船长坑掉的海盗头子们","type":"专题","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20965"},{"movieId":246012,"title":"被迪斯尼嫌娘炮？德普：我演的都同性恋","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20997"}],"id":246012,"img":"http://p0.meituan.net/w.h/movie/ee5e691b425292f455c3eac5c628cfc7904509.png","late":false,"localPubSt":0,"mk":9,"newsHeadlines":[{"movieId":246012,"title":"细数那些被杰克船长坑掉的海盗头子们","type":"专题","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20965"},{"movieId":246012,"title":"被迪斯尼嫌娘炮？德普：我演的都同性恋","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20997"}],"nm":"加勒比海盗5：死无对证","pn":227,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":1,"pubDate":1495728000000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-26","sc":9,"scm":"亡灵要复仇，船长要发愁","showCinemaNum":147,"showInfo":"今天147家影院放映3074场","showNum":3074,"showTimeInfo":"2017-05-26 本周五上映","showst":3,"snum":10712,"star":"约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹","totalShowNum":8758,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","videoId":85205,"videoName":"杰克船长预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x48048034498937d40f1b44f0153009bc710.mp4","vnum":33,"weight":1,"wish":518761,"wishst":0},{"boxInfo":"上映22天，累计票房86021万","cat":"喜剧,动作,家庭","civilPubSt":0,"desc":"主演:阿米尔·汗,萨卡诗·泰瓦,法缇玛·萨那·纱卡","dir":"尼特什·提瓦瑞","dur":140,"effectShowNum":0,"fra":"印度","frt":"2016-12-23","globalReleased":true,"headLineShow":false,"id":588362,"img":"http://p0.meituan.net/w.h/movie/aeb864fa21d578d845b9cefc056e40cb2874891.jpg","late":false,"localPubSt":0,"mk":9.8,"nm":"摔跤吧！爸爸","pn":65,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":47,"pubDate":1493913600000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-05","sc":9.8,"scm":"为圆摔跤梦，女儿不心疼","showCinemaNum":142,"showInfo":"今天142家影院放映1070场","showNum":1070,"showTimeInfo":"2017-05-05上映","showst":3,"snum":486908,"star":"阿米尔·汗,萨卡诗·泰瓦,法缇玛·萨那·纱卡","totalShowNum":2009,"ver":"2D","videoId":84972,"videoName":"超长终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480447637b28ad74d02b5cb9f1c3dcd1d00.mp4","vnum":8,"weight":1,"wish":27412,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动作,战争,历史","civilPubSt":0,"desc":"主演:赵文卓,洪金宝,万茜","dir":"陈嘉上","dur":129,"effectShowNum":470,"globalReleased":false,"headLineShow":false,"id":338486,"img":"http://p1.meituan.net/w.h/movie/cc7402daf3cc47719e08ecd773930992712461.png","late":false,"localPubSt":0,"mk":0,"nm":"荡寇风云","pn":257,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":1,"pubDate":1495814400000,"pubShowNum":470,"recentShowDate":1495728000000,"recentShowNum":13,"rt":"2017-05-27","sc":0,"scm":"战神戚继光，海战谁更强","showCinemaNum":13,"showInfo":"今天13家影院放映13场","showNum":13,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":745,"star":"赵文卓,洪金宝,万茜","totalShowNum":906,"ver":"2D","videoId":85384,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480257f46beb00d4baa8e20a101d77a435d.mp4","vnum":9,"weight":1,"wish":24220,"wishst":0},{"boxInfo":"上映8天，累计票房12172万","cat":"恐怖,惊悚,科幻","civilPubSt":0,"desc":"主演:杰克·吉伦哈尔,丽贝卡·弗格森,瑞安·雷诺兹","dir":"丹尼尔·伊斯皮诺萨","dur":104,"effectShowNum":0,"fra":"美国","frt":"2017-03-24","globalReleased":true,"headLineShow":false,"id":345719,"img":"http://p1.meituan.net/w.h/movie/cc0e89d4db9c01c505bc0387d9e4522d370864.png","late":false,"localPubSt":0,"mk":7.6,"nm":"异星觉醒","pn":80,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":24,"pubDate":1495123200000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-19","sc":7.6,"scm":"宇宙外来物，样本来揭秘","showCinemaNum":124,"showInfo":"今天124家影院放映436场","showNum":436,"showTimeInfo":"2017-05-19上映","showst":3,"snum":43978,"star":"杰克·吉伦哈尔,丽贝卡·弗格森,瑞安·雷诺兹","totalShowNum":540,"ver":"2D/中国巨幕/全景声","videoId":85367,"videoName":"\u201c赶尽杀绝\u201d公映版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480b3dcf05d79a1466bad97282067a65caf.mp4","vnum":36,"weight":1,"wish":38378,"wishst":0},{"boxInfo":"喵，即将上映","cat":"喜剧,爱情,奇幻","civilPubSt":0,"desc":"主演:徐熙娣,林志玲,金世佳","dir":"蔡康永","dur":93,"effectShowNum":328,"globalReleased":false,"headLineShow":false,"id":246360,"img":"http://p1.meituan.net/w.h/movie/2dcbf613194280eec7345f0b19371dc9935368.jpg","late":false,"localPubSt":0,"mk":0,"nm":"\u201c吃吃\u201d的爱","pn":126,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":1,"pubDate":1495814400000,"pubShowNum":328,"recentShowDate":1495728000000,"recentShowNum":1,"rt":"2017-05-27","sc":0,"scm":"康永脑洞开，群星浪起来","showCinemaNum":1,"showInfo":"今天1家影院放映1场","showNum":1,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":702,"star":"徐熙娣,林志玲,金世佳","totalShowNum":544,"ver":"2D","videoId":85260,"videoName":"\u201c全靠演技\u201d版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480cdab28dee7b14583bab02c552f20da91.mp4","vnum":13,"weight":1,"wish":12333,"wishst":0},{"boxInfo":"喵，即将上映","cat":"喜剧,爱情","civilPubSt":0,"desc":"主演:郑恺,热依扎,郭晓东","dir":"钱江汉,黄志伟","dur":93,"effectShowNum":281,"globalReleased":false,"headLineShow":false,"id":247373,"img":"http://p0.meituan.net/w.h/movie/c08f83a2c77357e5fe63884ff7e86e56665346.jpg","late":false,"localPubSt":0,"mk":0,"nm":"临时演员","pn":123,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":0,"pubDate":1495814400000,"pubShowNum":281,"recentShowDate":1495728000000,"recentShowNum":1,"rt":"2017-05-27","sc":0,"scm":"狗仔太难敌，逢场作个戏","showCinemaNum":1,"showInfo":"今天1家影院放映1场","showNum":1,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":592,"star":"郑恺,热依扎,郭晓东","totalShowNum":509,"ver":"2D","videoId":85334,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4804be9bad1949642e1989b17a527f2a190.mp4","vnum":14,"weight":1,"wish":52366,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动画,冒险","civilPubSt":0,"desc":"主演:水田山葵,大原惠美,嘉数由美","dir":"高桥敦史","dur":101,"effectShowNum":232,"globalReleased":false,"headLineShow":false,"id":1183351,"img":"http://p0.meituan.net/w.h/movie/7a2659d853c1c26e58e1e77d7d6c851a748072.png","late":false,"localPubSt":0,"mk":0,"nm":"哆啦A梦：大雄的南极冰冰凉大冒险","pn":111,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":0,"pubDate":1496073600000,"pubShowNum":232,"recentShowDate":1495987200000,"recentShowNum":5,"rt":"2017-05-30","sc":0,"scm":"大雄又冒险，勇闯南极地","showCinemaNum":0,"showInfo":"2017-05-30 下周二上映","showNum":0,"showTimeInfo":"2017-05-30 下周二上映","showst":4,"snum":482,"star":"水田山葵,大原惠美,嘉数由美","totalShowNum":353,"ver":"2D","videoId":85478,"videoName":"哆啦A梦遇险冰冻南极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x48095ba0207d34e47c3b3b2c5280469d14f.mp4","vnum":4,"weight":1,"wish":29095,"wishst":0},{"boxInfo":"喵，即将上映","cat":"爱情,悬疑","civilPubSt":0,"desc":"主演:黄子韬,杨采钰,杜天皓","dir":"张荣吉","dur":105,"effectShowNum":197,"globalReleased":false,"headLineShow":false,"id":345193,"img":"http://p1.meituan.net/w.h/movie/5e7399e0aad9f915cb39891c2dcdcd3a465161.jpg","late":false,"localPubSt":0,"mk":9.1,"nm":"夏天19岁的肖像","pn":202,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":2,"pubDate":1495814400000,"pubShowNum":197,"recentShowDate":1495728000000,"recentShowNum":2,"rt":"2017-05-27","sc":9.1,"scm":"少年恋红颜，青春大冒险","showCinemaNum":2,"showInfo":"今天2家影院放映2场","showNum":2,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":79,"star":"黄子韬,杨采钰,杜天皓","totalShowNum":307,"ver":"2D","videoId":85386,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4800d1dd06fcd344305beefacbb7f08948c.mp4","vnum":12,"weight":1,"wish":49606,"wishst":0},{"boxInfo":"上映22天，累计票房67629万","cat":"动作,冒险,科幻","civilPubSt":0,"desc":"主演:克里斯·帕拉特,佐伊·索尔达娜,戴夫·巴蒂斯塔","dir":"詹姆斯·古恩","dur":136,"effectShowNum":0,"fra":"美国","frt":"2017-05-05","globalReleased":true,"headLineShow":false,"id":248683,"img":"http://p0.meituan.net/w.h/movie/fbe5f97c016c9f4520109dc70f458d4d83363.jpg","late":false,"localPubSt":0,"mk":9.1,"nm":"银河护卫队2","pn":195,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":48,"pubDate":1493913600000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-05","sc":9.1,"scm":"星爵身世迷，终于见爹地","showCinemaNum":84,"showInfo":"今天84家影院放映193场","showNum":193,"showTimeInfo":"2017-05-05上映","showst":3,"snum":238240,"star":"克里斯·帕拉特,佐伊·索尔达娜,戴夫·巴蒂斯塔","totalShowNum":228,"ver":"2D/3D/IMAX 3D/中国巨幕","videoId":85047,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4807011e019c4ef46239c91f6636435051b.mp4","vnum":48,"weight":1,"wish":154543,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动画,奇幻,家庭","civilPubSt":0,"desc":"主演:吕豆,禹祥,绿绮","dir":"刘骏,牛志远","dur":84,"effectShowNum":132,"globalReleased":false,"headLineShow":false,"id":1204621,"img":"http://p0.meituan.net/w.h/movie/ba3cde77f7b3e1a0bc90ad2de522983d947078.jpg","late":false,"localPubSt":0,"mk":0,"nm":"我的爸爸是国王","pn":25,"preSale":1,"preShow":false,"proScore":0,"proScoreNum":0,"pubDate":1495814400000,"pubShowNum":132,"recentShowDate":1495814400000,"recentShowNum":0,"rt":"2017-05-27","sc":0,"scm":"公主带老爹，同闯新世界","showCinemaNum":0,"showInfo":"2017-05-27 本周六上映","showNum":0,"showTimeInfo":"2017-05-27 本周六上映","showst":4,"snum":493,"star":"吕豆,禹祥,绿绮","totalShowNum":294,"ver":"2D/3D","videoId":85437,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/1146x4800d53c438cd5a46f99ba6aa4134973e10.mp4","vnum":6,"weight":1,"wish":2988,"wishst":0},{"boxInfo":"喵，即将上映","cat":"动作,冒险,奇幻","civilPubSt":0,"desc":"主演:盖尔·加朵,克里斯·派恩,萨伊德·塔格马奥","dir":"派蒂·杰金斯","dur":142,"effectShowNum":129,"fra":"美国","frt":"2017-06-02","globalReleased":false,"headLineShow":false,"id":247731,"img":"http://p1.meituan.net/w.h/movie/8edf9effb721474878e6f2d77b492f1b952198.jpg","late":false,"localPubSt":0,"mk":0,"nm":"神奇女侠","pn":209,"preSale":1,"preShow":true,"proScore":0,"proScoreNum":0,"pubDate":1496332800000,"pubShowNum":129,"recentShowDate":1496160000000,"recentShowNum":6,"rt":"2017-06-02","sc":0,"scm":"神力女超人，救世又圈粉","showCinemaNum":0,"showInfo":"2017-06-02 下周五上映","showNum":0,"showTimeInfo":"2017-06-02 下周五上映","showst":4,"snum":587,"star":"盖尔·加朵,克里斯·派恩,萨伊德·塔格马奥","totalShowNum":288,"ver":"3D/IMAX 3D/中国巨幕/全景声","videoId":85455,"videoName":"盖尔.加朵解读神奇女侠版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x480cfe5fc36c0c24c72a4087e606ffc53c6.mp4","vnum":36,"weight":1,"wish":89906,"wishst":0},{"boxInfo":"上映8天，累计票房1981万","cat":"动作,冒险,科幻","civilPubSt":0,"desc":"主演:塞巴斯蒂安·斯萨克,艾琳娜·拉尼娜,安东·庞布施尼","dir":"萨里·奥德赛耶","dur":90,"effectShowNum":0,"fra":"俄罗斯","frt":"2017-02-23","globalReleased":true,"headLineShow":false,"id":346411,"img":"http://p0.meituan.net/w.h/movie/513f82152242db24230047c160601b87108614.jpg","late":false,"localPubSt":0,"mk":5.4,"nm":"守护者：世纪战元","pn":78,"preSale":0,"preShow":false,"proScore":0,"proScoreNum":4,"pubDate":1495123200000,"pubShowNum":0,"recentShowDate":1495728000000,"recentShowNum":0,"rt":"2017-05-19","sc":5.4,"scm":"邪恶老教授，爆炸惹争斗","showCinemaNum":41,"showInfo":"今天41家影院放映75场","showNum":75,"showTimeInfo":"2017-05-19上映","showst":3,"snum":15116,"star":"塞巴斯蒂安·斯萨克,艾琳娜·拉尼娜,安东·庞布施尼","totalShowNum":80,"ver":"2D/3D","videoId":85320,"videoName":"终极版预告片","videourl":"http://maoyan.meituan.net/movie/videos/854x4803ca9c91d5305422997f245c4a6677162.mp4","vnum":10,"weight":1,"wish":69195,"wishst":0}]
         * movieIds : [246012,588362,338486,345719,246360,247373,1183351,345193,248683,1204621,247731,346411,341753,248700,346103,1201630,345786,346648,672362,672114,338393,1205521,248818,337058,1203251,338409,344809,345672,38977,343351,1159320,345992,78610,672175,633,346116,1197452,1198613,490367,338499,1218,344225,341979,1204586,246319,1205313,341605,341669,343443,247885,349251,410606,346662]
         * stid : 576591972453269000
         * stids : [{"movieId":246012,"stid":"576591972453269000_a246012_c0"},{"movieId":588362,"stid":"576591972453269000_a588362_c1"},{"movieId":338486,"stid":"576591972453269000_a338486_c2"},{"movieId":345719,"stid":"576591972453269000_a345719_c3"},{"movieId":246360,"stid":"576591972453269000_a246360_c4"},{"movieId":247373,"stid":"576591972453269000_a247373_c5"},{"movieId":1183351,"stid":"576591972453269000_a1183351_c6"},{"movieId":345193,"stid":"576591972453269000_a345193_c7"},{"movieId":248683,"stid":"576591972453269000_a248683_c8"},{"movieId":1204621,"stid":"576591972453269000_a1204621_c9"},{"movieId":247731,"stid":"576591972453269000_a247731_c10"},{"movieId":346411,"stid":"576591972453269000_a346411_c11"}]
         * total : 53
         */

        private String stid;
        private int total;
        private List<?> coming;
        private List<HotBean> hot;
        private List<Integer> movieIds;
        private List<StidsBean> stids;

        public String getStid() {
            return stid;
        }

        public void setStid(String stid) {
            this.stid = stid;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<?> getComing() {
            return coming;
        }

        public void setComing(List<?> coming) {
            this.coming = coming;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public List<Integer> getMovieIds() {
            return movieIds;
        }

        public void setMovieIds(List<Integer> movieIds) {
            this.movieIds = movieIds;
        }

        public List<StidsBean> getStids() {
            return stids;
        }

        public void setStids(List<StidsBean> stids) {
            this.stids = stids;
        }

        public static class HotBean implements MultiItemEntity {
            /**
             * boxInfo : 上映1天，累计票房6208万
             * cat : 喜剧,动作,奇幻
             * civilPubSt : 0
             * desc : 主演:约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹
             * dir : 乔阿吉姆·罗恩尼,艾斯彭·山德伯格
             * dur : 129
             * effectShowNum : 0
             * fra : 美国
             * frt : 2017-05-26
             * globalReleased : true
             * headLineShow : false
             * headLines : []
             * headLinesVO : [{"movieId":246012,"title":"细数那些被杰克船长坑掉的海盗头子们","type":"专题","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20965"},{"movieId":246012,"title":"被迪斯尼嫌娘炮？德普：我演的都同性恋","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20997"}]
             * id : 246012
             * img : http://p0.meituan.net/w.h/movie/ee5e691b425292f455c3eac5c628cfc7904509.png
             * late : false
             * localPubSt : 0
             * mk : 9
             * newsHeadlines : [{"movieId":246012,"title":"细数那些被杰克船长坑掉的海盗头子们","type":"专题","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20965"},{"movieId":246012,"title":"被迪斯尼嫌娘炮？德普：我演的都同性恋","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=20997"}]
             * nm : 加勒比海盗5：死无对证
             * pn : 227
             * preSale : 0
             * preShow : false
             * proScore : 0
             * proScoreNum : 1
             * pubDate : 1495728000000
             * pubShowNum : 0
             * recentShowDate : 1495728000000
             * recentShowNum : 0
             * rt : 2017-05-26
             * sc : 9
             * scm : 亡灵要复仇，船长要发愁
             * showCinemaNum : 147
             * showInfo : 今天147家影院放映3074场
             * showNum : 3074
             * showTimeInfo : 2017-05-26 本周五上映
             * showst : 3
             * snum : 10712
             * star : 约翰尼·德普,哈维尔·巴登,布兰顿·思怀兹
             * totalShowNum : 8758
             * ver : 2D/3D/IMAX 3D/中国巨幕/全景声
             * videoId : 85205
             * videoName : 杰克船长预告片
             * videourl : http://maoyan.meituan.net/movie/videos/854x48048034498937d40f1b44f0153009bc710.mp4
             * vnum : 33
             * weight : 1
             * wish : 518761
             * wishst : 0
             */

            private String boxInfo;
            private String cat;
            private int civilPubSt;
            private String desc;
            private String dir;
            private int dur;
            private int effectShowNum;
            private String fra;
            private String frt;
            private boolean globalReleased;
            private boolean headLineShow;
            private int id;
            private String img;
            private boolean late;
            private int localPubSt;
            private double mk;
            private String nm;
            private int pn;
            private int preSale;
            private boolean preShow;
            private int proScore;
            private int proScoreNum;
            private long pubDate;
            private int pubShowNum;
            private long recentShowDate;
            private int recentShowNum;
            private String rt;
            private double sc;
            private String scm;
            private int showCinemaNum;
            private String showInfo;
            private int showNum;
            private String showTimeInfo;
            private int showst;
            private int snum;
            private String star;
            private int totalShowNum;
            private String ver;
            private int videoId;
            private String videoName;
            private String videourl;
            private int vnum;
            private int weight;
            private int wish;
            private int wishst;
            private List<?> headLines;
            private List<HeadLinesVOBean> headLinesVO;
            private List<NewsHeadlinesBean> newsHeadlines;

            public String getBoxInfo() {
                return boxInfo;
            }

            public void setBoxInfo(String boxInfo) {
                this.boxInfo = boxInfo;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getCivilPubSt() {
                return civilPubSt;
            }

            public void setCivilPubSt(int civilPubSt) {
                this.civilPubSt = civilPubSt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public int getEffectShowNum() {
                return effectShowNum;
            }

            public void setEffectShowNum(int effectShowNum) {
                this.effectShowNum = effectShowNum;
            }

            public String getFra() {
                return fra;
            }

            public void setFra(String fra) {
                this.fra = fra;
            }

            public String getFrt() {
                return frt;
            }

            public void setFrt(String frt) {
                this.frt = frt;
            }

            public boolean isGlobalReleased() {
                return globalReleased;
            }

            public void setGlobalReleased(boolean globalReleased) {
                this.globalReleased = globalReleased;
            }

            public boolean isHeadLineShow() {
                return headLineShow;
            }

            public void setHeadLineShow(boolean headLineShow) {
                this.headLineShow = headLineShow;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public int getLocalPubSt() {
                return localPubSt;
            }

            public void setLocalPubSt(int localPubSt) {
                this.localPubSt = localPubSt;
            }

            public double getMk() {
                return mk;
            }

            public void setMk(double mk) {
                this.mk = mk;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public boolean isPreShow() {
                return preShow;
            }

            public void setPreShow(boolean preShow) {
                this.preShow = preShow;
            }

            public int getProScore() {
                return proScore;
            }

            public void setProScore(int proScore) {
                this.proScore = proScore;
            }

            public int getProScoreNum() {
                return proScoreNum;
            }

            public void setProScoreNum(int proScoreNum) {
                this.proScoreNum = proScoreNum;
            }

            public long getPubDate() {
                return pubDate;
            }

            public void setPubDate(long pubDate) {
                this.pubDate = pubDate;
            }

            public int getPubShowNum() {
                return pubShowNum;
            }

            public void setPubShowNum(int pubShowNum) {
                this.pubShowNum = pubShowNum;
            }

            public long getRecentShowDate() {
                return recentShowDate;
            }

            public void setRecentShowDate(long recentShowDate) {
                this.recentShowDate = recentShowDate;
            }

            public int getRecentShowNum() {
                return recentShowNum;
            }

            public void setRecentShowNum(int recentShowNum) {
                this.recentShowNum = recentShowNum;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public int getShowCinemaNum() {
                return showCinemaNum;
            }

            public void setShowCinemaNum(int showCinemaNum) {
                this.showCinemaNum = showCinemaNum;
            }

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public int getShowNum() {
                return showNum;
            }

            public void setShowNum(int showNum) {
                this.showNum = showNum;
            }

            public String getShowTimeInfo() {
                return showTimeInfo;
            }

            public void setShowTimeInfo(String showTimeInfo) {
                this.showTimeInfo = showTimeInfo;
            }

            public int getShowst() {
                return showst;
            }

            public void setShowst(int showst) {
                this.showst = showst;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public int getTotalShowNum() {
                return totalShowNum;
            }

            public void setTotalShowNum(int totalShowNum) {
                this.totalShowNum = totalShowNum;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public String getVideoName() {
                return videoName;
            }

            public void setVideoName(String videoName) {
                this.videoName = videoName;
            }

            public String getVideourl() {
                return videourl;
            }

            public void setVideourl(String videourl) {
                this.videourl = videourl;
            }

            public int getVnum() {
                return vnum;
            }

            public void setVnum(int vnum) {
                this.vnum = vnum;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public int getWishst() {
                return wishst;
            }

            public void setWishst(int wishst) {
                this.wishst = wishst;
            }

            public List<?> getHeadLines() {
                return headLines;
            }

            public void setHeadLines(List<?> headLines) {
                this.headLines = headLines;
            }

            public List<HeadLinesVOBean> getHeadLinesVO() {
                return headLinesVO;
            }

            public void setHeadLinesVO(List<HeadLinesVOBean> headLinesVO) {
                this.headLinesVO = headLinesVO;
            }

            public List<NewsHeadlinesBean> getNewsHeadlines() {
                return newsHeadlines;
            }

            public void setNewsHeadlines(List<NewsHeadlinesBean> newsHeadlines) {
                this.newsHeadlines = newsHeadlines;
            }

            @Override
            public int getItemType() {
                if (getHeadLinesVO() != null && getHeadLinesVO().size() > 0) {
                    return BaseConstant.TYPE_HOT_HEADLINE;
                }
                return BaseConstant.TYPE_HOT_NORMAL;
            }

            public static class HeadLinesVOBean {
                /**
                 * movieId : 246012
                 * title : 细数那些被杰克船长坑掉的海盗头子们
                 * type : 专题
                 * url : meituanmovie://www.meituan.com/forum/newsDetail?id=20965
                 */

                private int movieId;
                private String title;
                private String type;
                private String url;

                public int getMovieId() {
                    return movieId;
                }

                public void setMovieId(int movieId) {
                    this.movieId = movieId;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class NewsHeadlinesBean {
                /**
                 * movieId : 246012
                 * title : 细数那些被杰克船长坑掉的海盗头子们
                 * type : 专题
                 * url : meituanmovie://www.meituan.com/forum/newsDetail?id=20965
                 */

                private int movieId;
                private String title;
                private String type;
                private String url;

                public int getMovieId() {
                    return movieId;
                }

                public void setMovieId(int movieId) {
                    this.movieId = movieId;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }

        public static class StidsBean {
            /**
             * movieId : 246012
             * stid : 576591972453269000_a246012_c0
             */

            private int movieId;
            private String stid;

            public int getMovieId() {
                return movieId;
            }

            public void setMovieId(int movieId) {
                this.movieId = movieId;
            }

            public String getStid() {
                return stid;
            }

            public void setStid(String stid) {
                this.stid = stid;
            }
        }
    }
}
