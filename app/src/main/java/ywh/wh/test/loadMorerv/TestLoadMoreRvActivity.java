package ywh.wh.test.loadMorerv;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import wh.ywh.base.BaseHelpActivity;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-23.
 */
public class TestLoadMoreRvActivity extends BaseHelpActivity{
    @Bind(R.id.rv)
    RecyclerView rv;
    private ArrayList<DemoBean> list;
    private AutoLoadMoreAdapter mAutoLoadMoreAdapter;
    private int pageIndex;

    @Override
    protected int setLayout() {
        return R.layout.ac_loadmore_rv;
    }

    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<>();
        rv.setLayoutManager(new GridLayoutManager(this,2));
        MyAdapter myAdapter = new MyAdapter(this, list);

        mAutoLoadMoreAdapter = new AutoLoadMoreAdapter(this,myAdapter);

        mAutoLoadMoreAdapter.setOnLoadListener(new AutoLoadMoreAdapter.OnLoadListener() {
            @Override
            public void onRetry() {
                Log.i("onRetry", "onRetry ");
                mockLoadmore();
            }

            @Override
            public void onLoadMore() {
                Log.i("onLoadMore", "onLoadMore " + pageIndex);
                mockLoadmore();
            }
        });
        rv.setAdapter(mAutoLoadMoreAdapter);
    }


    private void mockLoadmore() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Random r = new Random();
                int n = r.nextInt(5);
                //模拟出错
                boolean isError = false;
                if (n == 2) {
                    isError = true;
                }
                if (isError) {
                    mAutoLoadMoreAdapter.showLoadError();
                } else if (pageIndex >= 10) {
                    mAutoLoadMoreAdapter.showLoadComplete();
                } else {
                    for (int i = pageIndex * 10; i < pageIndex * 10 + 10; i++) {
                        DemoBean demoBean = new DemoBean();
                        demoBean.setTitle("Title " + i);
                        demoBean.setDesc("Desc " + i);
                        list.add(demoBean);
                    }
                    pageIndex++;
                    mAutoLoadMoreAdapter.finishLoading();
                }
                rv.getAdapter().notifyDataSetChanged();
            }
        }, 1000);
    }
}
