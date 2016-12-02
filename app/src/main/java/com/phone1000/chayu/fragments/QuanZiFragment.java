package com.phone1000.chayu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.phone1000.chayu.DetailsInFormation;
import com.phone1000.chayu.R;
import com.phone1000.chayu.adapters.QuanZiFragmentListAdapter;
import com.phone1000.chayu.modles.HuatiBean;
import com.phone1000.chayu.modles.QuanZiModel;
import com.phone1000.chayu.modles.QuanziListDatBean;
import com.phone1000.chayu.modles.QuanziListUpdatModel;
import com.phone1000.chayu.path.UtilPath;

import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/27 0027.
 */
public class QuanZiFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener, View.OnClickListener {
    public static final String TAG = QuanZiFragment.class.getSimpleName();
    private View layout;
    private PullToRefreshListView mPullToRefresh;
    private ListView mListView;
    private QuanZiFragmentListAdapter adapter;
    private View header;
    private ImageView mXueChaImage;
    private TextView mXueChaTitle;
    private TextView mXueChaGuanZhu;
    private TextView mXueChaHuaTi;
    private ImageView mPingChaImage;
    private ImageView mShaiChaImage;
    private TextView mPingChaTitle;
    private TextView mPingChaGuanZhu;
    private TextView mPingChaHuaTi;
    private TextView mShaiChaTitle;
    private TextView mShaiChaGuanzhu;
    private TextView mShaiChaHuaTi;
    private ImageView mTopImage;

    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 0x220:
                    mPullToRefresh.onRefreshComplete();
                    break;
            }

        }
    };
    private int p=1;
    private TextView mQuanzi;
    private TextView mHuati;
    private TextView mQuandao;
    private LinearLayout mXuchaquan;
    private LinearLayout mPingCha;
    private LinearLayout mShaiCha;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_quan_zi, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        mPullToRefresh = (PullToRefreshListView) layout.findViewById(R.id.fragment_quanzi_listView);
        mPullToRefresh.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefresh.setOnRefreshListener(this);
        mListView = mPullToRefresh.getRefreshableView();

        adapter = new QuanZiFragmentListAdapter(getContext(),null);

        header = LayoutInflater.from(getContext()).inflate(R.layout.header_fragment_quanzi,null);
        mListView.addHeaderView(header);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

        mTopImage = ((ImageView) header.findViewById(R.id.fragment_quanzi_topimage));
        mTopImage.setOnClickListener(this);
        mXueChaImage = (ImageView) header.findViewById(R.id.fragment_quanzi_xuechaquan_image);
        mXueChaTitle = (TextView) header.findViewById(R.id.fragment_quanzi_xuechaquan_title);
        mXueChaGuanZhu = (TextView) header.findViewById(R.id.fragment_quanzi_xuechaquan_guanzhu);
        mXueChaHuaTi = (TextView) header.findViewById(R.id.fragment_quanzi_xuechaquan_huati);
        mPingChaImage = (ImageView) header.findViewById(R.id.fragment_quanzi_chayupingcha_image);
        mShaiChaImage = (ImageView) header.findViewById(R.id.fragment_quanzi_shaicha_image);
        mPingChaTitle = (TextView) header.findViewById(R.id.fragment_quanzi_chayupingcha_title);
        mPingChaGuanZhu = (TextView) header.findViewById(R.id.fragment_quanzi_chayupingcha_guanzhu);
        mPingChaHuaTi = (TextView) header.findViewById(R.id.fragment_quanzi_chayupingcha_huati);
        mShaiChaTitle = ((TextView) header.findViewById(R.id.fragment_quanzi_shaicha_title));
        mShaiChaGuanzhu = ((TextView) header.findViewById(R.id.fragment_quanzi_shaicha_guanzhu));
        mShaiChaHuaTi = ((TextView) header.findViewById(R.id.fragment_quanzi_shaicha_huati));


        setUpDataFromNet();
        mQuanzi = (TextView) header.findViewById(R.id.fragment_quanzi_btn_quanzi);
        mQuanzi.setOnClickListener(this);
        mHuati = (TextView) header.findViewById(R.id.fragment_quanzi_btn_huati);
        mHuati.setOnClickListener(this);
        mQuandao = ((TextView) header.findViewById(R.id.fragment_quanzi_btn_qiandao));
        mQuandao.setOnClickListener(this);

        mXuchaquan = (LinearLayout) layout.findViewById(R.id.fragment_quanzi_xuechaquan);
        mXuchaquan.setOnClickListener(this);
        mPingCha = (LinearLayout) layout.findViewById(R.id.fragment_quanzi_chayupingcha);
        mPingCha.setOnClickListener(this);
        mShaiCha = (LinearLayout) layout.findViewById(R.id.fragment_quanzi_shaicha);
        mShaiCha.setOnClickListener(this);


    }

    private void setUpDataFromNet() {
        RequestParams params = new RequestParams(UtilPath.QUANZIPATH);
        params.addParameter("imei","91f64b1a7dbe8b9e");
        params.addParameter("agent","5");
        params.addParameter("version","5");
        params.addParameter("source","3");
        params.addParameter("versionCode","2.2.4");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " );
                Gson gson = new Gson();
                QuanZiModel quanZiModel = gson.fromJson(result, QuanZiModel.class);
                QuanZiModel.DataBean data = quanZiModel.getData();

                x.image().bind(mTopImage,data.getBanner());

                List<HuatiBean> huati = data.getHuati();
                adapter.updateRes(huati);

                List<QuanZiModel.DataBean.QuanziBean> quanzi = data.getQuanzi();
                initQuanZi(quanzi);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getMessage() );
                Log.e(TAG, "onError: "+ex.getCause() );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );

            }

            @Override
            public void onFinished() {

                Log.e(TAG, "onFinished: " );
            }
        });



    }

    private void initQuanZi(List<QuanZiModel.DataBean.QuanziBean> quanzi) {


        QuanZiModel.DataBean.QuanziBean quanziBean1 = quanzi.get(0);
        QuanZiModel.DataBean.QuanziBean quanziBean2 = quanzi.get(1);
        QuanZiModel.DataBean.QuanziBean quanziBean3 = quanzi.get(2);

        mXueChaTitle.setText(quanziBean1.getTitle());
        mPingChaTitle.setText(quanziBean2.getTitle());
        mShaiChaTitle.setText(quanziBean3.getTitle());

        mXueChaGuanZhu.setText("关注: "+quanziBean1.getSource().getAttention_num());
        mPingChaGuanZhu.setText("关注: "+quanziBean2.getSource().getAttention_num());
        mShaiChaGuanzhu.setText("关注: "+quanziBean3.getSource().getAttention_num());

        mXueChaHuaTi.setText("晒茶: "+quanziBean1.getSource().getTopics());
        mPingChaHuaTi.setText("晒茶: "+quanziBean2.getSource().getTopics());
        mShaiChaHuaTi.setText("晒茶: "+quanziBean3.getSource().getTopics());

        x.image().bind(mXueChaImage,quanziBean1.getThumb());
        x.image().bind(mPingChaImage,quanziBean2.getThumb());
        x.image().bind(mShaiChaImage,quanziBean3.getThumb());



    }

    //----------------------pulltoRefresh监听
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {

        setUpDataFromNet();
        mHandler.sendEmptyMessageDelayed(0x220,1000);
        p=1;

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        //上拉加载
        setupUpRefreshFromNet();
        mHandler.sendEmptyMessageDelayed(0x220,1000);
        p++;


    }

    //------------上拉加载----------
    private void setupUpRefreshFromNet() {


        RequestParams params = new RequestParams(UtilPath.QUANZISHANGLAPATH);
        params.addParameter("imei","91f64b1a7dbe8b9e");
        params.addParameter("agent","5");
        params.addParameter("version","5");
        params.addParameter("source","3");
        params.addParameter("versionCode","2.2.4");
        params.addParameter("pageSize","10");
        params.addParameter("pageNo",p);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " );
                Gson gson = new Gson();
                QuanziListUpdatModel quanziListUpdatModel = gson.fromJson(result, QuanziListUpdatModel.class);
                QuanziListDatBean data = quanziListUpdatModel.getData();

                List<HuatiBean> huati = data.getList();
                adapter.addRes(huati);

                mPullToRefresh.onRefreshComplete();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getMessage() );
                Log.e(TAG, "onError: "+ex.getCause() );
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: " );

            }

            @Override
            public void onFinished() {

                Log.e(TAG, "onFinished: " );
            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HuatiBean item = adapter.getItem(position-2);
        String url=null;
        if (item.getResource_id() != null&&!"".equals(item.getResource_id())) {
           url = UtilPath.QUANZI_XIANGQING+item.getResource_id();

        }else if( item.getTid()!= null&&!"".equals(item.getTemplate_id())){

            url=UtilPath.QUANZI_XIANGQING+item.getTid();

            }

        Intent intent = new Intent(getContext(), DetailsInFormation.class);
        intent.putExtra("path",url);
        startActivity(intent);

        }


    @Override
    public void onClick(View v) {

    }
}
