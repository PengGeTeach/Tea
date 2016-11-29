package com.phone1000.chayu.adapters;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class GridViewAdapter extends TeachBaseAdapter<TeaComm.DataBean.CategoryListBean.Children>{


    private static final String TAG = GridViewAdapter.class.getSimpleName();

    public GridViewAdapter(Context context, List<TeaComm.DataBean.CategoryListBean.Children> data, int layoutResId) {
        super(context, data, layoutResId);
        for (TeaComm.DataBean.CategoryListBean.Children child: data ) {
            child.getName();
            Log.e(TAG, "GridViewAdapter: "+child.getName());
        }
    }

    @Override
    protected void bindData(ViewHolder holder, TeaComm.DataBean.CategoryListBean.Children item, int i) {

        TextView gridText = (TextView) holder.getView(R.id.grid_text);
        Log.e(TAG, "bindData: "+item.getName() +i);
        gridText.setText(item.getName());

    }
}
