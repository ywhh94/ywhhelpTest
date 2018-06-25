package com.leoman.helper.photo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.leoman.helper.HelperConstant;
import com.leoman.helper.R;

/**
 * 单图放大
 */
public class SimpleImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_simple_image);
        String url = getIntent().getStringExtra(HelperConstant.URL);
        int defaultSource = getIntent().getIntExtra(HelperConstant.RESOURCES, R.mipmap.loading);
        PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);

        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(defaultSource)
                .error(defaultSource)
                .thumbnail(0.1f)
                .into(photoView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
