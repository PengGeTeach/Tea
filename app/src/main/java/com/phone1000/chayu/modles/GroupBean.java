package com.phone1000.chayu.modles;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
@Table(name ="GroupBean" )
public class GroupBean {


    @Column(name = "content")
    private String content;
    @Column(name = "resource_id")
    private String resource_id;
    @Column(name = "resource_type")
    private String resource_type;
    @Column(name = "template_id",isId = true,autoGen =false)
    private String template_id;
    @Column(name = "thumb")
    private String thumb;
    @Column(name = "title")
    private String title;
    @Column(name = "source")
    private GroupSourceBean source;
    @Column(name = "tid")
    private String tid;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    public GroupSourceBean getSource() {
        return source;
    }

    public void setSource(GroupSourceBean source) {
        this.source = source;
    }
}
