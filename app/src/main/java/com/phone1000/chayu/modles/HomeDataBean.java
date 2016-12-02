package com.phone1000.chayu.modles;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
@Table(name = "HomeDataBean")
public class HomeDataBean {

    @Column(name = "article")
    private List<ArticleBean> article;
    @Column(name = "group")
    private List<GroupBean> group;
    @Column(name = "shiji")
    private List<ShiJIBean> shiji;
    @Column(name = "slide")
    private List<SlideBean> slide;
    @Column(name = "teaCate")
    private List<TeaCateBean> teaCate;

    public List<ArticleBean> getArticle() {
        return article;
    }

    public void setArticle(List<ArticleBean> article) {
        this.article = article;
    }

    public List<GroupBean> getGroup() {
        return group;
    }

    public void setGroup(List<GroupBean> group) {
        this.group = group;
    }

    public List<ShiJIBean> getShiji() {
        return shiji;
    }

    public void setShiji(List<ShiJIBean> shiji) {
        this.shiji = shiji;
    }

    public List<SlideBean> getSlide() {
        return slide;
    }

    public void setSlide(List<SlideBean> slide) {
        this.slide = slide;
    }

    public List<TeaCateBean> getTeaCate() {
        return teaCate;
    }

    public void setTeaCate(List<TeaCateBean> teaCate) {
        this.teaCate = teaCate;
    }
}
