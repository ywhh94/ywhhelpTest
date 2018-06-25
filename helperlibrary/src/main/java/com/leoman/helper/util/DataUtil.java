package com.leoman.helper.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by spurs on 2017/3/22.
 * 时间戳和时间转换工具类
 */
public class DataUtil {

    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;

    /**
     * 掉此方法输入所要转换的时间输入例如（"2014年06月14日"）返回时间戳
     *
     * @param timeStamp
     * @return
     */
    public static String time2TimeStamp(String timeStamp) {
        if (TextUtils.isEmpty(timeStamp))
            return "";
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(timeStamp);
            long l = date.getTime();
            times = String.valueOf(l);
            Log.e("时间戳======", times);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }

    public static long time2LongTimeStamp(String timeStamp) {
        if (TextUtils.isEmpty(timeStamp))
            return 0;
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd HH:mm",
                Locale.CHINA);
        Date date;
        long l = 0;
        try {
            date = sdr.parse(timeStamp);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static String TimeStamp2time(String time) {
        if (TextUtils.isEmpty(time))
            return "";
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        String times = sdr.format(new Date(lcc));
        return times;

    }

    public static String TimeStamp2time2(String time) {
        if (TextUtils.isEmpty(time))
            return "";
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        String times = sdr.format(new Date(lcc));
        return times;

    }

    public static String TimeStamp2time3(String time) {
        if (TextUtils.isEmpty(time))
            return "";
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        String times = sdr.format(new Date(lcc));
        return times;

    }

    public static String TimeStamp2time4(String time) {
        if (TextUtils.isEmpty(time))
            return "";
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        String times = sdr.format(new Date(lcc));
        return times;

    }

    public static String TimeFormat(String time) {
        return TextUtils.isEmpty(time) ? "" : new DecimalFormat("#.##").format(Double.parseDouble(time));
    }

    public static String TimeFormat(Double time) {
        return time == null ? "0" : new DecimalFormat("#.##").format(time);
    }

    /**
     * 距离今天多久
     *
     * @param date
     * @return
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE + "分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR + "小时前";
        else if (ago <= ONE_DAY * 2)
            return "昨天";
        else if (ago <= ONE_DAY * 3)
            return "前天";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月前";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前";
        }

    }

    /**
     * 当前日期前七天，后七天
     *
     * @return
     */
    public static List<String> get7Day() {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for (int i = 7; i >= -7; i--) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i); // 向前一周；如果需要向后一周，用正数即可
            //    cal.add(java.util.Calendar.MONTH, -1); // 向前一月；如果需要向后一月，用正数即可
            list.add(sdf.format(cal.getTime()));
        }
        return list;
    }

    public static int Double2Int(Double value) {
        return value == null ? 0 : value.intValue();
    }

    public static int Float2Int(Float value) {
        return value == null ? 0 : value.intValue();
    }


    public static double Double2Dot(Double value) {
        return value == null ? 0 : new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String String2Dot(Double value) {
        return value == null ? "0" : new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * view 转bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

}
