package com.phone1000.chayu.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.ListBean;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.</>
 */
public class WenZhangZuiXinReMenAdapter extends TeachBaseAdapter<ListBean>{


    public WenZhangZuiXinReMenAdapter(Context context, List<ListBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }


    @Override
    protected void bindData(ViewHolder holder, ListBean item, int i) {
        ImageView view = (ImageView) holder.getView(R.id.image);
        x.image().bind(view,item.getThumb());
        ImageView imageView = (ImageView) holder.getView(R.id.toutiao);
        imageView.setVisibility(View.GONE);
        TextView textView = (TextView) holder.getView(R.id.text_item1);
        textView.setText(item.getTitle());
        TextView textView1 = (TextView) holder.getView(R.id.zan);
        textView1.setText(item.getSuports());
        TextView view1 = (TextView) holder.getView(R.id.liulan);
        view1.setText(item.getClicks());
    }
}
