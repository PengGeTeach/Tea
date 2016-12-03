package com.phone1000.chayu.event;

import com.phone1000.chayu.modles.TeaComm;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class Chapinevent {

    private List<TeaComm.DataBean.CategoryListBean> teaComm;

    public List<TeaComm.DataBean.CategoryListBean> getTeaComm() {
        return teaComm;
    }

    public void setTeaComm(List<TeaComm.DataBean.CategoryListBean> teaComm) {
        this.teaComm = teaComm;
    }
}
