package com.phone1000.chayu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaListModel;
import com.phone1000.chayu.modles.TeaListsSearchModel;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class TeaList1CheckedAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String TAG = TeaList1CheckedAdapter.class.getSimpleName();
    private List<TeaListsSearchModel.DataBean.CategoryListBean> data;
    private LayoutInflater inflater;
    private OnListItemClickListener listener;

    public void setListener(OnListItemClickListener listener) {
        this.listener = listener;
    }

    public TeaList1CheckedAdapter(Context context, List<TeaListsSearchModel.DataBean.CategoryListBean> data){
        inflater=LayoutInflater.from(context);
        if (data != null) {
            this.data=data;
        }else {
            this.data=new ArrayList<>();
        }
    }

    public void updateRes(List<TeaListsSearchModel.DataBean.CategoryListBean> data){
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    public void addRes(List<TeaListsSearchModel.DataBean.CategoryListBean> data){
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
    public TeaListsSearchModel.DataBean.CategoryListBean getItem(int position) {
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
            convertView=inflater.inflate(R.layout.item_fragment_tea_checked_list1,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        TeaListsSearchModel.DataBean.CategoryListBean item = getItem(position);

//        convertView.setTag(item);
        Log.e(TAG, "getView: 发送"+item );
        holder.name.setText(item.getName());
        x.image().bind(holder.image,item.getIco());


        return convertView;
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: v.gettag---------------------->"+v.getTag() );
        List<TeaListsSearchModel.DataBean.CateChilde> list = (List<TeaListsSearchModel.DataBean.CateChilde>) v.getTag();
        Log.e(TAG, "onClick:  list------------------->"+list  );
        listener.getItemPosition(list);

    }

    private class ViewHolder{

        ImageView image;
        TextView name;

        public ViewHolder(View itemView){
            image= (ImageView) itemView.findViewById(R.id.item_fragment_tea_checked_list1_image);
            name= (TextView) itemView.findViewById(R.id.item_fragment_tea_checked_list1_name);

        }
    }
    public interface OnListItemClickListener{
        void getItemPosition(List<TeaListsSearchModel.DataBean.CateChilde> list);
    }

}
