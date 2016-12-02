package com.phone1000.chayu.modles;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
@Table(name = "ArticleBean")
public class ArticleBean {
    @Column(name = "id",isId = true,autoGen = true)
    private int id;
    @Column(name = "commend")
    private String commend;
    @Column(name = "resource_id")
    private String resource_id;
    @Column(name = "resource_type")
    private String resource_type;
    @Column(name = "template_id")
    private String template_id;
    @Column(name = "thumb")
    private String thumb;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @Column(name = "source")
    private ArticleSourceBean source;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArticleSourceBean getSource() {
        return source;
    }

    public void setSource(ArticleSourceBean source) {
        this.source = source;
    }

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

}
