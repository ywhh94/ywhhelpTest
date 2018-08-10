package ywh.wh.test.intercep;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import wh.ywh.util.LogUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-09.
 */

public class InterceptTestActivity extends Activity{
    private TextView tv1;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_iac);
        tv1 = (TextView)findViewById(R.id.tv1);
        ll = (LinearLayout) findViewById(R.id.ll);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("tv1");
            }
        });
//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtil.e("ll");
//            }
//        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("Activity --- onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.e("Activity --- dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
