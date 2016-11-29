package com.phone1000.chayu.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/1 0001.
 */
public abstract class TeachBaseAdapter<T> extends BaseAdapter{

    private static final String TAG = TeachBaseAdapter.class.getSimpleName();
    private List<T> data;

    private LayoutInflater inflater;

    private int layoutResId;


    public TeachBaseAdapter(Context context,List<T> data,int layoutResId){
        inflater = LayoutInflater.from(context);
        this.layoutResId = layoutResId;
        if (data != null) {
            this.data = data;
        }else{
            this.data = new ArrayList<>();
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
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        Log.e(TAG, "getCount:+"+data.size() );
        return data!=null?data.size():0;
    }

    @Override
    public T getItem(int i) {
        Log.e(TAG, "getItem: "+i );
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view==null) {
            view = inflater.inflate(layoutResId,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Log.e(TAG, "getView: "+i );

        //数据加载
        bindData(holder,getItem(i),i);

        return view;
    }

    protected abstract void bindData(ViewHolder holder, T item, int i);

    public static class ViewHolder{

        View iteamView;

        //做一个Map缓存，专门做一个已经实例化过的View
        Map<Integer,View> cacheView;
        public ViewHolder(View iteamView){
            this.iteamView = iteamView;
            cacheView = new HashMap<>();
        }
        /**
         *
         * 获取iteamView中的childView
         * @param resId
         * @return
         */
        public View getView(int resId){
            //判断Map是否包含我们要实例化的VIEW
            //减少findViewById使用的时间，以前是在构造方法里面实例化里面的控件，只执行一次绑定到View上面，
            //现在不优化每次都需要通过findViewById寻找获得，优化后存到Map里面，有的话直接从里面取出来即可
            View view = null;

            //
            if (cacheView.containsKey(resId)) {
                //可以直接返回
                view = cacheView.get(resId);
            }else{
                //实例化一个，添加到缓存中
                view = iteamView.findViewById(resId);
                cacheView.put(resId,view);
            }
            return view;
        }

        public void setText(int resId,String context){
            TextView textView = (TextView) getView(resId);
            textView.setText(context);
        }


    }


}
