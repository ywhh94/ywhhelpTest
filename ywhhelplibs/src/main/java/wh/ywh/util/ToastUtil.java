package wh.ywh.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by yangwenhao on 2018-05-10.
 * 弹窗工具类
 */

public class ToastUtil {
    private static Toast toast;

    public static void toastLong(Context context,String str){
        toast(context,str,Toast.LENGTH_LONG,0);
    }

    public static void toastLong(Context context,String str,int gravity){
        toast(context,str,Toast.LENGTH_LONG,gravity);
    }

    public static void toastShort(Context context,String str){
        toast(context,str,Toast.LENGTH_SHORT,0);
    }

    public static void toastShort(Context context,String str,int gravity){
        toast(context,str,Toast.LENGTH_SHORT,gravity);
    }

    /**
     * 多次调用，只弹出一次
     * @param context
     * @param str           //文本
     * @param duration      //弹窗时长
     * @param gravity       //gravity
     */
    public static void toast(Context context,String str,int duration,int gravity){
        if(context==null){
            return ;
        }
        if(toast == null){
            toast = Toast.makeText(context, str, duration);
        }else{
            toast.setText(str);
            toast.setDuration(duration);
            if(gravity == Gravity.CENTER){
                toast.setGravity(Gravity.CENTER,0,0);
            }else if(gravity == Gravity.TOP){
                toast.setGravity(Gravity.TOP,0,0);
            }else if(gravity == Gravity.BOTTOM){
                toast.setGravity(Gravity.BOTTOM,0,0);
            }else{}
        }
        toast.show();
    }
}
