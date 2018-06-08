package ywh.wh.test.loop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import wh.ywh.util.LogUtil;

/**
 * Created by Administrator on 2018-06-06.
 */

public class LoopWheel extends View {
    private List<String> mData;
    private Paint textPaint;
    private Paint.FontMetrics fontMetrics;
    private float textHeight;
    private float textWidth;
    private Paint dividerPaint;
    private int itemHeight;
    private float lastDownY;
    private float moveLen;


    public LoopWheel(Context context) {
        super(context);
        init(context,null);
    }

    public LoopWheel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LoopWheel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        initPaints();//初始化画笔
    }

    private void initPaints() {
        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(40);

        dividerPaint = new Paint();
        dividerPaint.setColor(Color.WHITE);

        fontMetrics = textPaint.getFontMetrics();
        textHeight = fontMetrics.descent - fontMetrics.leading - fontMetrics.ascent;//文字高度
        textWidth = getTextWidth(textPaint, "你好");//文字宽度

    }

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        itemHeight = getHeight()/7;

        for(int i =0;i<8;i++){
            canvas.drawText("你好:"+i,(getWidth()-textWidth)/2, i*itemHeight+itemHeight/2 + +moveLen+textHeight / 2 - fontMetrics.descent,textPaint);
        }

        canvas.drawLine(0,3*itemHeight,getWidth(),3*itemHeight,dividerPaint);
        canvas.drawLine(0,4*itemHeight,getWidth(),4*itemHeight,dividerPaint);

//        canvas.drawLine(0,1*itemHeight,getWidth(),1*itemHeight,dividerPaint);
//        canvas.drawLine(0,2*itemHeight,getWidth(),2*itemHeight,dividerPaint);
//        canvas.drawLine(0,5*itemHeight,getWidth(),5*itemHeight,dividerPaint);
//        canvas.drawLine(0,6*itemHeight,getWidth(),6*itemHeight,dividerPaint);

        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,dividerPaint);
//        canvas.drawText("你好",(getWidth()-textWidth)/2, getHeight() / 2 + textHeight / 2 - fontMetrics.descent,textPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    
    public void setData(List<String> data){
        this.mData = data;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                lastDownY = event.getY();
                LogUtil.e("down:"+ event.getY());
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.e("up:"+ event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                moveLen = event.getY()-lastDownY;
                LogUtil.e("moveLen:"+ moveLen);
                invalidate();
                break;
        }
        return true;
    }
}
