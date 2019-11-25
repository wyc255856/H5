package com.faw.seniar9.util;

import android.content.Context;

/**
 * Created by wyc on 18/3/23.
 */

public class EVManuaConfig {
    public static String VERSION = "1.0.0";
    //    public static String BASE_URL = "http://www.haoweisys.com/EV/";
    public static String BASE_URL = "http://www.e-guides.faw.cn/EV/";

    public static String T086_BASE_URL = BASE_URL + "/car_engine_H5/";
    public static String H5_BASE_URL = BASE_URL + "/car_engine_H5/";

    //车辆型号区分常量
    public static int H5 = 1;
    public static int T086 = 2;

    public static String getManuaUrl(Context context) {
//        if (EVManuaApi.CAR_MODE == H5) {
//            return H5_BASE_URL;
//        } else if (EVManuaApi.CAR_MODE == T086) {
//            return T086_BASE_URL;
//        } else {
        return BASE_URL + EVSharedpreferencesUtil.getCarModel(context);
//        }
    }

    public static String getManuaDownLoadUrl(Context context) {

        return EVManuaConfig.BASE_URL + EVSharedpreferencesUtil.getCarModel(context) + ".zip";
    }

    public static String getManuaUpDataUrl(Context context) {

        return EVManuaConfig.BASE_URL + EVSharedpreferencesUtil.getCarModel(context) + "A.zip";
    }
}
