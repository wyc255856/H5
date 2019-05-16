package com.faw.controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.faw.h5.H5ManuaApi;
import com.faw.h5.H5ManuaWelecomActivity;
import com.faw.h5.util.H5SharedpreferencesUtil;
import com.faw.hs5.HS5ManuaApi;
import com.faw.hs7.HS7ManuaApi;
import com.faw.seniar9.EVManuaApi;
import com.faw.seniar9.util.FireUtil;

public class ManuaApi {

    public static String type = "";

    public static MANUA_CAR CURRENT_CAR;

    public enum MANUA_CAR {
        H5, EV, HS5, HS7
    }

    public static void openManua(Context context, MANUA_CAR car, String carModel) {
        FireUtil.delete(context);
        CURRENT_CAR = car;
        if (car == MANUA_CAR.EV) {
            type = "ev";
            EVManuaApi.getInstance().openManua(context, carModel);
        } else if (car == MANUA_CAR.H5) {
            type = "h5";
            H5ManuaApi.getInstance().openManua(context, carModel);
        } else if (car == MANUA_CAR.HS5) {
            type = "hs5";
            HS5ManuaApi.getInstance().openManua(context, carModel);
        } else if (car == MANUA_CAR.HS7) {
            type = "hs7";
            HS7ManuaApi.getInstance().openManua(context, carModel);
        }

    }
}
