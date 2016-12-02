package com.phone1000.chayu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.ShiJiModel;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public class ShijifragmentListAdapter extends BaseAdapter {

    private List<ShiJiModel.DataBean.MasterArrBean> data;
    private LayoutInflater inflater;
    private ImageOptions options;

    public ShijifragmentListAdapter(Context context, List<ShiJiModel.DataBean.MasterArrBean> data) {
        options = new ImageOptions.Builder()
                .setCircular(true)
                .build();
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<ShiJiModel.DataBean.MasterArrBean> data) {

        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    public void addRes(List<ShiJiModel.DataBean.MasterArrBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getmDinyiType();
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public ShiJiModel.DataBean.MasterArrBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder0 viewHolder0;
        ViewHolder1 viewHolder1;
        ViewHolder2 viewHolder2;

        ShiJiModel.DataBean.MasterArrBean item = getItem(position);
        switch (item.getmDinyiType()) {
            case 0:
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_shiji_00, parent, false);
                    viewHolder0 = new ViewHolder0(convertView);
                    convertView.setTag(viewHolder0);
                } else {
                    viewHolder0 = (ViewHolder0) convertView.getTag();
                }
                viewHolder0.content.setText(item.getContent());
                x.image().bind(viewHolder0.image, item.getThumb());

                break;
            case 1:

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_shiji_01, parent, false);
                    viewHolder1 = new ViewHolder1(convertView);
                    convertView.setTag(viewHolder1);
                } else {
                    viewHolder1 = (ViewHolder1) convertView.getTag();
                }
                x.image().bind(viewHolder1.image, item.getThumb());


                break;
            case 2:
                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.item_shiji_02, parent, false);
                    viewHolder2 = new ViewHolder2(convertView);
                    convertView.setTag(viewHolder2);
                } else {
                    viewHolder2 = (ViewHolder2) convertView.getTag();
                }

                viewHolder2.icon.setText(item.getIcon());
                viewHolder2.title.setText(item.getTitle());
                x.image().bind(viewHolder2.image, item.getThumb());
                viewHolder2.xiangqing.setText(item.getCatename() + "·" + item.getComment_count() + "条评论·" + item.getSellerTypeName());

                x.image().bind(viewHolder2.touxiang,item.getSellerAvatar(), options);
                break;
        }


        return convertView;
    }


    private class ViewHolder0 {
        ImageView image;
        TextView content;
        Button mingjiajieshao, zuopinxiangqing;

        public ViewHolder0(View v) {
            image = (ImageView) v.findViewById(R.id.item_shiji_00_image);
            content = (TextView) v.findViewById(R.id.item_shiji_00_content);
            mingjiajieshao = (Button) v.findViewById(R.id.item_shiji_00_mingjiajieshao);
            zuopinxiangqing = (Button) v.findViewById(R.id.item_shiji_00_zuopinxinagqing);
        }
    }

    private class ViewHolder1 {
        ImageView image;

        public ViewHolder1(View v) {
            image = (ImageView) v.findViewById(R.id.item_shiji_01_image);
        }
    }

    private class ViewHolder2 {
        ImageView image, touxiang;
        TextView title, icon, goumaijilu, xiangqing;

        public ViewHolder2(View v) {
            image = (ImageView) v.findViewById(R.id.item_shiji_02_image);
            title = (TextView) v.findViewById(R.id.item_shiji_02_title);
            touxiang = (ImageView) v.findViewById(R.id.item_shiji_02_touxiang);
            icon = (TextView) v.findViewById(R.id.item_shiji_02_icon);
            goumaijilu = (TextView) v.findViewById(R.id.item_shiji_02_jilu);
            xiangqing = (TextView) v.findViewById(R.id.item_shiji_02_xiangqing);
        }
    }


}
