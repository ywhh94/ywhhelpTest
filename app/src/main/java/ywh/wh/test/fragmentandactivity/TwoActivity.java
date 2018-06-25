package ywh.wh.test.fragmentandactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import butterknife.ButterKnife;
import wh.ywh.util.LogUtil;
import ywh.wh.test.R;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        LogUtil.e("two -- onCreate");
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e("two -- onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("two -- onResume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e("two -- onRestart");

    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e("two -- onPause");

    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e("two -- onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        LogUtil.e("two -- onDestroy");

    }

    @Override
    public void onAttachFragment(android.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        LogUtil.e("two -- onAttachFragment");

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.e("two -- onUserLeaveHint");

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        LogUtil.e("two -- onCreateView");
        return super.onCreateView(name, context, attrs);

    }
}
