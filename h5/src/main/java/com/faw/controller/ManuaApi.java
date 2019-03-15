package com.faw.controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.faw.h5.H5ManuaApi;
import com.faw.h5.H5ManuaWelecomActivity;
import com.faw.h5.util.H5SharedpreferencesUtil;
import com.faw.seniar9.EVManuaApi;

public class ManuaApi {
    public static MANUA_CAR CURRENT_CAR;

    public enum MANUA_CAR {
        H5, EV
    }

    public static void openManua( Context context,MANUA_CAR car, String carModel) {
        CURRENT_CAR = car;
        if (car == MANUA_CAR.EV) {
            EVManuaApi.getInstance().openManua(context, carModel);
        } else if (car == MANUA_CAR.H5) {
            H5ManuaApi.getInstance().openManua(context, carModel);
        }

    }
}
