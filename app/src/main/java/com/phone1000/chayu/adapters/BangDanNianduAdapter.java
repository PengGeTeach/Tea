package com.phone1000.chayu.adapters;

import android.content.Context;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.BangDanDetaileModle;
import com.phone1000.chayu.modles.BnagdanActivity;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class BangDanNianduAdapter extends TeachBaseAdapter<BnagdanActivity.DataBean.YearBean>{
    public BangDanNianduAdapter(Context context, List<BnagdanActivity.DataBean.YearBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, BnagdanActivity.DataBean.YearBean item, int i) {
        TextView view = (TextView) holder.getView(R.id.niandu);
        view.setText(item.getName());
    }
}
