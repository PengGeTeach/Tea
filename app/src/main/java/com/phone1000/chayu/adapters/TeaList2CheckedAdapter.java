package com.phone1000.chayu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaListsSearchModel;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TeaList2CheckedAdapter extends BaseAdapter implements View.OnClickListener {

    private List<TeaListsSearchModel.DataBean.CateChilde> data;
    private LayoutInflater inflater;
    private OnList2ItemClickListener listener;

    public void setListener(OnList2ItemClickListener listener) {
        this.listener = listener;
    }

    public TeaList2CheckedAdapter(Context context, List<TeaListsSearchModel.DataBean.CateChilde> data){
        inflater=LayoutInflater.from(context);
        if (data != null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }

    public void updateRes(List<TeaListsSearchModel.DataBean.CateChilde> data){
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
    public TeaListsSearchModel.DataBean.CateChilde getItem(int position) {
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
            convertView=inflater.inflate(R.layout.item_fragment_tea_checked_list2,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();

        }
        TeaListsSearchModel.DataBean.CateChilde item = getItem(position);
        holder.name.setTag(item);
        holder.name.setText(item.getName());


        return convertView;
    }

    @Override
    public void onClick(View v) {
        TeaListsSearchModel.DataBean.CateChilde childe = (TeaListsSearchModel.DataBean.CateChilde) v.getTag();
        listener.getItemBean(childe);

    }

    private class ViewHolder{


        TextView name;

        public ViewHolder(View itemView){

            name= (TextView) itemView.findViewById(R.id.item_fragment_tea_checked_list2_name);

        }
    }
    public interface OnList2ItemClickListener{
        void getItemBean(TeaListsSearchModel.DataBean.CateChilde childeBean);
    }

}
