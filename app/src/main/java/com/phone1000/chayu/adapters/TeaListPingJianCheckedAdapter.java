package com.phone1000.chayu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaListsSearchModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TeaListPingJianCheckedAdapter extends BaseAdapter {

    private static final String TAG = TeaListPingJianCheckedAdapter.class.getSimpleName();
    private List<TeaListsSearchModel.DataBean.PingjiannianfenBean> data;
    private LayoutInflater inflater;

    public TeaListPingJianCheckedAdapter(Context context, List<TeaListsSearchModel.DataBean.PingjiannianfenBean> data){
        inflater=LayoutInflater.from(context);
        if (data != null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }

    public void updateRes(List<TeaListsSearchModel.DataBean.PingjiannianfenBean> data){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public TeaListsSearchModel.DataBean.PingjiannianfenBean getItem(int position) {
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
            convertView=inflater.inflate(R.layout.item_fragment_pingfen_pingjian_checked_list,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());



        return convertView;
    }


    private class ViewHolder{

        TextView name;

        public ViewHolder(View itemView){
            name= (TextView) itemView.findViewById(R.id.item_fragment_tea_checked_list2_name);

        }
    }


}
