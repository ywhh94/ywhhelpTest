package wh.ywh.base.i;

import android.view.View;

/**
 * RecyclerView侧滑回调
 * Created by Administrator on 2018-08-03.
 */

public interface OnItemSlideListener {
    void onSlide(int position, boolean isOpened, View outSideView);
}
