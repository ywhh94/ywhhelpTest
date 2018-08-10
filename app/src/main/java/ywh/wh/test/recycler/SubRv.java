package ywh.wh.test.recycler;

import android.content.Context;

import java.util.List;

import wh.ywh.base.CommonAdapter;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-09.
 */
public class SubRv extends CommonAdapter<String> {
    public SubRv(Context mContent, List<String> data) {
        super(R.layout.item_head,data);
    }

    @Override
    protected void bindData(CommonViewHolder holder, String s, int position) {

    }
}
