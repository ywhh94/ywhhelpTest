package com.leoman.helper.photo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.leoman.helper.BaseApplication;
import com.leoman.helper.HelperConstant;
import com.leoman.helper.R;
import com.leoman.helper.listener.CompressListener;
import com.leoman.helper.luban.Luban;
import com.leoman.helper.luban.OnCompressListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by spurs on 2017/3/23.
 * 拍照/相册
 */

public class PhotoHelper {

    private Activity mActivity;
    private Dialog alertDialog;
    private Uri cropUri;
    private File cameraFile;
    private String pic_path;

    public PhotoHelper(Activity mContext) {
        this.mActivity = mContext;
    }

    public String getPic_path() {
        return pic_path;
    }

    public File getCameraFile() {
        return cameraFile;
    }

    public Uri getCropUri() {
        return cropUri;
    }

    public Uri getUriForFile(File file) {
        return FileProvider.getUriForFile(mActivity, mActivity.getResources().getString(com.leoman.helper.R.string.file_provider)
                , file);
    }

    /**
     * 相册/拍照 对话框
     *
     * @param type   0--多图  1--单图  2--视频
     * @param maxNum 最多选择图片
     */
    public void showDlg(int type, int maxNum) {
        if (alertDialog == null)
            alertDialog = new Dialog(mActivity, R.style.MyDialogStyle);
        alertDialog.show();
        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.getWindow().setWindowAnimations(R.style.bottomAnim);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.getWindow().setContentView(R.layout.window_choose_image);

        // 拍照
        TextView tv_register_pz = (TextView) alertDialog.getWindow().findViewById(R.id.tv_camera);
        // 从相册中选择
        TextView tv_register_xc = (TextView) alertDialog.getWindow().findViewById(R.id.tv_album);
        TextView tv_register_qx = (TextView) alertDialog.getWindow().findViewById(R.id.tv_confirm);

        tv_register_pz.setTag(R.id.text1, type);
        tv_register_pz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = (int) v.getTag(R.id.text1);
                if (type == 2)
                    getFromVideo();
                else
                    getFromCamera();
                alertDialog.dismiss();
            }
        });

        tv_register_xc.setTag(R.id.text1, type);
        tv_register_xc.setTag(R.id.text2, maxNum);
        tv_register_xc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = (int) v.getTag(R.id.text1);
                int maxNum = (int) v.getTag(R.id.text2);
                if (type == 0) {
                    Intent intent = new Intent(mActivity, PhotoAlbumActivity.class);
                    intent.putExtra("maxNum", maxNum);
                    mActivity.startActivityForResult(intent, HelperConstant.ALBUM);
                } else if (type == 1) {
                    doPickPhotoFromGallery();
                } else {
                    doPickVideoFromGallery();
                }
                alertDialog.dismiss();
            }
        });
        tv_register_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    /**
     * @return
     * @params 调用相机拍照
     */
    public void getFromCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File cacheDir = BaseApplication.getExternalCacheDir(mActivity);
            File folder = new File(cacheDir, "app");
            //创建新的完整文件路径
            cameraFile = new File(folder.getAbsolutePath() + System.currentTimeMillis() + ".jpg");
            pic_path = cameraFile.getAbsolutePath();
            Uri fromFile;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //改变Uri  注意和xml中的一致
                fromFile = FileProvider.getUriForFile(mActivity.getApplicationContext(), mActivity.getResources().getString(R.string.file_provider), cameraFile);
                //添加权限
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                fromFile = Uri.fromFile(cameraFile);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fromFile);
            mActivity.startActivityForResult(intent, HelperConstant.CAMERA);
        } else {
            Toast.makeText(mActivity, "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @return
     * @params 调用相机拍视频
     */
    public void getFromVideo() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File cacheDir = BaseApplication.getExternalCacheDir(mActivity);
            File folder = new File(cacheDir, "app");
            //创建新的完整文件路径
            File file = new File(folder.getAbsolutePath() + System.currentTimeMillis() + ".mp4");

            pic_path = file.getAbsolutePath();
            Uri fromFile;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //改变Uri  注意和xml中的一致
                fromFile = FileProvider.getUriForFile(mActivity.getApplicationContext(), mActivity.getResources().getString(R.string.file_provider), file);
                //添加权限
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                fromFile = Uri.fromFile(file);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fromFile);
            mActivity.startActivityForResult(intent, HelperConstant.VIDEO);
        } else {
            Toast.makeText(mActivity, "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @return
     * @params 调用相册
     */
    private void doPickPhotoFromGallery() {
        sdScan();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            File cacheDir = BaseApplication.getExternalCacheDir(mActivity);
            if (cacheDir != null) {
                File folder = new File(cacheDir, "app");
                //创建新的完整文件路径
                File file = new File(folder.getAbsolutePath() + System.currentTimeMillis() + ".jpg");

                pic_path = file.getAbsolutePath();
                Uri fromFile = FileProvider.getUriForFile(mActivity.getApplicationContext(), mActivity.getResources().getString(R.string.file_provider), file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fromFile);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        }
        mActivity.startActivityForResult(intent, HelperConstant.ALBUM);
    }

    /**
     * @return
     * @params 调用相册获取视频
     */
    private void doPickVideoFromGallery() {
        sdScan();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        mActivity.startActivityForResult(intent, HelperConstant.ALBUMVIDEO);
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

    /**
     * 裁剪图片方法实现
     */
    public void startPhotoZoom(Uri inputUri) {
        if (inputUri == null) {
            Log.e("error", "The uri is not exist.");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        File cacheDir = BaseApplication.getExternalCacheDir(mActivity);
        if (cacheDir != null) {
            File folder = new File(cacheDir, "crop");
            //创建新的完整文件路径
            File file = new File(folder.getAbsolutePath() + System.currentTimeMillis() + ".jpg");
            cropUri = Uri.fromFile(file);
            //sdk>=24
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            intent.setDataAndType(inputUri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);

            // 设置裁剪
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 150);
            intent.putExtra("outputY", 150);
            intent.putExtra("return-data", true);
            intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
            intent.putExtra("outputFormat", "JPG");// 图片格式
            mActivity.startActivityForResult(intent, HelperConstant.CROP);//这里就将裁剪后的图片的Uri返回了
        }
    }

    /**
     * 压缩图片（多图）
     *
     * @param paths 被压缩文件路径
     */
    public void compressFile(List<String> paths, OnCompressListener compressListener, int tag) {
        Luban.with(mActivity)
                .load(paths)                     //传人要压缩的图片
                .ignoreBy(100)
                .setCompressListener(compressListener)
                .setTag(tag)
                .launch();    //启动压缩
    }

    /**
     * 压缩图片（单图）
     *
     * @param path 被压缩文件路径
     */
    public void compressFile(String path, OnCompressListener compressListener, int tag) {
        Luban.with(mActivity)
                .load(path)                     //传人要压缩的图片
                .ignoreBy(100)
                .setCompressListener(compressListener)
                .setTag(tag)
                .launch();    //启动压缩
    }

    /**
     * 压缩图片（文件）
     *
     * @param file 被压缩文件
     */
    public void compressFile(File file, OnCompressListener compressListener, int tag) {
        Luban.with(mActivity)
                .load(file)                     //传人要压缩的图片
                .ignoreBy(100)
                .setCompressListener(compressListener)
                .setTag(tag)
                .launch();    //启动压缩
    }
}
