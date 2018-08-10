package wh.ywh.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018-04-19.
 */
public class CommonItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int top;
    private int bottom;
    private int left;
    private  int right;
    private  int h;

    private int wtop ;
    private int wBottom;
    private int wLeft ;
    private int wRight ;
    private int nh ;
    private int nv ;
    private int num = 1;

    //横向布局
    public CommonItemDecoration(Context context, int top, int bottom, int left, int right, int h) {
        if(this.mContext==null) {
            this.mContext = context;
        }

    }


    /**
     * @param wtop  距外边框上部
     * @param wBottom 距外边框下部
     * @param wLeft 距外边框左部
     * @param wRight 距外边框右部
     * @param nh    item之间横向距离
     * @param nv item之间纵向距离
     */
    public CommonItemDecoration(Context context, int wtop, int wBottom, int wLeft, int wRight, int nh, int nv, int num) {
        this.mContext = context;
//        int w = WindowUtil.getWindowWidth(mContext);
//            this.wtop = wtop*w/750;
//            this.wBottom = wBottom*w/750;
//            this.wLeft = wLeft*w/750;
//            this.wRight = wRight*w/750;
//            this.nh = nh*w/750;
//            this.nv = nv*w/750;
//            if(num>0) {
//                this.num = num;
//        }
    }

    public CommonItemDecoration(Context context, int wtop, int wBottom, int wLeft, int wRight, int nh, int nv) {
        this.wtop = wtop;
        this.wBottom = wBottom;
        this.wLeft = wLeft;
        this.wRight = wRight;
        this.nh = nh;
        this.nv = nv;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        //竖直方向的
        if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
            int hang = ((parent.getChildAdapterPosition(view))/num)+1; //几行
            int postion = (parent.getChildAdapterPosition(view))%num; //一行中第几个
            int maxhang ;
            if(layoutManager.getItemCount()%num==0){
                maxhang = layoutManager.getItemCount() / 2;
            }else{
                maxhang = (layoutManager.getItemCount() / 2)+1;
            }
            if(num == 1) {
                outRect.left = wLeft;
                outRect.right = wRight;
                if(hang ==1) {
                    outRect.top = wtop;
                    outRect.bottom = nv/2;
                } else if(hang ==layoutManager.getItemCount()) {
                    outRect.top = nv/2;
                    outRect.bottom = wBottom;
                } else {
                    outRect.top = nv/2;
                    outRect.bottom = nv/2;
                }
            } else if(num == 2) {//5个，第3行一列,
                if(postion == 0) {
                    outRect.left = wLeft;
                    outRect.right = nh/2;
                } else {
                    outRect.left = nh/2;
                    outRect.right = wRight;
                }
                if(hang == 1) {
                    outRect.top = wtop;
                    outRect.bottom = nv/2;
                } else if(hang == maxhang) {
                    outRect.bottom = wBottom;
                    outRect.top = nv/2;
                } else {
                    outRect.top = nv/2;
                    outRect.bottom = nv/2;
                }
            } else {
                if(postion == 0) {
                    outRect.left = wLeft;
                    outRect.right = nh/2;
                } else if(postion == num-1){
                    outRect.left = nh/2;
                    outRect.right = wRight;
                }else{
                    outRect.left = nh/2;
                    outRect.right = nh/2;
                }
                if(hang == 1) {
                    outRect.top = wtop;
                    outRect.bottom = nv/2;
                } else if(hang == layoutManager.getItemCount()/2) {
                    outRect.bottom = wBottom;
                    outRect.top = nv/2;
                } else {
                    outRect.top = nv/2;
                    outRect.bottom = nv/2;
                }
            }
        }
        else {
            if(parent.getChildAdapterPosition(view) == 0) {
                outRect.left = left;
                outRect.right = h/2;
            } else if(parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1) {
                outRect.right = right;
                outRect.left = h/2;
            } else {
                outRect.left = h/2;
                outRect.right = h/2;
            }
            outRect.top = top;
            outRect.bottom = bottom;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
