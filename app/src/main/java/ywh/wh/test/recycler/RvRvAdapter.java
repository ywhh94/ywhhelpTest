package ywh.wh.test.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import wh.ywh.View.NestRLGLinearLayout;
import wh.ywh.base.CommonAdapter;
import wh.ywh.base.i.OnItemClickListener;
import wh.ywh.util.LogUtil;
import wh.ywh.util.TestUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-09.
 */
public class RvRvAdapter extends CommonAdapter<String>{
    private Context mContent;
    public RvRvAdapter(Context context, List<String> data) {
        super(R.layout.item_rvrv,data);
        mContent = context;
    }

    @Override
    protected void bindData(CommonViewHolder holder, String s, final int wposition) {
        NestRLGLinearLayout nLinearLayout =  (NestRLGLinearLayout)holder.findView(R.id.nLinearLayout);
        RecyclerView subRv =  (RecyclerView)holder.findView(R.id.subRv);
        ListView listView = (ListView) holder.findView(R.id.listView);
        TextView tv =  (TextView) holder.findView(R.id.tv);
        nLinearLayout.setInterceptView(subRv,false);
        nLinearLayout.setInterceptView(listView,true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("点击tv");
            }
        });
        SubRv adapter = new SubRv(mContent, TestUtil.getData(5));
        subRv.setLayoutManager(new LinearLayoutManager(mContent));
        subRv.setAdapter(adapter);
        adapter.setOnItemClicked(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                LogUtil.e("外层："+wposition+",内层item:"+position);
            }
        });

        listView.setAdapter(new MyListAdapter(mContent,TestUtil.getData(4)));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.e("listView -- :"+position);
            }
        });
    }
}
