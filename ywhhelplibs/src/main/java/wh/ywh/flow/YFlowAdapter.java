package wh.ywh.flow;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2018-08-06.
 */

public abstract class YFlowAdapter<T> {
    private int layoutId;
    private List<T> data;
    private IFlowClickListener listener;

    public YFlowAdapter(List<T> data) {
        this.data = data;
    }

    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public T getItem(int position) {
        return data.get(position);
    }

    public abstract View getView(YFlowLayout parent, int position, T t);

    public List<T> getData() {
        return data;
    }

    public void setOnItemClickListener(IFlowClickListener listener) {
        this.listener = listener;
    }
}
