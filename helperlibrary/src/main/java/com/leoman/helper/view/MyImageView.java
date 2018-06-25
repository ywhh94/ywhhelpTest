package com.leoman.helper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.leoman.helper.R;

/**
 * Created by spurs on 2017/4/1.
 * 自定义比例(w/h)
 */

public class MyImageView extends ImageView {

    private float aspectRatio;

    public MyImageView(Context context, float aspectRatio) {
        super(context);
        this.aspectRatio = aspectRatio;
    }

    public MyImageView(Context context) {
        super(context);
        init(null, 0);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyImageView);
        aspectRatio = a.getFloat(R.styleable.MyImageView_aspectRatio, 1);//  aspectRatio w/h
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (aspectRatio <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width / aspectRatio);
            setMeasuredDimension(width, height);
        }

    }
}
