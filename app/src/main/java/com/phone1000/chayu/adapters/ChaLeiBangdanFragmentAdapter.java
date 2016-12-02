package com.phone1000.chayu.adapters;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.BnagdanActivity;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import org.greenrobot.eventbus.EventBus;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class ChaLeiBangdanFragmentAdapter extends TeachBaseAdapter<BnagdanActivity.DataBean.TeaCateBean>{


    public ChaLeiBangdanFragmentAdapter(Context context, List<BnagdanActivity.DataBean.TeaCateBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, BnagdanActivity.DataBean.TeaCateBean item, int i) {

        ImageView imageView = (ImageView) holder.getView(R.id.parent_image);
        ImageView imageView1 = (ImageView) holder.getView(R.id.parent_down);
        imageView1.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) holder.getView(R.id.parent_text);
        x.image().bind(imageView,item.getThumb());
        textView.setText(item.getName());
    }
}
