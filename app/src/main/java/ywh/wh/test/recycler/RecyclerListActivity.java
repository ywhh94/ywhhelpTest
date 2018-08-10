package ywh.wh.test.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wh.ywh.base.i.OnItemClickListener;
import wh.ywh.base.i.OnItemLongClickListener;
import ywh.wh.test.R;

public class RecyclerListActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private OneAdapter adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        ButterKnife.bind(this);

        String type = getIntent().getStringExtra("type");
        String an = getIntent().getStringExtra("an");
        list = new ArrayList<>();
        for(int i = 0;i< 20;i++){
            list.add(""+i);
        }
        adapter = new OneAdapter(this,list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollHorizontally() {
                return super.canScrollHorizontally();
            }

            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically();
            }

        };

        GridLayoutManager gridLayoutManaer = new GridLayoutManager(this,2);
        if("g".equals(type)){
            recyclerView.setLayoutManager(gridLayoutManaer);
        }else{
            recyclerView.setLayoutManager(linearLayoutManager);
        }

        //默认动画
        if("1".equals(an)){
            DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
            DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
            defaultItemAnimator.setAddDuration(1500);
            defaultItemAnimator.setRemoveDuration(1000);
            defaultItemAnimator.setMoveDuration(2000);
            defaultItemAnimator.setChangeDuration(2000);
            recyclerView.setItemAnimator(defaultItemAnimator);
        }



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
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setStackFromEnd()不支持GridLayoutManager()
                ((LinearLayoutManager)recyclerView.getLayoutManager()).setStackFromEnd(true);    // 跳到最后
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayoutManager)recyclerView.getLayoutManager()).setReverseLayout(true);   // 反转
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPosition(5);       // 跳到指定位置，第五个的底部在底部对齐
//                ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(5,-100); // 跳到第五个数据，第二个参数为偏移量,第五个数据的顶部与顶部对齐
            }
        });
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayoutManager)recyclerView.getLayoutManager()).smoothScrollToPosition(recyclerView, null,15); //滑到指定位置
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager linearLayoutManager1 = ((LinearLayoutManager)recyclerView.getLayoutManager());
                int fist = linearLayoutManager1.findFirstVisibleItemPosition();           // 第一个可见item
                int fistCom = linearLayoutManager1.findFirstCompletelyVisibleItemPosition(); // 第一个可见完整的item
                int last = linearLayoutManager1.findLastVisibleItemPosition();            // 最后一个可见的item
                int lastCom = linearLayoutManager1.findLastCompletelyVisibleItemPosition();  // 最后一个可见完整的item
                tv7.setText("第一个可见item:"+fist+"/n/n"+"第一个可见完整的item:"+fistCom+
                        "/n/n"+"最后一个可见item:"+last+"/n/n"+"最后一个可见完整的item:"+lastCom);
            }
        });

//        recyclerView.smoothScrollToPosition(15);
//        recyclerView.scrollToPosition(5);

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
