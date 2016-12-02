package com.phone1000.chayu.modles;

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

        public static class MasterArrBean {
            /**
             * title : 一壶唯得仙人骨、气神韵,方大师境界 大师级潮州手拉朱泥壶合集
             * goodsList : []
             * type : 4
             * sellerUid :
             * data : 191
             * juheType : 1
             * thumb : http://img.chayu.com/mallblock/1611/30/544583dc5c12612e.jpg!750500
             * sales_count : 1732
             * catename : 花茶
             * comment_count : 44
             * content :
             * sellerTypeName : 陈成忠
             * sellerAvatar : http://img.chayu.com/avatar/000/26/28/55/5717c27750223.jpg!160160
             * icon :
             * gid : 10
             * mainid : 13
             * goods_num : 18
             * goods_id : 10029
             */

            private String title;
            private int type;
            private String sellerUid;
            private String data;
            private String juheType;
            private String thumb;
            private int sales_count;
            private String catename;
            private String comment_count;
            private String content;
            private String sellerTypeName;
            private String sellerAvatar;
            private String icon;
            private String gid;
            private String mainid;
            private int goods_num;
            private String goods_id;
            private List<?> goodsList;
            private int mDinyiType;

            public int getmDinyiType() {
                return mDinyiType;
            }

            public void setmDinyiType(int mDinyiType) {
                this.mDinyiType = mDinyiType;
            }

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

            public int getSales_count() {
                return sales_count;
            }

            public void setSales_count(int sales_count) {
                this.sales_count = sales_count;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSellerTypeName() {
                return sellerTypeName;
            }

            public void setSellerTypeName(String sellerTypeName) {
                this.sellerTypeName = sellerTypeName;
            }

            public String getSellerAvatar() {
                return sellerAvatar;
            }

            public void setSellerAvatar(String sellerAvatar) {
                this.sellerAvatar = sellerAvatar;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getMainid() {
                return mainid;
            }

            public void setMainid(String mainid) {
                this.mainid = mainid;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public List<?> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<?> goodsList) {
                this.goodsList = goodsList;
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
