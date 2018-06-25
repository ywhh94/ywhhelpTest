package com.leoman.helper;


/**
 * Created by spurs on 2017/3/22.
 */

public class HelperLibrary {

    private static HelperLibrary instance = null;
    private int color;
    private String imageUrl;

    private HelperLibrary() {
    }

    public static HelperLibrary getInstance() {
        if (instance == null) {
            instance = new HelperLibrary();
        }
        return instance;
    }

    /**
     * 配置应用主题色
     *
     * @param color 色值
     */
    public void setThemeColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
