package ywh.wh.test.recycler;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wh.ywh.base.BaseHelpActivity;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-07-19.
 */

public class RecyclerAnimatorActivity extends BaseHelpActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;
    private OneAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.ac_recycler_animator;
    }

    @Override
    protected void initData() {
        super.initData();
        final List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(""+i);
        }
        adapter = new OneAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(5000);
        animator.setRemoveDuration(2000);
        animator.setMoveDuration(3000);
//        recyclerView.setItemAnimator(animator);
        FlyAnimator flyAnimator = new FlyAnimator();
        recyclerView.setItemAnimator(flyAnimator);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add("添加的");
                adapter.notifyItemInserted(1);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(0);
                adapter.notifyItemRemoved(0);

            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyItemMoved(1,2);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(0,"test0");
                list.add(0,"test1");
                list.add(0,"test2");
                list.add(0,"test3");
                adapter.notifyItemRangeInserted(1,4);
            }
        });
//        adapter.notifyItemRangeInserted();
//        adapter.notifyItemChanged(0);
//        adapter.notifyItemMoved(0,1);
    }
}
