package com.leoman.helper.view.recycleView.grid;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by Administrator on 2017/3/31.
 * grid分割线
 */

public class GridDividerItemDecoration extends ItemDecoration {

    //    private Drawable mHorizontalDivider;
//    private Drawable mVerticalDivider;
    private int mNumColumns;

    private Drawable mDivider;
    private int mSize;

//    public GridDividerItemDecoration(Drawable horizontalDivider, Drawable verticalDivider, int numColumns) {
//        this.mHorizontalDivider = horizontalDivider;
//        this.mVerticalDivider = verticalDivider;
//        this.mNumColumns = numColumns;
//    }

    public GridDividerItemDecoration(int color, int size, int numColumns) {
        this.mDivider = new ColorDrawable(color);
        this.mSize = size;
        this.mNumColumns = numColumns;
    }

    public void onDraw(Canvas canvas, RecyclerView parent, State state) {
        this.drawHorizontalDividers(canvas, parent);
        this.drawVerticalDividers(canvas, parent);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        boolean childIsInLeftmostColumn = parent.getChildAdapterPosition(view) % this.mNumColumns == 0;
        if (!childIsInLeftmostColumn) {
            outRect.left = mSize;
        }

        boolean childIsInFirstRow = parent.getChildAdapterPosition(view) < this.mNumColumns;
        if (!childIsInFirstRow) {
            outRect.top = mSize;
        }

    }

    private void drawHorizontalDividers(Canvas canvas, RecyclerView parent) {
        int parentTop = parent.getPaddingTop();
        int parentBottom = parent.getHeight() - parent.getPaddingBottom();

        for (int i = 0; i < this.mNumColumns; ++i) {
            View child = parent.getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            int parentLeft = child.getRight() + params.rightMargin;
            int parentRight = parentLeft + mSize;
            this.mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            this.mDivider.draw(canvas);
        }

    }

    private void drawVerticalDividers(Canvas canvas, RecyclerView parent) {
        int parentLeft = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        int numChildrenOnLastRow = childCount % this.mNumColumns;
        int numRows = childCount / this.mNumColumns;
        if (numChildrenOnLastRow == 0) {
            --numRows;
        }

        for (int i = 0; i < numRows; ++i) {
            View child = parent.getChildAt(i * this.mNumColumns);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            int parentTop = child.getBottom() + params.bottomMargin;
            int parentBottom = parentTop + mSize;
            this.mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            this.mDivider.draw(canvas);
        }

    }
}

