package wh.ywh.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于LinearLayout中包含RecyclerView时，拦截RecyclerView的点击事件，
 * 其他View不拦截，
 * 一般用户当RecyclerView/listView 中itemView还嵌套有RecyclerView时，itemView用此 NLinearLayout布局
 * 后续升级测试listView,GridView
 * Created by Administrator on 2018-08-03.
 */

public class NestRLGLinearLayout extends LinearLayout {
    private boolean isIntercept;//是否被拦截
    private Map<View,Boolean> map;

    public NestRLGLinearLayout(Context context) {
        super(context);
        map = new HashMap<>();
    }

    public NestRLGLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        map = new HashMap<>();
    }

    public NestRLGLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        map = new HashMap<>();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float touchX = ev.getRawX();
                float touchY = ev.getRawY();
                if (isOnInterceptView(touchX, touchY)) {
                    isIntercept = true;
                } else {
                    isIntercept = false;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isIntercept){
            return isIntercept;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 是否在recyclerView上
     *
     * @param touchX
     * @param touchY
     * @return
     */
    private boolean isOnInterceptView(float touchX, float touchY) {
        int[] location = new int[2];
        if(map!=null&&map.size()>0){
            boolean isViewIntercept = false;
            for(View view :map.keySet()){
                isViewIntercept = map.get(view);
                if(isViewIntercept){
                    if (view != null) {
                        view.getLocationOnScreen(location);
                        int left = location[0];
                        int top = location[1];
                        int right = left + view.getMeasuredWidth();
                        int bottom = top + view.getMeasuredHeight();
                        if (touchY >= (float) top && touchY <= bottom && touchX >= left && touchX <= right)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public void setInterceptView(RecyclerView recyclerView,boolean isIntercept){
        if(map!=null&&!map.containsKey(recyclerView)){
            map.put(recyclerView,isIntercept);
        }
    }

    //listView/GridView
    public void setInterceptView(AbsListView absListView, boolean isIntercept){
        if(map!=null&&!map.containsKey(absListView)){
            map.put(absListView,isIntercept);
        }
    }
}
