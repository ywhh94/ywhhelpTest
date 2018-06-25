package com.leoman.helper.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.leoman.helper.R;
import com.spurs.retrofit.APIClient;
import com.spurs.retrofit.APIService;
import com.spurs.retrofit.ProgressListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/1/13.
 * 下载apk
 */

public class DownLoadUtil {

    private Activity mActivity;
    private TextView tv_center, tv_commit;
    private Dialog dialog;
    private String codeNum;
    private Call<ResponseBody> call;

    public void showDlg(Activity activity, String codeName, final String path) {
        if (activity != null && !activity.isFinishing()) {
            this.mActivity = activity;
            this.codeNum = codeName;
            if (dialog == null)
                dialog = new Dialog(mActivity, R.style.MyDialogStyle);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            dialog.show();
            dialog.setCanceledOnTouchOutside(true);
            window.setContentView(R.layout.dlg_down);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = DisplayUtil.getWidth(mActivity) * 3 / 4;
            window.setAttributes(lp);

            TextView tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
            tv_commit = (TextView) window.findViewById(R.id.tv_commit);
            tv_center = (TextView) window.findViewById(R.id.tv_center);

            tv_center.setText("请升级APP至版本V" + codeNum);

            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (call != null && call.isExecuted()) {
                        call.cancel();
                    }
                }
            });

            tv_commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    File file = new File(Environment.getExternalStorageDirectory(), CodeUtil.getAppName(mActivity.getApplicationContext()) + "_V" + codeNum + ".apk");
                    if (file.exists()) {
                        ToastUtil.showToast(mActivity.getApplicationContext(), "安装包已下载，请前往文件夹安装", Toast.LENGTH_SHORT);
                        dialog.dismiss();
                    } else
                        down(path);
                }
            });
        }

    }

    private void down(String path) {
        tv_commit.setEnabled(false);
        tv_commit.setText("正在更新...");
        tv_center.setText("正在准备下载");
        APIService apiService = new APIClient(new ProgressListener() {
            @Override
            public void onProgress(final long progress, final long total, final boolean done) {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!done) {
                            long pro = 0;
                            if (total > 0)
                                pro = progress * 100 / total;
                            tv_center.setText(String.valueOf(pro) + "%");
                        } else {
                            tv_center.setText("正在准备安装");
                        }

                    }
                });

            }
        }).getAPIService();

        call = apiService.GetAPI(path);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("response.code()==", response.code() + "  url==" + response.raw().networkResponse().request().url().url());
                try {
                    InputStream is = response.body().byteStream();
                    File file = new File(Environment.getExternalStorageDirectory(), CodeUtil.getAppName(mActivity.getApplicationContext()) + "_V" + codeNum + ".apk");
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    bis.close();
                    is.close();

                    /*安装apk*/
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()), "application/vnd.android.package-archive");
                    mActivity.startActivity(intent);

                    call.cancel();

                    tv_commit.setEnabled(true);
                    tv_commit.setText("确认更新");
                    dialog.cancel();
                } catch (IOException e) {
                    e.printStackTrace();

                    tv_commit.setEnabled(true);
                    tv_commit.setText("确认更新");
                    tv_center.setText("解析失败，请重试");
                    if (call != null && call.isExecuted()) {
                        call.cancel();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tv_commit.setEnabled(true);
                tv_commit.setText("确认更新");
                tv_center.setText("下载失败，请重试");
                call.cancel();
            }
        });
    }


}
