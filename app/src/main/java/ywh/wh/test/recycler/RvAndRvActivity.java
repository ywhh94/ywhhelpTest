package ywh.wh.test.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import wh.ywh.base.BaseHelpActivity;
import wh.ywh.base.i.OnItemClickListener;
import wh.ywh.util.LogUtil;
import wh.ywh.util.TestUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-07.
 */
public class RvAndRvActivity extends BaseHelpActivity{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private RvRvAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.layout_rv;
    }

    @Override
    protected void initData() {
        super.initData();

        adapter = new RvRvAdapter(this, TestUtil.getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                LogUtil.e("外层item:"+position);
            }
        });
    }
}
