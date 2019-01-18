/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.che.codrivercustom1;

import com.baidu.che.codriversdk.manager.CdConfigManager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class CoDriverCustomApp extends Application {

    private static final String TAG = "CoDriverCustomApp";
    private static CoDriverCustomApp mInstance = null;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void runUITask(Runnable run) {
        mHandler.post(run);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static CoDriverCustomApp getInstance() {
        return mInstance;
    }

}
