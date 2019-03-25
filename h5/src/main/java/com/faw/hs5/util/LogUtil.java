package com.faw.hs5.util;

import android.util.Log;


/**
 * Created by liangjie on 2015/4/10 by Android studio.
 * Description:日志打印统一
 */
public class LogUtil {
    static String TAG = "PlayCombApp";
    public static boolean debug = true;

    public static void logError(String error) {
        if (debug) {
            Log.e(TAG, error);
//            Logger.init(TAG).methodCount(3)       ;            // default 0
//            Logger.e(error);
        }
    }

    public static void logVerbose(String info) {
        if (debug) {
            Log.v(TAG, info);
//            Logger.init(TAG);
//            Logger.i(info);
        }
    }

    public static void logDebug(String debugInfo) {
        if (debug) {
            Log.d(TAG, debugInfo);
//            Logger.init(TAG);
//            Logger.d(debugInfo);
        }
    }

    public static void logInfo(String info) {
        if (debug) {
            Log.i(TAG, info);
//            Logger.init(TAG);
//            Logger.d(info);
        }
    }

    public static void logWarn(String warn) {
        if (debug) {
            Log.w(TAG, warn);
//            Logger.init(TAG);
//            Logger.d(warn);
        }
    }

}
