package com.phone1000.chayu.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.ListBean;
import com.phone1000.chayu.modles.WenZhangModle;
import com.phone1000.chayu.utils.TeachMultiTypeAdapter;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
public class WenzhangAdapter extends TeachMultiTypeAdapter<ListBean>{

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getShow_type()-1;
    }


    public WenzhangAdapter(Context context, List<ListBean> data, int... layoutIds) {
        super(context, data, layoutIds);
    }

    @Override
    public void bindData(ViewHolder holder, ListBean item, int i) {


        switch (item.getShow_type()){

            case 1:
                ImageView imageView = (ImageView) holder.getView(R.id.image);
                x.image().bind(imageView,item.getThumb());
                TextView item1text = (TextView) holder.getView(R.id.text_item1);
                ImageView imageView2 = (ImageView) holder.getView(R.id.toutiao);

                if (item.getIs_toutiao() == 1){
                    item1text.setText("          "+item.getTitle());
                }else{
                    imageView2.setVisibility(View.GONE);
                    item1text.setText(item.getTitle());
                }
                TextView zanitem1 = (TextView) holder.getView(R.id.zan);
                zanitem1.setText(item.getSuports());
                TextView liulanitem1 = (TextView) holder.getView(R.id.liulan);
                liulanitem1.setText(item.getClicks());
                break;
            case 2:

                TextView textitem2 = (TextView) holder.getView(R.id.text);
                textitem2.setText(item.getTitle());
                LinearLayout layout = (LinearLayout) holder.getView(R.id.ll);
                for (int j = 0; j < item.getThumbs().size(); j++) {
                    ImageView imageView1 = (ImageView) layout.getChildAt(j);
                    x.image().bind(imageView1,item.getThumbs().get(j).getThumb());
                }
                break;

            case 3:
                ImageView imageView1 = (ImageView) holder.getView(R.id.image);
                x.image().bind(imageView1,item.getThumb());
                break;
        }

    }
}
