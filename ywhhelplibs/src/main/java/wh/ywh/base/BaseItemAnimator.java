package wh.ywh.base;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2018-07-19.
 */

public class BaseItemAnimator extends DefaultItemAnimator {
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return super.animateRemove(holder);
    }

}
