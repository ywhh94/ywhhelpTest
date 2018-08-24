package ywh.wh.test.dagger;

import javax.inject.Inject;

import wh.ywh.base.BaseHelpActivity;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-08-16.
 */
public class DaggerActivity extends BaseHelpActivity{

    @Inject
    Person person;


    @Override
    protected int setLayout() {
        return R.layout.ac_dagger;
    }

    @Override
    protected void initData() {

        super.initData();

    }
}
