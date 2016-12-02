package com.phone1000.chayu.modles;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class HuatiBean {

    /**
     * content : 茶语圈子新人报到季第1期活动中奖名单公布，小伙伴们快来领茶样咯！PS:第2期正在火热进行中…
     * resource_id : 607517
     * resource_type : 4
     * source : {"created_uid":"241460","hits":"7848","nickname":"茶语网茶小二","replies":"30","tid":"607517"}
     * template_id : 10
     * thumb : http://img.chayu.com/banner/1611/30/664583e267a62bf4.jpg
     * title : [新人报到季]第1期中奖名单
     */

    private String content;
    private String resource_id;
    private String resource_type;
    private SourceBean source;
    private String template_id;
    private String thumb;
    private String title;

    private String attach;
    private String created_time;
    private String created_uid;
    private String subject;
    private String replies;
    private String hits;
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getCreated_uid() {
        return created_uid;
    }

    public void setCreated_uid(String created_uid) {
        this.created_uid = created_uid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public SourceBean getSource() {
        return source;
    }

    public void setSource(SourceBean source) {
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

    public static class SourceBean {
        /**
         * created_uid : 241460
         * hits : 7848
         * nickname : 茶语网茶小二
         * replies : 30
         * tid : 607517
         */

        private String created_uid;
        private String hits;
        private String nickname;
        private String replies;
        private String tid;

        public String getCreated_uid() {
            return created_uid;
        }

        public void setCreated_uid(String created_uid) {
            this.created_uid = created_uid;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getReplies() {
            return replies;
        }

        public void setReplies(String replies) {
            this.replies = replies;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }

}
