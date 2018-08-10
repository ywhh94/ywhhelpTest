package ywh.wh.test.recycler;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wh.ywh.base.BaseHelpActivity;
import wh.ywh.base.SideSlipRecyclerView2;
import wh.ywh.base.i.OnItemClickListener;
import wh.ywh.base.i.OnItemSlideListener;
import wh.ywh.util.LogUtil;
import wh.ywh.util.ToastUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-07-19.
 */

public class TestSwipeRecyclerViewActivity extends BaseHelpActivity implements OnItemSlideListener {
    @Bind(R.id.swipeRecyclerView)
    SideSlipRecyclerView2 sideSlipRecyclerView;
    private SwipeAdapter adapter;
    private TextView item1;
    private TextView item2;

    @Override
    protected int setLayout() {
        return R.layout.ac_swipe_rv;
    }

    @Override
    protected void initData() {
        super.initData();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        adapter = new SwipeAdapter(this, list);

        sideSlipRecyclerView.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return !sideSlipRecyclerView.getIsScrollingH();
            }
        });
        sideSlipRecyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                ToastUtil.toastShort(TestSwipeRecyclerViewActivity.this, "内容");
            }
        });
        sideSlipRecyclerView.setOnItemSlide(R.id.ll_outside, this);
    }

    @Override
    public void onSlide(final int position, boolean isOpened, View outSideView) {
        LogUtil.e("isOpened:"+ isOpened);
        if (outSideView != null) {
            item1 = (TextView) outSideView.findViewById(R.id.item1);
            item2 = (TextView) outSideView.findViewById(R.id.item2);
            item1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.toastShort(TestSwipeRecyclerViewActivity.this,"点击了item1:"+position);
                }
            });
            item2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.toastShort(TestSwipeRecyclerViewActivity.this,"点击了item2:"+position);
                }
            });
        }
    }


}
