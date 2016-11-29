package com.phone1000.chayu.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TeaListModel {

    /**
     * data : {"count":"582","list":[{"bid":"1","brand":"峨眉雪芽","catename":"绿茶〉其他","id":"2708","price":"178","review_score":"8.8","score":"8.8","sid":"63","thumb":"http://img.chayu.com/tea/1611/10/776582435cabd877.JPG","title":"峨眉雨花（2016）","remain":"0","smapleid":"3246"},{"bid":"1","brand":"甘百香","catename":"绿茶〉其他","id":"2674","price":"160","review_score":"9.0","score":"8.7","sid":"63","thumb":"http://img.chayu.com/tea/1611/07/850581fd22d2f09e.jpg","title":"黄山白茶(2016)"},{"bid":"1","brand":"湘湖","catename":"绿茶〉其他","id":"2603","price":"150","review_score":"8.6","score":"8.4","sid":"63","thumb":"http://img.chayu.com/tea/1610/23/115580c891245b3f.jpg","title":"湘湖龙井（2016）"},{"bid":"1","brand":"径香綠","catename":"绿茶〉其他","id":"2602","price":"53","review_score":"8.4","score":"8.5","sid":"63","thumb":"http://img.chayu.com/tea/1610/23/266580c87be389e6.jpg","title":"径山茶（2016）"},{"bid":"1","brand":"三亩良田","catename":"绿茶〉其他","id":"2601","price":"0","remain":"0","review_score":"8.4","score":"8.4","sid":"63","smapleid":"3246","thumb":"http://img.chayu.com/tea/1610/23/248580c851622a24.jpg","title":"崂山绿茶·春芽特级（2016）"},{"bid":"1","brand":"茗福茶业 ","catename":"绿茶〉其他","id":"2600","price":"0","remain":"0","review_score":"8.5","score":"8.6","sid":"63","smapleid":"3233","thumb":"http://img.chayu.com/tea/1610/23/971580c836dd47ba.jpg","title":"太湖翠竹（2016）"},{"bid":"1","brand":"茗福茶业 ","catename":"绿茶〉其他","id":"2599","price":"0","remain":"0","review_score":"8.6","score":"8.6","sid":"63","smapleid":"3232","thumb":"http://img.chayu.com/tea/1610/23/231580c800b2557a.jpg","title":"雪芽特级（2016）"},{"bid":"1","brand":"御青","catename":"绿茶〉其他","id":"2595","price":"244","remain":"0","review_score":"8.6","score":"8.4","sid":"63","smapleid":"3212","thumb":"http://img.chayu.com/tea/1610/23/422580c6f4b56082.jpg","title":"日照绿茶(2016)"},{"bid":"1","brand":"云升茶业","catename":"绿茶〉其他","id":"2593","price":"179","remain":"0","review_score":"8.9","score":"8.6","sid":"63","smapleid":"3206","thumb":"http://img.chayu.com/tea/1610/23/311580c6db78c672.jpg","title":"永川秀芽特级(2016)"},{"bid":"1","brand":"侗乡福","catename":"绿茶〉其他","id":"2592","price":"198","remain":"0","review_score":"8.8","score":"8.5","sid":"63","smapleid":"3198","thumb":"http://img.chayu.com/tea/1610/23/526580c6bf5b0ec7.jpg","title":"黎平毛尖(2016)"}],"share":{"description":"茶语网茶评，以专业的态度，忠实测评所遇到的每一款茶叶，大量测评，方便茶友以最快的方式对茶叶进行对比。","thumb":"http://static.chayu.com/mobile/main/img/common/site_logo.png","title":"茶语网|茶评-茶语网，当代茶文化推广者","url":"http://m.chaping.chayu.com/lists"}}
     * msg : 数据获取成功
     * state : 400
     */

    private DataBean data;
    private String msg;
    private int state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * count : 582
         * list : [{"bid":"1","brand":"峨眉雪芽","catename":"绿茶〉其他","id":"2708","price":"178","review_score":"8.8","score":"8.8","sid":"63","thumb":"http://img.chayu.com/tea/1611/10/776582435cabd877.JPG","title":"峨眉雨花（2016）"},{"bid":"1","brand":"甘百香","catename":"绿茶〉其他","id":"2674","price":"160","review_score":"9.0","score":"8.7","sid":"63","thumb":"http://img.chayu.com/tea/1611/07/850581fd22d2f09e.jpg","title":"黄山白茶(2016)"},{"bid":"1","brand":"湘湖","catename":"绿茶〉其他","id":"2603","price":"150","review_score":"8.6","score":"8.4","sid":"63","thumb":"http://img.chayu.com/tea/1610/23/115580c891245b3f.jpg","title":"湘湖龙井（2016）"},{"bid":"1","brand":"径香綠","catename":"绿茶〉其他","id":"2602","price":"53","review_score":"8.4","score":"8.5","sid":"63","thumb":"http://img.chayu.com/tea/1610/23/266580c87be389e6.jpg","title":"径山茶（2016）"},{"bid":"1","brand":"三亩良田","catename":"绿茶〉其他","id":"2601","price":"0","remain":"0","review_score":"8.4","score":"8.4","sid":"63","smapleid":"3246","thumb":"http://img.chayu.com/tea/1610/23/248580c851622a24.jpg","title":"崂山绿茶·春芽特级（2016）"},{"bid":"1","brand":"茗福茶业 ","catename":"绿茶〉其他","id":"2600","price":"0","remain":"0","review_score":"8.5","score":"8.6","sid":"63","smapleid":"3233","thumb":"http://img.chayu.com/tea/1610/23/971580c836dd47ba.jpg","title":"太湖翠竹（2016）"},{"bid":"1","brand":"茗福茶业 ","catename":"绿茶〉其他","id":"2599","price":"0","remain":"0","review_score":"8.6","score":"8.6","sid":"63","smapleid":"3232","thumb":"http://img.chayu.com/tea/1610/23/231580c800b2557a.jpg","title":"雪芽特级（2016）"},{"bid":"1","brand":"御青","catename":"绿茶〉其他","id":"2595","price":"244","remain":"0","review_score":"8.6","score":"8.4","sid":"63","smapleid":"3212","thumb":"http://img.chayu.com/tea/1610/23/422580c6f4b56082.jpg","title":"日照绿茶(2016)"},{"bid":"1","brand":"云升茶业","catename":"绿茶〉其他","id":"2593","price":"179","remain":"0","review_score":"8.9","score":"8.6","sid":"63","smapleid":"3206","thumb":"http://img.chayu.com/tea/1610/23/311580c6db78c672.jpg","title":"永川秀芽特级(2016)"},{"bid":"1","brand":"侗乡福","catename":"绿茶〉其他","id":"2592","price":"198","remain":"0","review_score":"8.8","score":"8.5","sid":"63","smapleid":"3198","thumb":"http://img.chayu.com/tea/1610/23/526580c6bf5b0ec7.jpg","title":"黎平毛尖(2016)"}]
         * share : {"description":"茶语网茶评，以专业的态度，忠实测评所遇到的每一款茶叶，大量测评，方便茶友以最快的方式对茶叶进行对比。","thumb":"http://static.chayu.com/mobile/main/img/common/site_logo.png","title":"茶语网|茶评-茶语网，当代茶文化推广者","url":"http://m.chaping.chayu.com/lists"}
         */

        private String count;
        private ShareBean share;
        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ShareBean {
            /**
             * description : 茶语网茶评，以专业的态度，忠实测评所遇到的每一款茶叶，大量测评，方便茶友以最快的方式对茶叶进行对比。
             * thumb : http://static.chayu.com/mobile/main/img/common/site_logo.png
             * title : 茶语网|茶评-茶语网，当代茶文化推广者
             * url : http://m.chaping.chayu.com/lists
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

        public static class ListBean {
            /**
             * bid : 1
             * brand : 峨眉雪芽
             * catename : 绿茶〉其他
             * id : 2708
             * price : 178
             * review_score : 8.8
             * score : 8.8
             * sid : 63
             * thumb : http://img.chayu.com/tea/1611/10/776582435cabd877.JPG
             * title : 峨眉雨花（2016）
             * remain : 0
             * smapleid : 3246
             */

            private String bid;
            private String brand;
            private String catename;
            private String id;
            private String price;
            private String review_score;
            private String score;
            private String sid;
            private String thumb;
            private String title;
            private String remain;
            private String smapleid;

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

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getReview_score() {
                return review_score;
            }

            public void setReview_score(String review_score) {
                this.review_score = review_score;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
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

            public String getRemain() {
                return remain;
            }

            public void setRemain(String remain) {
                this.remain = remain;
            }

            public String getSmapleid() {
                return smapleid;
            }

            public void setSmapleid(String smapleid) {
                this.smapleid = smapleid;
            }
        }
    }
}
