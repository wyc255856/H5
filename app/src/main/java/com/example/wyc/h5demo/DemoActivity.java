package com.example.wyc.h5demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.faw.controller.ManuaApi;
import com.faw.h5.H5ManuaApi;
import com.faw.seniar9.EVManuaApi;

/**
 * Created by wyc on 2018/6/29.
 */

public class DemoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.startEV) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.EV, "");
        } else if (view.getId() == R.id.startH5) {
            ManuaApi.openManua(this, ManuaApi.MANUA_CAR.H5, "");
        }

    }

}
