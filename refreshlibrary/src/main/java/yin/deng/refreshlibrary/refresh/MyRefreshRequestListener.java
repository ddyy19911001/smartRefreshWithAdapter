package yin.deng.refreshlibrary.refresh;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import yin.deng.refreshlibrary.refresh.api.RefreshLayout;
import yin.deng.refreshlibrary.refresh.listener.OnLoadmoreListener;
import yin.deng.refreshlibrary.refresh.listener.OnRefreshListener;

public class MyRefreshRequestListener<T> implements OnRefreshListener,OnLoadmoreListener {
    List<T> datas;
    boolean isRefresh=true;
    OnRequestListener onRequest;
    long loadDelayTime;
    public MyRefreshRequestListener(List<T> datas, long loadDelayTime, OnRequestListener onRequest){
        if(datas==null){
            datas=new ArrayList<>();
        }
        this.datas=datas;
        this.onRequest=onRequest;
        this.loadDelayTime=loadDelayTime;
    }

    public interface OnRequestListener{
        void onRequest(boolean isRefresh);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        isRefresh=false;
        if(loadDelayTime>0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(onRequest!=null){
                        onRequest.onRequest(isRefresh);
                    }
                }
            },loadDelayTime);
        }else{
            if(onRequest!=null){
                onRequest.onRequest(isRefresh);
            }
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        isRefresh=true;
        if(loadDelayTime>0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(onRequest!=null){
                        onRequest.onRequest(isRefresh);
                    }
                }
            },loadDelayTime);
        }else{
            if(onRequest!=null){
                onRequest.onRequest(isRefresh);
            }
        }
    }

    public boolean isRefresh(){
        return isRefresh;
    }
}
