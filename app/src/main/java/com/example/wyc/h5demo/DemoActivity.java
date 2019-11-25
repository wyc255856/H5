package com.example.wyc.h5demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.faw.controller.ManuaApi;
import com.faw.h5.H5ManuaApi;
import com.faw.seniar9.EVManuaApi;
import com.yufuparty.LHR.ARShow.UnityPlayerActivity;

/**
 * Created by wyc on 2018/6/29.
 */

public class DemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.startEV) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.EV, "");
        } else if (view.getId() == R.id.startH5) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.H5, "");
        } else if (view.getId() == R.id.startHS5) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.HS5, "");
        } else if (view.getId() == R.id.startHS7) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.HS7, "");
        } else if (view.getId() == R.id.startEV_1) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.EV, "EV_1");
        } else if (view.getId() == R.id.startH5_1) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.H5, "HONGQIH5_1");
        } else if (view.getId() == R.id.startHS5_1) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.HS5, "HS5_1");
        } else if (view.getId() == R.id.startHS7_1) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.HS7, "HS7_1");
        }else if (view.getId()==R.id.startAR){
            startActivity(new Intent(this, UnityPlayerActivity.class));
        }

    }

}
