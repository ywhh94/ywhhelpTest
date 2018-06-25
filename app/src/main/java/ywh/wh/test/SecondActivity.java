package ywh.wh.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import wh.ywh.util.LogUtil;
import wh.ywh.util.SpUtil;
import wh.ywh.util.ToastUtil;

//测试ywhhelplibs
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_choose_photo);
        ToastUtil.toastLong(this,"SecondActivitylong1");
        ToastUtil.toastShort(this,"SecondActivitylong2");
        ToastUtil.toastLong(this,"SecondActivitylong3");

        Log.i("Second--getClass:",""+ SpUtil.getInstance().hashCode());

        if(getIntent() != null){
            LogUtil.d(getIntent().getData());
            LogUtil.d(getIntent().getStringExtra("ywh"));
            LogUtil.d(getIntent().getExtras().get("ywh"));
            LogUtil.d(getIntent().getExtras().get("ywh2"));
            LogUtil.d(getIntent().getExtras().get("ywh33"));
            LogUtil.d(getIntent().getExtras().getSerializable("bean"));
            LogUtil.d(getIntent().getSerializableExtra("bean"));
//            setResult(RESULT_OK,getIntent());
            finish();
        }
    }
}
