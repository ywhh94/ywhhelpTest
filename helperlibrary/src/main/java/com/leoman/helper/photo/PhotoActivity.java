package com.leoman.helper.photo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leoman.helper.BaseAcHelper;
import com.leoman.helper.HelperLibrary;
import com.leoman.helper.R;
import com.leoman.helper.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 多图选择
 */
public class PhotoActivity extends BaseAcHelper {

    private GridView gv;
    private PhotoAibum aibum;
    private PhotoAdappter adapter;
    private TextView tv_right, tv_num, btn_sure;
    private LinearLayout ll_back;
    private RelativeLayout rl_title;
    private ArrayList<PhotoItem> gl_arr = new ArrayList<PhotoItem>();
    private ArrayList<String> paths = new ArrayList<String>();
    private ArrayList<String> ids = new ArrayList<String>();
    private int maxNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoalbum_gridview);
        maxNum = getIntent().getIntExtra("maxNum", 1);
        aibum = (PhotoAibum) getIntent().getSerializableExtra("aibum");
        initView();
    }

    private void initView() {
        btn_sure = (TextView) findViewById(R.id.btn_sure);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_right = (TextView) findViewById(R.id.tv_right);
        gv = (GridView) findViewById(R.id.photo_gridview);
        adapter = new PhotoAdappter(this, aibum, null, maxNum, new PhotoAdappter.CheckListener() {
            @Override
            public void change(int position, boolean isSelect) {
                PhotoItem gridItem = aibum.getBitList().get(position);
                if (isSelect) {
                    paths.remove(gridItem.getPath());
                    ids.remove(gridItem.getPhotoID() + "");
                    gl_arr.remove(gridItem);
                } else {
                    if (paths.size() < maxNum) {
                        ids.add(gridItem.getPhotoID() + "");
                        paths.add(gridItem.getPath());
                        gl_arr.add(gridItem);
                    } else {
                        ToastUtil.showToast(PhotoActivity.this, "可不要贪心哟,最多选" + maxNum + "张！", Toast.LENGTH_SHORT);
                    }
                }
                tv_num.setText(String.valueOf(paths.size()));
            }
        });
        gv.setAdapter(adapter);

        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);

        rl_title.setBackgroundResource(HelperLibrary.getInstance().getColor());
//        btn_sure.setBackgroundResource(HelperLibrary.getInstance().getColor());

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_sure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("path", (Serializable) paths);
                setResult(20, getIntent());
                finish();
            }
        });
        tv_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                paths.clear();
                getIntent().putExtra("path", (Serializable) paths);
                setResult(20, getIntent());
                finish();
            }
        });
    }

}
