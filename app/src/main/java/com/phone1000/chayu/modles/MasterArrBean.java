package com.phone1000.chayu.modles;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
@Table(name = "MasterArrBean")
public class MasterArrBean {


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

        @Column(name = "id",isId = true,autoGen = true)
        private int id;
        @Column(name = "title")
        private String title;
        @Column(name = "type")
        private int type;
        @Column(name = "sellerUid")
        private String sellerUid;
        @Column(name = "data")
        private String data;
        @Column(name = "juheType")
        private String juheType;
        @Column(name = "thumb")
        private String thumb;
        @Column(name = "sales_count")
        private int sales_count;
        @Column(name = "catename")
        private String catename;
        @Column(name = "comment_count")
        private String comment_count;
        @Column(name = "content")
        private String content;
        @Column(name = "sellerTypeName")
        private String sellerTypeName;
        @Column(name = "sellerAvatar")
        private String sellerAvatar;
        @Column(name = "icon")
        private String icon;
        @Column(name = "gid")
        private String gid;
        @Column(name = "mainid")
        private String mainid;
        @Column(name = "goods_num")
        private int goods_num;
        @Column(name = "goods_id")
        private String goods_id;
        private List<?> goodsList;
        @Column(name = "mDinyiType")
        private int mDinyiType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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
