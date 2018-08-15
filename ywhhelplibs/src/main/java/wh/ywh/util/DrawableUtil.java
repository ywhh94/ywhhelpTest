package wh.ywh.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * 设置textView DrawableLeft...
 * Created by Administrator on 2018-08-15.
 */

public class DrawableUtil {
    public void setTop(TextView textView, Drawable drawable) {
        if (drawable != null)
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    public void setBottom(TextView textView, Drawable drawable) {
        if (drawable != null)
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, null, drawable);
    }

    public void setLeft(TextView textView, Drawable drawable) {
        if (drawable != null)
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public void setRight(TextView textView, Drawable drawable) {
        if (drawable != null)
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }
}
