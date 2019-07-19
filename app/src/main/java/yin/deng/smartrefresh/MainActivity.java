package yin.deng.smartrefresh;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import yin.deng.refreshlibrary.refresh.MyRefreshRequestListener;
import yin.deng.refreshlibrary.refresh.MyRefreshResponseListener;
import yin.deng.refreshlibrary.refresh.SmartRefreshLayout;
import yin.deng.refreshlibrary.refresh.api.DefaultRefreshFooterCreater;
import yin.deng.refreshlibrary.refresh.api.DefaultRefreshHeaderCreater;
import yin.deng.refreshlibrary.refresh.api.RefreshFooter;
import yin.deng.refreshlibrary.refresh.api.RefreshHeader;
import yin.deng.refreshlibrary.refresh.api.RefreshLayout;
import yin.deng.refreshlibrary.refresh.help.MyQuckAdapter;
import yin.deng.refreshlibrary.refresh.help.MyRecycleHelper;
import yin.deng.refreshlibrary.refresh.viewimpl.MyFooterView;
import yin.deng.refreshlibrary.refresh.viewimpl.MyHeaderView;

public class MainActivity extends AppCompatActivity {
    private SmartRefreshLayout smRf;
    private RecyclerView rcView;
    private MyQuckAdapter<String> adapter;
    private List<String> datas=new ArrayList<>();


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(yin.deng.refreshlibrary.R.color.normal_bg, yin.deng.refreshlibrary.R.color.normal_4a);//全局设置主题颜色
                MyHeaderView head = new MyHeaderView(context);
                return head;//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setPrimaryColorsId(yin.deng.refreshlibrary.R.color.normal_bg, yin.deng.refreshlibrary.R.color.normal_4a);//全局设置主题颜色
                MyFooterView footer = new MyFooterView(context);
                return footer;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<20;i++) {
            datas.add("数据1");
            datas.add("数据2");
        }
        smRf = (SmartRefreshLayout) findViewById(R.id.smRf);
        rcView = (RecyclerView) findViewById(R.id.rcView);
        MyRefreshRequestListener<String> refreshListener = new MyRefreshRequestListener<String>(datas, 300, new MyRefreshRequestListener.OnRequestListener() {
            @Override
            public void onRequest(boolean isRefresh) {
                List<String> getDatas=new ArrayList<>();
                if(isRefresh){
                    getDatas.add("刷新");
                }else{
                    if(datas.size()<=60) {
                        getDatas.add("加载更多");
                        getDatas.add("加载更多");
                        getDatas.add("加载更多");
                        getDatas.add("加载更多");
                        getDatas.add("加载更多");
                        getDatas.add("加载更多");
                    }
                }
                MyRefreshResponseListener.dealWithResponseRefresh(getDatas, datas, smRf, isRefresh, new MyRefreshResponseListener.OnLoadMoreOverListener() {
                    @Override
                    public void onLoadMoreOver() {

                    }

                    @Override
                    public void onRefreshFail() {

                    }
                });
                setAdapter();
            }
        });
        smRf.setOnLoadmoreListener(refreshListener);
        smRf.setOnRefreshListener(refreshListener);
        setAdapter();
    }

    private void setAdapter() {
        if(adapter==null){
            adapter=new MyQuckAdapter<String>(R.layout.item_text,datas,this) {
                @Override
                protected void convert(BaseViewHolder helper, String item) {
                    helper.setText(R.id.tv_test,item);
                }
            };
            MyRecycleHelper.initRecycle(rcView,this,true);
            rcView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }
}
