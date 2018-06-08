package ywh.wh.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wh.ywh.base.OnRvClickListener;
import wh.ywh.base.OnRvLongClickListener;
import wh.ywh.util.DateUtil;
import wh.ywh.util.FrescoUtil;
import wh.ywh.util.HanziToPinyin;
import wh.ywh.util.LogUtil;
import wh.ywh.util.PhotoUtil;
import wh.ywh.util.SpUtil;
import wh.ywh.util.StrUtil;
import wh.ywh.util.ToastUtil;
import ywh.view.circlewheel.CircleWheel;
import ywh.view.circlewheel.WheelWhole;
import ywh.wh.test.loop.LoopActivity;

//测试ywhhelplibs
public class MainActivity extends AppCompatActivity implements OnRvClickListener, OnRvLongClickListener,  View.OnClickListener,
        CircleWheel.DataSelectListener, CircleWheel.CancelListener, CircleWheel.SureListener {

    private TextView tv;
    private RecyclerView recyclerView;
    private SimpleDraweeView iv_DraweeView;
    private PhotoUtil photoutil;
    private CircleWheel circlewheel;
    TextView tvwheel;
    TextView takephoto;
    private TextView loopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);//在setContentView之前
        setContentView(R.layout.activity_main);
        tvwheel = (TextView) findViewById(R.id.circlewheel);
        takephoto = (TextView) findViewById(R.id.takephoto);
        loopView = (TextView) findViewById(R.id.loopView);
        loopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoopActivity.class));
            }
        });
        tvwheel.setOnClickListener(this);
        takephoto.setOnClickListener(this);
        testDate();
        testSp();




//        DialogUtils.getInstance().showWindowImage(this);

        
        tv = (TextView)findViewById(R.id.tv);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        iv_DraweeView = (SimpleDraweeView) findViewById(R.id.iv_DraweeView);
        StrUtil.changeColor(tv,2,6, Color.parseColor("#00ffff"));
        StrUtil.changeSize(tv,2,6,100);
        StrUtil.addUnderLine(tv);
        LogUtil.i("pingying:"+HanziToPinyin.getInstance().getPinYin("杨文浩ywh"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FrescoUtil.setImage(iv_DraweeView,"http://p2.qhimgs4.com/t0199516ade911ca217.jpg");

        List<String> list = new ArrayList<>();

        MainAdapter adapter = new MainAdapter(this, R.layout.item_main, list);
        adapter.setHeadView(LayoutInflater.from(this).inflate(R.layout.item_head_load,null));
        adapter.setOnItemClicked(this);
        adapter.setOnItemLongClicked(this);

        recyclerView.setAdapter(adapter);
        for(int i=0;i<10;i++){
            list.add(""+i);
        }
        adapter.setData(list);
        adapter.setFootView(LayoutInflater.from(this).inflate(R.layout.item_foot,null));

//        adapter.notifyDataSetChanged();
//        testIntent();
//        testCamera();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circlewheel:
                List<String> list = new ArrayList<>();
                for(int i=0;i<10;i++){
                    list.add(""+i);
                }
                Map<Integer,WheelWhole> map = new HashMap<>();
                WheelWhole wheelWhole = new WheelWhole();
                wheelWhole.setInitIndex(5)
                        .setLoop(true)
                        .setOutTextColor(Color.RED)
                        .setSelTextColor(Color.RED)
                        .setmData(list);

                map.put(0,wheelWhole);
                map.put(1,new WheelWhole(list));
                map.put(2,new WheelWhole(list));
                View view = LayoutInflater.from(this).inflate(R.layout.item_foot,null);
                circlewheel = new CircleWheel.Builder()
                        .wheelNum(5)
                        .dataMap(map)
                        .canceledOnTouchOutside(false)
                        .dataSelectListener(this,new int[]{0,1,2})
                        .headTitle("时间选择")
                        .headView(true,view)
                        .cancellistener(this)
                        .sureListener(this)
                        .build();
                circlewheel.show(this);
                view.findViewById(R.id.item_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.toastLong(MainActivity.this,"点击");
                    }
                });
                break;

            case R.id.takephoto:
                photoutil = new PhotoUtil(this);
                photoutil.show();
                break;
        }
    }

    private void testCamera() {
        photoutil.openCamera();
    }

    private void testIntent() {
        Intent intent = new Intent(this,SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("ywh","ywh");
        bundle.putString("ywh2","ywh2");
        bundle.putSerializable("bean",new Bean(1));
        intent.putExtras(bundle);
        intent.putExtra("ywh33","intent_ywh");
        intent.putExtra("bean",new Bean(100));
//        startActivity(intent);
        startActivityForResult(intent,1);
    }

    private void testSp() {
        SpUtil.getInstance().putString("a1","111111111");
        SpUtil.getInstance().putInt("a2",2000);
        SpUtil.getInstance().putBoolean("a3",false);
        SpUtil.getInstance().putString("a1","2222222222");
        SpUtil.getInstance().putString("a2","2000000000000");
        Log.i("a1:",""+SpUtil.getInstance().getString("a1"));
        Log.i("a2:",""+SpUtil.getInstance().getString("a2"));
        Log.i("a3:",""+SpUtil.getInstance().getBoolean("a3"));
        Log.i("getClass:",""+SpUtil.getInstance().hashCode());
        Log.i("getClass2:",""+SpUtil.getInstance().hashCode());  //两个hashcode一样
    }

    private void testDate() {
        LogUtil.i(null);
        LogUtil.i("dayofweek:"+DateUtil.getDayOfWeek());
        LogUtil.i("2018年5月总共:"+DateUtil.getMaxDayOfMonth()+",2018年2月："+DateUtil.getMaxDayOfMonth(2018,2)+",2018 4 "+DateUtil.getMaxDayOfMonth(2018,4));
        LogUtil.i(DateUtil.getDayOfWeek(new Date())+":20180505:"+DateUtil.getDayOfWeek("2018-05-05")+""+":20180429:"+DateUtil.getDayOfWeek("2018-04-29"));
        LogUtil.i(DateUtil.getCurrentYear()+"-"+DateUtil.getCurrentMonth()+"-"+DateUtil.getCurrentDay());

        LogUtil.i("main:",DateUtil.compTime("02:00:30","03:00:30","HH:mm:ss"));
        LogUtil.i("main:",DateUtil.compTime("02:00:30","02:00:30","HH:mm:ss"));
    }

    @Override
    public void onItemClick(int position, View view) {
        ToastUtil.toastShort(this,"position:"+position);
    }

    @Override
    public boolean onItemLongClick(int position, View view) {
        ToastUtil.toastShort(this,"long:"+position);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK){
            LogUtil.d("ok");
            switch (requestCode){
                case 10001:
                   LogUtil.d("相机路径："+photoutil.cameraFile.getAbsolutePath());
                    FrescoUtil.setImage(iv_DraweeView, "file://"+photoutil.cameraFile.getAbsolutePath());
                    break;
            }
//        }
//        if(data !=null){
//            LogUtil.d("data:"+data.getStringExtra("ywh"));
//        }
//        else{
//            LogUtil.e("data kong");
//        }
    }


    @Override
    public void onDataSelected(Map<Integer,CircleWheel.BackData> map) {
        if(map!=null&&map.size()>0){
            for(int i=0;i<map.size();i++){
                if(map.containsKey(i)){
                    CircleWheel.BackData bean= map.get(i);
                    LogUtil.e("轮："+bean.getTag()+",值为"+bean.getStr());
                }
            }
        }
    }

    @Override
    public void onCancel() {
        circlewheel.cancel();
    }

    @Override
    public void onSure() {
        circlewheel.cancel();
    }
}
