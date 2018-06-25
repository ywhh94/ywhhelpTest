package ywh.wh.test.fragmentandactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wh.ywh.util.LogUtil;
import ywh.wh.test.R;

public class OneActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one2);

        LogUtil.e("one -- onCreate");

        ButterKnife.bind(this);
        initViewPager();
        initTabLayout();

    }

    private void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new F1Fragment());
        fragments.add(new F2Fragment());
        fragments.add(new F3Fragment());
//        fragments.add(new F4Fragment());

        MyFragmentPagerAdapter myPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);

        viewPager.setOffscreenPageLimit(1); //预加载 数量 ，<=1时默认加载一个,
        viewPager.setAdapter(myPagerAdapter);

    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("one"));
        tabLayout.addTab(tabLayout.newTab().setText("two"));
        tabLayout.addTab(tabLayout.newTab().setText("three"));
//        tabLayout.addTab(tabLayout.newTab().setText("four"));
//        tabLayout.setupWithViewPager(viewPager,true);                      // 使用这个显示one，two，three不出来
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e("one -- onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e("one -- onResume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e("one -- onRestart");

    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e("one -- onPause");

    }


    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e("one -- onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        LogUtil.e("one -- onDestroy");

    }

    @Override
    public void onAttachFragment(android.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        LogUtil.e("one -- onAttachFragment");

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.e("one -- onUserLeaveHint");

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        LogUtil.e("one -- onCreateView");
        return super.onCreateView(name, context, attrs);

    }

}
