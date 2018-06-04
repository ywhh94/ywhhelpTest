package wh.ywh.util;

import android.os.Environment;

/**
 * Created by yangwenhao on 2018-05-21.
 * 文件夹工具类
 */

public class FileUtil {

    /**
     * sdCard是否存在
     * @return
     */
    public static boolean isSdCardExist(){
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    /**
     * sdCard路径
     * @return
     */
    public static String getSdCardPath(){
        if(!isSdCardExist()) return null;
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 当前应用包的路径
     * @param context
     * @return
     */
}
