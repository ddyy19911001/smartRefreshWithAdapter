package yin.deng.refreshlibrary.refresh;

import java.util.List;

public class MyRefreshResponseListener{
    public interface OnLoadMoreOverListener{
        void onLoadMoreOver();
        void onRefreshFail();
    }

    public static <T>List<T> dealWithResponseRefresh(List<T> getDatas,List<T> realDatas,SmartRefreshLayout smRf,boolean isRefresh,OnLoadMoreOverListener onLoadMoreOverListener){
        if(getDatas==null||getDatas.size()==0){
            if(isRefresh){
                smRf.finishRefresh();
                if(onLoadMoreOverListener!=null){
                    onLoadMoreOverListener.onRefreshFail();
                }
            }else{
                smRf.finishLoadmore();
                smRf.setLoadmoreFinished(true);
                if(onLoadMoreOverListener!=null){
                    onLoadMoreOverListener.onLoadMoreOver();
                }
            }
        }else{
            if(isRefresh){
                realDatas.clear();
                smRf.finishRefresh();
            }else{
                smRf.finishLoadmore();
            }
            realDatas.addAll(getDatas);
        }
        return realDatas;
    }
}
