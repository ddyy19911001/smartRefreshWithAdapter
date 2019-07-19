package yin.deng.refreshlibrary.refresh.help;


import android.content.Context;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycleHelper {
    public static LinearLayoutManager initRecycle(RecyclerView rc, Context context,boolean needAnimate){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        if(needAnimate) {
            rc.setItemAnimator(new DefaultItemAnimator());
        }
        rc.setLayoutManager(linearLayoutManager);
        return linearLayoutManager;
    }
}
