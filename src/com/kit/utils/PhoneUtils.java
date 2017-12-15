package com.kit.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.kit.utils.log.ZogUtils;

/**
 * Created by Zhao on 16/9/1.
 */

public class PhoneUtils {


    /**
     * 是否正在通话中
     * @return
     */
    public static boolean isTelephonyCalling() {
        boolean calling = false;
        TelephonyManager telephonyManager = (TelephonyManager) ResWrapper.getInstance().getApplicationContext()
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (TelephonyManager.CALL_STATE_OFFHOOK == telephonyManager.getCallState()
                || TelephonyManager.CALL_STATE_RINGING == telephonyManager.getCallState()) {
            calling = true;
        }
        return calling;
    }



    public static void mkCall(String strPhone) {

        if (ContextCompat.checkSelfPermission(ResWrapper.getInstance().getApplicationContext()
                , Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Context context = ResWrapper.getInstance().getApplicationContext();
        Uri uri = Uri.parse("tel:" + strPhone);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);// 注意：call是直接就打出去了，dial是经过系统的确定才能打
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            ZogUtils.showException(e);
        }

    }

    public static void mkDail(Context context, String strPhone) {
        Uri uri = Uri.parse("tel:" + strPhone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);// 注意：call是直接就打出去了，dial是经过系统的确定才能打
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            ZogUtils.showException(e);
        }


    }


}
