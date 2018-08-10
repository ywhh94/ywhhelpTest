package wh.ywh.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于ScrollView嵌套RecyclerView、listView、GridView时，
 * 可以设置当按下屏幕的点的View是RecyclerView时，是否可以滑动，
 * 支持多个滑动
 * Created by Administrator on 2018-08-07.
 */

public class NestRLVScrollView extends ScrollView {
    private boolean isOnRLv; // 触摸是否在Rlv上
    private boolean isCanRLvScroll = false; //是否需要拦截rlv,即是否允许触摸到rlv时,先滑动rlv，再滑动ScrollView
    private List<View> canScrollViews = new ArrayList<>(); //需要滑动的RecyclerView、listView、GridView集合

    public NestRLVScrollView(Context context) {
        super(context);
    }

    public NestRLVScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestRLVScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isTouchOnRlv(ev)) {
                    isOnRLv = true;
                } else {
                    isOnRLv = false;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否触摸在RecyclerView/listView/GridView上
     *
     * @param ev
     * @return boolean
     */
    private boolean isTouchOnRlv(MotionEvent ev) {
        int[] location = new int[2];
        float touchX = ev.getRawX();
        float touchY = ev.getRawY();
        if (canScrollViews != null && canScrollViews.size() > 0) {
            for (View view : canScrollViews) {
                view.getLocationOnScreen(location);
                int left = location[0];
                int top = location[1];
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
//              LogUtil.e("touchX:"+touchX+",touchY:"+touchY);
//              LogUtil.e("left:" + left + ",top:" + top + ",right:" + right + ",bottom:" + bottom);
                if (touchY >= (float) top && touchY <= bottom && touchX >= left && touchX <= right)
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanRLvScroll && isOnRLv) {
            return false;
        }
        return true;
    }

    public void setCanRlvScroll(RecyclerView recyclerView, boolean canScroll) {
        this.isCanRLvScroll = canScroll;
        canScrollViews.add(recyclerView);
    }

    public void setCanRlvScroll(AbsListView abslistView, boolean canScroll) {
        this.isCanRLvScroll = canScroll;
        canScrollViews.add(abslistView);
    }
}
