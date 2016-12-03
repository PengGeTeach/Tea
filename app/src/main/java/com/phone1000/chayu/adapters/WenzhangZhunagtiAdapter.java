package com.phone1000.chayu.adapters;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.WenZhangZhuangTiModle;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class WenzhangZhunagtiAdapter extends TeachBaseAdapter<WenZhangZhuangTiModle.DataBean>{
    public WenzhangZhunagtiAdapter(Context context, List<WenZhangZhuangTiModle.DataBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, WenZhangZhuangTiModle.DataBean item, int i) {

        ImageView imageView = (ImageView) holder.getView(R.id.zhaunti_image);
        x.image().bind(imageView,item.getThumb());
        TextView textView = (TextView) holder.getView(R.id.zhuanti_text);
        textView.setText(item.getTitle());
        TextView textView1 = (TextView) holder.getView(R.id.wenzhang_text);
        textView1.setText(item.getNum().getArticleNum());
        TextView textView2 = (TextView) holder.getView(R.id.wenzhangliulan);
        textView2.setText(item.getNum().getClicks());
    }
}
