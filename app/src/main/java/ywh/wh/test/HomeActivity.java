package ywh.wh.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import wh.ywh.base.BaseHelpActivity;
import wh.ywh.photo.PhotoUtil;
import wh.ywh.util.LogUtil;
import ywh.wh.test.intercep.InterceptTestActivity;
import ywh.wh.test.recycler.RecyclerActivity;

/**
 * Created by Administrator on 2018-07-05.
 */

public class HomeActivity extends BaseHelpActivity {

    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.textView5)
    TextView textView5;

    @Bind(R.id.relativeLayout)
    RelativeLayout relativeLayout;

    private static int s;
    private PhotoUtil photoUtil;

    @Override
    protected void initWindows() {
        super.initWindows();
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        return super.initArgs(bundle);
    }

    @Override
    protected int setLayout() {
        return R.layout.ac_home;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        s = 1;
        LogUtil.e("s:" + s);
        s = 2;
        LogUtil.e("s:" + s);
    }

    @OnClick({R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4,R.id.textView5})
    public void onClicked(View v) {
        switch (v.getId()) {
            case R.id.textView1:
//                jump(HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", "1002");
                jump(Home2Activity.class, 1, bundle);
                break;
            case R.id.textView2:
                jump(ywh.wh.test.fragmentandactivity.OneActivity.class);
                break;
            case R.id.textView3:
                jump(RecyclerActivity.class);
                break;
            case R.id.textView4:
                photoUtil = new PhotoUtil(this);
                photoUtil.setImageNum(3).show();
                break;
            case R.id.textView5:
                jump(InterceptTestActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PhotoUtil.PHOTO_PICTURE:
                if(data!=null){
                    Uri uri = data.getData();
                    if(uri!=null){
                        LogUtil.d("URI:"+uri.toString());
                    }
                }
                break;
            case PhotoUtil.PHOTO_CAMERA:

                break;

        }
    }
}
