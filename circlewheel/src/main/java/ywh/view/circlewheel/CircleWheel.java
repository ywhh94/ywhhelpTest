package ywh.view.circlewheel;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018-06-05.
 */

public class CircleWheel implements View.OnClickListener {

    private static Dialog alertDialog;

    private View headView;
    private View footView;
    private boolean headViewShow;            //是否显示headView

    private CancelListener cancellistener;
    private SureListener sureListener;

    private int wheelNum;                    //滚轮的个数
    private Map<Integer,WheelWhole> dataMap; //数据的map
    private boolean canceledOnTouchOutside;  //点击外面位置是否取消
    private DataSelectListener  dataSelectListener;   //选择数据只有监听
    private int[] tag;                                  //tag第几个轮的数据监听
    private int dividerColor;                      //选中分割线的颜色
    private String headTitle;                      //head的title



    private LinearLayout ll_head;
    private LinearLayout ll_foot;
    private LinearLayout ll_wheel;
    private Activity mActivity;
    private Map<Integer,BackData> map = new HashMap<>();

    private CircleWheel(Builder builder) {
        headView = builder.headView;
        headViewShow = builder.headViewShow;

        footView = builder.footView;
        dataSelectListener = builder.dataSelectListener;
        tag = builder.tag;
        wheelNum = builder.wheelNum;
        dataMap = builder.dataMap;
        canceledOnTouchOutside = builder.canceledOnTouchOutside;
        dividerColor = builder.dividerColor;
        headTitle = builder.headTitle;
        cancellistener = builder.cancellistener;
        sureListener = builder.sureListener;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.circlewheel_left) {
            if(cancellistener != null){
                cancellistener.onCancel();
            }
        } else if (i == R.id.circlewheel_right) {
            if(sureListener != null){
                sureListener.onSure();
            }
        }
    }

    public interface DataSelectListener{
        void onDataSelected(Map<Integer,BackData> map);
    }

    public interface CancelListener{
        void onCancel();
    }

    public interface SureListener{
        void onSure();
    }

    public void show(Activity activity){
        this.mActivity = activity;
        if (alertDialog == null)
            alertDialog = new Dialog(activity, R.style.DefaultDialogStyle);
        alertDialog.show();
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        alertDialog.getWindow().setContentView(R.layout.window_circlewheel_ymd);

        ll_head = (LinearLayout) alertDialog.getWindow().findViewById(R.id.ll_head);
        ll_foot = (LinearLayout) alertDialog.getWindow().findViewById(R.id.ll_foot);
        ll_wheel = (LinearLayout) alertDialog.getWindow().findViewById(R.id.ll_wheel);

        addWheels();
        showHeadView();
    }
    public void cancel(){
        if(alertDialog!=null){
            alertDialog.cancel();
        }
    }

    /**
     * headView
     */
    private void showHeadView() {
        if(headViewShow){
            if(headView != null) {
                ll_head.removeAllViews();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                headView.setLayoutParams(lp);
                ll_head.addView(headView);
            }
            else{
                TextView cancel = (TextView) ll_head.findViewById(R.id.circlewheel_left);
                TextView sure = (TextView) ll_head.findViewById(R.id.circlewheel_right);
                TextView headtitle = (TextView) ll_head.findViewById(R.id.circlewheel_title);
                cancel.setOnClickListener(this);
                sure.setOnClickListener(this);
                if(!TextUtils.isEmpty(headTitle)){
                    headtitle.setText(headTitle);
                }
            }
        }
    }

    /**
     * 添加几个滚轮
     * 不设置 默认为一个
     */
    private void addWheels() {
        LoopView loopView;
        int weightNum = 0;
        if(dataMap!=null&&dataMap.size()>0){
            if(dataMap.size()<wheelNum){
                weightNum = dataMap.size();
            }else{
                weightNum = wheelNum;
            }
        }
        Log.e("wheelNum:",""+wheelNum);
        ll_wheel.setOrientation(LinearLayout.HORIZONTAL);
        ll_wheel.setWeightSum(weightNum);
        Log.e("weightNum:",""+weightNum);
        for(int i = 0;i<weightNum;i++) {
            Log.e("for:",""+i);
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_circlewheel,null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1 ;
            view.setLayoutParams(lp);//设置布局参数
            ll_wheel.addView(view);
            loopView = (LoopView) view.findViewById(R.id.loopView);
            setWheelDatas(i,loopView);
        }
    }

    /**
     * @param i
     * @param loopView
     */
    private void setWheelDatas(final int i, LoopView loopView) {
        if(dataMap!=null&&dataMap.size()>0){
            if(dataMap.containsKey(i)){
                final WheelWhole wheelWhole = dataMap.get(i);
                List<String> datalist = wheelWhole.getmData();
                loopView.setItems(datalist);
                if(wheelWhole.getmData()!=null &&wheelWhole.getmData().size()>0) {
                    if(wheelWhole.getInitIndex()>=0&&wheelWhole.getInitIndex()<wheelWhole.getmData().size()){
                        loopView.setInitPosition(wheelWhole.getInitIndex()); //初始化数据为data中的第几个，必须在setItems之后
                    }
                }
                if(wheelWhole.getTextSize() != 0){
                    loopView.setTextSize(wheelWhole.getTextSize());//文字大小
                }
                if(wheelWhole.getSelTextColor() != 0){
                    loopView.setCenterTextColor(wheelWhole.getSelTextColor());  //选中的文字颜色
                }
                if(wheelWhole.getOutTextColor() != 0){
                    loopView.setOuterTextColor(wheelWhole.getOutTextColor()); //未选中文字颜色
                }
                if(!wheelWhole.getLoop()){
                    loopView.setNotLoop();//循环
                }
                if(dividerColor != 0){
                    loopView.setDividerColor(dividerColor); // 中间选择线的颜色
                }
                if(datalist!=null&& datalist.size()>0){
                    loopView.setListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(int index) {
                            if(dataSelectListener!=null){
                                Log.e("onItemSelected:","onItemSelected");
                                BackData bean = new BackData();
                                bean.setTag(i);
                                bean.setPosition(index);
                                bean.setStr(wheelWhole.getmData().get(index));
                                map.put(i,bean);
                                dataSelectListener.onDataSelected(map);
                            }
                        }
                    });
                }
            }
        }
    }

    public static final class Builder {
        private View headView;
        private boolean headViewShow = true;

        private View footView;
        private DataSelectListener dataSelectListener;
        private int[] tag;
        private int wheelNum = 2;
        private Map<Integer,WheelWhole> dataMap;
        private boolean canceledOnTouchOutside;
        private int dividerColor;
        private String headTitle;
        private CancelListener cancellistener;
        private SureListener sureListener;


        public Builder() {
        }

        public Builder headView(boolean show) {
            headViewShow = show;
            return this;
        }
        public Builder headView(boolean show,View val) {
            headViewShow = show;
            headView = val;
            return this;
        }
        public Builder cancellistener(CancelListener val) {
            cancellistener = val;
            return this;
        }
        public Builder sureListener(SureListener val) {
            sureListener = val;
            return this;
        }

        public Builder footView(View val) {
            footView = val;
            return this;
        }

        public Builder dataSelectListener(DataSelectListener val, int[] cTag) {
            dataSelectListener = val;
            tag = cTag;
            return this;
        }

        public Builder wheelNum(int val) {
            wheelNum = val;
            return this;
        }

        public Builder dataMap(Map<Integer,WheelWhole> val) {
            dataMap = val;
            return this;
        }
        public Builder canceledOnTouchOutside(boolean val){
            canceledOnTouchOutside = val;
            return this;
        }
        public Builder dividerColor(int val){
            dividerColor = val;
            return this;
        }
        public Builder headTitle(String val){
            headTitle = val;
            return this;
        }

        public CircleWheel build() {
            return new CircleWheel(this);
        }
    }


    public class BackData{
        private int tag;
        private int position;
        private String str;

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
