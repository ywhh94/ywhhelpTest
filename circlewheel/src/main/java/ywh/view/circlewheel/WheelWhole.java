package ywh.view.circlewheel;

import java.util.List;

/**
 * Created by Administrator on 2018-06-05.
 */

public class WheelWhole {
    private String unit ;  //单位
    private int textSize = 16;  //文字大小
    private int outTextColor ; // 未选中文字颜色
    private int selTextColor ; //选中文字的颜色
    private List<String> mData; //数据源
    private boolean loop;      //是否循环
    private int initIndex;     //初始值选中数据的第几个

    public WheelWhole() {

    }
    public WheelWhole(List<String> mData) {
        this.mData = mData;
    }
    public int getInitIndex() {
        return initIndex;
    }

    public WheelWhole setInitIndex(int initIndex) {
        this.initIndex = initIndex;
        return this;
    }

    public boolean getLoop() {
        return loop;
    }

    public WheelWhole setLoop(boolean loop) {
        this.loop = loop;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public WheelWhole setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public WheelWhole setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getOutTextColor() {
        return outTextColor;
    }

    public WheelWhole setOutTextColor(int outTextColor) {
        this.outTextColor = outTextColor;
        return this;
    }

    public int getSelTextColor() {
        return selTextColor;
    }

    public WheelWhole setSelTextColor(int selTextColor) {
        this.selTextColor = selTextColor;
        return this;
    }

    public List<String> getmData() {
        return mData;
    }

    public WheelWhole setmData(List<String> mData) {
        this.mData = mData;
        return this;
    }
}
