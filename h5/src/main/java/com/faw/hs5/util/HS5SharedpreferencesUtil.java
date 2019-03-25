package com.faw.hs5.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class HS5SharedpreferencesUtil {
    static String PREFERENCES_NAME = "share";

    private static String ISFIRST = "isFirst";
    private static String CAR_MODE = "car_mode";//比较版本更新时间(非强制更新一周一次提示)
    private static String CAR_MODEL = "car_model";//比较版本更新时间(非强制更新一周一次提示)
    private static String HAVE_LOCAL = "have_local";
    private static String MODEL_LOCAL = "model_local";
    private static String VERSION = "version";
    public static void setCarMode(Context context, String carMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sharedPreferences.edit().putString(CAR_MODE, carMode).commit();
    }

    public static String  getCarMode(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(CAR_MODE,"1");
    }
    public static void setCarModel(Context context, String carMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sharedPreferences.edit().putString(CAR_MODEL, carMode).commit();
    }

    public static String  getCarModel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(CAR_MODEL,"HS5_5");
    }

    public static void setHaveLocal(Context context, String carMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sharedPreferences.edit().putString(HAVE_LOCAL, carMode).commit();
    }

    public static String  getHaveLocal(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(HAVE_LOCAL,"0");
    }


    //判断是否第一次登录
    public static Boolean getIsFirst(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(ISFIRST, true);
    }

    public static void setIsFirst(Context context, boolean defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(ISFIRST, defValue).commit();
    }
    public static void setModelLocal(Context context, String carMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sharedPreferences.edit().putString(MODEL_LOCAL, carMode).commit();
    }

    public static String getModelLocal(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(MODEL_LOCAL,"HS5_5");
    }

    public static void setVersion(Context context, String carMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sharedPreferences.edit().putString(VERSION, carMode).commit();
    }

    public static String  getVersion(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(VERSION,"1.0.0");
    }
    //判断是否第一次登录
    public static Boolean isGuest(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean("Guest", false);
    }

    public static void setGuest(Context context, boolean defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppUtil.getPackageName(context)
                + PREFERENCES_NAME, Activity.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("Guest", defValue).commit();
    }
}
