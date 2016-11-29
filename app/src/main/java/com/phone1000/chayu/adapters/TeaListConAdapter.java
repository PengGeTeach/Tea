package com.phone1000.chayu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaListModel;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TeaListConAdapter extends BaseAdapter {

    private List<TeaListModel.DataBean.ListBean> data;
    private LayoutInflater inflater;
    public TeaListConAdapter(Context context,List<TeaListModel.DataBean.ListBean> data){
        inflater=LayoutInflater.from(context);
        if (data != null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }

    public void updateRes(List<TeaListModel.DataBean.ListBean> data){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    public void addRes(List<TeaListModel.DataBean.ListBean> data){
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public TeaListModel.DataBean.ListBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.item_tealistconlist,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        TeaListModel.DataBean.ListBean item = getItem(position);
        x.image().bind(holder.image, item.getThumb());
        holder.title.setText("["+item.getBrand()+"]"+item.getTitle());
        holder.chayupingfen.setText(item.getReview_score());
        holder.zonghepingfen.setText(item.getScore());
        holder.price.setText("参考价: ¥"+item.getPrice());
        holder.catename.setText(item.getCatename());


        return convertView;
    }

    private class ViewHolder{

        ImageView image;
        TextView title,catename,chayupingfen,zonghepingfen,price;

        public ViewHolder(View itemView){
            image= (ImageView) itemView.findViewById(R.id.item_tealistconlist_image);
            title= (TextView) itemView.findViewById(R.id.item_tealistconlist_title);
            catename= (TextView) itemView.findViewById(R.id.item_tealistconlist_tea_type);
            chayupingfen= (TextView) itemView.findViewById(R.id.item_tealistconlist_chayu);
            zonghepingfen= (TextView) itemView.findViewById(R.id.item_tealistconlist_zonghe);
            price= (TextView) itemView.findViewById(R.id.item_tealistconlist_price);
        }
    }

}
