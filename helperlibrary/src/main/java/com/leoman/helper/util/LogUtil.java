package com.leoman.helper.util;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/6/8.
 * 打印日志
 */

public class LogUtil {
    private static boolean sDebug = true;
    private static String sTag = "Log_err";
    private static final int JSON_INDENT = 2;

    public static void show() {
        if (sDebug) {
            Log.e(sTag, "===============我出现啦===============");
        }
    }

    /**
     * L.json(new Gson().toJson());
     *
     * @param json
     */
    public static void json(String json) {
        json(null, json);
    }

    public static void e(String msg, Object... params) {
        e(null, msg, params);
    }

    /**
     * @param msg 转换符       说明
     *            <p>
     *            %s         字符串类型
     *            %c         字符类型
     *            %b         布尔类型
     *            %d         整数类型（十进制）
     *            %x         整数类型（十六进制）
     *            %o         整数类型（八进制）
     *            %n         换行符
     *            %tx         日期与时间类型（x代表不同的日期与时间转换符
     */

    public static void e(String tag, String msg, Object[] params) {
        if (!sDebug) return;
        LogText.e(getFinalTag(tag), String.format(msg, params));
    }

    public static void json(String tag, String json) {
        if (!sDebug) return;
        LogText.e(getFinalTag(tag), getPrettyJson(json));
    }

    private static String getPrettyJson(String jsonStr) {
        try {
            jsonStr = jsonStr.trim();
            if (jsonStr.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                return jsonObject.toString(JSON_INDENT);
            }
            if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonStr);
                return jsonArray.toString(JSON_INDENT);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Invalid Json, Please Check: " + jsonStr;
    }


    private static String getFinalTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            return tag;
        }
        return sTag;
    }

    private static class LogText {
        private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════\n";
        private static final String SINGLE_DIVIDER = "────────────────────────────────────────────\n";

        private String mTag;

        public LogText(String tag) {
            mTag = tag;
        }

        public static void e(String tag, String content) {
            LogText logText = new LogText(tag);
            logText.setup(content);
        }

        public void setup(String content) {
            setUpHeader();
            setUpContent(content);
            setUpFooter();
        }

        private void setUpHeader() {
            Log.e(mTag, SINGLE_DIVIDER);
        }

        private void setUpFooter() {
            Log.e(mTag, DOUBLE_DIVIDER);
        }

        public void setUpContent(String content) {
            StackTraceElement targetStackTraceElement = getTargetStackTraceElement();
            Log.e(mTag, "(" + targetStackTraceElement.getFileName() + ":"
                    + targetStackTraceElement.getLineNumber() + ")");
            Log.e(mTag, content);
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
