package com.phone1000.chayu.modles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class BnagdanActivity implements Parcelable{


    /**
     * data : {"order":[{"id":"1","name":"茶语评分榜","thumb":""},{"id":"2","name":"综合评分榜","thumb":""}],"teaCate":[{"id":"0","name":"全部","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_09.png"},{"id":"1","name":"绿茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_01.png"},{"id":"2","name":"乌龙茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_02.png"},{"id":"3","name":"红茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_03.png"},{"id":"4","name":"普洱茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_04.png"},{"id":"5","name":"黑茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_05.png"},{"id":"6","name":"白茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_06.png"},{"id":"7","name":"黄茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_07.png"},{"id":"8","name":"花茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_08.png"},{"id":"other","name":"时尚茶","thumb":"http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_09.png"}],"year":[{"id":"0","name":"全部年份","thumb":""},{"id":"2016","name":"2016年","thumb":""},{"id":"2015","name":"2015年","thumb":""},{"id":"2014","name":"2014年","thumb":""}]}
     * msg : 数据获取成功
     * state : 400
     */

    private DataBean data;
    private String msg;
    private int state;

    protected BnagdanActivity(Parcel in) {
        msg = in.readString();
        state = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeInt(state);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BnagdanActivity> CREATOR = new Creator<BnagdanActivity>() {
        @Override
        public BnagdanActivity createFromParcel(Parcel in) {
            return new BnagdanActivity(in);
        }

        @Override
        public BnagdanActivity[] newArray(int size) {
            return new BnagdanActivity[size];
        }
    };

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
        private List<OrderBean> order;
        private List<TeaCateBean> teaCate;
        private List<YearBean> year;

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public List<TeaCateBean> getTeaCate() {
            return teaCate;
        }

        public void setTeaCate(List<TeaCateBean> teaCate) {
            this.teaCate = teaCate;
        }

        public List<YearBean> getYear() {
            return year;
        }

        public void setYear(List<YearBean> year) {
            this.year = year;
        }

        public static class OrderBean {
            /**
             * id : 1
             * name : 茶语评分榜
             * thumb :
             */

            private String id;
            private String name;
            private String thumb;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class TeaCateBean {
            /**
             * id : 0
             * name : 全部
             * thumb : http://static.chayu.com/mobile/main/img/common/icons_tea/ico_tea_09.png
             */

            private String id;
            private String name;
            private String thumb;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class YearBean {
            /**
             * id : 0
             * name : 全部年份
             * thumb :
             */

            private String id;
            private String name;
            private String thumb;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }
}
