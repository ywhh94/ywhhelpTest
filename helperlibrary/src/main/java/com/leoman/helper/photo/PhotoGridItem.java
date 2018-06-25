package com.leoman.helper.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.leoman.helper.R;
import com.leoman.helper.util.DisplayUtil;

/**
 * Created by Administrator on 2016/9/20.
 */
public class PhotoGridItem extends RelativeLayout implements Checkable {
    private Context mContext;
    private boolean mCheck;
    private ImageView mImageView;
    private ImageView mSelect;
    private FrameLayout fl_view;

    public PhotoGridItem(Context context) {
        this(context, null, 0);
    }

    public PhotoGridItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhotoGridItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.photoalbum_gridview_item, this);
        mImageView = (ImageView) findViewById(R.id.photo_img_view);
        mSelect = (ImageView) findViewById(R.id.photo_select);
        fl_view = (FrameLayout) findViewById(R.id.fl_view);
        int imgW = (DisplayUtil.getWidth(mContext) - DisplayUtil.dip2px(mContext, 32)) / 3;
        mImageView.getLayoutParams().width = imgW;
        mImageView.getLayoutParams().height = imgW;
    }

    @Override
    public void setChecked(boolean checked) {
        mCheck = checked;
        System.out.println(checked);
        if (mCheck) {
            mSelect.setImageResource(R.mipmap.pic_select);
        } else {
            mSelect.setImageResource(R.mipmap.pic_noselect);
        }
    }

    @Override
    public boolean isChecked() {
        return mCheck;
    }

    @Override
    public void toggle() {
        setChecked(!mCheck);
    }

    public void setImgResID(int id) {
        if (mImageView != null) {
            mImageView.setBackgroundResource(id);
        }
    }

    public void SetBitmap(Bitmap bit) {
        if (mImageView != null) {
            mImageView.setImageBitmap(bit);
        }
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public FrameLayout getFl_view() {
        return fl_view;
    }

    public ImageView getCheckView() {
        return mSelect;
    }
}
