package com.leoman.helper.view.recycleView.linear;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * Created by spurs on 2017/3/31.
 * 距离顶部距离
 */
public class StartOffsetItemDecoration extends ItemDecoration {
    private int mOffsetPx;

    public StartOffsetItemDecoration(int offsetPx) {
        this.mOffsetPx = offsetPx;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) < 1) {
            int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
            if (orientation == 0) {
                outRect.left = this.mOffsetPx;
            } else if (orientation == 1) {
                outRect.top = this.mOffsetPx;
            }
        }

    }
}

