package ywh.wh.test.java;

import wh.ywh.base.BaseHelpActivity;
import wh.ywh.util.LogUtil;

/**
 * Created by Administrator on 2018-08-23.
 */

public class TestJavaActivity extends BaseHelpActivity{
    private static String ja = "1";
    @Override
    protected int setLayout() {
        return 0;
    }

    @Override
    protected void initData() {
        super.initData();
        ja = "2";
        LogUtil.d("ja:"+ja);
    }

    static void get(){
        LogUtil.d("static -- get");
    }

    {
        int  a = 0;
        LogUtil.d("静态代码块");
    }
}
