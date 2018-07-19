package ywh.wh.test.recycler;

import android.view.View;

import butterknife.OnClick;
import wh.ywh.base.BaseHelpActivity;
import ywh.wh.test.R;

/**
 * Created by Administrator on 2018-07-18.
 */

public class RecyclerActivity extends BaseHelpActivity  {

    @Override
    protected int setLayout() {
        return R.layout.ac_recycler;
    }

    @OnClick({R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5})
    public void onClicked(View v){
        switch (v.getId()){
            case R.id.textView1:
                jump(RecyclerListActivity.class,1,"type","l");
                break;
            case R.id.textView2:
                jump(RecyclerListActivity.class,1,"type","g");
                break;
            case R.id.textView3:
                jump(RecyclerStaggeredGridActivity.class);
                break;
            case R.id.textView4:
                jump(RecyclerAnimatorActivity.class);
                break;
            case R.id.textView5:
                jump(TestSwipeRecyclerViewActivity.class);
                break;
        }
    }

}
