package com.leoman.helper.util;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.leoman.helper.R;

/**
 * Created by spurs on 2017/3/23.
 * 拨打电话
 */

public class CallUtil {

    public static void call(Activity mActivity, String phone) {
        TelephonyManager mTelephonyManager = (TelephonyManager) mActivity.getSystemService(Service.TELEPHONY_SERVICE);
        if (mTelephonyManager.getSimState() != TelephonyManager.SIM_STATE_READY) //SIM卡没有就绪
        {
            ToastUtil.showToast(mActivity, mActivity.getResources().getString(R.string.no_sim), Toast.LENGTH_SHORT);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mActivity.startActivity(intent);
        }
    }

}
