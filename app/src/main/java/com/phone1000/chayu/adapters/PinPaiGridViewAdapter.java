package com.phone1000.chayu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.ChaLeiModle;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class PinPaiGridViewAdapter extends TeachBaseAdapter<ChaLeiModle.DataBean.BrandListBean> implements View.OnClickListener {
    private Pingpaiitem p;

    private static final String TAG = PinPaiGridViewAdapter.class.getSimpleName();

    public PinPaiGridViewAdapter(Context context, List<ChaLeiModle.DataBean.BrandListBean> data, int layoutResId,Pingpaiitem p) {
        super(context, data, layoutResId);
        this.p = p;
    }

    @Override
    protected void bindData(ViewHolder holder, ChaLeiModle.DataBean.BrandListBean item, int i) {

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
        p.sendpingpaiitem(tag);

    }

    public interface Pingpaiitem{
        void sendpingpaiitem(int position);
    }



}
