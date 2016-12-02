package com.phone1000.chayu.modles;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
@Table(name = "TeaCateBean")
public class TeaCateBean {

    @Column(name = "id",isId = true,autoGen = true)
    private int id;

    @Column(name = "bid")
    private String bid;
    @Column(name = "ico")
    private String ico;
    @Column(name = "name")
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
