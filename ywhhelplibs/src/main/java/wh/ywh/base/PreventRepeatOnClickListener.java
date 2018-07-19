package wh.ywh.base;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseLongArray;
import android.view.View;

/**
 * Created by Administrator on 2018-07-19.
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public abstract class PreventRepeatOnClickListener implements View.OnClickListener{
    private SparseLongArray mLastClickTimeRecordArray= new SparseLongArray();
    private long mClickInterVal=300;
    //是阻止一个控件快速点击(比如快速点击A)，还是所有设置了该监听的控件都不能快速点击（比如不能同时点击A,B控件，包括快速点击A）
    private boolean isPreventRepeatOnClickOneView=true;
    private long mLastClickTime=0;

    public void setClickInterVal(long clickInterVal){
        this.mClickInterVal=clickInterVal;
    }

    public void setPreventRepeatOnClickOneView(boolean isPreventRepeatOnClickOneView){
        this.isPreventRepeatOnClickOneView=isPreventRepeatOnClickOneView;
    }
    @Override
    public void onClick(View v) {
        if (v.getId()!=0){
            if (isPreventRepeatOnClickOneView) {
                preventOneViewRepeatClick(v);
            }else{
                preventAllViewRepeatClick(v);
            }
        }else{
            Log.e(this.getClass().getName(),"this view("+v.toString()+") not set id, so we can't prevent repeat click!!");
        }
    }

    private void preventAllViewRepeatClick(View v) {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime-mLastClickTime>=mClickInterVal){
            mLastClickTime=currentClickTime;
            mLastClickTimeRecordArray.put(v.getId(),currentClickTime);
            onClickView(v);
        }
    }

    private void preventOneViewRepeatClick(View v) {
        long lastClickTime = mLastClickTimeRecordArray.get(v.getId());
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime >= mClickInterVal) {
            mLastClickTime=currentClickTime;
            mLastClickTimeRecordArray.put(v.getId(), currentClickTime);
            onClickView(v);
        }
    }

    abstract void onClickView(View v);
}