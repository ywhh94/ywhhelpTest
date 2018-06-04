package wh.ywh.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.leoman.ywhhelplibs.R;

import java.io.File;

import wh.ywh.App;
import wh.ywh.Constanct;

/**
 * Created by Administrator on 2018-06-01.
 */

public class PhotoUtil {
    private  Activity mActivity;
    public File cameraFile;  //相机拍照之后的文件路径

    private Dialog alertDialog;
    private String oneStr;
    private int oneStrColor;
    private int oneStrSize;
    private String twoStr;
    private int twoStrColor;
    private int twoStrSize;
    private String cancleStr;
    private int cancleStrColor;
    private int cancleStrSize;


    public PhotoUtil(Activity activity){
        this.mActivity = activity;
    }
    public PhotoUtil setOneText(String textValue){ //text
        this.oneStr = textValue;
        return this;
    }
    public PhotoUtil setOneTextColor(int color){    //text颜色
        this.oneStrColor = color;
        return this;
    }
    public PhotoUtil setOneTextSize(int size){   //text大小
        this.oneStrSize = size;
        return this;
    }

    public PhotoUtil setTwoText(String textValue){ //text2
        this.twoStr = textValue;
        return this;
    }
    public PhotoUtil setTwoTextColor(int color){    //text2颜色
        this.twoStrColor = color;
        return this;
    }
    public PhotoUtil setTwoTextSize(int size){   //text2大小
        this.twoStrSize = size;
        return this;
    }

    public PhotoUtil setCancleText(String textValue){ //cancle
        this.cancleStr = textValue;
        return this;
    }
    public PhotoUtil setCancleTextColor(int color){    //cancle颜色
        this.cancleStrColor = color;
        return this;
    }
    public PhotoUtil setCancleTextSize(int size){   //cancle大小
        this.cancleStrSize = size;
        return this;
    }

    /**
     * 显示窗口
     */
    public void show(){
        if(alertDialog == null){
            alertDialog = new Dialog(mActivity,R.style.DefaultDialogStyle);
        }
        alertDialog.show();
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
//        alertDialog.getWindow().setWindowAnimations(R.style.bottomAnim);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.getWindow().setContentView(R.layout.window_choose_image);

        TextView tv_choose1 = (TextView) alertDialog.getWindow().findViewById(R.id.tv_choose_image1);
        TextView tv_choose2 = (TextView) alertDialog.getWindow().findViewById(R.id.tv_choose_image2);
        TextView tv_cancel = (TextView) alertDialog.getWindow().findViewById(R.id.tv_choose_image_cancel);
        if(!TextUtils.isEmpty(oneStr))
            tv_choose1.setText(oneStr);
        if(0 != oneStrColor)
            tv_choose1.setTextColor(oneStrColor);
        if(0!= oneStrSize)
            tv_choose1.setTextSize(oneStrSize);

        if(!TextUtils.isEmpty(twoStr))
            tv_choose2.setText(twoStr);
        if(0 != twoStrColor)
            tv_choose2.setTextColor(twoStrColor);
        if(0!= twoStrSize)
            tv_choose2.setTextSize(twoStrSize);

        if(!TextUtils.isEmpty(cancleStr))
            tv_choose2.setText(cancleStr);
        if(0 != cancleStrColor)
            tv_choose2.setTextColor(cancleStrColor);
        if(0!= cancleStrSize)
            tv_choose2.setTextSize(cancleStrSize);

        tv_choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xiangce
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }


    /**
     * 打开相机
     */
    public void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String photoPath = App.getPackPath(mActivity);
        LogUtil.d("photoPath:"+photoPath);
        cameraFile = new File(photoPath,"camera"+System.currentTimeMillis()+".jpg");
        Uri photoUri = Uri.fromFile(cameraFile); // 传递路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
        mActivity.startActivityForResult(intent, Constanct.PHOTO_CAMERA);
    }

}
