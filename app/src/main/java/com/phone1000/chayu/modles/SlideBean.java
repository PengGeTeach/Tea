package com.phone1000.chayu.modles;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class SlideBean {
    private String resource_id;
    private String resource_type;
    private String tags;
    private String template_id;
    private String thumb;
    private String title;
    private String url;
    private SlideSourceBean source;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public SlideSourceBean getSource() {
        return source;
    }

    public void setSource(SlideSourceBean source) {
        this.source = source;
    }
}
