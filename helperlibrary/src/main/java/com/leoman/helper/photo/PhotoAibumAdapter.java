package com.leoman.helper.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.leoman.helper.R;
import com.leoman.helper.util.ViewHolder;

import java.util.List;


public class PhotoAibumAdapter extends BaseAdapter {

    private List<PhotoAibum> aibumList;
    private Context context;


    public PhotoAibumAdapter(List<PhotoAibum> list, Context context) {
        this.aibumList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return aibumList.size();
    }

    @Override
    public Object getItem(int position) {
        return aibumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.photoalbum_item, null);
        ImageView iv_pic = ViewHolder.get(convertView, R.id.photoalbum_item_image);
        TextView tv = ViewHolder.get(convertView, R.id.photoalbum_item_name);
        PhotoAibum photoAibum = aibumList.get(position);
        /** 通过ID 获取缩略图 */
        Glide.with(context).load("file://"
                + photoAibum.getPath()).
                placeholder(R.color.gray_99).error(R.color.gray_99).
                dontAnimate().thumbnail(0.1f).into(iv_pic);
        tv.setText(photoAibum.getName() + " ( "
                + photoAibum.getCount() + " )");
        return convertView;
    }

}
