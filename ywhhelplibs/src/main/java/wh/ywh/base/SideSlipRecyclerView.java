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

public class SideSlipRecyclerView extends RecyclerView {

    private Handler handler;
    private Runnable runnable;
    private Context mContext;
    private View itemView;   //itemview
    private View outSideView; // 外部的view
    private int outSideViewWidth;  //外部view的宽度
    private int outSideLayoutId = 0;   // 外部View的id
    private boolean isScrollingH;  //是否正在横向滑动
    private RecyclerView.ViewHolder viewHolder;
    private int lastX;
    private int lastY;
    private Scroller scroller;
    private boolean isItemOpened = false;  //表示是否侧滑菜单显示
    private int itemViewPosition; // 滑动item的position
    private OnItemSlideListener onItemSlideListener;   //滑动监听事件
    private boolean bool = false; //有item打开时,判断再次点击的时候是当前打开的item
    private boolean isClosing = false; //正在关闭
    private int scrollDuration = 250; // Scroller默认滑动时间

    public SideSlipRecyclerView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public SideSlipRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public SideSlipRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
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
                isClosing = false;
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
                if (!isItemOpened) {  //未打开
                    if (!isClosing) {
                        itemView = findChildViewUnder(x, y);//根据用户点击的坐标，找到RecyclerView下的子View，这里也就是每一个Item
                        viewHolder = (RecyclerView.ViewHolder) getChildViewHolder(itemView);//获得每一个Item的ViewHolder
                        itemViewPosition = getChildLayoutPosition(itemView);
                        if (outSideLayoutId == 0) {
                            new NullPointerException("请设置outSideLayoutId");
                        } else {
                            outSideView = itemView.findViewById(outSideLayoutId);
                            outSideViewWidth = outSideView.getWidth();//获得侧滑菜单的宽度
                        }
                        LogUtil.d("outSideViewWidth:" + outSideViewWidth);
                    }

                } else {
                    View view1 = findChildViewUnder(x, y);
                    RecyclerView.ViewHolder viewHolder1 = (RecyclerView.ViewHolder) getChildViewHolder(view1);
                    bool = viewHolder.equals(viewHolder1);//判断当前用户指向的Item是否为之前打开的那个Item
                    LogUtil.e("itemView.getScrollX():" + itemView.getScrollX());
                    LogUtil.e("bool:" + bool);
                    if (bool) {
                        break;
                    } else {
                        scroller.startScroll(itemView.getScrollX(), 0, -outSideViewWidth, 0, scrollDuration);//弹性滑动
                        handler.postDelayed(runnable, scrollDuration);
                        invalidate();
                        isScrollingH = false;
                        return true;   //加上这一句会好一些，
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                isScrollingH = true;
                int scrollX = itemView.getScrollX();  //获得用户在滑动后，View相对初始位置移动的距离
                int dx = lastX - x; //得到用户实时移动的距离（横向）
                int dy = lastY - y;
                if (Math.abs(dx) > Math.abs(dy)) {  //只要左右移动的举例比上下移动的距离大，就执行滑动菜单操作
                    LogUtil.e("scrollX:" + scrollX + ",dx:" + dx);
                    if (scrollX + dx >= outSideViewWidth) {    //检测右边界
                        itemView.scrollTo(outSideViewWidth, 0); //scrollTo()中的参数是指要“移动到的位置”
                        Log.e("Move", "view.scrollTo(deleteWidth,0)");
                        return true;    //表示已经消费这个事件，不必再传递了
                    } else if (scrollX + dx <= 0) {    //检测左边界
                        itemView.scrollTo(0, 0);
                        Log.e("Move", "view.scrollTo(0,0)");
                        isItemOpened = false;
                        isScrollingH = false;
                        return true;
                    }
                    LogUtil.e("Move --- scrollBy:" + dx);
                    itemView.scrollBy(dx, 0);  //scrollBy()中的参数是指要“移动的距离（也就是像素的数量）”
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.e("scroller.isFinished():" + scroller.isFinished());
                if (isItemOpened && !bool) {
                    if (!scroller.isFinished()) {
                        isClosing = true;
                    }
                    isItemOpened = false;
                } else {
                    int deltaX = 0;
                    int upScrollX = itemView.getScrollX();//获得Item总共移动的距离
                    Log.e("hehe", "scrollX2:" + upScrollX);
                    if (upScrollX >= outSideViewWidth / 2) {  //如果显示超过一半，则弹性滑开
                        deltaX = outSideViewWidth - upScrollX;
                        isItemOpened = true;
                    } else if (upScrollX < outSideViewWidth / 2) {//否则关闭
                        deltaX = -upScrollX;//在startScroll()方法中，第三个参数小于0，表示向右滑。
                        isItemOpened = false;
                        isScrollingH = false;
                    }
                    scroller.startScroll(upScrollX, 0, deltaX, 0, 100);//弹性滑动
                    invalidate();
                    if (onItemSlideListener != null) {
                        onItemSlideListener.onSlide(itemViewPosition, isItemOpened, outSideView);
                    }
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
     *
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
