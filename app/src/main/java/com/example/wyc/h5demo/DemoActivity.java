package com.example.wyc.h5demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.faw.h5.H5ManuaApi;

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
        H5ManuaApi.getInstance().openManua(this,"");
    }

}
