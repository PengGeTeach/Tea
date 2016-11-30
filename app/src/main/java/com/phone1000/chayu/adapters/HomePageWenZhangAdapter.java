package com.phone1000.chayu.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.ArticleBean;
import com.phone1000.chayu.modles.ArticleSourceBean;
import com.phone1000.chayu.modles.HomePageModel;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class HomePageWenZhangAdapter extends RecyclerView.Adapter<HomePageWenZhangAdapter.ViewHolder> implements View.OnClickListener {

    private static final String TAG = HomePageWenZhangAdapter.class.getSimpleName();
    private List<ArticleBean> data;
    private LayoutInflater inflater;
    private Context context;


    public HomePageWenZhangAdapter(Context context, List<ArticleBean> data) {
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
        View item = inflater.inflate(R.layout.item_wenzhang_homepage, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ArticleBean articleBean = data.get(position);
        ArticleSourceBean source = articleBean.getSource();

        holder.itemView.setTag(articleBean);
        holder.itemView.setOnClickListener(this);

        holder.title.setText(articleBean.getTitle());


        x.image().bind(holder.image,articleBean.getThumb());


        switch (articleBean.getCommend()) {
            case "1":
                holder.tag.setBackgroundResource(R.mipmap.tou_tiao);
                break;
            case "2":
                holder.tag.setText("最新| ");
                break;
            case "3":
                holder.tag.setText("最热| ");
                break;
            case "4":
                holder.tag.setText("专题| ");
                break;
        }
        holder.tag.setOnClickListener(this);

        holder.text1.setText(source.getSuports());


        holder.text2.setText(source.getClicks());

    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public void updataRes(List<ArticleBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(View v) {

        ArticleBean bean = (ArticleBean) v.getTag();
        String url = bean.getSource().getUrl();
        Intent intent = new Intent(context, DetailsInFormation.class);
        intent.putExtra("path",url);
        context.startActivity(intent);


    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

         ImageView image;
         TextView tag,title,text1,text2;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.item_wenzhang_homepage_image);
            tag= (TextView) itemView.findViewById(R.id.item_wenzhang_homepage_tag);
            title= (TextView) itemView.findViewById(R.id.item_wenzhang_homepage_title);
            text1= (TextView) itemView.findViewById(R.id.item_wenzhang_homepage_text1);
            text2= (TextView) itemView.findViewById(R.id.item_wenzhang_homepage_text2);
        }
    }
}
