package ywh.wh.test.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;


/**
 * loading等待框
 */

public class AVLoadingIndicatorView extends View {
    //Sizes (with defaults in DP)
    public static final int DEFAULT_SIZE = 45;
    int mIndicatorColor;
    Paint mPaint;
    private boolean mHasAnimation;
    public static final float SCALE=1.0f;
    public static final int ALPHA=255;

    float[] scaleFloats=new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};

    int[] alphas=new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};



    public AVLoadingIndicatorView(Context context) {
        super(context);
        init(null, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
//        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AVLoadingIndicatorView);
//        mIndicatorColor = a.getColor(R.styleable.AVLoadingIndicatorView_indicator_color, Color.WHITE);
//        a.recycle();
        mPaint = new Paint();
//        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(dp2px(DEFAULT_SIZE), widthMeasureSpec);
        int height = measureDimension(dp2px(DEFAULT_SIZE), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float radius=getWidth()/10;
        for (int i = 0; i < 8; i++) {
            canvas.save();
            Point point = circleAt(getWidth(),getHeight(),getWidth()/2-radius,i*(Math.PI/4));
            canvas.translate(point.x,point.y);
            canvas.scale(scaleFloats[i],scaleFloats[i]);
            mPaint.setAlpha(alphas[i]);
            canvas.drawCircle(0,0,radius,mPaint);
            canvas.restore();
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasAnimation) {
            mHasAnimation = true;
            int[] delays= {0, 120, 240, 360, 480, 600, 720, 780, 840};
            for (int i = 0; i < 8; i++) {
                final int index=i;
                ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);
                scaleAnim.setDuration(1000);
                scaleAnim.setRepeatCount(-1);
                scaleAnim.setStartDelay(delays[i]);
                scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        scaleFloats[index] = (float) animation.getAnimatedValue();
                        postInvalidate();
                    }
                });
                scaleAnim.start();

                ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 77, 255);
                alphaAnim.setDuration(1000);
                alphaAnim.setRepeatCount(-1);
                alphaAnim.setStartDelay(delays[i]);
                alphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        alphas[index] = (int) animation.getAnimatedValue();
                        postInvalidate();
                    }
                });
                alphaAnim.start();
            }
        }
    }

    /**
     * 圆O的圆心为(a,b),半径为R,点A与到X轴的为角α.
     *则点A的坐标为(a+R*cosα,b+R*sinα)
     * @param width
     * @param height
     * @param radius
     * @param angle
     * @return
     */
    Point circleAt(int width,int height,float radius,double angle){
        float x= (float) (width/2+radius*(Math.cos(angle)));
        float y= (float) (height/2+radius*(Math.sin(angle)));
        return new Point(x,y);
    }

    final class Point{
        public float x;
        public float y;

        public Point(float x, float y){
            this.x=x;
            this.y=y;
        }
    }


    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }


}
