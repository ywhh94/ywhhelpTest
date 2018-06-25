package com.leoman.helper.pay;

/**
 * Created by spurs on 2017/3/21.
 */

public interface PayResultListener {
    void resultCode(String code);//9000--成功  8000--确认中  其他--失败
}
