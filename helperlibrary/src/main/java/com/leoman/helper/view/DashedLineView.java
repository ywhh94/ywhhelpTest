package com.leoman.helper.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;
import com.leoman.helper.R;

/**
 * 绘制虚线
 * Created by Administrator on 2015/12/22.
 */
public class DashedLineView extends View {

    private int color = Color.parseColor("#CCCCCC");
    private float lineLength = 8f;
    private float interval = 4f;
    private float phase = 0f;

    public DashedLineView(Context context) {
        super(context);
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DashedLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.dash_line);
        color = a.getColor(R.styleable.dash_line_color, Color.parseColor("#CCCCCC"));//  虚线颜色
        lineLength = a.getFloat(R.styleable.dash_line_lineLength, 8f);//  虚线长度
        interval = a.getFloat(R.styleable.dash_line_interval, 4f);//  虚线间隔
        phase = a.getFloat(R.styleable.dash_line_phase, 0f);//  虚线相对起始位置偏移量
        a.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);//颜色可以自己设置
        Path path = new Path();
        path.moveTo(0, 0);//起始坐标

        if (getWidth() < getHeight()) {
            path.lineTo(0, getHeight());//终点坐标
        } else {
            path.lineTo(getWidth(), 0);//终点坐标
        }
        PathEffect effects = new DashPathEffect(new float[]{lineLength, interval, lineLength, interval}, phase);//设置虚线的间隔和点的长度
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }

    public DashedLineView setLineColor(int color) {
        this.color = color;
        return this;
    }

    public DashedLineView setLineLength(float lineLength) {
        this.lineLength = lineLength;
        return this;
    }

    public DashedLineView setInterval(float interval) {
        this.interval = interval;
        return this;
    }

    public DashedLineView setPhase(float phase) {
        this.phase = phase;
        return this;
    }

}