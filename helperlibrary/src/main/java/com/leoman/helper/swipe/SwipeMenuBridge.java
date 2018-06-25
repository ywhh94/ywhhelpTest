package com.leoman.helper.swipe;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 */
public final class SwipeMenuBridge {

    @SwipeMenuRecyclerView.DirectionMode
    private final int mDirection;
    private final int mPosition;
    private final SwipeSwitch mSwipeSwitch;
    private final View mViewRoot;

    int mAdapterPosition;
    TextView mTextView;
    ImageView mImageView;

    SwipeMenuBridge(@SwipeMenuRecyclerView.DirectionMode int direction, int position, SwipeSwitch swipeSwitch, View viewRoot) {
        mDirection = direction;
        mPosition = position;
        mSwipeSwitch = swipeSwitch;
        mViewRoot = viewRoot;
    }

    public SwipeMenuBridge setBackgroundDrawable(@DrawableRes int resId) {
        return setBackgroundDrawable(ContextCompat.getDrawable(mViewRoot.getContext(), resId));
    }

    public SwipeMenuBridge setBackgroundDrawable(Drawable background) {

//        ViewCompat.setBackground(mViewRoot, background);
        return this;
    }

    public SwipeMenuBridge setBackgroundColorResource(@ColorRes int color) {
        return setBackgroundColor(ContextCompat.getColor(mViewRoot.getContext(), color));
    }

    public SwipeMenuBridge setBackgroundColor(@ColorInt int color) {
        mViewRoot.setBackgroundColor(color);
        return this;
    }

    public SwipeMenuBridge setImage(int resId) {
        return setImage(ContextCompat.getDrawable(mViewRoot.getContext(), resId));
    }

    public SwipeMenuBridge setImage(Drawable icon) {
        if (mImageView != null)
            mImageView.setImageDrawable(icon);
        return this;
    }

    public SwipeMenuBridge setText(int resId) {
        return setText(mViewRoot.getContext().getString(resId));
    }

    public SwipeMenuBridge setText(String title) {
        if (mTextView != null)
            mTextView.setText(title);
        return this;
    }

    @SwipeMenuRecyclerView.DirectionMode
    public int getDirection() {
        return mDirection;
    }

    public int getPosition() {
        return mPosition;
    }

    public int getAdapterPosition() {
        return mAdapterPosition;
    }

    public void closeMenu() {
        mSwipeSwitch.smoothCloseMenu();
    }
}
