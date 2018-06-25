package com.leoman.helper.view.recycleView.grid;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
/**
 * Created by spurs on 2017/3/31.
 * 距离顶部距离
 */
public class GridBottomOffsetItemDecoration extends ItemDecoration {
    private int mOffsetPx;
    private int mNumColumns;

    public GridBottomOffsetItemDecoration(int offsetPx, int numColumns) {
        this.mOffsetPx = offsetPx;
        this.mNumColumns = numColumns;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemCount = state.getItemCount();
        int numChildrenOnLastRow = itemCount % this.mNumColumns;
        if(numChildrenOnLastRow == 0) {
            numChildrenOnLastRow = this.mNumColumns;
        }

        boolean childIsInBottomRow = parent.getChildAdapterPosition(view) >= itemCount - numChildrenOnLastRow;
        if(childIsInBottomRow) {
            outRect.bottom = this.mOffsetPx;
        }

    }
}

