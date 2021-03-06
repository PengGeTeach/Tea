package com.phone1000.chayu.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.BangDanModle;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class BangdanChildListAdapter extends TeachBaseAdapter<BangDanModle.DataBean.TopListBean.ListBean> implements View.OnClickListener {

    private static final String TAG = BangdanChildListAdapter.class.getSimpleName();
    private Context context;

    public BangdanChildListAdapter(Context context, List<BangDanModle.DataBean.TopListBean.ListBean> data, int layoutResId) {
        super(context, data, layoutResId);
        this.context = context;
    }

    @Override
    protected void bindData(ViewHolder holder, BangDanModle.DataBean.TopListBean.ListBean item, int i) {

        LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.pingfenbang_ll);

        linearLayout.setOnClickListener(this);
        linearLayout.setTag(item);

        TextView textView = (TextView) linearLayout.getChildAt(0);
        textView.setText((i+1)+"");
        if (i>2){
            textView.setBackgroundResource(R.drawable.pingfen1);
        }
        ((TextView) linearLayout.getChildAt(1)).setText("["+item.getBrand()+"]");
        ((TextView) linearLayout.getChildAt(2)).setText(item.getTitle());
        ((TextView) linearLayout.getChildAt(3)).setText(item.getReview_score());
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(context, DetailsInFormation.class);
        BangDanModle.DataBean.TopListBean.ListBean tag = (BangDanModle.DataBean.TopListBean.ListBean) view.getTag();
        Log.e(TAG, "onClick: "+tag.getId() );
        intent.putExtra("path","http://chaping.chayu.com/tea/"+tag.getId());
        context.startActivity(intent);
    }
}
