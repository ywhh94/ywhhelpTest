package com.leoman.helper.listener;


import com.leoman.helper.view.ObservableScrollView;

/**
 * Created by Administrator on 2016/12/15.
 * scrollView滑动监听
 */
public interface ScrollViewListener {
    void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
}
