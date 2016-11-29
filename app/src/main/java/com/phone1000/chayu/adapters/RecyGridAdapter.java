package com.phone1000.chayu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaComm;

import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class RecyGridAdapter extends RecyclerView.Adapter<RecyGridAdapter.ViewHolder>{


    private List<TeaComm.DataBean.CategoryListBean.Children> data;
    private LayoutInflater inflater;
    private RecyclerView mRecycler;

    public RecyGridAdapter(Context context,List<TeaComm.DataBean.CategoryListBean.Children> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.gridview_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.grid_text));
        }
    }

}
