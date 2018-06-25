package wh.ywh.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by yangwenhao on 2018-05-15.
 */

public abstract  class CommonAdapter<T>  extends RecyclerView.Adapter<CommonAdapter.CommonViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private int mLayoutId;
    private List<T> mData;

    private View headView;
    private View footView;

    private OnItemClickListener mListener;
    private OnItemLongClickListener mLongListener;

    private static final int TYPE_HEADVIEW = 0x0000;     //头布局
    private static final int TYPE_CONTENTVIEW = 0x0001;  //内容
    private static final int TYPE_FOOTVIEW = 0x0002;     //底布局

    public CommonAdapter(int layoutId, List<T> data){
        if(layoutId == 0){
            throw new NullPointerException("请设置资源id");
        }
        this.mLayoutId = layoutId;
        this.mData = (data == null?new ArrayList<T>():data);
    }
    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADVIEW)
            return new CommonViewHolder(headView);
        else if(viewType == TYPE_FOOTVIEW)
            return new CommonViewHolder(footView);
        else
            return new CommonViewHolder(LayoutInflater.from(parent.getContext()).inflate(mLayoutId,parent,false));
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
//        LogUtil.i(""+headView==null?true:false+",pos:"+position);
        if(headView !=null && position ==0 )
            return ;
        if (footView != null && position == getItemCount() - 1)
            return;
        if(headView != null)
            position--;
        bindData(holder,mData.get(position),position);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setOnLongClickListener(this);
        holder.itemView.setTag(position);
    }

    protected abstract void bindData(CommonViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        if(headView != null && footView != null) return mData.size()+2;
        else if((headView != null && footView == null)||(headView == null && footView != null)) return mData.size()+1;
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(headView != null && position == 0) return TYPE_HEADVIEW;
        else if(footView != null && position == (mData.size()+1)) return TYPE_FOOTVIEW;
        else return TYPE_CONTENTVIEW;
    }

    public void setHeadView(View view){
        this.headView = view;
        notifyItemInserted(0);//因为指定了第一个位置头布局，最后一个是底布局，notifyItemInserted(0)参数0没什么用
    }

    public void setFootView(View view){
        this.footView = view;
        notifyItemInserted(0);
    }

    public void setData(List<T> data){
        mData = data;
        notifyDataSetChanged();
    }
    public void setOnItemClicked(OnItemClickListener listener){
        this.mListener = listener;
    }
    public void setOnItemLongClicked(OnItemLongClickListener listener){
        this.mLongListener = listener;
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick((int)v.getTag(),v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(mLongListener != null){
            return  mLongListener.onItemLongClick((int)v.getTag(),v);
        }
        return false; //同时有点击和长按时，返回true,不会触发点击，返回false,up时会触发点击
    }


    public static class CommonViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> sparseArray;
        public CommonViewHolder(View itemView) {
            super(itemView);
            sparseArray = new SparseArray<>();
        }

        public <T extends View> T findView(int viewId) {
            View view = sparseArray.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                sparseArray.put(viewId, view);
            }
            return (T) view;
        }

        public void setText(int viewId,String str){
            View view = findView(viewId);
            if( view == null){
                throw  new NullPointerException("没有找到资源id");
            }
            ((TextView)view).setText(""+str);
        }

//        public void setImage(int viewId,String url){
//            View view = findView(viewId);
//            if( view == null){
//                throw  new NullPointerException("没有找到资源id");
//            }
//        }

        public void setSimpleDrawImage(){

        }

    }

}
