package ywh.wh.test;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/15.
 */

public class CustomView extends View{

    private int DEFAULT_SIZE = 100;//默认尺寸
    private int DEFAULT_COLOR = Color.GREEN;//默认颜色
    private float DEFAULT_MAXVALUE = 1.6f;//默认放大倍数

    private int mSize;
    private int mColor;
    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private Float value = 1f;
    private Float maxValue;

    private ValueAnimator anim;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,R.styleable.CustomView,0,0);
        mSize = typedArray.getDimensionPixelSize(R.styleable.CustomView_size,DEFAULT_SIZE);
        mColor = typedArray.getColor(R.styleable.CustomView_color,DEFAULT_COLOR);
        maxValue = typedArray.getFloat(R.styleable.CustomView_maxValue,DEFAULT_MAXVALUE);

        typedArray.recycle();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);//抗锯齿效果
        //画圆，前两个参数确定圆心，第三个参数是半径
        canvas.drawCircle(mWidth/2,mHeight/2,mSize *value,mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // 重设圆半径，防止超出视图大小
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
        int maxSize = (int) Math.min(mHeight/(2 * maxValue),mWidth/(2 * maxValue));
        mSize = mSize > maxSize ? maxSize : mSize;

        anim = ValueAnimator.ofFloat(value,maxValue);
        anim.setRepeatCount(ValueAnimator.INFINITE);//设置无限重复
        anim.setRepeatMode(ValueAnimator.REVERSE);//设置重复模式
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        anim.start();
    }

    public void startAnimation(){
        anim = ValueAnimator.ofFloat(value,maxValue);
        anim.setRepeatCount(ValueAnimator.INFINITE);//设置无限重复
        anim.setRepeatMode(ValueAnimator.REVERSE);//设置重复模式
        anim.setDuration(500);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (Float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        anim.start();
    }


    /**
     * 需要停止时调用
     */
    public void endAnimation(){
        anim.end();
    }

    /**
     * view离开窗口时调用
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        endAnimation();
    }



}
