package com.leoman.helper.util;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator on 2016/9/2.
 * 解压压缩文件
 */
public class ZipUtil {

    /**
     * 解压缩文件到指定的目录.
     *
     * @param unZipfileName 需要解压缩的文件
     * @param mDestPath     解压缩后存放的路径
     */
    public static void unZip(String unZipfileName, String mDestPath) {
        if (!mDestPath.endsWith("/")) {
            mDestPath = mDestPath + "/";
        }
        FileOutputStream fileOut = null;
        ZipInputStream zipIn = null;
        ZipEntry zipEntry = null;
        File file = null;
        int readedBytes = 0;
        byte buf[] = new byte[4096];
        try {
            zipIn = new ZipInputStream(new BufferedInputStream(new FileInputStream(unZipfileName)));
            while ((zipEntry = zipIn.getNextEntry()) != null) {
                file = new File(mDestPath + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    file.mkdirs();
                } else {
                    // 如果指定文件的目录不存在,则创建之.
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    fileOut = new FileOutputStream(file);
                    while ((readedBytes = zipIn.read(buf)) > 0) {
                        fileOut.write(buf, 0, readedBytes);
                    }
                    fileOut.close();
                }
                zipIn.closeEntry();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 测试获取文件路径
     *
     * @param files SearchFile(YiCiYuanApplication.getInstance().getExternalCacheDir(MainActivity.this).listFiles());
     */
    private void SearchFile(File[] files) {
        for (File file : files
                ) {
            if (file.isDirectory())//若为目录则递归查找
            {
                SearchFile(file.listFiles());
            } else if (file.isFile()) {
                String path = file.getPath();
                Log.e("文件路径==", path);
            }
        }
    }


}
