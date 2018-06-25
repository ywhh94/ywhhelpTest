package wh.ywh.util;

import android.app.Dialog;

/**
 * 弹窗
 * Created by Administrator on 2018-05-28.
 */

public class DialogUtil {
    private static DialogUtil instance;
    private static Dialog alertDialog;

    public static DialogUtil getInstance(){
        if( instance == null ){
            instance = new DialogUtil();
        }
        return instance;
    }
}
