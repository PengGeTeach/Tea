package com.phone1000.chayu.modles;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class ShiJiModel {



    private int state;
    private String msg;
    private DataBean data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private TouPhotosArrBean touPhotosArr;
        private BannerArrBean bannerArr;
        private String SearchHotWords;
        private ShareBean share;
        private List<SlideArrBean> slideArr;
        private List<MasterArrBean> masterArr;
        private List<?> famousProArr;
        private List<FamousArrBean> famousArr;

        public TouPhotosArrBean getTouPhotosArr() {
            return touPhotosArr;
        }

        public void setTouPhotosArr(TouPhotosArrBean touPhotosArr) {
            this.touPhotosArr = touPhotosArr;
        }

        public BannerArrBean getBannerArr() {
            return bannerArr;
        }

        public void setBannerArr(BannerArrBean bannerArr) {
            this.bannerArr = bannerArr;
        }

        public String getSearchHotWords() {
            return SearchHotWords;
        }

        public void setSearchHotWords(String SearchHotWords) {
            this.SearchHotWords = SearchHotWords;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public List<SlideArrBean> getSlideArr() {
            return slideArr;
        }

        public void setSlideArr(List<SlideArrBean> slideArr) {
            this.slideArr = slideArr;
        }

        public List<MasterArrBean> getMasterArr() {
            return masterArr;
        }

        public void setMasterArr(List<MasterArrBean> masterArr) {
            this.masterArr = masterArr;
        }

        public List<?> getFamousProArr() {
            return famousProArr;
        }

        public void setFamousProArr(List<?> famousProArr) {
            this.famousProArr = famousProArr;
        }

        public List<FamousArrBean> getFamousArr() {
            return famousArr;
        }

        public void setFamousArr(List<FamousArrBean> famousArr) {
            this.famousArr = famousArr;
        }

        public static class TouPhotosArrBean {
            private String thumb;

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class BannerArrBean {
        }

        public static class ShareBean {
            /**
             * thumb : http://static.chayu.com/mobile/main/img/common/site_logo.png
             * title : 茶语市集·为懂茶的你找好茶-爱茶人的生活美学-顶级茶 行内价
             * description : 茶语网-通茶语，会知己-当代茶文化推广者
             * url : http://m.shiji.chayu.com
             */

            private String thumb;
            private String title;
            private String description;
            private String url;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class SlideArrBean {
            /**
             * type : 2
             * title : 柑普和醇
             * thumb : http://img.chayu.com/mallblock/1610/20/36958089bd4e6854.jpg
             * gid :
             * data : 10135
             */

            private String type;
            private String title;
            private String thumb;
            private String gid;
            private String data;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }


        public static class FamousArrBean {
            /**
             * title : 杨中原-1
             * goodsList : []
             * type : 4
             * sellerUid :
             * data : 54
             * juheType : 1
             * thumb : http://img.chayu.com/mallblock/1608/26/55357c02481657ff.jpg!750500
             */

            private String title;
            private int type;
            private String sellerUid;
            private String data;
            private String juheType;
            private String thumb;
            private List<?> goodsList;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getSellerUid() {
                return sellerUid;
            }

            public void setSellerUid(String sellerUid) {
                this.sellerUid = sellerUid;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getJuheType() {
                return juheType;
            }

            public void setJuheType(String juheType) {
                this.juheType = juheType;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public List<?> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<?> goodsList) {
                this.goodsList = goodsList;
            }
        }
    }
}
