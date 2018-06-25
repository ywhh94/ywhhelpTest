package com.leoman.helper.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by spurs on 2017/3/27.
 * 字符串样式
 */

public class StringUtil {

    /**
     *android:autoLink="web"
     * android:autoLink="phone"
     * android:autoLink="email"
     *android:autoLink="all"  网页，邮件，电话
     * 自定义模式（abc开头）
     * Linkify.addLinks(tv_multiHyperLink,Linkify.PHONE_NUMBERS);
     Pattern pattern = Pattern.compile("abc://\\S*");
     Linkify.addLinks(tv_multiHyperLink, pattern, "abc");
     */


    /**
     * 改变字体颜色
     *
     * @param textView
     * @param start    开始位置
     * @param end      结束位置
     * @param color    字体颜色
     */
    public static void changeTextColor(TextView textView, int start, int end, int color) {
        String str = textView.getText().toString();
        // 将str字符串载入SpannableStringBuilder对象中
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        // 设置字体颜色
        style.setSpan(new ForegroundColorSpan(color),
                start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(style);
    }

    /**
     * 改变字体大小
     *
     * @param textView
     * @param start    开始位置
     * @param end      结束位置
     * @param textSize 字体大小
     * @param Typeface 字体样式(normal传0--android.graphics.Typeface.NORMAL)
     */
    public static void changeTextSize(TextView textView, int start, int end, int textSize, int Typeface) {
        String str = textView.getText().toString();
        // 将str字符串载入SpannableStringBuilder对象中
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        // 设置字体
        style.setSpan(new AbsoluteSizeSpan(textSize
        ), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 字体样式
        style.setSpan(new StyleSpan(Typeface), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(style);
    }


    /**
     * 文本添加下划线
     *
     * @param textView
     * @param start
     * @param end
     * @param color
     */
    public static void setlineSpan(Context mContext, TextView textView, int start, int end, int color, Class<?> cls, String title) {
        String str = textView.getText().toString();
        SpannableString style = new SpannableString(str);
        if (cls == null) {
            /*添加下划线 没有超链接功能*/
            style.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            /*添加下划线 有超链接功能*/
            style.setSpan(new SpantoActivity(mContext, cls, "", title), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        style.setSpan(new ForegroundColorSpan(color),
                start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(style);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 高亮关键字
     *
     * @param textView
     * @param key      关键字
     */
    public static void HighlightTextColor(TextView textView, String key, String content) {
        String[] test = content.split(key);
        SpannableStringBuilder mSpannableStringBuilder = new SpannableStringBuilder(content);
        int length = 0;
        if (test.length == 0) {
            mSpannableStringBuilder.setSpan
                    (new ForegroundColorSpan(Color.RED), length, length + key.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        } else if (test.length == 1) {
            length = test[0].length();
            mSpannableStringBuilder.setSpan
                    (new ForegroundColorSpan(Color.RED), length, length + key.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        } else {
            for (int i = 0; i < test.length; i++) {
                if (i == test.length - 1) {
                    break;
                }
                if (i == 0) {
                    length = test[i].length();
                } else {
                    length += test[i].length() + key.length();
                }

                mSpannableStringBuilder.setSpan
                        (new ForegroundColorSpan(Color.RED), length, length + key.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
        }
        textView.setText(mSpannableStringBuilder);
    }

    /**
     * 拦截超链接
     *
     * @param tv
     */
    public static void interceptHyperLink(Context mContext, TextView tv, Class<?> cls, String title) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable spannable = (Spannable) tv.getText();
            URLSpan[] urlSpans = spannable.getSpans(0, end, URLSpan.class);
            if (urlSpans.length == 0) {
                return;
            }

            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            // 循环遍历并拦截 所有http://开头的链接
            for (URLSpan uri : urlSpans) {
                String url = uri.getURL();
                if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
                    SpantoActivity customUrlSpan = new SpantoActivity(mContext, cls, url, title);
                    spannableStringBuilder.setSpan(customUrlSpan, spannable.getSpanStart(uri),
                            spannable.getSpanEnd(uri), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            tv.setText(spannableStringBuilder);
        }
    }

    /**
     * 去除下划线
     *
     * @param tv
     */
    public static void removeHyperLinkUnderline(TextView tv) {
        //删除链接
//        tv.setAutoLinkMask(0);
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            Spannable spannable = (Spannable) tv.getText();
            UnderlineSpan noUnderlineSpan = new UnderlineSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                }
            };
            spannable.setSpan(noUnderlineSpan, 0, text.length(), Spanned.SPAN_MARK_MARK);
//            tv.setText(spannable);
        }
    }
}
