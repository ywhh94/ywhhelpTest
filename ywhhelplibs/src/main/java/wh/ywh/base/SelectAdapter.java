package wh.ywh.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2018-07-18.
 */

public class SelectAdapter extends CommonAdapter {
    public int selVlayoutId;
    public SelectAdapter(int layoutId, List data,int selVLayoutId) {
        super(layoutId, data);
        this.selVlayoutId = selVLayoutId;
    }

    @Override
    protected void bindData(CommonViewHolder holder, Object o, int position) {
        View selView = holder.findView(selVlayoutId);
        selView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
