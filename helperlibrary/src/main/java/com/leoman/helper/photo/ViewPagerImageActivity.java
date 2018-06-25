package com.leoman.helper.photo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.HackyViewPager;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leoman.helper.HelperConstant;
import com.leoman.helper.R;
import com.leoman.helper.bean.ImageBean;
import com.leoman.helper.util.ToastUtil;
import com.spurs.retrofit.APIClient;
import com.spurs.retrofit.APIService;
import com.spurs.retrofit.ProgressListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 多图大图
 */
public class ViewPagerImageActivity extends Activity {

    private TextView tv_pagesize, tv_save;
    private int index;
    private int defaultSource;
    private List<ImageBean> list = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();//已下载图片
    /*记录图片是否存在于SD卡*/
    public static final String SDPIC = "sdpic";
    public static String SP_NAME = "PICDOWNLOAD";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_view_pager_image);
        index = getIntent().getIntExtra(HelperConstant.INDEX, 0);//当前选中位置
        list = (List<ImageBean>) getIntent().getSerializableExtra(HelperConstant.IMAGES);//图片数据
        defaultSource = getIntent().getIntExtra(HelperConstant.RESOURCES, R.mipmap.loading);//默认图片
        initView();

    }

    private void initView() {
        tv_pagesize = (TextView) findViewById(R.id.tv_pagesize);//页码
        tv_save = (TextView) findViewById(R.id.tv_save);//保存
        ViewPager viewPager = (HackyViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new SamplePagerAdapter());
        if (index > 0) {
            tv_pagesize.setText((index + 1) + "/" + list.size());
            viewPager.setCurrentItem(index);
        } else {
            tv_pagesize.setText(1 + "/" + list.size());
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_pagesize.setText((position + 1) + "/" + list.size());
                isDowload(position);
                url = list.get(position).url;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (!TextUtils.isEmpty(getString(SDPIC)))
            imageList = new Gson().fromJson(getString(SDPIC), new TypeToken<List<String>>() {
            }.getType());

        isDowload(index);
        url = list.get(index).url;

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down();
            }
        });
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());

            Glide.with(container.getContext())
                    .load(list.get(position).url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(defaultSource)
                    .error(defaultSource)
                    .thumbnail(0.1f)
                    .dontAnimate()
                    .into(photoView);

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewPagerImageActivity.this.finish();
                }
            });

            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    private void isDowload(int position) {

        tv_save.setEnabled(true);
        tv_save.setText("保存图片");
        for (int i = 0; i < imageList.size(); i++) {
            if (imageList.get(i).equals(list.get(position).url)) {
                tv_save.setEnabled(false);
                tv_save.setText("已保存");
                break;
            }
        }
    }

    private void down() {
        tv_save.setEnabled(false);
        tv_save.setText("正在保存...");
        APIService apiService = new APIClient(new ProgressListener() {
            @Override
            public void onProgress(final long progress, final long total, final boolean done) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        long pro = 0;
                        if (total > 0)
                            pro = progress * 100 / total;
                        tv_save.setText(String.valueOf(pro) + "%");
                    }
                });

            }
        }).getAPIService();

        Call<ResponseBody> call = apiService.GetAPI(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    InputStream is = response.body().byteStream();
                    File cacheDir = ViewPagerImageActivity.this.getExternalCacheDir();
                    if (cacheDir == null) {
                        ToastUtil.showToast(ViewPagerImageActivity.this, "保存失败", Toast.LENGTH_SHORT);
                        return;
                    }
                    File folder = new File(cacheDir, "download");
                    //创建新的完整文件路径
                    File file = new File(folder.getAbsolutePath() + System.currentTimeMillis() + ".jpg");

                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    bis.close();
                    is.close();

                    tv_save.setEnabled(false);
                    tv_save.setText("已保存");
                    imageList.add(url);
                    putString(SDPIC, new Gson().toJson(imageList));

                    /*保存后要扫描一下文件，及时更新到系统目录（一定要加绝对路径，这样才能更新）*/
                    MediaScannerConnection.scanFile(ViewPagerImageActivity.this,
                            new String[]{file.getAbsolutePath()}, null, null);

                } catch (IOException e) {
                    e.printStackTrace();
                    tv_save.setEnabled(true);
                    tv_save.setText("保存图片");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ToastUtil.showToast(ViewPagerImageActivity.this, "保存失败", Toast.LENGTH_SHORT);
                tv_save.setEnabled(true);
                tv_save.setText("保存图片");
                call.cancel();
            }
        });
    }

    public void putString(String key, String value) {
        SharedPreferences mSharedPreferences = getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key) {
        SharedPreferences mSharedPreferences = getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
        return mSharedPreferences.getString(key, "");
    }
}
