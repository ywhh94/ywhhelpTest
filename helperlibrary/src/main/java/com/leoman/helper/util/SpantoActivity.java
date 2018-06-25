package com.leoman.helper.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.leoman.helper.R;


/**
 * Created by zhaoxin on 2015/8/24.
 * Span自定义超链接
 */
public class SpantoActivity extends ClickableSpan {

    Context mContext;
    Class<?> cls;
    String url, title;

    public SpantoActivity(Context mContext, Class<?> cls, String url, String title) {
        this.mContext = mContext;
        this.cls = cls;
        this.url = url;
        this.title = title;
    }

    @Override
    public void onClick(View widget) {
        //Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com")

        Intent intent = new Intent(mContext, cls);
        if (!TextUtils.isEmpty(url))
            intent.putExtra("url", url);
        if (!TextUtils.isEmpty(title))
            intent.putExtra("title", title);
        mContext.startActivity(intent);

    }

}
