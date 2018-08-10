package ywh.wh.test.intercep;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import wh.ywh.util.LogUtil;

/**
 * Created by Administrator on 2018-08-09.
 */

public class TestView extends TextView {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("View --- onTouchEvent");
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.e("View --- dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }
}
