package com.phone1000.chayu.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.GroupBean;
import com.phone1000.chayu.modles.GroupSourceBean;
import com.phone1000.chayu.modles.HomePageModel;
import com.phone1000.chayu.path.UtilPath;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class HomePageQuanZiAdapter extends RecyclerView.Adapter<HomePageQuanZiAdapter.ViewHolder> implements View.OnClickListener {

    private List<GroupBean> data;
    private LayoutInflater inflater;
    private Context context;


    public HomePageQuanZiAdapter(Context context, List<GroupBean> data) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        if (data != null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.item_quanzi_homepage, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GroupBean groupBean = data.get(position);
        GroupSourceBean source = groupBean.getSource();
        switch (groupBean.getResource_type()) {
            case "3":
                holder.text1.setText("关注: "+source.getAttention_num());
                holder.text2.setText("  话题: "+source.getPosts());
                holder.text3.setText("圈主: "+source.getNickname());
                break;
            case "4":
                holder.text1.setText("评论: "+source.getReplies());
                holder.text2.setText("  阅读: "+source.getHits());
                holder.text3.setText("贴主: "+source.getNickname());
                break;
        }
        holder.title.setText(groupBean.getTitle());

        x.image().bind(holder.image,groupBean.getThumb());

        holder.itemView.setTag(groupBean);
        holder.itemView.setOnClickListener(this);


    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public void updataRes(List<GroupBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(View v) {
        GroupBean bean = (GroupBean) v.getTag();
        String url = UtilPath.QUANZI_XIANGQING+bean.getResource_id();
        Intent intent = new Intent(context, DetailsInFormation.class);
        intent.putExtra("path",url);
        context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

         ImageView image;
         TextView text1,text2,text3,title;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.item_quanzi_homepage_image);
            text1= (TextView) itemView.findViewById(R.id.item_quanzi_homepage_text1);
            text2= (TextView) itemView.findViewById(R.id.item_quanzi_homepage_text2);
            text3= (TextView) itemView.findViewById(R.id.item_quanzi_homepage_text3);
            title= (TextView) itemView.findViewById(R.id.item_quanzi_homepage_title);
        }
    }
}
