package wh.ywh.flow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018-08-06.
 */

public class YFlowLayout extends ViewGroup {
    private YFlowAdapter mFlowAdapter;
    private Context mContext;
    private int itemCount;

    public YFlowLayout(Context context) {
        super(context);
        mContext = context;
    }

    public YFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public YFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

    }


    public void setAdapter(YFlowAdapter flowAdapter) {
        this.mFlowAdapter = flowAdapter;
        itemCount = flowAdapter.getItemCount();

        for (int i = 0; i < itemCount; i++) {
            View view = mFlowAdapter.getView(this, i, flowAdapter.getItem(i));
            addView(view);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int itemCount = getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int itemCount = getChildCount();


    }
}
