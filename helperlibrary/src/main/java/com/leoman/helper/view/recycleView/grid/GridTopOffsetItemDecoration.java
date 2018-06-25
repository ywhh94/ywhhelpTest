package com.leoman.helper.view.recycleView.grid;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by Administrator on 2017/3/31.
 * 距离底部距离
 */
public class GridTopOffsetItemDecoration extends ItemDecoration {
    private int mOffsetPx;
    private int mNumColumns;

    public GridTopOffsetItemDecoration(int offsetPx, int numColumns) {
        this.mOffsetPx = offsetPx;
        this.mNumColumns = numColumns;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        boolean childIsInTopRow = parent.getChildAdapterPosition(view) < this.mNumColumns;
        if (childIsInTopRow) {
            outRect.top = this.mOffsetPx;
        }

    }
}

