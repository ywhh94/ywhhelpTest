package com.leoman.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.leoman.helper.util.SystemBarTintManager;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by spurs on 2017/3/23.
 * Activity基类
 */

public class BaseAcHelper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setBarColor(boolean isFullscreen, int color) {
        if (!isFullscreen && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);//通知栏所需颜色
        }
    }
    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void startActivity(Intent intent) {
        // TODO Auto-generated method stub
        super.startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        // TODO Auto-generated method stub
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
//        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top
                    && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public void hiddenSoftInput(EditText v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    // 返回时关闭软键盘
    public void CloseKeyboard() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /*自动弹出软键盘*/
    public void showKeyboard(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
//        inputManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    public void gotoOtherActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    public void gotoFlagOtherActivity(Class<?> cls, int flag) {
        Intent intent = new Intent();
        intent.putExtra("flag", flag);
        intent.setClass(this, cls);
        startActivity(intent);
    }

    public void gotoFlagIdOtherActivity(Class<?> cls, String id, int flag) {
        Intent intent = new Intent();
        intent.putExtra("flag", flag);
        intent.putExtra("id", id);
        intent.setClass(this, cls);
        startActivity(intent);
    }

    public void gotoIdOtherActivity(Class<?> cls, String id) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.setClass(this, cls);
        startActivity(intent);
    }

    public void gotoFlagTitleActivity(Class<?> cls, String title, int flag) {
        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.putExtra("flag", flag);
        intent.setClass(this, cls);
        startActivity(intent);
    }

    public void gotoFlagDataOtherActivity(Class<?> cls, Object data, int flag) {
        Intent intent = new Intent();
        intent.putExtra("flag", flag);
        intent.putExtra("data", (Serializable) data);
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /**
     * listview
     *
     * @param recyclerView
     * @param orientation  方向 (LinearLayoutManager.HORIZONTAL)
     */
    public void setLayoutManager(RecyclerView recyclerView, int orientation, boolean isScroll) {
        recyclerView.setHasFixedSize(true);
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager;
        if (isScroll) {
            layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(orientation);
        } else
            layoutManager = new LinearLayoutManager(this,
                    orientation, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }

                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * gridview
     *
     * @param recyclerView
     * @param spanCount    列数
     */
    public void setGridLayoutManager(RecyclerView recyclerView, int spanCount, boolean isScroll) {
        recyclerView.setHasFixedSize(true);
        // 创建一个线性布局管理器
        GridLayoutManager layoutManager;
        if (isScroll)
            layoutManager = new GridLayoutManager(this, spanCount);
        else
            layoutManager = new GridLayoutManager(this, spanCount,
                    LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        recyclerView.setLayoutManager(layoutManager);

    }


    public Gson gson = new GsonBuilder().
            registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

                @Override
                public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                    if (src == src.longValue())
                        return new JsonPrimitive(src.longValue());
                    return new JsonPrimitive(src);
                }
            })
            .disableHtmlEscaping()
            .create();


    // 解析assets文件
    public String getFromAssets(Context context, String fileName) {
        String result = "";
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            // 获取文件的字节数
            int lenght = in.available();
            // 创建byte数组
            byte[] buffer = new byte[lenght];
            // 将文件中的数据读到byte数组中
            in.read(buffer);
            result = new String(buffer, "UTF-8");

        } catch (Exception e) {
            Log.e("Exception====", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
