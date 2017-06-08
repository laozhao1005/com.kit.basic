package com.kit.utils.intentutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;

import com.kit.app.ActivityManager;
import com.kit.utils.StringUtils;

import java.net.URISyntaxException;

public class IntentUtils extends IntentBaseUtils {


    public static Intent getIntentFromString(String string) {
        if (string == null || string.isEmpty()) {
            return new Intent();
        } else {
            try {
                return new Intent().parseUri(string, 0);
            } catch (URISyntaxException e) {
                return new Intent();
            }
        }
    }

    public static String getIntentAsString(Intent intent) {
        if (intent == null) {
            return "";
        } else {
            return intent.toUri(0);
        }
    }

    /**
     * 发送广播
     *
     * @param packageContext
     * @param data
     * @param action
     */
    public static void sendBroadcast(Context packageContext,
                                     BundleData data, String action) {
        Intent intent = new Intent();//创建Intent对象
        pushData(intent, data);
        intent.setAction(action);
        packageContext.sendBroadcast(intent);
    }

    /**
     * 发送广播
     *
     * @param activity
     * @param data
     * @param resultCode
     */
    public static void setResult(Activity activity, int resultCode,
                                 BundleData data) {
        Intent intent = new Intent();//创建Intent对象
        pushData(intent, data);
        activity.setResult(resultCode);
        activity.finish();
    }


    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     */
    public static void gotoSingleNextActivity(Context packageContext, Class<?> cls,
                                              BundleData data) {


        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);

        if (ActivityManager.getInstance().isExistActivity(cls)) {
            ActivityManager.getInstance().popActivity(cls);
        }
    }

    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     */
    public static void gotoNextActivity(Context packageContext, Class<?> cls,
                                        BundleData data) {
        Intent intent = new Intent();//创建Intent对象
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);
    }


    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     * @param isCloseThis    是否关闭当前界面
     */
    public static void gotoNextActivity(Context packageContext, Class<?> cls,
                                        BundleData data, boolean isCloseThis) {

        Intent intent = new Intent();//创建Intent对象
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);

        if (isCloseThis) {
            ((Activity) packageContext).finish();
        }
    }



    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     * @param isCloseThis    是否关闭当前界面
     */
    public static void gotoNextActivity(Context packageContext, Class<?> cls,
                                        BundleData data, ActivityOptionsCompat transitionActivityOptions, boolean isCloseThis) {

        Intent intent = new Intent();//创建Intent对象
        pushData(intent, data);
        intent.setClass(packageContext, cls);

        ActivityCompat.startActivity(packageContext,
                intent, transitionActivityOptions.toBundle());

        if (isCloseThis) {
            ((Activity) packageContext).finish();
        }
    }

    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     */
    public static void gotoNextActivity(Context packageContext, Class<?> cls,
                                        BundleData data, int requestFlag) {
        Intent intent = new Intent();//创建Intent对象
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        ((Activity) packageContext).startActivityForResult(intent, requestFlag);

    }


    /**
     * 跳转界面并传值
     *
     * @param fragment
     * @param cls
     * @param data
     */
    public static void gotoNextActivity(Fragment fragment, Class<?> cls,
                                        BundleData data, int requestFlag) {
        Intent intent = new Intent();//创建Intent对象
        pushData(intent, data);
        intent.setClass(fragment.getActivity(), cls);
        fragment.startActivityForResult(intent, requestFlag);

    }


    /**
     * 跳转界面
     *
     * @param packageContext
     * @param cls
     * @param isCloseThis
     */
    public static void gotoSingleNextActivity(Context packageContext, Class<?> cls, boolean isCloseThis) {

        if (ActivityManager.getInstance().isExistActivity(cls)) {
            ActivityManager.getInstance().popActivity(cls);
        }

        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        ((Activity) packageContext).startActivity(intent);

        if (isCloseThis) {
            ((Activity) packageContext).finish();
        }

    }



    /**
     * 从Service到Activity
     *
     * @param packageContext
     * @param cls
     */
    public static void gotoSingleNextActivityFromReceiver(Context packageContext,
                                                          Class<?> cls, BundleData data, boolean isCloseThis) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        packageContext.startActivity(intent);

        if (isCloseThis && (packageContext instanceof Activity)) {
            ((Activity) packageContext).finish();
        }
    }

    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     */
    public static void gotoSingleNextActivity(Context packageContext, Class<?> cls,
                                              BundleData data, boolean isCloseThis) {

        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        ((Activity) packageContext).startActivity(intent);

        if (isCloseThis && (packageContext instanceof Activity)) {
            ((Activity) packageContext).finish();
        }

        if (ActivityManager.getInstance().isExistActivity(cls)) {
            ActivityManager.getInstance().popActivity(cls);
        }

    }


    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     */
    public static void gotoSingleNextActivity(Context packageContext, Class<?> cls,
                                              BundleData data, int requestFlag) {

        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        ((Activity) packageContext).startActivityForResult(intent, requestFlag);

        if (ActivityManager.getInstance().isExistActivity(cls)) {
            ActivityManager.getInstance().popActivity(cls);
        }

    }


    /**
     * 跳转界面并传值
     *
     * @param fragment
     * @param cls
     * @param data
     */
    public static void gotoSingleNextActivity(Fragment fragment, Class<?> cls,
                                              BundleData data, int requestFlag) {


        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(fragment.getActivity(), cls);
        fragment.startActivityForResult(intent, requestFlag);

        if (ActivityManager.getInstance().isExistActivity(cls)) {
            ActivityManager.getInstance().popActivity(cls);
        }
    }


    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     */
    public static void gotoSingleNextActivity(Context packageContext, Class<?> cls,
                                              BundleData data, boolean isCloseThis, int requestFlag) {


        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        ((Activity) packageContext).startActivityForResult(intent, requestFlag);


        if (isCloseThis) {
            ((Activity) packageContext).finish();
        }

        if (ActivityManager.getInstance().isExistActivity(cls)) {
            ActivityManager.getInstance().popActivity(cls);
        }
    }


    /**
     * 跳转界面并传值
     *
     * @param fragment
     * @param cls
     * @param data
     */
    public static void gotoSingleNextActivity(Fragment fragment, Class<?> cls,
                                              BundleData data, int requestFlag, boolean isCloseThis) {


        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(fragment.getActivity(), cls);
        fragment.startActivityForResult(intent, requestFlag);


        if (isCloseThis) {
            fragment.getActivity().finish();
        }

        if (ActivityManager.getInstance().isExistActivity(cls)) {
            ActivityManager.getInstance().popActivity(cls);
        }
    }


    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param cls
     * @param data
     * @param isCloseThis    是否关闭当前界面
     */
    public static void gotoNextActivity(Context packageContext, Class<?> cls,
                                        BundleData data, boolean isCloseThis, int requestFlag) {


        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(packageContext, cls);
        ((Activity) packageContext).startActivityForResult(intent, requestFlag);

        if (isCloseThis) {
            ((Activity) packageContext).finish();
        }
    }

    /**
     * 跳转界面并传值
     *
     * @param packageContext
     * @param data
     * @param isCloseThis    是否关闭当前界面
     */
    public static void gotoNextActivity(Context packageContext, String action, String uri,
                                        BundleData data, boolean isCloseThis) {


        Intent intent;
        if (!StringUtils.isEmptyOrNullStr(uri)) {
            Uri realUri = Uri.parse(uri);
            intent = new Intent(action, realUri);

        } else {
            intent = new Intent(action);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pushData(intent, data);
        packageContext.startActivity(intent);

        if (packageContext instanceof Activity) {
            if (isCloseThis) {
                ((Activity) packageContext).finish();
            }
        }
    }


    /**
     * 跳转界面并传值
     *
     * @param fragment
     * @param cls
     * @param data
     * @param isCloseThis 是否关闭当前界面
     */
    public static void gotoNextActivity(Fragment fragment, Class<?> cls,
                                        BundleData data, boolean isCloseThis, int requestFlag) {

        Intent intent = new Intent();
        pushData(intent, data);
        intent.setClass(fragment.getActivity(), cls);
        fragment.startActivityForResult(intent, requestFlag);

        if (isCloseThis) {
            (fragment.getActivity()).finish();
        }

    }


}
