package wh.ywh.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by yangwenhao on 2018-05-10.
 * 日志工具类
 */

public class LogUtil {
    private static boolean isDebug = true;
    static String TAG = "=============Debug=============";

    //STRING,JSON,BUNDLE,MAP,LIST,
    public static void i (Object o){
        i(null,o);
    }

    public static void i(String tag, Object o) {
        if(isDebug){
            if(TextUtils.isEmpty(tag)){
                i(TAG,o);
            }else{
                Log.i(tag,getStr(o));
            }
        }
    }

    public static void e (Object o){
        e(null,o);
    }

    public static void e(String tag, Object o) {
        if(isDebug){
            if(TextUtils.isEmpty(tag)){
                e(TAG,o);
            }else{
                Log.e(tag,getStr(o));
            }
        }
    }

    public static void d (Object o){
        d(null,o);
    }

    public static void d(String tag, Object o) {
        if(isDebug){
            if(TextUtils.isEmpty(tag)){
                d(TAG,o);
            }else{
                Log.d(tag,getStr(o));
            }
        }
    }

    //boolean?a:b
    private static String getStr(Object o){
        return o==null?"":o.toString();
    }

}
