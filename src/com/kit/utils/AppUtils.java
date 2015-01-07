package com.kit.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;

import com.kit.app.ActivityManager;
import com.kit.app.core.task.DoSomeThing;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {
    public static String getAppVersion(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static int getAppCode(Context context) {
        int versionCode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;

            if (versionCode <= 0) {
                return 0;
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionCode;
    }

    public static String getAppName(Context context) {

        String appName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);

            appName = pm.getApplicationLabel(pi.applicationInfo).toString();

            if (appName == null || appName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return appName;
    }

    public static String getAppPackage(Context context) {

        String packageName = context.getPackageName();
        System.out.println("packageName" + packageName);

        return packageName;
    }

    public static String getPackageByApkFilePath(Context context, String path) {
        PackageManager pm = context.getPackageManager();
        String packageName = "";
        PackageInfo info = pm.getPackageArchiveInfo(path,
                PackageManager.GET_ACTIVITIES);
        ApplicationInfo appInfo = null;
        if (info != null) {
            appInfo = info.applicationInfo;
            packageName = appInfo.packageName;
            System.out.println("packageName" + packageName);
        }
        return packageName;
    }

    public static boolean destoryUser(Context context) {
        try {
            SharedPreferences userInfo = context.getApplicationContext()
                    .getSharedPreferences("user_info", 0);
            userInfo.edit().putString("loginName", "").commit();
            userInfo.edit().putString("password", "").commit();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean autoChangeAlarm(Context context) {
        try {

            AudioManager audioManager = (AudioManager) context
                    .getApplicationContext().getSystemService(
                            Context.AUDIO_SERVICE);
            // audioManager.setRingerMode(RINGER_MODE_NORMAL或者RINGER_MODE_SILENT或者RINGER_MODE_VIBRATE);
            // android.media.AudioManager.RINGER_MODE_NORMAL = 2;
            if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            } else if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 判断手机已安装某程序的方法：
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();// 用于存储所有已安装程序的包名
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    @SuppressWarnings("unused")
    public static void launchApk(Context context, String launchApkUrl) {

        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(launchApkUrl);
        context.startActivity(intent);

    }


    public static void makeCrash() {
        int[] ints = new int[2];
        System.out.print(ints[99] + "");
    }

    /**
     * 强制关闭应用程序
     */
    public static void forceExit(Context context) {

        //目前最为通用的 关闭进程的方法以后的版本使用
//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(startMain);

        ZogUtils.printLog(AppUtils.class, "forceExit 0");

        ActivityManager.getInstance().popAllActivity();
        ZogUtils.printLog(AppUtils.class, "forceExit 1");

        System.exit(0);
        ZogUtils.printLog(AppUtils.class, "forceExit 2");

        android.os.Process.killProcess(android.os.Process.myPid());
        ZogUtils.printLog(AppUtils.class, "forceExit 3");

    }

    /**
     * 暂停主线程
     * @param time
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            ZogUtils.printLog(AppUtils.class, "Exception:" + e);
        }
    }

    /**
     * 延时执行
     * @param time
     * @param doSomeThing
     */
    public static void delay(long time, final DoSomeThing doSomeThing) {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                doSomeThing.execute();
            }

        }, time);
    }
}
