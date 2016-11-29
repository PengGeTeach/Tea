package com.phone1000.chayu.adapters;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.BangDanModle;
import com.phone1000.chayu.utils.ListViewUtils;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class BangDanAdapter extends TeachBaseAdapter<BangDanModle.DataBean.TopListBean>{

    private Context context;

    public BangDanAdapter(Context context, List<BangDanModle.DataBean.TopListBean> data, int layoutResId) {
        super(context, data, layoutResId);
        this.context = context;
    }

    @Override
    protected void bindData(ViewHolder holder, BangDanModle.DataBean.TopListBean item, int i) {

        TextView textView = (TextView) holder.getView(R.id.pingfenbang);
        textView.setText(item.getTitle()+"("+item.getYear()+")");
        ListView listView = (ListView) holder.getView(R.id.bangdan_listView);

        BangdanChildListAdapter bangdanChildListAdapter = new BangdanChildListAdapter(context, item.getList(), R.layout.bangdan_listview_child_item);

        listView.setAdapter(bangdanChildListAdapter);

        ListViewUtils.setListViewHeightBasedOnChildren(listView);

    }
}