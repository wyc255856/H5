package com.faw.hs5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wyc on 2018/4/22.
 */

public class HS5AppReceiver extends BroadcastReceiver {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {  // install
            String packageName = intent.getDataString();

            Log.i("homer", "安装了 :" + packageName);
        }

        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) { // uninstall
            String packageName = intent.getDataString();

            Log.i("homer", "卸载了 :" + packageName);
        }
    }
}
