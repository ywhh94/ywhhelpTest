package ywh.wh.test.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import wh.ywh.base.OnItemClickListener;
import wh.ywh.base.OnItemLongClickListener;
import ywh.wh.test.R;

public class RecyclerStaggeredGridActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private OneAdapter adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        for(int i = 0;i< 20;i++){
            if(i == 1){
                list.add("啥啥啥啥啥啥所所所所所所所所所所所所所所所所所所所所所所所所所所所" +
                        "所所所所所所所所所啥啥啥啥啥啥所所所所所所所所所所所所所所所所所所");
            }else{
                list.add(""+i);
            }
        }
        adapter = new OneAdapter(this,list);

        StaggeredGridLayoutManager staggeredGridLayout  = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayout);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(this);
        adapter.setOnItemLongClicked(this);
        View headView = LayoutInflater.from(this).inflate(R.layout.item_head,null);
        View footView = LayoutInflater.from(this).inflate(R.layout.item_foot,null);
        adapter.setHeadView(headView);
        adapter.setFootView(footView);
        TextView tv1 = (TextView) headView.findViewById(R.id.item_tv1); // 跳到最后
        TextView tv2 = (TextView) headView.findViewById(R.id.item_tv2); // 反转
        TextView tv3 = (TextView) headView.findViewById(R.id.item_tv3); // 跳到指定的item位置
        TextView tv4 = (TextView) headView.findViewById(R.id.item_tv4); // 滑到指定的item位置

        TextView tv5 = (TextView) headView.findViewById(R.id.item_tv5);
        TextView tv6 = (TextView) headView.findViewById(R.id.item_tv6);
        final TextView tv7 = (TextView) headView.findViewById(R.id.item_tv7);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(int position, View view) {

    }

    @Override
    public boolean onItemLongClick(int position, View view) {
        return false;
    }
}
