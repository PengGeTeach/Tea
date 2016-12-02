package com.phone1000.chayu.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class ListBean {
    /**
     * clicks : 29.81万
     * commend : 1
     * is_toutiao : 1
     * resource_id : 195163
     * resource_type : 1
     * show_type : 1
     * source : {"clicks":"29.81万","id":"195163","is_top":"1473849207","suports":"12182","url":"http://m.chayu.com/article/195163"}
     * suports : 12182
     * template_id : 11
     * thumb : http://img.chayu.com/banner/1612/01/395583ff39a16e69.png
     * title : 假金骏眉中添加的柠檬黄、日落黄是什么？急，在线等！
     * url :
     * id : 195975
     * thumbs : [{"commend":"0","resource_id":"196195","resource_type":"1","source":{"clicks":"2.33万","id":"196195","is_top":"1480489543","suports":"335","url":"http://m.chayu.com/article/196195"},"template_id":"11","thumb":"http://img.chayu.com/banner/1612/01/699583ff41a618ed.png","title":"《茶经》中记载的上佳的生长环境 孕育了这杯清新鲜爽的\u201c岩韵\u201d","url":""},{"commend":"0","resource_id":"196195","resource_type":"1","source":{"clicks":"2.33万","id":"196195","is_top":"1480489543","suports":"335","url":"http://m.chayu.com/article/196195"},"template_id":"11","thumb":"http://img.chayu.com/banner/1612/01/229583ff461e5948.png","title":"《茶经》中记载的上佳的生长环境 孕育了这杯清新鲜爽的\u201c岩韵\u201d","url":""},{"commend":"0","resource_id":"196195","resource_type":"1","source":{"clicks":"2.33万","id":"196195","is_top":"1480489543","suports":"335","url":"http://m.chayu.com/article/196195"},"template_id":"11","thumb":"http://img.chayu.com/banner/1612/01/705583ff47a94a1e.png","title":"《茶经》中记载的上佳的生长环境 孕育了这杯清新鲜爽的\u201c岩韵\u201d","url":""}]
     */

    private String clicks;
    private String commend;
    private int is_toutiao;
    private String resource_id;
    private String resource_type;
    private int show_type;
    private SourceBeanX source;
    private String suports;
    private String template_id;
    private String thumb;
    private String title;
    private String url;
    private String id;
    private List<ThumbsBean> thumbs;

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getCommend() {
        return commend;
    }

    public void setCommend(String commend) {
        this.commend = commend;
    }

    public int getIs_toutiao() {
        return is_toutiao;
    }

    public void setIs_toutiao(int is_toutiao) {
        this.is_toutiao = is_toutiao;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public int getShow_type() {
        return show_type;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }

    public SourceBeanX getSource() {
        return source;
    }

    public void setSource(SourceBeanX source) {
        this.source = source;
    }

    public String getSuports() {
        return suports;
    }

    public void setSuports(String suports) {
        this.suports = suports;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ThumbsBean> getThumbs() {
        return thumbs;
    }

    public void setThumbs(List<ThumbsBean> thumbs) {
        this.thumbs = thumbs;
    }

    public static class SourceBeanX {
        /**
         * clicks : 29.81万
         * id : 195163
         * is_top : 1473849207
         * suports : 12182
         * url : http://m.chayu.com/article/195163
         */

        private String clicks;
        private String id;
        private String is_top;
        private String suports;
        private String url;

        public String getClicks() {
            return clicks;
        }

        public void setClicks(String clicks) {
            this.clicks = clicks;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getSuports() {
            return suports;
        }

        public void setSuports(String suports) {
            this.suports = suports;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class ThumbsBean {
        /**
         * commend : 0
         * resource_id : 196195
         * resource_type : 1
         * source : {"clicks":"2.33万","id":"196195","is_top":"1480489543","suports":"335","url":"http://m.chayu.com/article/196195"}
         * template_id : 11
         * thumb : http://img.chayu.com/banner/1612/01/699583ff41a618ed.png
         * title : 《茶经》中记载的上佳的生长环境 孕育了这杯清新鲜爽的“岩韵”
         * url :
         */

        private String commend;
        private String resource_id;
        private String resource_type;
        private SourceBeanXX source;
        private String template_id;
        private String thumb;
        private String title;
        private String url;

        public String getCommend() {
            return commend;
        }

        public void setCommend(String commend) {
            this.commend = commend;
        }

        public String getResource_id() {
            return resource_id;
        }

        public void setResource_id(String resource_id) {
            this.resource_id = resource_id;
        }

        public String getResource_type() {
            return resource_type;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public SourceBeanXX getSource() {
            return source;
        }

        public void setSource(SourceBeanXX source) {
            this.source = source;
        }

        public String getTemplate_id() {
            return template_id;
        }

        public void setTemplate_id(String template_id) {
            this.template_id = template_id;
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

        public static class SourceBeanXX {
            /**
             * clicks : 2.33万
             * id : 196195
             * is_top : 1480489543
             * suports : 335
             * url : http://m.chayu.com/article/196195
             */

            private String clicks;
            private String id;
            private String is_top;
            private String suports;
            private String url;

            public String getClicks() {
                return clicks;
            }

            public void setClicks(String clicks) {
                this.clicks = clicks;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_top() {
                return is_top;
            }

            public void setIs_top(String is_top) {
                this.is_top = is_top;
            }

            public String getSuports() {
                return suports;
            }

            public void setSuports(String suports) {
                this.suports = suports;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
