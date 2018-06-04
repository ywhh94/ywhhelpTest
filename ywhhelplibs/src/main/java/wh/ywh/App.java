package wh.ywh;

import android.app.Application;
import android.content.Context;

import java.io.File;

import wh.ywh.util.FileUtil;
import wh.ywh.util.LogUtil;

/**
 * Created by Administrator on 2018-05-28.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        createPackPath();
    }

    public static App getInstance(){
        return instance;
    }

    private void createPackPath() {
        LogUtil.d("createPackPath");
        File file = new File(getPackPath(this));
        if(!file.exists()) {
            LogUtil.e("创建文件夹"+file.mkdirs());
        }
    }

    /**
     *
     * @param context
     * @return
     */
    public static String getPackPath(Context context){
        if(!FileUtil.isSdCardExist()) return null;
        return FileUtil.getSdCardPath()+"/Android/data/" + context.getPackageName()
                + "/cache/";
    }
}
