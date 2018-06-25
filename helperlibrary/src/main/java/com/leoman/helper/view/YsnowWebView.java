package com.leoman.helper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by spurs on 2015/4/20.
 */
public class YsnowWebView extends WebView {
    public float oldY;
    private int t;

    public YsnowWebView(Context context) {
        super(context);
    }

    public YsnowWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YsnowWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float Y = ev.getY();
                float Ys = Y - oldY;
                if (Ys > 0 && t == 0) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
                oldY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);

                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     *
     * @param l
     * @param t  t==0  代表滑动到了最顶端  t的值代表webview滑动后停留在最顶端时Y方向的值
     * @param oldl  滑动起始值 （t>oldt 向上滑动  t<oldt 向下滑动）
     * @param oldt
     */

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        this.t = t;
        super.onScrollChanged(l, t, oldl, oldt);
    }


}
