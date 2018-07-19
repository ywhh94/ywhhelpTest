package ywh.wh.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-05-29.
 */

public class LoadingPointView extends View {
    private static final float SCALE = 1.0f;
    private float pointSize;
    private int pointColor;
    private float pointScaleValue;
    private int pointNum;
    private float textSize;
    private int textColor;
    private String text;
    float[] oneY = new float[]{0,0.5f,1};
    float[] twoY = new float[]{0.5f,1,0.5f};
    float[] threeY = new float[]{1,0.5f,0};

    float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};
    private Paint textPaint;  //text
    private Paint pointPaint; //圆
    private float textHeight; //文字高度
    private float textWidth;  //文字宽度
    private Paint.FontMetrics fontMetrics;
    private int width;  //控件宽
    private int height;  //控件高

    public LoadingPointView(Context context) {
        super(context);
        initParams(null, context);
    }

    public LoadingPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(attrs, context);
    }

    public LoadingPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(attrs, context);
    }

    /**
     * 获取自定义参数
     *
     * @param attrs
     * @param context
     */
    private void initParams(AttributeSet attrs, Context context) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LoadingPointView);
        text = a.getString(R.styleable.LoadingPointView_loadText);
        if (TextUtils.isEmpty(text)) text = "正在加载 ";

        textColor = a.getColor(R.styleable.LoadingPointView_loadTextColor, Color.BLACK);
        textSize = a.getDimension(R.styleable.LoadingPointView_loadTextSize, 16);

        pointNum = a.getInt(R.styleable.LoadingPointView_pointNum, 3);
        pointColor = a.getColor(R.styleable.LoadingPointView_pointColor, Color.BLACK);
        pointSize = a.getDimension(R.styleable.LoadingPointView_pointSize, 10);
        pointScaleValue = a.getFloat(R.styleable.LoadingPointView_pointScaleVaule, 0.4f);

        initPaints();
        a.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaints() {
        pointPaint = new Paint();
        pointPaint.setColor(pointColor);
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(sp2px(getContext(), textSize));
        fontMetrics = textPaint.getFontMetrics();
        textHeight = fontMetrics.descent - fontMetrics.leading - fontMetrics.ascent;//文字高度
        textWidth = getTextWidth(textPaint, text);//文字宽度
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureDimension(dp2px(120), widthMeasureSpec);
//        LogUtil.e("width:"+width,"widthMeasureSpec:"+widthMeasureSpec+",dp2px(60):"+dp2px(60));
        height = measureDimension(dp2px(60), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {//设置为match_parent
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {//设置为wrap_content
            result = Math.min(defaultSize, specSize);
        } else {                                     //
            result = defaultSize;
        }
        return result;
    }

    private int dp2px(int dpValue) {
        return (int) getContext().getResources().getDisplayMetrics().density * dpValue;
    }

    private int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, new Paint());
        drawText(canvas);
        drawPoint(canvas);
        //圆之间间距默认为radius
    }

    /**
     * 画圆
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {

        float radius = 0;
        if (pointSize != 20) {
            radius = sp2px(getContext(), pointSize) / 2;
        } else {
            radius = (getWidth() - textWidth) / (2 * pointNum + pointNum - 1);
        }
        for (int i = 0; i < pointNum; i++) {
            canvas.save();
            float ix = (3 * i + 1) * radius;
            canvas.drawCircle(textWidth + ix, getHeight() / 2, radius * scaleFloats[i], pointPaint);
            canvas.restore();
        }
    }

    /**
     * 画文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        canvas.drawText(text, 0, getHeight() / 2 + textHeight / 2 - fontMetrics.descent, textPaint);
    }


    /**
     * 获取文字的宽度
     *
     * @param p
     * @param string
     * @return
     */
    private float getTextWidth(Paint p, String string) {
        float width = 0;
        float[] widths = new float[string.length()];
        p.getTextWidths(string, 0, string.length(), widths);
        for (int i = 0; i < string.length(); i++) {
            width += widths[i];
        }
        return width;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int[] delays = {0, 120, 240, 360, 480, 600, 720, 780, 840};
        for (int i = 0; i < 8; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, pointScaleValue, 1);
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();//重绘
                }
            });
            scaleAnim.start();
        }
    }

}
