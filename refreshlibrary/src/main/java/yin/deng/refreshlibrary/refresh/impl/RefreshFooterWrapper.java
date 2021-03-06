package yin.deng.refreshlibrary.refresh.impl;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import yin.deng.refreshlibrary.refresh.SmartRefreshLayout;
import yin.deng.refreshlibrary.refresh.api.RefreshFooter;
import yin.deng.refreshlibrary.refresh.api.RefreshKernel;
import yin.deng.refreshlibrary.refresh.api.RefreshLayout;
import yin.deng.refreshlibrary.refresh.constant.RefreshState;
import yin.deng.refreshlibrary.refresh.constant.SpinnerStyle;


/**
 * 刷新底部包装
 * Created by SCWANG on 2017/5/26.
 */

public class RefreshFooterWrapper implements RefreshFooter {

    private static final String TAG_REFRESH_FOOTER_WRAPPER = "TAG_REFRESH_FOOTER_WRAPPER";

    private View mWrapperView;
    private SpinnerStyle mSpinnerStyle;

    public RefreshFooterWrapper(View wrapper) {
        this.mWrapperView = wrapper;
        this.mWrapperView.setTag(TAG_REFRESH_FOOTER_WRAPPER.hashCode(), TAG_REFRESH_FOOTER_WRAPPER);
    }

    public static boolean isTagedFooter(View view) {
        return TAG_REFRESH_FOOTER_WRAPPER.equals(view.getTag(TAG_REFRESH_FOOTER_WRAPPER.hashCode()));
    }

    @NonNull
    public View getView() {
        return mWrapperView;
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
		return 0;
	}

    @Override@Deprecated
    public void setPrimaryColors(@ColorInt int ... colors) {

    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        if (mSpinnerStyle != null) {
            return mSpinnerStyle;
        }
        ViewGroup.LayoutParams params = mWrapperView.getLayoutParams();
        if (params instanceof SmartRefreshLayout.LayoutParams) {
            mSpinnerStyle = ((SmartRefreshLayout.LayoutParams) params).spinnerStyle;
            if (mSpinnerStyle != null) {
                return mSpinnerStyle;
            }
        }
        if (params != null) {
            if (params.height == 0) {
                return mSpinnerStyle = SpinnerStyle.Scale;
            }
        }
        return mSpinnerStyle = SpinnerStyle.Translate;
    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
        ViewGroup.LayoutParams params = mWrapperView.getLayoutParams();
        if (params instanceof SmartRefreshLayout.LayoutParams) {
            kernel.requestDrawBackgoundForFooter(((SmartRefreshLayout.LayoutParams) params).backgroundColor);
        }
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public void onPullingUp(float percent, int offset, int footerHeight, int extendHeight) {

    }

    @Override
    public void onPullReleasing(float percent, int offset, int footerHeight, int extendHeight) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int footerHeight, int extendHeight) {

    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }

    @Override
    public boolean setLoadmoreFinished(boolean finished) {
        return false;
    }
}
