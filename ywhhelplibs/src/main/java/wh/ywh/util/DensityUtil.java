package wh.ywh.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2018-06-01.
 */

public class DensityUtil {

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
}
