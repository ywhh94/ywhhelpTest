package com.leoman.helper;

import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import java.io.File;

/**
 * Created by Administrator on 2017/11/23.
 * Application
 */

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }


    public static File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/storage/sdcard0/Android/data/" + context.getPackageName()
                + "/cache/";
        return new File(cacheDir);
    }


}
