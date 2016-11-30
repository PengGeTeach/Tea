package com.phone1000.chayu.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.chayu.R;
import com.phone1000.chayu.modles.TeaComm;
import com.phone1000.chayu.weidgt.MyGridView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class PinPaiFragmentAdapter extends BaseExpandableListAdapter{

    private static final String TAG = PinPaiFragmentAdapter.class.getSimpleName();
    private List<TeaComm.DataBean.CategoryListBean> data;

    private LayoutInflater inflater;

    private Context context;

    public PinPaiFragmentAdapter(Context context,List<TeaComm.DataBean.CategoryListBean> data){

        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return data!=null?data.size():0;
    }

    @Override
    public int getChildrenCount(int i) {
        List<TeaComm.DataBean.CategoryListBean.Children> childrens = (List<TeaComm.DataBean.CategoryListBean.Children>) data.get(i).getChildren();
        Log.e(TAG, "getChildrenCount: "+ data.get(i).getChildren().size());
//        return childrens!=null?data.get(i).getChildren().size():0;
        return childrens!=null?1:0;
    }

    @Override
    public TeaComm.DataBean.CategoryListBean getGroup(int i) {
        return data.get(i);
    }

    @Override
    public TeaComm.DataBean.CategoryListBean.Children getChild(int i, int i1) {
        Log.e(TAG, "getChild: "+ getGroup(i).getChildren().get(i1));
        return (TeaComm.DataBean.CategoryListBean.Children) getGroup(i).getChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        ViewHolderParent holder = null;
        if (holder == null) {
            view = inflater.inflate(R.layout.chapingparentitem,viewGroup,false);
            holder = new ViewHolderParent(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolderParent) view.getTag();
        }

        x.image().bind(holder.imageView1,data.get(i).getIco());
        holder.parenttext.setText(data.get(i).getName());

        if (i==0){
            holder.imageView2.setVisibility(View.INVISIBLE);
        }else{
            holder.imageView2.setVisibility(View.VISIBLE);
        }

        return view;
    }


    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        ViewHolderChild holder = null;

        if (view == null) {
            view = inflater.inflate(R.layout.chanpinchilditem,viewGroup,false);
            holder = new ViewHolderChild(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolderChild) view.getTag();
        }

        GridViewAdapter gridViewAdapter = new GridViewAdapter(context, data.get(i).getChildren(),i, R.layout.gridview_item);

        for (TeaComm.DataBean.CategoryListBean.Children childen:data.get(i).getChildren()) {
            Log.e(TAG, "getChildView: " +childen.getName());
            childen.getName();
        }

        //holder.myGridView.setOnItemClickListener(this);

       holder.myGridView.setAdapter(gridViewAdapter);

       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        holder.myGridView.setLayoutManager(gridLayoutManager);


        RecyGridAdapter recyGridAdapter = new RecyGridAdapter(context, data.get(i).getChildren());
        holder.myGridView.setAdapter(recyGridAdapter);*/

        return view;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

   /* @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Log.e(TAG, "onItemClick: 我点击了"+i );
        if (view instanceof TextView){
            Log.e(TAG, "onItemClick: "+"我是textview" );
        }

    }*/


    public static class ViewHolderParent{

        ImageView imageView1,imageView2;
        TextView parenttext;

        public ViewHolderParent(View itemView){
            imageView1 = ((ImageView) itemView.findViewById(R.id.parent_image));
            imageView2 = ((ImageView) itemView.findViewById(R.id.parent_down));
            parenttext = ((TextView) itemView.findViewById(R.id.parent_text));
        }
    }

    public static class ViewHolderChild{

        MyGridView myGridView;
        //RecyclerView myGridView;

        public ViewHolderChild(View itemView){

            myGridView = ((MyGridView) itemView.findViewById(R.id.recycler_gridview));
            //myGridView = ((RecyclerView) itemView.findViewById(R.id.recycler_gridview));

        }

    }


}
