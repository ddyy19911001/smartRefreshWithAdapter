package yin.deng.refreshlibrary.refresh;

import android.app.Application;
import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;

import yin.deng.refreshlibrary.R;
import yin.deng.refreshlibrary.refresh.api.DefaultRefreshFooterCreater;
import yin.deng.refreshlibrary.refresh.api.DefaultRefreshHeaderCreater;
import yin.deng.refreshlibrary.refresh.api.RefreshFooter;
import yin.deng.refreshlibrary.refresh.api.RefreshHeader;
import yin.deng.refreshlibrary.refresh.api.RefreshLayout;
import yin.deng.refreshlibrary.refresh.help.MyQuckAdapter;
import yin.deng.refreshlibrary.refresh.viewimpl.MyFooterView;
import yin.deng.refreshlibrary.refresh.viewimpl.MyHeaderView;

public class DemoAc extends Application {

    /**
     * 可以指定全局header和footer
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.normal_bg, R.color.normal_4a);//全局设置主题颜色
                MyHeaderView head = new MyHeaderView(context);
                return head;//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setPrimaryColorsId(R.color.normal_bg, R.color.normal_4a);//全局设置主题颜色
                MyFooterView footer = new MyFooterView(context);
                return footer;
            }
        });
    }

    /**
     * 重写的时候重写这几个方法
     * refresh里面的
     *  @Override
     *     public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
     *         gifView.play();//开始动画
     *     }
     *
     *     @Override
     *     public int onFinish(RefreshLayout layout, boolean success) {
     *         gifView.pause();//停止动画
     *         if (success){
     *             Log.d(L,"刷新完成");
     *         } else {
     *             Log.d(L,"刷新完成");
     *         }
     *         return 300;//延迟300毫秒之后再弹回
     *     }
     *
     *     @Override
     *     public boolean isSupportHorizontalDrag() {
     *         return false;
     *     }
     *
     *     @Override
     *     public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
     *         switch (newState) {
     *             case None:
     *             case PullDownToRefresh:
     *                 Log.i(L,"下拉开始刷新");
     *                 break;
     *             case Refreshing:
     *                 Log.i(L,"正在刷新");
     *                 break;
     *             case ReleaseToRefresh:
     *                 Log.i(L,"释放立即刷新");
     *                 break;
     *         }
     *     }
     *
     *
     *     footer里面的
     *      @Override
     *     public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
     *         if(isFinished){
     *                 gifView.setVisibility(GONE);
     *         }else {
     *                 gifView.setVisibility(VISIBLE);
     *                 gifView.play();//开始动画
     *         }
     *     }
     *
     *     @Override
     *     public int onFinish(RefreshLayout layout, boolean success) {
     *         if(isFinished){
     *                 gifView.setVisibility(GONE);
     *         }else {
     *                 gifView.setVisibility(VISIBLE);
     *                 gifView.pause();//开始动画
     *         }
     *         if (success){
     *             Log.d(L,"刷新完成");
     *         } else {
     *             Log.d(L,"刷新完成");
     *         }
     *         return 500;//延迟500毫秒之后再弹回
     *     }
     *
     *     @Override
     *     public boolean isSupportHorizontalDrag() {
     *         return false;
     *     }
     *
     *     @Override
     *     public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
     *             switch (newState) {
     *                 case None:
     *                 case PullDownToRefresh:
     *                     Log.i(L, "下拉开始刷新");
     *                     break;
     *                 case Refreshing:
     *                     Log.i(L, "正在刷新");
     *                     if (footerText != null && footerText.getVisibility() == VISIBLE) {
     *                         footerText.setVisibility(GONE);
     *                     }
     *                     break;
     *                 case ReleaseToRefresh:
     *                     Log.i(L, "释放立即刷新");
     *                     break;
     *             }
     *     }
     */


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
