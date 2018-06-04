package wh.ywh.util;

import android.app.Activity;
import android.content.SharedPreferences;

import wh.ywh.App;

/**
 * Created by yangwenhao on 2018-05-10.
 * SharedPreferences工具类
 */

public class SpUtil {
    private static SpUtil instance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SpUtil(){
        if(App.getInstance() == null){
            LogUtil.i("空");
        }
        else{
            sharedPreferences = App.getInstance().getSharedPreferences("ywhSpUtil", Activity.MODE_PRIVATE);
        }
        editor = sharedPreferences.edit();
    }
    //双重检查锁定
    public static SpUtil getInstance() {
        if(instance == null){
            synchronized (SpUtil.class){
                if(instance == null){
                    instance = new SpUtil();
                }
            }
        }
        return instance;
    }

    public void putString(String key,String value){
        editor.putString(key, value);
        editor.apply(); //异步，1提交到内存，2开启线程提交到硬盘
//        editor.commit();//直接提交到硬盘
    }

    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }

    public void putBoolean(String key,Boolean value){
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }

    public void putInt(String key,int value){
        editor.putInt(key,value);
        editor.apply();
    }
    public int getInt(String key){
        return sharedPreferences.getInt(key,-1);
    }

    public void putLong(String key,long value){
        editor.putLong(key,value);
        editor.apply();
    }
    public long getLong(String key){
        return sharedPreferences.getLong(key,-1l);
    }

    public void putFloat(String key,float value){
        editor.putFloat(key,value);
        editor.apply();
    }
    public float getFloat(String key){
        return sharedPreferences.getFloat(key,-1f);
    }
    /**
     *移除指定key对应的值
     */
    public void remove(String key){
        editor.remove(key);
        editor.apply();
    }

    /**
     * 移除所有
     */
    public void clear(){
        editor.clear();
        editor.apply();
    }

    /**
     * 是否存在key
     * @param key
     * @return
     */
    public boolean contains(String key){
        return sharedPreferences.contains(key);
    }

}
