package wh.ywh.util;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2018-05-29.
 */

public class FrescoUtil {
    public static void setImage(SimpleDraweeView simpleDraweeView,String url){
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setTapToRetryEnabled(true) // 设置是否开启点击重试
                .setOldController(simpleDraweeView.getController()) //设置旧的controller
                .build();
        simpleDraweeView.setController(controller);
    }
}
