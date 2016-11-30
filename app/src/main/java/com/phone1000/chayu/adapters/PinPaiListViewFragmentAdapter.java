package com.phone1000.chayu.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.PinPaiListViewModle;
import com.phone1000.chayu.utils.TeachBaseAdapter;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class PinPaiListViewFragmentAdapter extends TeachBaseAdapter<PinPaiListViewModle.DataBean.ListBean>{


    public PinPaiListViewFragmentAdapter(Context context, List<PinPaiListViewModle.DataBean.ListBean> data, int layoutResId) {
        super(context, data, layoutResId);
    }

    @Override
    protected void bindData(ViewHolder holder, PinPaiListViewModle.DataBean.ListBean item, int i) {
        ImageView imageView = (ImageView) holder.getView(R.id.item_tealistconlist_image);
        x.image().bind(imageView,item.getThumb());
        TextView textViewtitle = (TextView) holder.getView(R.id.item_tealistconlist_title);
        textViewtitle.setText(item.getTitle());
        TextView textViewchayupingfen = (TextView) holder.getView(R.id.item_tealistconlist_chayu);
        textViewchayupingfen.setText(item.getReview_score());
        TextView textViewzonghe = (TextView) holder.getView(R.id.item_tealistconlist_zonghe);
        textViewzonghe.setText(item.getScore());
        TextView textViewfenlei = (TextView) holder.getView(R.id.item_tealistconlist_tea_type);
        textViewfenlei.setText(item.getCatename());
        TextView textViewprice = (TextView) holder.getView(R.id.item_tealistconlist_price);
        textViewprice.setText(item.getPrice());
    }
}
