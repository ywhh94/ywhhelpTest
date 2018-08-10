package ywh.wh.test.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import butterknife.Bind;
import wh.ywh.View.NestRLVScrollView;
import wh.ywh.base.BaseHelpActivity;
import wh.ywh.base.i.OnItemClickListener;
import wh.ywh.util.TestUtil;
import wh.ywh.util.ToastUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-07.
 */
public class ScrollViewAndRvActivity extends BaseHelpActivity{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.gridView)
    GridView gridView;
    @Bind(R.id.nestRLVScrollView)
    NestRLVScrollView nestRLVScrollView;
    private OneAdapter adapter;
    private MyListAdapter listadapter;
    private MyListAdapter gridadapter;

    @Override
    protected int setLayout() {
        return R.layout.ac_scroll_rv;
    }

    @Override
    protected void initData() {
        super.initData();
        gridadapter = new MyListAdapter(this,TestUtil.getData());
        gridView.setAdapter(gridadapter);
        nestRLVScrollView.setCanRlvScroll(gridView,true);
        listadapter = new MyListAdapter(this,TestUtil.getData());
        listView.setAdapter(listadapter);
        nestRLVScrollView.setCanRlvScroll(listView,true);

        adapter = new OneAdapter(this, TestUtil.getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        nestRLVScrollView.setCanRlvScroll(recyclerView,true);
        adapter.setOnItemClicked(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                ToastUtil.toastShort(ScrollViewAndRvActivity.this,"position:"+position);
            }
        });
    }
}
