package com.phone1000.chayu.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.HomePageModel;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class HomePageShiJiAdapter extends RecyclerView.Adapter<HomePageShiJiAdapter.ViewHolder> {

    private List<HomePageModel.DataBean.ShijiBean> data;
    private LayoutInflater inflater;


    public HomePageShiJiAdapter(Context context,List<HomePageModel.DataBean.ShijiBean> data) {
        inflater=LayoutInflater.from(context);
        if (data != null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.item_shiji_homepage, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HomePageModel.DataBean.ShijiBean shijiBean = data.get(position);
        holder.text1.setText(shijiBean.getTags()+" |");
        holder.text2.setText(shijiBean.getTitle());

        x.image().bind(holder.image,shijiBean.getThumb());


    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public void updataRes(List<HomePageModel.DataBean.ShijiBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

         ImageView image;
         TextView text1,text2;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.item_shiji_homepage_image);
            text1= (TextView) itemView.findViewById(R.id.item_shiji_homepage_text1);
            text2= (TextView) itemView.findViewById(R.id.item_shiji_homepage_text2);
        }
    }
}
