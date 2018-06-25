package com.leoman.helper.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ImageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String id;
    public String url;
    public String width;
    public String height;
    public boolean isSave;

    public String imgsrc;// 图片路径(file://) 显示用
    public String picUrl;// 图片文件路径 上传后台
}
