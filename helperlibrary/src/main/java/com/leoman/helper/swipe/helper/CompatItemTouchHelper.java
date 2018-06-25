package com.leoman.helper.swipe.helper;


import android.support.v7.widget.helper.ItemTouchHelper;

/**
 */
public class CompatItemTouchHelper extends ItemTouchHelper {

    Callback mCallback;

    public CompatItemTouchHelper(Callback callback) {
        super(callback);
        mCallback = callback;
    }

    /**
     * Developer callback which controls the behavior of ItemTouchHelper.
     *
     * @return {@link Callback}
     */
    public Callback getCallback() {
        return mCallback;
    }
}
