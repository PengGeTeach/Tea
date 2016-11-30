package com.phone1000.chayu.adapters;

import android.content.Context;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.PinPaiGridViewModle;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class PinJianNianDuAdapter extends TeachBaseAdapter<PinPaiGridViewModle.DataBean.PingjiannianfenBean>{


    public PinJianNianDuAdapter(Context context, List<PinPaiGridViewModle.DataBean.PingjiannianfenBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, PinPaiGridViewModle.DataBean.PingjiannianfenBean item, int i) {
        TextView textView = (TextView) holder.getView(R.id.niandu);
        textView.setText(item.getName());
    }
}
