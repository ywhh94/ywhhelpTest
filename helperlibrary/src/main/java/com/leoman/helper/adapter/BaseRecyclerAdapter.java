package com.leoman.helper.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spurs on 2017/3/29.
 * RecycleView adapter管理类
 */

public abstract class BaseRecyclerAdapter<D> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;
    private View mHeaderView;
    private View mFooterView;
    /**
     * 数据源
     */
    public List<D> list;
    /**
     * 布局资源id
     */
    private int layoutResId;

    /**
     * Item点击事件
     */
    private onItemClickListener clickListener;
    /**
     * Item长按事件
     */
    private onItemLongClickListener longListener;

    public BaseRecyclerAdapter(int layoutResId, List<D> data) {
        this.list = data == null ? new ArrayList<D>() : data;
        if (layoutResId != 0) {
            this.layoutResId = layoutResId;
        } else {
            throw new NullPointerException("请设置Item资源id");
        }
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(TYPE_HEADER);
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(TYPE_FOOTER);
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) return TYPE_HEADER;
        if (mFooterView != null && position == getItemCount() - 1) return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new BaseViewHolder(mHeaderView);
        if (mFooterView != null && viewType == TYPE_FOOTER) return new BaseViewHolder(mFooterView);
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (mHeaderView != null && position == 0)
            return;
        if (mFooterView != null && position == getItemCount() - 1)
            return;
        if (mHeaderView != null)
            position--;
        bindTheData(holder, list.get(position), position);
        //设置Item的点击事件
        holder.itemView.setOnClickListener(this);
        //设置Item的长按事件
        holder.itemView.setOnLongClickListener(this);
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        if (mHeaderView != null && mFooterView != null) return list.size() + 2;
        if ((mHeaderView == null && mFooterView != null) || (mHeaderView != null && mFooterView == null))
            return list.size() + 1;
        return list.size();
    }

    /**
     * 处理gridView 添加header
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER)
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 清除数据
     */

    public void clearData() {
        if (list != null && list.size() > 0) {
            list.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 绑定数据
     *
     * @param holder 视图管理者
     * @param data   数据源
     */
    protected abstract void bindTheData(BaseViewHolder holder, D data, int position);

    @Override
    public void onClick(View v) {
        //点击回调
        if (clickListener != null) {
            clickListener.onItemClick((Integer) v.getTag(), v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        //长按回调
        return longListener != null && longListener.onItemLonClick((Integer) v.getTag(), v);
    }

    /*********************************************
     * 基础视图管理者 内部类用静态
     *********************************************/
    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        /**
         * 集合类，layout里包含的View,以view的id作为key，value是view对象
         */
        private SparseArray<View> mViews;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
        }

        public <T extends View> T findViewById(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }

    /**
     * 设置点击监听
     *
     * @param clickListener 监听器
     */
    public void setClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 设置长按监听
     *
     * @param longListener 监听器
     */
    public void setLongListener(onItemLongClickListener longListener) {
        this.longListener = longListener;
    }

    public interface onItemClickListener {
        void onItemClick(int position, View v);
    }

    public interface onItemLongClickListener {
        boolean onItemLonClick(int position, View v);
    }
}
