package com.phone1000.chayu.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class PinPaiListViewModle {


    /**
     * data : {"count":"15","list":[{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"2341","price":"380","review_score":"9.0","score":"9.1","sid":"63","thumb":"http://img.chayu.com/tea/1608/24/71057bd730e366b9.JPG","title":"金奖茉莉花茶王（散装）（2016）","remain":"0","smapleid":"1266"},{"bid":"1","brand":"吴裕泰","catename":"绿茶〉其他","id":"2108","price":"120","review_score":"8.4","score":"8.7","sid":"63","thumb":"http://img.chayu.com/tea/1605/11/1255732a1271907b.jpg","title":"滇绿(2016)"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"1677","price":"100","review_score":"9.0","score":"9.0","sid":"63","thumb":"http://img.chayu.com/tea/1511/20/564e935fec1ed8oig.jpg","title":"翠谷幽兰"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"1914","price":"119","review_score":"7.0","score":"7.9","sid":"63","thumb":"http://img.chayu.com/tea/1512/02/565dddae6e4c1hcix.jpg","title":"东方兰舞"},{"bid":"4","brand":"吴裕泰","catename":"普洱〉生茶","id":"1","price":"0","remain":"0","review_score":"7.9","score":"7.8","sid":"40","smapleid":"1266","thumb":"http://img.chayu.com/tea/1401/04/db3c2a92ca9aazjql.jpg","title":"精品普洱（生）"},{"bid":"2","brand":"吴裕泰","catename":"乌龙〉其他","id":"1007","price":"59","review_score":"8.8","score":"8.2","sid":"63","thumb":"http://img.chayu.com/tea/1411/14/5465cdc5d9663ujqo.JPG","title":"桂花乌龙"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"1008","price":"59","review_score":"8.1","score":"8.0","sid":"63","thumb":"http://img.chayu.com/tea/1411/14/5465d11458b0445sz.JPG","title":"栀子红茶"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉茉莉花茶","id":"1018","price":"59","review_score":"9.0","score":"9.1","sid":"64","thumb":"http://img.chayu.com/tea/1411/20/546d3dfdf10a1qaqj.JPG","title":"茉莉1887"},{"bid":"1","brand":"吴裕泰","catename":"绿茶〉其他","id":"388","price":"0","review_score":"8.1","score":"8.3","sid":"63","thumb":"http://img.chayu.com/tea/1401/07/fa005d0bfb08fm4h2.jpg","title":"径山茶"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉茉莉花茶","id":"385","price":"2000","review_score":"8.1","score":"7.9","sid":"64","thumb":"http://img.chayu.com/tea/1401/07/c334d696b52f5u8r8.jpg","title":"裕泰香茉莉花魁"}],"share":{"description":"茶语网茶评，以专业的态度，忠实测评所遇到的每一款茶叶，大量测评，方便茶友以最快的方式对茶叶进行对比。","thumb":"http://static.chayu.com/mobile/main/img/common/site_logo.png","title":"茶语网|茶评-茶语网，当代茶文化推广者","url":"http://m.chaping.chayu.com/lists"}}
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
         * count : 15
         * list : [{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"2341","price":"380","review_score":"9.0","score":"9.1","sid":"63","thumb":"http://img.chayu.com/tea/1608/24/71057bd730e366b9.JPG","title":"金奖茉莉花茶王（散装）（2016）"},{"bid":"1","brand":"吴裕泰","catename":"绿茶〉其他","id":"2108","price":"120","review_score":"8.4","score":"8.7","sid":"63","thumb":"http://img.chayu.com/tea/1605/11/1255732a1271907b.jpg","title":"滇绿(2016)"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"1677","price":"100","review_score":"9.0","score":"9.0","sid":"63","thumb":"http://img.chayu.com/tea/1511/20/564e935fec1ed8oig.jpg","title":"翠谷幽兰"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"1914","price":"119","review_score":"7.0","score":"7.9","sid":"63","thumb":"http://img.chayu.com/tea/1512/02/565dddae6e4c1hcix.jpg","title":"东方兰舞"},{"bid":"4","brand":"吴裕泰","catename":"普洱〉生茶","id":"1","price":"0","remain":"0","review_score":"7.9","score":"7.8","sid":"40","smapleid":"1266","thumb":"http://img.chayu.com/tea/1401/04/db3c2a92ca9aazjql.jpg","title":"精品普洱（生）"},{"bid":"2","brand":"吴裕泰","catename":"乌龙〉其他","id":"1007","price":"59","review_score":"8.8","score":"8.2","sid":"63","thumb":"http://img.chayu.com/tea/1411/14/5465cdc5d9663ujqo.JPG","title":"桂花乌龙"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉其他","id":"1008","price":"59","review_score":"8.1","score":"8.0","sid":"63","thumb":"http://img.chayu.com/tea/1411/14/5465d11458b0445sz.JPG","title":"栀子红茶"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉茉莉花茶","id":"1018","price":"59","review_score":"9.0","score":"9.1","sid":"64","thumb":"http://img.chayu.com/tea/1411/20/546d3dfdf10a1qaqj.JPG","title":"茉莉1887"},{"bid":"1","brand":"吴裕泰","catename":"绿茶〉其他","id":"388","price":"0","review_score":"8.1","score":"8.3","sid":"63","thumb":"http://img.chayu.com/tea/1401/07/fa005d0bfb08fm4h2.jpg","title":"径山茶"},{"bid":"8","brand":"吴裕泰","catename":"花茶〉茉莉花茶","id":"385","price":"2000","review_score":"8.1","score":"7.9","sid":"64","thumb":"http://img.chayu.com/tea/1401/07/c334d696b52f5u8r8.jpg","title":"裕泰香茉莉花魁"}]
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
             * bid : 8
             * brand : 吴裕泰
             * catename : 花茶〉其他
             * id : 2341
             * price : 380
             * review_score : 9.0
             * score : 9.1
             * sid : 63
             * thumb : http://img.chayu.com/tea/1608/24/71057bd730e366b9.JPG
             * title : 金奖茉莉花茶王（散装）（2016）
             * remain : 0
             * smapleid : 1266
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
