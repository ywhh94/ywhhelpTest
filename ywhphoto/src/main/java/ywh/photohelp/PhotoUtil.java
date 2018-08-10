package ywh.photohelp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2018-06-01.
 */

public class PhotoUtil {
    public static final int PHOTO_CAMERA  = 20020;
    public static final int PHOTO_PICTURE = 20021;
    private  Activity mActivity;
    public File cameraFile;  //相机拍照之后的文件路径

    private Dialog alertDialog;

    private String oneStr;      // 第一个文字
    private int oneStrColor;    //第一个文字颜色
    private String twoStr;      //第二个文字
    private int twoStrColor;    //第二个文字颜色
    private int textSize;       //两个文字的大小


    private String cancleStr;    //取消的文字
    private int cancleStrColor;  //取消的文字颜色
    private int cancleStrSize;   //取消文字大小

    private int imageNum;        //图片数量  1 单图, 1以上多图数量 ，默认是单图
    public String pic_path;


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

    public PhotoUtil setTwoText(String textValue){ //text2
        this.twoStr = textValue;
        return this;
    }
    public PhotoUtil setTwoTextColor(int color){    //text2颜色
        this.twoStrColor = color;
        return this;
    }
    public PhotoUtil setTextSize(int size){
        this.textSize = size;
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
    public PhotoUtil setImageNum(int num){
        this.imageNum = num;
        return this;
    }

    /**
     * 底部弹出  相册、相机、取消
     */
//    public void show(){
//        if(alertDialog == null){
//            alertDialog = new Dialog(mActivity,R.style.DefaultDialogStyle);
//        }
//        alertDialog.show();
//        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
////        alertDialog.getWindow().setWindowAnimations(R.style.bottomAnim);
//        alertDialog.setCanceledOnTouchOutside(true);
//        alertDialog.getWindow().setContentView(R.layout.window_choose_photo);
//
//        TextView tv_choose1 = (TextView) alertDialog.getWindow().findViewById(R.id.tv_choose_photo1);
//        TextView tv_choose2 = (TextView) alertDialog.getWindow().findViewById(R.id.tv_choose_photo2);
//        TextView tv_cancel = (TextView) alertDialog.getWindow().findViewById(R.id.tv_choose_photo_cancel);
//        if(!TextUtils.isEmpty(oneStr)) tv_choose1.setText(oneStr);
//        if(0 != oneStrColor) tv_choose1.setTextColor(oneStrColor);
//
//        if(!TextUtils.isEmpty(twoStr)) tv_choose2.setText(twoStr);
//        if(0 != twoStrColor) tv_choose2.setTextColor(twoStrColor);
//        if(0!= textSize) {tv_choose1.setTextSize(textSize);tv_choose2.setTextSize(textSize);}
//
//        if(!TextUtils.isEmpty(cancleStr)) tv_cancel.setText(cancleStr);
//        if(0 != cancleStrColor) tv_cancel.setTextColor(cancleStrColor);
//        if(0!= cancleStrSize) tv_cancel.setTextSize(cancleStrSize);
//
//        if(imageNum<=0) imageNum = 1;
//        tv_choose1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openCamera();
//            }
//        });
//        tv_choose2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(imageNum == 1)
//                    openAlbum();         //选择单图
//                else{
//                    Intent intent = new Intent(mActivity,PhotoClassifyActivity.class);
//                    mActivity.startActivity(intent);
//                }
//            }
//        });
//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancel();
//            }
//        });
//    }

    private void cancel(){
        if(alertDialog!=null){
            alertDialog.cancel();
        }
    }
    /**
     * 打开相机
     */
//    private void openCamera(){
//        String state = Environment.getExternalStorageState();
//        if (state.equals(Environment.MEDIA_MOUNTED)) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            String photoPath = App.getPackPath(mActivity);
//            LogUtil.d("photoPath:" + photoPath);
//            cameraFile = new File(photoPath, "camera" + System.currentTimeMillis() + ".jpg");
//            Uri photoUri = Uri.fromFile(cameraFile); // 传递路径
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
//            cancel();
//            mActivity.startActivityForResult(intent, PHOTO_CAMERA);
//        }else{
//            ToastUtil.toastShort(mActivity,"没有SD卡");
//        }
//    }

    /**
     * 打开相册，选一张
     */
    private void openAlbum() {
//        sdScan();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
//            String cacheDir = App.getPackPath(mActivity);
//            if (cacheDir != null) {
//                File folder = new File(cacheDir, "app");
//                //创建新的完整文件路径
//                File file = new File(folder.getAbsolutePath() + System.currentTimeMillis() + ".jpg");
//
//                pic_path = file.getAbsolutePath();
//                LogUtil.e("pic_path:"+pic_path);
//                Uri fromFile = FileProvider.getUriForFile(mActivity.getApplicationContext(), mActivity.getResources().getString(R.string.app_name), file);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, fromFile);
//                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            }
//        }
        mActivity.startActivityForResult(intent, PHOTO_PICTURE);
    }

    public void sdScan() {
        try {
            mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                    + Environment.getExternalStorageDirectory())));
        } catch (Exception e) {
            MediaScannerConnection.scanFile(mActivity, new String[]{"file://"
                    + Environment.getExternalStorageDirectory()}, null, null);
        }
    }

}
