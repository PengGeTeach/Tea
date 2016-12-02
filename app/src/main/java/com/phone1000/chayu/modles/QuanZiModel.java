package com.phone1000.chayu.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class QuanZiModel {

    /**
     * data : {"banner":"http://img.chayu.com/banner/1608/07/63157a6e607a9283.jpg","cover":"http://static.chayu.com/mobile/main/img/main/quanzi/icons/icon_quanzi_logo.png","huati":[{"content":"茶语圈子新人报到季第1期活动中奖名单公布，小伙伴们快来领茶样咯！PS:第2期正在火热进行中\u2026","resource_id":"607517","resource_type":"4","source":{"created_uid":"241460","hits":"7848","nickname":"茶语网茶小二","replies":"30","tid":"607517"},"template_id":"10","thumb":"http://img.chayu.com/banner/1611/30/664583e267a62bf4.jpg","title":"[新人报到季]第1期中奖名单"},{"content":"喝茶能解酒吗？这不是一道判断题，是一道救命题啊。答案颠覆你之前的认知，赶快码住看起来。","resource_id":"607506","resource_type":"4","source":{"created_uid":"215594","hits":"7360","nickname":"茶语茶小花","replies":"29","tid":"607506"},"template_id":"10","thumb":"http://img.chayu.com/banner/1611/30/918583e2731ea486.jpg","title":"茶友问答|喝茶能解酒吗？"},{"content":"茶汤呈浅杏黄色，曾相识的苦香钻入鼻中，似\u201c雀舌\u201d的味道，当时还以为串味了，没喝几口就倒掉了。","resource_id":"607601","resource_type":"4","source":{"created_uid":"1284929","hits":"4421","nickname":"rhymist","replies":"26","tid":"607601"},"template_id":"10","thumb":"http://img.chayu.com/banner/1611/30/633583e29a7a9f89.jpg","title":"晒茶记|曾经被误解的滋味"}],"is_sign":"flase","quanzi":[{"resource_id":"23","resource_type":"3","source":{"attention_num":"2155","created_uid":"225540","gid":"23","nickname":"暂无","posts":"16830","topics":"6824"},"template_id":"9","thumb":"http://img.chayu.com/banner/1609/27/10257e9d911b489c.jpg","title":"学茶圈","url":""},{"resource_id":"10","resource_type":"3","source":{"attention_num":"797","created_uid":"225540","gid":"10","nickname":"zoetory","posts":"1181","topics":"200"},"template_id":"9","thumb":"http://img.chayu.com/banner/1609/27/69857e9d9c7147fa.jpg","title":"茶语评茶","url":""},{"resource_id":"21","resource_type":"3","source":{"attention_num":"1343","created_uid":"225540","gid":"21","nickname":"慧者心清","posts":"26884","topics":"3968"},"template_id":"9","thumb":"http://img.chayu.com/banner/1609/27/49757e9d9355e189.jpg","title":"晒茶","url":""}],"share":{"description":"茶语圈子，专为茶叶爱好者提供交流的茶叶社区，在这里，茶友们可以获得最新最热的茶叶资讯。","thumb":"http://static.chayu.com/mobile/main/img/common/site_logo.png","title":"茶语网|茶语圈子-茶语网，当代茶文化推广者","url":"http://m.quanzi.chayu.com"}}
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
         * banner : http://img.chayu.com/banner/1608/07/63157a6e607a9283.jpg
         * cover : http://static.chayu.com/mobile/main/img/main/quanzi/icons/icon_quanzi_logo.png
         * huati : [{"content":"茶语圈子新人报到季第1期活动中奖名单公布，小伙伴们快来领茶样咯！PS:第2期正在火热进行中\u2026","resource_id":"607517","resource_type":"4","source":{"created_uid":"241460","hits":"7848","nickname":"茶语网茶小二","replies":"30","tid":"607517"},"template_id":"10","thumb":"http://img.chayu.com/banner/1611/30/664583e267a62bf4.jpg","title":"[新人报到季]第1期中奖名单"},{"content":"喝茶能解酒吗？这不是一道判断题，是一道救命题啊。答案颠覆你之前的认知，赶快码住看起来。","resource_id":"607506","resource_type":"4","source":{"created_uid":"215594","hits":"7360","nickname":"茶语茶小花","replies":"29","tid":"607506"},"template_id":"10","thumb":"http://img.chayu.com/banner/1611/30/918583e2731ea486.jpg","title":"茶友问答|喝茶能解酒吗？"},{"content":"茶汤呈浅杏黄色，曾相识的苦香钻入鼻中，似\u201c雀舌\u201d的味道，当时还以为串味了，没喝几口就倒掉了。","resource_id":"607601","resource_type":"4","source":{"created_uid":"1284929","hits":"4421","nickname":"rhymist","replies":"26","tid":"607601"},"template_id":"10","thumb":"http://img.chayu.com/banner/1611/30/633583e29a7a9f89.jpg","title":"晒茶记|曾经被误解的滋味"}]
         * is_sign : flase
         * quanzi : [{"resource_id":"23","resource_type":"3","source":{"attention_num":"2155","created_uid":"225540","gid":"23","nickname":"暂无","posts":"16830","topics":"6824"},"template_id":"9","thumb":"http://img.chayu.com/banner/1609/27/10257e9d911b489c.jpg","title":"学茶圈","url":""},{"resource_id":"10","resource_type":"3","source":{"attention_num":"797","created_uid":"225540","gid":"10","nickname":"zoetory","posts":"1181","topics":"200"},"template_id":"9","thumb":"http://img.chayu.com/banner/1609/27/69857e9d9c7147fa.jpg","title":"茶语评茶","url":""},{"resource_id":"21","resource_type":"3","source":{"attention_num":"1343","created_uid":"225540","gid":"21","nickname":"慧者心清","posts":"26884","topics":"3968"},"template_id":"9","thumb":"http://img.chayu.com/banner/1609/27/49757e9d9355e189.jpg","title":"晒茶","url":""}]
         * share : {"description":"茶语圈子，专为茶叶爱好者提供交流的茶叶社区，在这里，茶友们可以获得最新最热的茶叶资讯。","thumb":"http://static.chayu.com/mobile/main/img/common/site_logo.png","title":"茶语网|茶语圈子-茶语网，当代茶文化推广者","url":"http://m.quanzi.chayu.com"}
         */

        private String banner;
        private String cover;
        private String is_sign;
        private ShareBean share;
        private List<HuatiBean> huati;
        private List<QuanziBean> quanzi;

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(String is_sign) {
            this.is_sign = is_sign;
        }

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public List<HuatiBean> getHuati() {
            return huati;
        }

        public void setHuati(List<HuatiBean> huati) {
            this.huati = huati;
        }

        public List<QuanziBean> getQuanzi() {
            return quanzi;
        }

        public void setQuanzi(List<QuanziBean> quanzi) {
            this.quanzi = quanzi;
        }

        public static class ShareBean {
            /**
             * description : 茶语圈子，专为茶叶爱好者提供交流的茶叶社区，在这里，茶友们可以获得最新最热的茶叶资讯。
             * thumb : http://static.chayu.com/mobile/main/img/common/site_logo.png
             * title : 茶语网|茶语圈子-茶语网，当代茶文化推广者
             * url : http://m.quanzi.chayu.com
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



        public static class QuanziBean {
            /**
             * resource_id : 23
             * resource_type : 3
             * source : {"attention_num":"2155","created_uid":"225540","gid":"23","nickname":"暂无","posts":"16830","topics":"6824"}
             * template_id : 9
             * thumb : http://img.chayu.com/banner/1609/27/10257e9d911b489c.jpg
             * title : 学茶圈
             * url :
             */

            private String resource_id;
            private String resource_type;
            private SourceBeanX source;
            private String template_id;
            private String thumb;
            private String title;
            private String url;

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

            public SourceBeanX getSource() {
                return source;
            }

            public void setSource(SourceBeanX source) {
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

            public static class SourceBeanX {
                /**
                 * attention_num : 2155
                 * created_uid : 225540
                 * gid : 23
                 * nickname : 暂无
                 * posts : 16830
                 * topics : 6824
                 */

                private String attention_num;
                private String created_uid;
                private String gid;
                private String nickname;
                private String posts;
                private String topics;

                public String getAttention_num() {
                    return attention_num;
                }

                public void setAttention_num(String attention_num) {
                    this.attention_num = attention_num;
                }

                public String getCreated_uid() {
                    return created_uid;
                }

                public void setCreated_uid(String created_uid) {
                    this.created_uid = created_uid;
                }

                public String getGid() {
                    return gid;
                }

                public void setGid(String gid) {
                    this.gid = gid;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getPosts() {
                    return posts;
                }

                public void setPosts(String posts) {
                    this.posts = posts;
                }

                public String getTopics() {
                    return topics;
                }

                public void setTopics(String topics) {
                    this.topics = topics;
                }
            }
        }
    }
}
