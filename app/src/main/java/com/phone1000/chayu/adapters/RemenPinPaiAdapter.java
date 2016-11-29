package com.phone1000.chayu.adapters;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.ChaLeiModle;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class RemenPinPaiAdapter extends TeachBaseAdapter <ChaLeiModle.DataBean.HotListBean>{


    private static final String TAG = RemenPinPaiAdapter.class.getSimpleName();

    public RemenPinPaiAdapter(Context context, List<ChaLeiModle.DataBean.HotListBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, ChaLeiModle.DataBean.HotListBean item, int i) {
        ImageView imageView = (ImageView) holder.getView(R.id.remen_imageview);
        TextView textView = (TextView) holder.getView(R.id.remen_textview);

        x.image().bind(imageView,item.getLogo());
        Log.e(TAG, "bindData: 测试"+item.getLogo() );
        textView.setText(item.getName());

    }
}
