package com.phone1000.chayu.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone1000.chayu.PinPaiActivity;
import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.ChaLeiModle;
import com.phone1000.chayu.utils.TeachBaseAdapter;
import com.phone1000.chayu.utils.TeachMultiTypeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class PinPaiGridViewAdapter2 extends TeachMultiTypeAdapter<ChaLeiModle.DataBean.BrandListBean.BrandListChild> implements View.OnClickListener {

    private static final String TAG = PinPaiGridViewAdapter2.class.getSimpleName();

    private SendPinpaiPositiob sendPinpaiPositiob;
    private int position;
    private List<ChaLeiModle.DataBean.BrandListBean.BrandListChild> data;
    private Context context;

    public PinPaiGridViewAdapter2(Context context, List<ChaLeiModle.DataBean.BrandListBean.BrandListChild> data,SendPinpaiPositiob sendPinpaiPositiob,int position, int... layoutIds) {
        super(context, data, layoutIds);
        this.sendPinpaiPositiob = sendPinpaiPositiob;
        this.position = position;
        this.data = data;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        if(tag ==0){
            data.remove(0);
            sendPinpaiPositiob.sendposition(tag);
        }else{

            Intent intent = new Intent(context, PinPaiActivity.class);
            intent.putExtra("name",data.get(tag).getName());
            intent.putExtra("brandid",data.get(tag).getId());
            context.startActivity(intent);
        }
    }

    @Override
    public void bindData(ViewHolder holder, ChaLeiModle.DataBean.BrandListBean.BrandListChild item, int i) {

        switch (item.getType()){

            case 0:
                LinearLayout linerview = (LinearLayout) holder.getView(R.id.ll);
                ((TextView) linerview.getChildAt(0)).setText(item.getName());
                Log.e(TAG, "bindData: 测试姓名"+item.getName() );
                linerview.setTag(i);
                linerview.setOnClickListener(this);

                break;
            case 1:
                TextView textView = (TextView) holder.getView(R.id.grid_text);
                textView.setText(item.getName());
                textView.setTag(i);
                textView.setOnClickListener(this);
                break;
        }
    }

    public interface SendPinpaiPositiob{

        void sendposition(int position);

    }

}
