package wh.ywh.util;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.leoman.ywhhelplibs.R;

/**
 * Created by Administrator on 2018-05-28.
 */

public class DialogUtils  {
    private static DialogUtils instance;
    private static Dialog alertDialog;

    public static DialogUtils getInstance(){
        if( instance==null ){
            instance = new DialogUtils();

        }
        return instance;
    }

    public  void showWindowImage(Activity activity){
        if (alertDialog == null)
            alertDialog = new Dialog(activity, R.style.DefaultDialogStyle);
        alertDialog.show();
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
//        alertDialog.getWindow().setWindowAnimations(R.style.bottomAnim);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.getWindow().setContentView(R.layout.window_choose_image);
        alertDialog.getWindow().findViewById(R.id.tv_choose_image_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }
}
