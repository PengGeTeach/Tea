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
import com.phone1000.chayu.modles.HuatiBean;
import com.phone1000.chayu.modles.QuanZiModel;
import com.phone1000.chayu.modles.TeaListsSearchModel;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class QuanZiFragmentListAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String TAG = QuanZiFragmentListAdapter.class.getSimpleName();
    private List<HuatiBean> data;
    private LayoutInflater inflater;
    private OnListItemClickListener listener;

    public void setListener(OnListItemClickListener listener) {
        this.listener = listener;
    }

    public QuanZiFragmentListAdapter(Context context, List<HuatiBean> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<HuatiBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addRes(List<HuatiBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public HuatiBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_fragment_quanzi_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HuatiBean item = getItem(position);

        if (item.getSource() != null && !"".equals(item.getSource())) {
            x.image().bind(holder.image, item.getThumb());
            holder.title.setText(item.getTitle());
            holder.content.setText(item.getContent());
            holder.replies.setText(" "+item.getSource().getReplies());
            holder.hits.setText(" "+item.getSource().getHits());
        } else {
            if (item.getAttach() != null && !"".equals(item.getAttach())) {
                holder.image.setVisibility(View.VISIBLE);
                x.image().bind(holder.image, item.getAttach());
            } else if (item.getAttach() == null || "".equals(item.getAttach())) {
                holder.image.setVisibility(View.GONE);
            }

            holder.title.setText(item.getSubject());
            holder.content.setText(item.getContent());
            holder.replies.setText(" "+item.getReplies());
            holder.hits.setText(" "+item.getHits());
        }


        return convertView;
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "onClick: v.gettag---------------------->" + v.getTag());
        List<TeaListsSearchModel.DataBean.CateChilde> list = (List<TeaListsSearchModel.DataBean.CateChilde>) v.getTag();
        Log.e(TAG, "onClick:  list------------------->" + list);
        listener.getItemPosition(list);

    }

    private class ViewHolder {

        ImageView image;
        TextView title, content, replies, hits;

        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.item_fragment_quanzi_list_image);
            title = (TextView) itemView.findViewById(R.id.item_fragment_quanzi_list_title);
            content = (TextView) itemView.findViewById(R.id.item_fragment_quanzi_list_content);
            replies = (TextView) itemView.findViewById(R.id.item_fragment_quanzi_list_pinlun);
            hits = (TextView) itemView.findViewById(R.id.item_fragment_quanzi_list_liulan);

        }
    }

    public interface OnListItemClickListener {
        void getItemPosition(List<TeaListsSearchModel.DataBean.CateChilde> list);
    }

}
