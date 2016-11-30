package com.phone1000.chayu.modles;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class GroupSourceBean {

    private String created_uid;
    private String hits;
    private String nickname;
    private String replies;
    private String tid;
    private String attention_num;
    private String posts;

    public String getAttention_num() {
        return attention_num;
    }

    public void setAttention_num(String attention_num) {
        this.attention_num = attention_num;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

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
