package com.phone1000.chayu.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class WenZhangShangLaJiaZai {


    /**
     * data : [{"clicks":"5090","id":"196200","suports":"80","thumb":"http://img.chayu.com/article/1612/01/241583fb36ca0ff7.jpg!160120","title":"市集丨想喝熟普却受不了那一股子堆味？尝尝这些熟普，口感纯净，入口顺柔，你一定会爱上。"},{"clicks":"4.70万","id":"196199","suports":"972","thumb":"http://img.chayu.com/article/1611/30/788583e7b54b09de.jpg!160120","title":"这个潮州最帅的男人，自己花了几百万，把非遗技艺做成了非遗公益"},{"clicks":"2.27万","id":"196197","suports":"383","thumb":"http://img.chayu.com/article/1611/30/530583e77dc463bd.jpg!160120","title":"中国\u201c普洱茶第一人\u201d邓时海 \u2014\u2014\u201c福禄圆茶\u201d十周年品鉴会，畅谈未来20年茶行业发展！"},{"clicks":"2.22万","id":"196198","suports":"388","thumb":"http://img.chayu.com/article/1611/30/637583e76e78af4d.jpg!160120","title":"茗星私享│付磊：最爱是那家乡祁门红"},{"clicks":"5.61万","id":"196194","suports":"965","thumb":"http://img.chayu.com/article/1611/29/563583d1c4f7d42d.jpg!160120","title":"上流社会喝茶用水指南：你确定依云真的是高端水？"},{"clicks":"1.41万","id":"196193","suports":"280","thumb":"http://img.chayu.com/article/1611/29/641583d0fef65f7e.jpg!160120","title":"祥源茶\u201c小产区  大未来\u201d品牌魅力耀动深圳 \u2014\u2014祥源茶2016深圳品牌推介会举行"},{"clicks":"1.53万","id":"196191","suports":"277","thumb":"http://img.chayu.com/article/1611/29/979583d101d6bc08.jpg!160120","title":"直播预告 |　探访爱马仕设计师的首个茶店作品\u2014\u2014继续广州行"},{"clicks":"2.24万","id":"196192","suports":"427","thumb":"http://img.chayu.com/article/1611/29/557583d10005c671.jpg!160120","title":"茗星私享│林葆松：既要不枉此生，也要见到辉煌"},{"clicks":"1.45万","id":"196190","suports":"270","thumb":"http://img.chayu.com/article/1611/29/203583d102d39860.jpg!160120","title":"市集|两次发售均一抢而光普洱界中的\u201c费列罗\u201d丹珠为什么这么火爆？"},{"clicks":"5.45万","id":"196181","suports":"914","thumb":"http://img.chayu.com/article/1611/28/247583bd6af0bdc9.jpg!160120","title":"大山深处的老茶馆  普洱渡的历史和记忆"}]
     * msg : 数据获取成功
     * state : 400
     */

    private String msg;
    private int state;
    private List<ListBean> data;

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

    public List<ListBean> getData() {
        return data;
    }

    public void setData(List<ListBean> data) {
        this.data = data;
    }

}
