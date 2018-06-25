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
                LogText.e(tag,getStr(o),"info");
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
                LogText.e(tag,getStr(o),"err");
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



    private static class LogText {
        private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════\n";

        private String mTag;
        private String mType;

        public LogText(String tag,String type) {
            mTag = tag;
            mType = type;
        }

        public static void e(String tag, String content,String type) {
            LogText logText = new LogText(tag,type);
            logText.setup(content);

        }

        public void setup(String content) {
            setUpContent(content);
            setUpFooter();
        }

        private void setUpFooter() {
            if("err".equals(mType)){
                Log.e(mTag, DOUBLE_DIVIDER);
            }else if("info".equals(mType)){
                Log.i(mTag, DOUBLE_DIVIDER);
            }

        }

        public void setUpContent(String content) {

            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();

            if("err".equals(mType)){
                Log.e(mTag, "(" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
                Log.e(mTag, content);
            }else if("info".equals(mType)){
                Log.i(mTag, "(" + targetStackTraceElement.getFileName() + ":"
                        + targetStackTraceElement.getLineNumber() + ")");
                Log.i(mTag, content);
            }
        }

        private StackTraceElement getTargetStackTraceElement() {
            // find the target invoked method
            StackTraceElement targetStackTrace = null;
            boolean shouldTrace = false;
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtil.class.getName());
                if (shouldTrace && !isLogMethod) {
                    targetStackTrace = stackTraceElement;
                    break;
                }
                shouldTrace = isLogMethod;
            }
            return targetStackTrace;
        }
    }

}
