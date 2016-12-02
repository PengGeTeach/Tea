package com.phone1000.chayu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.event.TeaListEvent;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class GridViewAdapter extends TeachBaseAdapter<TeaComm.DataBean.CategoryListBean.Children> implements View.OnClickListener {


    private static final String TAG = GridViewAdapter.class.getSimpleName();
    private int j;
    private List<TeaComm.DataBean.CategoryListBean.Children> data;

    public GridViewAdapter(Context context, List<TeaComm.DataBean.CategoryListBean.Children> data,int i ,int layoutResId) {
        super(context, data, layoutResId);
        this.j = i;
        this.data = data;
    }

    @Override
    protected void bindData(ViewHolder holder, TeaComm.DataBean.CategoryListBean.Children item, int i) {

        TextView gridText = (TextView) holder.getView(R.id.grid_text);
        gridText.setText(item.getName());
        gridText.setTag(i);
        gridText.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Log.e(TAG, "onClick: "+ view.getTag());
        int tag = (int) view.getTag();
        TeaListEvent event = new TeaListEvent(0x100);
        Log.e(TAG, "onClick:我在这里测试点击 "+tag );
        //event.setSid(data.get(tag).getName());

    }
}
