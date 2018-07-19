package ywh.wh.test.recycler;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wh.ywh.base.BaseHelpActivity;
import wh.ywh.base.OnItemClickListener;
import wh.ywh.base.SwipeRecyclerView;
import wh.ywh.util.ToastUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-07-19.
 */

public class TestSwipeRecyclerViewActivity extends BaseHelpActivity {
    @Bind(R.id.swipeRecyclerView)
    SwipeRecyclerView swipeRecyclerView;
    private SwipeAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.ac_swipe_rv;
    }

    @Override
    protected void initData() {
        super.initData();
        List<String> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add(""+i);
        }
        adapter = new SwipeAdapter(this,list);

        swipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRecyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                ToastUtil.toastShort(TestSwipeRecyclerViewActivity.this,"内容");
            }
        });
        swipeRecyclerView.setOutSideLayoutId(R.id.ll_outside);
        swipeRecyclerView.getIsOpen();
    }
}
