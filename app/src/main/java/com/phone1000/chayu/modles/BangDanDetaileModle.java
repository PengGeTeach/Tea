package com.phone1000.chayu.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class BangDanDetaileModle {


    /**
     * data : [{"bid":"2","brand":"孝文家茶","id":"2344","review_score":"9.4","title":"牛首（2016）"},{"bid":"2","brand":"孝文家茶","id":"2023","review_score":"9.4","title":"牛肉（2015）"},{"bid":"8","brand":"二三两","id":"2124","review_score":"9.3","title":"茉莉银针（2015）"},{"bid":"6","brand":"二三两","id":"2530","review_score":"9.3","title":"白毫银针（2016）"},{"bid":"2","brand":"老台湾","id":"2000","review_score":"9.2","title":"东方美人（2015）"},{"bid":"2","brand":"孝文家茶","id":"2343","review_score":"9.2","title":"牛肉（2016）"},{"bid":"2","brand":"老台湾","id":"1474","review_score":"9.2","title":"杉林溪乌龙（1990）"},{"bid":"5","brand":"怡清源","id":"2030","review_score":"9.2","title":"金花之父（2015）"},{"bid":"4","brand":"斗记茶业","id":"2064","review_score":"9.2","title":"麻黑寨（2015）"},{"bid":"4","brand":"斗记茶业","id":"2065","review_score":"9.2","title":"刮风寨（2015）"},{"bid":"4","brand":"岁月知味","id":"2743","review_score":"9.2","title":"易武正山古树茶（2006）"},{"bid":"6","brand":"二三两","id":"2358","review_score":"9.2","title":"白牡丹（2016）"},{"bid":"4","brand":"岁月知味","id":"2329","review_score":"9.2","title":"易道（2015）"},{"bid":"1","brand":"老仙","id":"2266","review_score":"9.2","title":"狗牯脑（2016）"},{"bid":"8","brand":"伯韵","id":"2348","review_score":"9.1","title":"极品莫离（2016）"},{"bid":"4","brand":"斗记茶业","id":"2399","review_score":"9.1","title":"莫辞醉（2016）"},{"bid":"7","brand":"几叶","id":"2733","review_score":"9.1","title":"蒙顶黄芽（2016）"},{"bid":"1","brand":"硒沁情","id":"1743","review_score":"9.1","title":"富硒贡芽"},{"bid":"4","brand":"斗记茶业","id":"2063","review_score":"9.1","title":"易武大树（2015）"},{"bid":"1","brand":"王老二","id":"2365","review_score":"9.1","title":"特级太平猴魁（2016）"}]
     * msg : 数据获取成功
     * share : {"description":"茶语网|茶语分类评分榜","thumb":"http://static.chayu.com/mobile/main/img/common/site_logo.png","title":"茶语网|茶语分类评分榜","url":"http://m.chaping.chayu.com/top/category?bid=&year=2016&order=1"}
     * state : 400
     */

    private String msg;
    private ShareBean share;
    private int state;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ShareBean {
        /**
         * description : 茶语网|茶语分类评分榜
         * thumb : http://static.chayu.com/mobile/main/img/common/site_logo.png
         * title : 茶语网|茶语分类评分榜
         * url : http://m.chaping.chayu.com/top/category?bid=&year=2016&order=1
         */

        private String description;
        private String thumb;
        private String title;
        private String url;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class DataBean {
        /**
         * bid : 2
         * brand : 孝文家茶
         * id : 2344
         * review_score : 9.4
         * title : 牛首（2016）
         */

        private String bid;
        private String brand;
        private String id;
        private String review_score;
        private String title;

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReview_score() {
            return review_score;
        }

        public void setReview_score(String review_score) {
            this.review_score = review_score;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
