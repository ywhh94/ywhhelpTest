package com.leoman.helper.view.recycleView.linear;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by spurs on 2017/3/31.
 * 距离底部距离
 */

public class EndOffsetItemDecoration extends ItemDecoration {
    private int mOffsetPx;

    public EndOffsetItemDecoration(int offsetPx) {
        this.mOffsetPx = offsetPx;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemCount = state.getItemCount();
        if (parent.getChildAdapterPosition(view) == itemCount - 1) {
            int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
            if (orientation == 0) {
                outRect.right = this.mOffsetPx;
            } else {
                outRect.bottom = this.mOffsetPx;
            }
        }

    }
}