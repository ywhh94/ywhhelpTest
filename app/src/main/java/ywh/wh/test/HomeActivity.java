package ywh.wh.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import wh.ywh.base.BaseHelpActivity;
import ywh.wh.test.testrecycler.OneActivity;

/**
 * Created by Administrator on 2018-07-05.
 */

public class HomeActivity extends BaseHelpActivity {

    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.textView2)
    TextView textView2;


    @Override
    protected void initWindows() {
        super.initWindows();
    }

    @Override
    protected boolean  initArgs(Bundle bundle) {
        return super.initArgs(bundle);
    }

    @Override
    protected int setLayout() {
        return R.layout.ac_home;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

    }

    @OnClick({R.id.textView1,R.id.textView2})
    public void onClicked(View v){
        switch (v.getId()){
            case R.id.textView1:
                Bundle bundle = new Bundle();
                bundle.putString("id","1002");
//                startActivity(new Intent(this,Home2Activity.class).putExtra("bun",bundle));
//                jump(Home2Activity.class,1,"wo","wo a");
//                jump(Home2Activity.class,1,new Bean(19));
                jump(Home2Activity.class,1,bundle);
                break;
            case R.id.textView2:
                jump(OneActivity.class);
                break;
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.textView1:

                break;
            case R.id.textView2:

                break;
        }
    }
}
