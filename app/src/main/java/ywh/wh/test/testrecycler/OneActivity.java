package ywh.wh.test.testrecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wh.ywh.base.OnItemClickListener;
import wh.ywh.base.OnItemLongClickListener;
import ywh.wh.test.R;

public class OneActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener {

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
            list.add(""+i);
        }
        adapter = new OneAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(this);
        adapter.setOnItemLongClicked(this);
        View headView = LayoutInflater.from(this).inflate(R.layout.item_head,null);
        View footView = LayoutInflater.from(this).inflate(R.layout.item_foot,null);
        adapter.setHeadView(headView);
        adapter.setFootView(footView);
//        adapter.setData();
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
