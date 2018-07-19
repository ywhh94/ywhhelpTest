package ywh.wh.test.recycler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import wh.ywh.base.CommonAdapter;
import wh.ywh.util.ToastUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-07-19.
 */
public class SwipeAdapter extends CommonAdapter<String>{
    private Context mContext;
    public SwipeAdapter(Context context, List<String> list) {
        super(R.layout.item_swipe_rv,list);
        this.mContext = context;
    }

    @Override
    protected void bindData(CommonViewHolder holder, String s, int position) {
        TextView item1 = (TextView)holder.findView(R.id.item1);
        TextView item2 = (TextView)holder.findView(R.id.item2);
        TextView content = (TextView) holder.findView(R.id.item);
        content.setText(""+s);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.toastShort(mContext,"item1");
            }
        });
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.toastShort(mContext,"item2");
            }
        });

    }
}
