package wh.ywh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangwenhao on 2018-05-10.
 * 日期工具类
 */

public class DateUtil {
    /**
     * 获取当前年度
     * @return
     */
    public static int getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return Integer.valueOf(sdf.format(new Date()));
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getCurrentMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return Integer.valueOf(sdf.format(new Date()));
    }

    /**
     * 获取当天几号
     * @return
     */
    public static int getCurrentDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return Integer.valueOf(sdf.format(new Date()));
    }

    /**
     * 获取当前时分秒
     * @return
     */
    public static String getCurrentHms(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取当天 是星期几
     * @return 周一到周日 依次返回 1~7
     */
    public static int getDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(1 == dayOfWeek){
            return 7;
        }
        return dayOfWeek-1;
    }

    /**
     * 指定日期是星期几
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(1 == dayOfWeek){
            return 7;
        }
        return dayOfWeek-1;
    }

    /**
     * 获取指定日期是星期几
     * @param str   yyyy-MM-dd
     * @return
     */
    public static int getDayOfWeek(String str){
        int dayOfWeek = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(1 == dayOfWeek){
            return 7;
        }
        return dayOfWeek-1;
    }

    /**
     * 当前月共有天数
     * @return
     */
    public static int getMaxDayOfMonth(){
        return getMaxDayOfMonth(getCurrentYear(),getCurrentMonth());
    }
    /**
     * 获取指定年月有多少天
     * @param year
     * @param month
     * @return
     */
    public static int getMaxDayOfMonth(int year,int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 0);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 比较时间大小（  HH:mm:ss  ）
     * @param time1
     * @param time2
     * @return time1比time2早,返回-1,time1比time2晚返回 1, 相等返回 0; 参数不正确 -100 ;catch  返回-101
     */
    public static int compTime(String time1,String time2,String format){
        if(time1.indexOf(":")<0||time2.indexOf(":")<0){
            LogUtil.i("DateUtil","参数不正确");
            return -100;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                Date date1 = sdf.parse(time1);
                Date date2 = sdf.parse(time2);
                if(date1.getTime()>date2.getTime()){
                    return 1;
                }else if(date1.getTime()==date2.getTime()){
                    return 0;
                } else{
                    return -1;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return -101;
            }
        }
    }
}
