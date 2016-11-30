package com.phone1000.chayu.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.event.PinPaiGridViewEvent;
import com.phone1000.chayu.fragments.PinPaiGridView;
import com.phone1000.chayu.modles.ChaLeiModle;
import com.phone1000.chayu.modles.PinPaiGridViewModle;
import com.phone1000.chayu.utils.ListViewUtils;
import com.phone1000.chayu.utils.TeachMultiTypeAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class PinPaiGridViewAdapter4 extends TeachMultiTypeAdapter<PinPaiGridViewModle.DataBean.BrandListBean.BrandListBeanChild> implements View.OnClickListener {


    private static final String TAG = PinPaiGridViewAdapter4.class.getSimpleName();
    private List<PinPaiGridViewModle.DataBean.BrandListBean.BrandListBeanChild> data;
    private SendPinpaiPositiob sendPinpaiPositiob;

    public PinPaiGridViewAdapter4(Context context, List<PinPaiGridViewModle.DataBean.BrandListBean.BrandListBeanChild> data,SendPinpaiPositiob sendPinpaiPositiob, int... layoutIds) {
        super(context, data, layoutIds);
        this.data = data;
        this.sendPinpaiPositiob = sendPinpaiPositiob;
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        if(tag ==0){
            data.remove(0);
            sendPinpaiPositiob.sendposition(tag);
        }else{
            Log.e(TAG, "onClick: "+data.get(tag).getName()+""+data.get(tag).getId());
            PinPaiGridViewEvent event = new PinPaiGridViewEvent(198);
            event.setId(data.get(tag).getId());
            event.setName(data.get(tag).getName());
            EventBus.getDefault().post(event);
            PinPaiGridViewEvent pinPaiGridViewEvent = new PinPaiGridViewEvent(197);
            pinPaiGridViewEvent.setId(data.get(tag).getId());
            EventBus.getDefault().postSticky(pinPaiGridViewEvent);
        }
    }

    @Override
    public void bindData(ViewHolder holder, PinPaiGridViewModle.DataBean.BrandListBean.BrandListBeanChild item, int i) {

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
