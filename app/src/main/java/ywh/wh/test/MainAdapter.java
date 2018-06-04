package ywh.wh.test;

import android.content.Context;

import java.util.List;

import wh.ywh.base.CommonRvAdapter;

/**
 * Created by Administrator on 2018-05-29.
 */
public class MainAdapter extends CommonRvAdapter<String>{

    private Context mContext;
    public MainAdapter(Context context,int layoutId, List<String> data) {
        super(layoutId, data);
        this.mContext = context;
    }

    @Override
    protected void bindData(CommonViewHolder holder, String s, int position) {
        holder.setText(R.id.item_tv,"tv1"+s);
        holder.setText(R.id.item_tv2,"tv2"+s);
    }
}
