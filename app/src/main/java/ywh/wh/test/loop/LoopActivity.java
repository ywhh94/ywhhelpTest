package ywh.wh.test.loop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-06-06.
 */

public class LoopActivity extends AppCompatActivity{
    private PickerScrollView pickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_loop);

        pickerView = (PickerScrollView) findViewById(R.id.pickerView);
        List<Pickers> list22 = new ArrayList<>();
        for(int i = 0;i<20;i++){
            Pickers p = new Pickers("2018:"+i,""+i, Color.RED);
            list22.add(p);
        }
        pickerView.setData(list22);
        pickerView.setSelected(3);


        final WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
        wheelView.setTextSize(80);
        wheelView.setVisibilityCount(7);
        wheelView.setTextGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        wheelView.setSelectedTextColor(getResources().getColor(R.color.colorAccent));
        final List<String> dataSources = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataSources.add("数据" + i);
        }
        wheelView.setDataSources(dataSources);
        wheelView.setCallBack(new WheelView.CallBack() {
            @Override
            public void onPositionSelect(int position) {
                String.format(Locale.getDefault(), "当前为：" + dataSources.get(position));
            }
        });

    }
}
