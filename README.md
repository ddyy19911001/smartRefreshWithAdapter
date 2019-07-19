# smartRefreshWithAdapter
Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.ddyy19911001:smartRefreshWithAdapter:Tag'
	}

Step 3. Add the these in gradle.properties
 
    android.useAndroidX=true
    android.enableJetifier=true

# ForExample How To Use
    
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(yin.deng.refreshlibrary.R.color.normal_bg, yin.deng.refreshlibrary.R.color.normal_4a);
                //全局设置主题颜色
                MyHeaderView head = new MyHeaderView(context);
                return head;//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setPrimaryColorsId(yin.deng.refreshlibrary.R.color.normal_bg, yin.deng.refreshlibrary.R.color.normal_4a);
                //全局设置主题颜色
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
        smRf.setEnableAutoLoadmore(true);
        MyRefreshRequestListener<String> refreshListener = new MyRefreshRequestListener<String>(datas, 0, new MyRefreshRequestListener.OnRequestListener() {
            @Override
            public void onRequest(boolean isRefresh) {
                List<String> getDatas=new ArrayList<>();
                if(isRefresh){
                    for(int i=0;i<20;i++) {
                        getDatas.add("数据1");
                        getDatas.add("数据2");
                    }
                }else{
                    if(datas.size()<=40) {
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
