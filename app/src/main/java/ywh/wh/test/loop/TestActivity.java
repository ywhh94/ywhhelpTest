package ywh.wh.test.loop;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import wh.ywh.util.LogUtil;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-06-12.
 */

public class TestActivity extends Activity {
    private LinearLayout ac_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_test);
        ac_test = (LinearLayout) findViewById(R.id.ac_test);
        LogUtil.e("isChild:"+this.isChild());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        DisplayMetrics dm = new DisplayMetrics();
//
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ac_test.getLayoutParams();
//        lp.gravity = Gravity.CENTER;
//        lp.height = (int) (dm.heightPixels * 0.5);
//        lp.width = (int) (dm.widthPixels * 0.5);
//        ac_test.setLayoutParams(lp);

//        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        WindowManager.LayoutParams p = getWindow().getAttributes();
//        p.height = (int) (dm.heightPixels * 0.5);
//        p.width = (int) (dm.widthPixels * 0.5);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

}

