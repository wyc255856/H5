package com.faw.controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.faw.h5.H5ManuaApi;
import com.faw.h5.H5ManuaWelecomActivity;
import com.faw.h5.util.H5SharedpreferencesUtil;
import com.faw.hs5.HS5ManuaApi;
import com.faw.hs7.HS7ManuaApi;
import com.faw.seniar9.EVManuaApi;
import com.faw.seniar9.util.FireUtil;
import com.yufuparty.LHR.ARShow.UnityPlayerActivity;

public class ManuaApi {

    public static String type = "";

    public static MANUA_CAR CURRENT_CAR;

    public enum MANUA_CAR {
        H5, EV, HS5, HS7
    }

    public static void openManua(Context context, MANUA_CAR car, String carModel) {

        if (context != null) {
            try {
                FireUtil.delete(context);
            } catch (Exception e) {
                Toast.makeText(context, "请开启SD卡读写权限", Toast.LENGTH_SHORT).show();
            }
        }
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

    public static void openHS5AR(Context context) {
        context.startActivity(new Intent(context, UnityPlayerActivity.class));
    }
}
