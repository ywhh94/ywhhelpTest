package wh.ywh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2018-06-27.
 */

public class DataBaseUtil {

    private static DataBaseUtil instance;
    public  static DataBaseUtil getInstance(){
        if(instance == null){
            instance = new DataBaseUtil();
        }
        return instance;
    }

    /**
     * 获取可以读写的SQLiteDatabase对象
     * @param context
     * @return
     */
    public SQLiteDatabase getSQLiteDataBase (Context context){
        ComSQLiteOpenHelper dbHelper = new ComSQLiteOpenHelper(context,"stu_db",null,1);
        //得到一个可读的SQLiteDatabase对象
        SQLiteDatabase db =dbHelper.getReadableDatabase();
        return db;
    }
}
