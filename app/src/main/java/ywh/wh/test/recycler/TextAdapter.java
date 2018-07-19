package ywh.wh.test.recycler;

import java.util.List;

import wh.ywh.base.SelectAdapter;

/**
 * Created by Administrator on 2018-07-18.
 */

public class TextAdapter extends SelectAdapter {

    public TextAdapter(int layoutId, List data, int selView) {
        super(layoutId, data, selView);
    }

    @Override
    protected void bindData(CommonViewHolder holder, Object o, int position) {
        super.bindData(holder, o, position);
    }
}
