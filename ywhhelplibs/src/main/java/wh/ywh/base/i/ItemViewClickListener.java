package wh.ywh.base.i;

/**
 * item中子视图点击
 * Created by Administrator on 2018-07-27.
 */

public interface ItemViewClickListener {
    //fromId:来源,viewTag:子item中的哪个view,position:点击所在的item的位置,extraData:传递额外的数据
    void onItemViewClick(int fromId,int viewTag,int position,Object extraData);
}
