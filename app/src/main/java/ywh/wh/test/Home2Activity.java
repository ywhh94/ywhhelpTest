package ywh.wh.test;

import android.os.Bundle;

import wh.ywh.Constanct;
import wh.ywh.base.BaseHelpActivity;
import wh.ywh.util.LogUtil;

/**
 * Created by Administrator on 2018-07-05.
 */

public class Home2Activity extends BaseHelpActivity {

    @Override
    protected void initWindows() {
        super.initWindows();
        LogUtil.e("home2--initWindows");
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        if(bundle!=null){
            Bundle bundle2 = bundle.getBundle(Constanct.transmit_Bundle);
            if(bundle2!=null){
                String id = bundle2.getString("id");
                LogUtil.e("id:::"+id);
            }
        }
        return super.initArgs(bundle);
    }

    @Override
    protected int setLayout() {
        return R.layout.ac_home;
    }
}
