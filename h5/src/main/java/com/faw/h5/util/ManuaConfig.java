package com.faw.h5.util;

import android.content.Context;

/**
 * Created by wyc on 18/3/23.
 */

public class ManuaConfig {
    public static String VERSION = "1.0.0";
    public static String BASE_URL = "http://www.haoweisys.com/HONGQIH5/";


    public static String T086_BASE_URL = BASE_URL + "/car_engine_H5/";
    public static String H5_BASE_URL = BASE_URL + "/car_engine_H5/";

    //车辆型号区分常量
    public static int H5 = 1;
    public static int T086 = 2;

    public static String getManuaUrl(Context context) {
//        if (ManuaApi.CAR_MODE == H5) {
//            return H5_BASE_URL;
//        } else if (ManuaApi.CAR_MODE == T086) {
//            return T086_BASE_URL;
//        } else {
        return BASE_URL + SharedpreferencesUtil.getCarModel(context);
//        }
    }

    public static String getManuaDownLoadUrl(Context context) {

        return ManuaConfig.BASE_URL + SharedpreferencesUtil.getCarModel(context) + ".zip";
    }
    public static String getManuaUpDataUrl(Context context) {

        return ManuaConfig.BASE_URL + SharedpreferencesUtil.getCarModel(context) + "A.zip";
    }
}
