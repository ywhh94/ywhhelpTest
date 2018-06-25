package com.leoman.helper.photo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leoman.helper.R;
import com.leoman.helper.util.ToastUtil;

import java.util.ArrayList;

public class PhotoAdappter extends BaseAdapter {

    private Context context;
    private PhotoAibum aibum;
    private ArrayList<PhotoItem> gl_arr;
    CheckListener listener;
    int maxNum;
    private int current = 0;

    public interface CheckListener {
        void change(int position, boolean isSelect);
    }

    public PhotoAdappter(Context context, PhotoAibum aibum, ArrayList<PhotoItem> gl_arr, int maxNum, CheckListener listener) {
        this.context = context;
        this.aibum = aibum;
        this.gl_arr = gl_arr;
        this.maxNum = maxNum;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if (gl_arr == null) {
            return aibum.getBitList().size();
        } else {
            return gl_arr.size();
        }

    }

    @Override
    public PhotoItem getItem(int position) {
        if (gl_arr == null) {
            return aibum.getBitList().get(position);
        } else {
            return gl_arr.get(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PhotoGridItem item;
        if (convertView == null) {
            item = new PhotoGridItem(context);
            item.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT));
        } else {
            item = (PhotoGridItem) convertView;
        }

        item.getFl_view().setTag(position);
        item.getFl_view().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                if (aibum.getBitList().get(position).isSelect()) {
                    current--;
                    aibum.getBitList().get(position).setSelect(false);
                    item.getCheckView().setImageResource(R.mipmap.pic_noselect);
                } else {
                    if (current < maxNum) {
                        current++;
                        aibum.getBitList().get(position).setSelect(true);
                        item.getCheckView().setImageResource(R.mipmap.pic_select);
                    } else {
                        ToastUtil.showToast(context, "可不要贪心哟,最多选" + maxNum + "张！", Toast.LENGTH_SHORT);
                        return;
                    }
                }

                if (listener != null)
                    listener.change(position, !aibum.getBitList().get(position).isSelect());
            }
        });

        // 通过ID 加载缩略图
        if (gl_arr == null) {
            Glide.with(context).load("file://"
                    + aibum.getBitList().get(position).getPath()).
                    placeholder(R.color.gray_99).error(R.color.gray_99).
                    dontAnimate().thumbnail(0.1f).into(item.getImageView());
            boolean flag = aibum.getBitList().get(position).isSelect();
            item.setChecked(flag);
        } else {
            Glide.with(context).load("file://"
                    + gl_arr.get(position).getPath()).
                    placeholder(R.color.gray_99).error(R.color.gray_99).
                    dontAnimate().thumbnail(0.1f).into(item.getImageView());
        }
        return item;
    }


}
