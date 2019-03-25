package com.faw.hs5.util;

import android.content.Context;

/**
 * Created by wyc on 18/3/23.
 */

public class HS5ManuaConfig {
    public static String VERSION = "1.0.0";
    //    public static String BASE_URL = "http://www.haoweisys.com/EV/";
    public static String BASE_URL = "http://www.haoweisys.com/HS5/";


    public static String getManuaUrl(Context context) {
        return BASE_URL + HS5SharedpreferencesUtil.getCarModel(context);
    }

    public static String getManuaDownLoadUrl(Context context) {

        return HS5ManuaConfig.BASE_URL + HS5SharedpreferencesUtil.getCarModel(context) + ".zip";
    }

    public static String getManuaUpDataUrl(Context context) {

        return HS5ManuaConfig.BASE_URL + HS5SharedpreferencesUtil.getCarModel(context) + "A.zip";
    }
}
