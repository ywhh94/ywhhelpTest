package ywh.wh.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wh.ywh.util.LogUtil;
import wh.ywh.util.ToastUtil;
import ywh.wh.test.database.BeanDataInfo;
import ywh.wh.test.database.MySQLiteOpenHelper;
import ywh.wh.test.database.StuBean;

/**
 * Created by Administrator on 2018-06-27.
 */
public class DataBaseActivity extends AppCompatActivity{

    @Bind(R.id.create_database)
    TextView create;
    @Bind(R.id.add)
    TextView add;
    @Bind(R.id.del)
    TextView del;
    @Bind(R.id.up)
    TextView up;
    @Bind(R.id.query)
    TextView query;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_database);
        ButterKnife.bind(this);
        MySQLiteOpenHelper openhelper = new MySQLiteOpenHelper(this,"stu_table",null,1);
        db = openhelper.getWritableDatabase();
    }

    @OnClick({R.id.create_database,R.id.add,R.id.del,R.id.up,R.id.query})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.create_database:
                createDataBase();
                break;
            case R.id.add:
                add();
                break;
            case R.id.del:
                del();
                break;
            case R.id.up:
                up();
                break;
            case R.id.query:
                query();
                break;
        }
    }

    private long add() {
        ContentValues cv = new ContentValues();
        cv.put("sname", "刘能");
        cv.put("sage", 15);
        cv.put("ssex", "男");
        //返回值是该行的主键，如果出错返回-1
        long i = db.insert("stu_table", null, cv);
        if(i!=-1){
            ToastUtil.toastShort(this,"增加成功");
        }
        return i;
    }
    private int del() {
        int i = db.delete("stu_table", "sname = ?", new String[]{"刘能"});
        return i;//删除的行数
    }
    private long up() {
        ContentValues cv = new ContentValues();
        cv.put("sname", "刘能");
        cv.put("sage", 15);
        cv.put("ssex", "女");
        int i = db.update("stu_table", cv, "name = ?", new String[]{"刘能"});
        return i;
    }
    private void query() {
        Cursor resultCursor = db.query("stu_table", new String[]{"_id", "sname", "sage", "ssex"}, null, null, null, null, null, null);
        if(resultCursor!=null){
            List<BeanDataInfo> list = null;
            try {
                StuBean bean = new StuBean();
                list = new ArrayList<>();
                for (Field field : bean.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    BeanDataInfo beanData = new BeanDataInfo(field.getGenericType().toString(),field.getName());
                    String fieldType = field.getGenericType().toString();
                    LogUtil.e(field.getName() + ":" + field.get(bean)+":"+field.getGenericType().toString()+":"+field.getClass().getMethods().toString());
                    list.add(beanData);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            while (resultCursor.moveToNext()) {
                if(list!=null&&list.size()>0){
                    StuBean stubean = new StuBean();
                    for(BeanDataInfo bean:list){
                        if("class java.lang.String".equals(bean.getType())){

                        }else if("class java.lang.Integer".equals(bean.getType())){

                        }
                    }
                }
                Integer id = resultCursor.getInt(resultCursor.getColumnIndex("_id"));
                String name = resultCursor.getString(resultCursor.getColumnIndex("sname"));
                Integer age = resultCursor.getInt(resultCursor.getColumnIndex("sage"));
                String sex = resultCursor.getString(resultCursor.getColumnIndex("ssex"));
                LogUtil.e("id:"+id+",name:"+name+",age:"+age+",sex:"+sex);
            }
        }
    }

    private void createDataBase() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
