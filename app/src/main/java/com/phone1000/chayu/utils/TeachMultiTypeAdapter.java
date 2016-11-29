package com.phone1000.chayu.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
public abstract class TeachMultiTypeAdapter<T> extends BaseAdapter{

    private static final String TAG = TeachMultiTypeAdapter.class.getCanonicalName();
    private List<T> data;
    private LayoutInflater inflater;

    private int [] layoutIds;

    public TeachMultiTypeAdapter(Context context,List<T> data,int ... layoutIds){
        inflater = LayoutInflater.from(context);
        this.layoutIds = layoutIds;
        if (data==null) {
            this.data = new ArrayList<>();
        }else{
            this.data = data;
        }
    }

    public void updateRes(List<T> data){
        if (data!=null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addRes(List<T> data){
        if (data!=null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }



    @Override
    public int getCount() {
        return data!=null?data.size():0;
    }

    @Override
    public T getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return layoutIds.length;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        //根据id获取对应的对象
        T item = getItem(position);
        //获取对象的Class
        Class<?> itemClass = item.getClass();
        try {
            //获取class的type字段
            Field field = itemClass.getDeclaredField("type");
            //添加访问权限,私有字段加权限
            field.setAccessible(true);
            //获取字段中的值
            type = field.getInt(item);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view==null) {
            //写布局时候，布局ID在传入时要和type对应，type0对应索引0，type对应索引1
            view = inflater.inflate(layoutIds[getItemViewType(i)],viewGroup,false);
            Log.e(TAG, "getView: "+getItemViewType(i) );
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        //加载数据
        bindData(holder,getItem(i),i);

        return view;
    }

    public abstract void bindData(ViewHolder holder, T item, int i);


    protected static class ViewHolder{

        private View iteamView;
        private Map<Integer,View> cahceViews;

        public ViewHolder(View iteamView) {
            this.iteamView = iteamView;
            cahceViews = new HashMap<>();
        }

        public View getView(int resId){
            View view = null;

            if (cahceViews.containsKey(resId)) {
                view = cahceViews.get(resId);
            }else {
                view = iteamView.findViewById(resId);
                cahceViews.put(resId,view);
            }
            return view;
        }

    }



}
