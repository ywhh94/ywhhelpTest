package ywh.wh.test.intercep;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import wh.ywh.util.LogUtil;

/**
 * Created by Administrator on 2018-08-09.
 */

public class MyLinearLayout2 extends LinearLayout {

    public MyLinearLayout2(Context context) {
        super(context);
    }

    public MyLinearLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.e("ViewGroup2 --- dispatchTouchEvent :"+super.onTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.e("ViewGroup2 --- onInterceptTouchEvent :"+super.onTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("ViewGroup2 --- onTouchEvent :"+super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }



}
