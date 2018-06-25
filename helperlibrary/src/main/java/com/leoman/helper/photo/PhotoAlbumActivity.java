package com.leoman.helper.photo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.leoman.helper.BaseAcHelper;
import com.leoman.helper.HelperConstant;
import com.leoman.helper.HelperLibrary;
import com.leoman.helper.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/******************************************
 * 类描述： 相册管理类 类名称：PhotoAlbumActivity
 *
 * @version: 1.0
 *****************************************/
public class PhotoAlbumActivity extends BaseAcHelper {

    private RelativeLayout rl_title;
    private LinearLayout ll_back;
    private ListView aibumGV;
    private List<PhotoAibum> aibumList;
    // 设置获取图片的字段信息
    private static final String[] STORE_IMAGES = {MediaStore.Images.Media.DISPLAY_NAME, // 显示的名�?
            MediaStore.Images.Media.DATA, MediaStore.Images.Media.LONGITUDE, // 经度
            MediaStore.Images.Media._ID, // id
            MediaStore.Images.Media.BUCKET_ID, // dir id 目录
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME // dir name 目录名字
    };
    private int maxNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoalbum);
        maxNum = getIntent().getIntExtra("maxNum", 1);
        initTitle();
        aibumGV = (ListView) findViewById(R.id.album_gridview);
        aibumList = getPhotoAlbum();
        aibumGV.setAdapter(new PhotoAibumAdapter(aibumList, this));
        aibumGV.setOnItemClickListener(aibumClickListener);
    }

    private void initTitle() {
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);

        rl_title.setBackgroundResource(HelperLibrary.getInstance().getColor());
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 相册点击事件
     */
    OnItemClickListener aibumClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(PhotoAlbumActivity.this, PhotoActivity.class);
            intent.putExtra("aibum", (Serializable) aibumList.get(position));
            intent.putExtra("maxNum", maxNum);
            startActivityForResult(intent, 20);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            if (resultCode == 20) {
                @SuppressWarnings("unchecked")
                ArrayList<String> paths = (ArrayList<String>) data.getExtras().getSerializable(
                        "path");
                Intent intent = new Intent();
                intent.putExtra(HelperConstant.PATHS, paths);
                setResult(Activity.RESULT_OK, intent);
            }
            onBackPressed();
        }
    }

    /**
     * 方法描述：按相册获取图片信息
     */
    private List<PhotoAibum> getPhotoAlbum() {
        List<PhotoAibum> aibumList = new ArrayList<PhotoAibum>();
        Cursor cursor = MediaStore.Images.Media.query(getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES);
        Map<String, PhotoAibum> countMap = new HashMap<String, PhotoAibum>();
        PhotoAibum pa = null;
        while (cursor.moveToNext()) {
            // String path=cursor.getString(1);
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
            String id = cursor.getString(3);
            String dir_id = cursor.getString(4);
            String dir = cursor.getString(5);
//            Log.e("info", "id===" + id + "==dir_id==" + dir_id + "==dir==" + dir + "==path=" + path);
            if (!countMap.containsKey(dir_id)) {
                pa = new PhotoAibum();
                pa.setName(dir);
                pa.setBitmap(Integer.parseInt(id));
                pa.setCount("1");
                pa.getBitList().add(new PhotoItem(Integer.valueOf(id), path));
                countMap.put(dir_id, pa);
                pa.setPath(path);
            } else {
                pa = countMap.get(dir_id);
                pa.setCount(String.valueOf(Integer.parseInt(pa.getCount()) + 1));
                pa.getBitList().add(new PhotoItem(Integer.valueOf(id), path));
                pa.setPath(path);
            }
        }
        cursor.close();
        Iterable<String> it = countMap.keySet();
        for (String key : it) {
            aibumList.add(countMap.get(key));
        }
        return aibumList;
    }
}
