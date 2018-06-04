package wh.ywh.util;

import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by yangwenhao on 2018-05-15.
 * 字符串工具类
 */

public class StrUtil {

    /**
     * 转字符串
     * @param object
     * @return String
     */
    public static String getStr(Object object){
        return object==null?"":object.toString();
    }

    /**
     * 改变指定位置的字体颜色
     * @param textView
     * @param start 开始位置
     * @param end   结束位置
     * @param color 颜色
     */
    public static void changeColor(TextView textView,int start,int end,int color){
        if(textView == null){
            return ;
        }
        String str = textView.getText().toString();

        //如果不处理,下标越界会报错 IndexOutOfBoundsException
        if(start<0){
            start = 0;
        }if(end>str.length()){
            end = str.length();
        }
        //将str字符串载入SpannableStringBuilder对象中。
//        SpannableString spannableString = new SpannableString(str);
//        spannableString.setSpan(new ForegroundColorSpan(color), 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView.setText(spannableString);
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        //设置字体颜色
        builder.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        textView.setText(builder);
    }

    /**
     * 改变指定位置的字体大小
     * @param textView
     * @param start
     * @param end
     */
    public static void changeSize(TextView textView,int start,int end,int textSize){
        if(textView == null){
            return;
        }
        String str = textView.getText().toString();
        if(start<0) {
            start = 0;
        }
        if(end>str.length()){
            end = str.length();
        }
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(str);
        stringBuilder.setSpan(new AbsoluteSizeSpan(textSize),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(stringBuilder);
    }

    /**
     * 添加下划线
     * @param textView
     */
    public static void addUnderLine(TextView textView){
        if(textView == null){
            return ;
        }
        textView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        textView.getPaint().setAntiAlias(true);//抗锯齿
    }

    /**
     * 添加中划线
     */

}
