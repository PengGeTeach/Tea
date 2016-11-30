package com.phone1000.chayu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.PinPaiGridViewModle;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class PinPaiGridViewAdapter3 extends TeachBaseAdapter<PinPaiGridViewModle.DataBean.BrandListBean> implements View.OnClickListener {


    private static final String TAG = PinPaiGridViewAdapter3.class.getSimpleName();
    private Pingpaiitem pingpaiitem;

    public PinPaiGridViewAdapter3(Context context, List<PinPaiGridViewModle.DataBean.BrandListBean> data, int layoutResId,Pingpaiitem pingpaiitem) {
        super(context, data, layoutResId);
        this.pingpaiitem = pingpaiitem;
    }

    @Override
    protected void bindData(ViewHolder holder, PinPaiGridViewModle.DataBean.BrandListBean item, int i) {

        TextView textView = (TextView) holder.getView(R.id.grid_text);
        Log.e(TAG, "bindData: " +item.getLetter());
        textView.setText(item.getLetter());

        textView.setTag(i);
        textView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        Log.e(TAG, "onClick: 我点击了"+tag );
        pingpaiitem.sendpingpaiitem(tag);
    }

    public interface Pingpaiitem{
        void sendpingpaiitem(int position);
    }

}
