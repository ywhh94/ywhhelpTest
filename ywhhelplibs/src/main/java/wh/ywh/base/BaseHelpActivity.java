package wh.ywh.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.io.Serializable;
import butterknife.ButterKnife;
import wh.ywh.Constanct;
import wh.ywh.util.LogUtil;

/**
 *
 * Created by Administrator on 2017-07-05.
 */

public abstract class BaseHelpActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();
        Bundle bundle = getIntent().getExtras();
        if(initArgs(bundle)){
            if(setLayout() == 0 ){
                throw new NullPointerException("请设置布局id");
            }else{
                setContentView(setLayout());
                ButterKnife.bind(this);
                initWidget();
                initData();
            }
        }
    }

    /**
     * 加载布局之前的操作
     */
    protected void initWindows() {
        LogUtil.e("base---initWindows");
    }

    /**
     * 获取参数
     * @param bundle
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 布局文件
     * @return
     */
    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    protected void initWidget() {
    }

    protected void initData() {
    }
    
    @Override
    public void onClick(View v) {
    }

    /**
     * 无参跳转
     * @param clazz
     */
    protected void jump(Class<?> clazz){
        startActivity(new Intent(this,clazz));
    }

    /**
     * 一个参数跳转
     * @param clazz
     * @param fromClassId     标注来源
     * @param key
     * @param value
     */
    protected void jump(Class<?> clazz,Integer fromClassId,String key,String value){
        Intent intent = new Intent(this,clazz);
        intent.putExtra(Constanct.transmit_fromClassId,fromClassId);
        intent.putExtra(key,value);
        startActivity(intent);
    }

    /**
     * 传递序列化实体类参数跳转
     * @param clazz
     * @param fromClassId
     * @param serializable
     */
    protected void jump(Class<?> clazz, Integer fromClassId, Serializable serializable){
        Intent intent = new Intent(this,clazz);
        intent.putExtra(Constanct.transmit_fromClassId,fromClassId);
        intent.putExtra(Constanct.transmit_Serializable,serializable);
        startActivity(intent);
    }

    protected void jump(Class<?> clazz, Integer fromClassId, Bundle bundle){
        Intent intent = new Intent(this,clazz);
        intent.putExtra(Constanct.transmit_fromClassId,fromClassId);
        intent.putExtra(Constanct.transmit_Bundle,bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
