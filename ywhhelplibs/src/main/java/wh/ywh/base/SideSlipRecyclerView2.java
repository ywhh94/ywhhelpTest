package wh.ywh.base;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import wh.ywh.base.i.OnItemSlideListener;
import wh.ywh.util.LogUtil;

/**
 * 侧滑
 * bug：快速点击时，可能会导致打开的item还没有关闭，就停止了,可能会有两个item都开着,
 * Created by yangwenhao on 2017/4/25.
 */

public class SideSlipRecyclerView2 extends RecyclerView {

    private Handler handler;
    private Runnable runnable;
    private Context mContext;
    private View itemView;   //itemview
    private View outSideView; // 外部的view
    private int outSideViewWidth;  //外部view的宽度
    private int outSideLayoutId = 0;   // 外部View的id
    private ViewHolder viewHolder;
    private int lastX;
    private int lastY;
    private Scroller scroller;
    private int itemViewPosition; // 滑动item的position
    private OnItemSlideListener onItemSlideListener;   //滑动监听事件
    private int scrollDuration = 250; // Scroller默认滑动时间

    private boolean isScrollingH = false;  //用户是否正在横向滑动
    private boolean isTheOpendItem = true; //有item打开时,判断再次点击的时候是当前打开的item
    private int itemStatus = 0;  //0 没有item打开, 1 有item打开 , 2 item正在关闭 , 3 item正在打开

    public SideSlipRecyclerView2(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public SideSlipRecyclerView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public SideSlipRecyclerView2(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    private void init() {
        scroller = new Scroller(mContext, new LinearInterpolator());
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                isScrollingH = false;
            }
        };
    }

    //这里出现了事件分发的冲突，导致RecyclerView无法响应ACTION_DOWN事件，所以需要RecycleView进行该事件的劫持。
    //而且在滑动的过程当中，ACTION_MOVE事件也没有很好的响应，所以也把这个事件劫持了。
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onTouchEvent(e);
                break;
            case MotionEvent.ACTION_MOVE:
                onTouchEvent(e);
                break;
            case MotionEvent.ACTION_UP:
                onTouchEvent(e);
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int x = (int) e.getX();//获得当前点击的X坐标
        int y = (int) e.getY();//获得当前点击的Y坐标
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.e("itemStatus:" + itemStatus + ",isScrollingH:" + isScrollingH);
                if (0 == itemStatus) {  //未打开
                    LogUtil.e("未打开");
                    itemView = findChildViewUnder(x, y);//根据用户点击的坐标，找到RecyclerView下的子View，这里也就是每一个Item
                    viewHolder = (ViewHolder) getChildViewHolder(itemView);//获得每一个Item的ViewHolder
                    itemViewPosition = getChildLayoutPosition(itemView);
                    if (outSideLayoutId == 0) {
                        new NullPointerException("请设置outSideLayoutId");
                    } else {
                        outSideView = itemView.findViewById(outSideLayoutId);
                        outSideViewWidth = outSideView.getWidth();//获得侧滑菜单的宽度
                    }
                } else {
                    View view1 = findChildViewUnder(x, y);
                    ViewHolder viewHolder1 = (ViewHolder) getChildViewHolder(view1);
                    isTheOpendItem = viewHolder.equals(viewHolder1);//判断当前用户指向的Item是否为之前打开的那个Item
                    LogUtil.e("是否是之前打开的item:" + isTheOpendItem);
                    if (isTheOpendItem) {//是之前打开的item
                        break;
                    } else {
                        if (itemStatus != 3) {
                            itemStatus = 2;
                        }
                        if (!isScrollingH) {
                            scroller.startScroll(scroller.getCurrX(), 0, -outSideViewWidth, 0, scrollDuration);//弹性滑动
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    isScrollingH = false;
                                    itemStatus = 0;
                                    isTheOpendItem = true;
                                }
                            }, scrollDuration);
                            invalidate();
                        }

                        return true;   //加上这一句会好一些，
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.e("ACTION_MOVE");
                LogUtil.e("itemStatus:" + itemStatus + ",isScrollingH:" + isScrollingH + ",isTheOpendItem:" + isTheOpendItem);
                if (isTheOpendItem) {
                    int scrollX = itemView.getScrollX();  //获得用户在滑动后，View相对初始位置移动的距离
                    int dx = lastX - x; //得到用户实时移动的距离（横向）
                    int dy = lastY - y;
                    if (Math.abs(dx) > Math.abs(dy)) {  //只要左右移动的举例比上下移动的距离大，就执行滑动菜单操作
//                        LogUtil.e("scrollX:" + scrollX + ",dx:" + dx);
                        isScrollingH = true;
                        if (scrollX + dx >= outSideViewWidth) {    //检测右边界
                            itemView.scrollTo(outSideViewWidth, 0); //scrollTo()中的参数是指要“移动到的位置”
                            handler.postDelayed(runnable, scrollDuration);
                            return true;    //表示已经消费这个事件，不必再传递了
                        } else if (scrollX + dx <= 0) {    //检测左边界
                            itemView.scrollTo(0, 0);
                            return true;
                        }
                        itemView.scrollBy(dx, 0);  //scrollBy()中的参数是指要“移动的距离（也就是像素的数量）”
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.e("ACTION_UP");
                LogUtil.e("itemStatus:" + itemStatus + ",isScrollingH:" + isScrollingH);
                if (isScrollingH && isTheOpendItem) {
                    int deltaX = 0;
                    int upScrollX = itemView.getScrollX();//获得Item总共移动的距离
                    Log.e("ACTION_UP", "scrollX2:" + upScrollX);
                    if (upScrollX >= outSideViewWidth / 2) {  //如果显示超过一半，则弹性滑开
                        deltaX = outSideViewWidth - upScrollX;
                        itemStatus = 3;
                    } else if (upScrollX < outSideViewWidth / 2) {//否则关闭
                        deltaX = -upScrollX;//在startScroll()方法中，第三个参数小于0，表示向右滑。
                        itemStatus = 2;
                    }
                    scroller.startScroll(upScrollX, 0, deltaX, 0, 100);//弹性滑动
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isTheOpendItem = true;
                            isScrollingH = false;
                            if (itemStatus == 3) {
                                itemStatus = 1;
                            } else if (itemStatus == 2) {
                                itemStatus = 0;
                            }
                            LogUtil.e("itemStatus:"+itemStatus);
                            if (onItemSlideListener != null) {
                                onItemSlideListener.onSlide(itemViewPosition, itemStatus == 1, outSideView);
                            }
                        }
                    }, 100);
                    invalidate();
                }

                break;
        }

        lastX = x;
        lastY = y;
        return super.onTouchEvent(e);//返回调用父类的方法，来处理我们没有处理的操作，比如上下滑动操作
    }


    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
//            itemView.scrollTo(scroller.getCurrX(),scroller.getCurrY());
            itemView.scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    /**
     * 是否正在横向滑动
     * @return
     */
    public boolean getIsScrollingH() {
        return isScrollingH;
    }

    public void setOnItemSlide(int outSideLayoutId, OnItemSlideListener listener) {
        this.outSideLayoutId = outSideLayoutId;
        this.onItemSlideListener = listener;
    }
}
