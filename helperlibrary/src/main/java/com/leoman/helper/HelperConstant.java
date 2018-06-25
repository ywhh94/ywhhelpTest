package com.leoman.helper;

/**
 * Created by Administrator on 2017/3/24.
 * 常量
 */

public class HelperConstant {

    public static final String URL = "url";//加载图片
    public static final String RESOURCES = "resources";//默认图片
    public static final String PATHS = "paths";//多图回调
    public static final int CAMERA = 1001;//相机
    public static final int ALBUM = 1002;//相册
    public static final int CROP = 1003;//剪裁
    public static final int VIDEO = 1004;//视频
    public static final int ALBUMVIDEO = 1005;//相册视频
    public static final String INDEX = "index";//多图当前选中位置
    public static final String IMAGES = "images";//多图
    public static final String IDENTITY = "(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)";
    public static final String TELREGEX = "^(1([3456789][0-9]))\\d{8}$";
    public static final String NUMBER = "[0-9]*";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String PWDREGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{8,12}";
    public static final String MONEYREGEX = "^\\-?([1-9]\\d*|0)(\\.\\d{0,2})?$";
}
