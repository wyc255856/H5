package com.faw.h5.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.faw.h5.ManuaApi;
import com.faw.h5.ManuaPlayerActivity;
import com.faw.h5.ManuaSetActivity;
import com.faw.h5.ManualWebActivity;
import com.faw.h5.ar.ManuaARActivity;

import java.util.List;


/**
 * Created by wyc on 17/4/26.
 */

public class NativeInterface {
    @JavascriptInterface
    public void selectModel(final String model) {
        LogUtil.logError("=======selectModel========" + model);
//        Toast.makeText(ManualWebActivity.context, "执行到了selectModel", Toast.LENGTH_SHORT).show();
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SharedpreferencesUtil.setCarModel(ManualWebActivity.context, model);
                Intent intent = new Intent(ManuaSetActivity.context, ManualWebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                ManuaSetActivity.context.startActivity(intent);
                ManuaSetActivity.context.finish();
            }
        });
    }

    @JavascriptInterface
    public void cleanCache() {
        LogUtil.logError("=======cleanCache========");

        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ManualWebActivity.context.deleteDatabase("webview.db");
                ManualWebActivity.context.deleteDatabase("webviewCache.db");
                Toast.makeText(ManualWebActivity.context, "缓存已清除", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @JavascriptInterface
    public void modeCheck(final String mode) {
        LogUtil.logError("=======modeCheck========" + mode);

        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(ManualWebActivity.context, "执行到了modeCheck", Toast.LENGTH_SHORT).show();

                if (SharedpreferencesUtil.getHaveLocal(ManualWebActivity.context).equals("1")) {
                    SharedpreferencesUtil.setCarMode(ManualWebActivity.context, mode);
                    Intent intent = new Intent(ManuaSetActivity.context, ManualWebActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    ManuaSetActivity.context.startActivity(intent);
                    ManuaSetActivity.context.finish();
                }

            }
        });
    }

    @JavascriptInterface
    public void downloadZip() {
        LogUtil.logError("=======downloadZip========");
        ManuaSetActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                ManuaSetActivity.isUpload = false;
                ManuaApi.getInstance().manuaDownLoadZip(ManuaSetActivity.context);
            }
        });

    }

    @JavascriptInterface
    public String getModel() {
        LogUtil.logError("=======getModel========" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context));
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(ManualWebActivity.context, "执行到了getModel = " + SharedpreferencesUtil.getCarModel(ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return SharedpreferencesUtil.getCarModel(ManualWebActivity.context);

    }

    @JavascriptInterface
    public String getMode() {
        LogUtil.logError("=======getMode========" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context));
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(ManualWebActivity.context, "执行到了getMode = " + SharedpreferencesUtil.getCarMode(ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return SharedpreferencesUtil.getCarMode(ManualWebActivity.context);
    }

    @JavascriptInterface
    public void goSetPage() {
        LogUtil.logError("=======goSetPage========" + "http://www.haoweisys.com/car_engine_C217/pages/set.html?model=" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context) + "&mode=" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context));
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManualWebActivity.context, ManuaSetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                if (SharedpreferencesUtil.getCarMode(ManualWebActivity.context).equals("1")) {
//
//                    intent.putExtra("url", ManuaConfig.getManuaUrl(ManualWebActivity.context) + "/pages/set.html?model=" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context) + "&mode=" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context) + "&haveLocalPackage=" + SharedpreferencesUtil.getHaveLocal(ManualWebActivity.context) + "&version=v" + SharedpreferencesUtil.getVersion(ManualWebActivity.context) + "&upLoad=" + (ManuaConfig.VERSION.equals(SharedpreferencesUtil.getVersion(ManualWebActivity.context)) ? "0" : "1"));
//
//                } else {
//                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(ManualWebActivity.context) + SharedpreferencesUtil.getModelLocal(ManualWebActivity.context) + "/pages/set.html" + "?model=" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context) + "&mode=" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context) + "&haveLocalPackage=" + SharedpreferencesUtil.getHaveLocal(ManualWebActivity.context) + "&version=v" + SharedpreferencesUtil.getVersion(ManualWebActivity.context) + "&upLoad=" + (ManuaConfig.VERSION.equals(SharedpreferencesUtil.getVersion(ManualWebActivity.context)) ? "0" : "1"));
//
//                }
                if (SharedpreferencesUtil.getCarMode(ManualWebActivity.context).equals("1")) {
                    if (SharedpreferencesUtil.isGuest(ManualWebActivity.context)) {
                        intent.putExtra("url", ManuaConfig.getManuaUrl(ManualWebActivity.context) + "/pages/set.html?model=" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context) + "&mode=" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context) + "&haveLocalPackage=" + SharedpreferencesUtil.getHaveLocal(ManualWebActivity.context) + "&version=v" + SharedpreferencesUtil.getVersion(ManualWebActivity.context) + "&upLoad=" + (ManuaConfig.VERSION.equals(SharedpreferencesUtil.getVersion(ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", ManuaConfig.getManuaUrl(ManualWebActivity.context) + "/pages/setPhone.html?model=" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context) + "&mode=" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context) + "&haveLocalPackage=" + SharedpreferencesUtil.getHaveLocal(ManualWebActivity.context) + "&version=v" + SharedpreferencesUtil.getVersion(ManualWebActivity.context) + "&upLoad=" + (ManuaConfig.VERSION.equals(SharedpreferencesUtil.getVersion(ManualWebActivity.context)) ? "0" : "1"));
                    }


                } else {
                    if (SharedpreferencesUtil.isGuest(ManualWebActivity.context)) {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(ManualWebActivity.context) + SharedpreferencesUtil.getModelLocal(ManualWebActivity.context) + "/pages/set.html" + "?model=" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context) + "&mode=" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context) + "&haveLocalPackage=" + SharedpreferencesUtil.getHaveLocal(ManualWebActivity.context) + "&version=v" + SharedpreferencesUtil.getVersion(ManualWebActivity.context) + "&upLoad=" + (ManuaConfig.VERSION.equals(SharedpreferencesUtil.getVersion(ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(ManualWebActivity.context) + SharedpreferencesUtil.getModelLocal(ManualWebActivity.context) + "/pages/setPhone.html" + "?model=" + SharedpreferencesUtil.getCarModel(ManualWebActivity.context) + "&mode=" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context) + "&haveLocalPackage=" + SharedpreferencesUtil.getHaveLocal(ManualWebActivity.context) + "&version=v" + SharedpreferencesUtil.getVersion(ManualWebActivity.context) + "&upLoad=" + (ManuaConfig.VERSION.equals(SharedpreferencesUtil.getVersion(ManualWebActivity.context)) ? "0" : "1"));
                    }
                }
                ManualWebActivity.context.startActivity(intent);
            }
        });
    }


    @JavascriptInterface
    public void goBack() {

        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (getTopActivity(ManualWebActivity.context).toString().contains("ManuaSetActivity")) {
                    LogUtil.logError("=======goBack========" + "finish1");
//                    if (ManuaSetActivity.DOWNLOAD_STATE == ManuaSetActivity.MACHINE_STATE.DOWN_LOADING) {
//                        return;
//                    }
                    ManuaSetActivity.context.finish();
                    LogUtil.logError("=======goBack========" + "finish");
                } else {
                    LogUtil.logError("=======goBack========" + "goback1" + ManualWebActivity.webView.canGoBack());
                    if (ManualWebActivity.webView.canGoBack()) {
                        LogUtil.logError("=======goBack========" + "goback");

//                        ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
                        ManualWebActivity.webView.goBack();
//                        ManualWebActivity.webView.goBackOrForward(-1);
                    } else {
                        LogUtil.logError("=======goBack========" + "goback2");
                    }
                }
//                Toast.makeText(ManualWebActivity.context, "执行到了getMode = " + SharedpreferencesUtil.getCarMode(ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void goHome() {
        LogUtil.logError("=======goHome========");
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LogUtil.logError("=======goHome========" + getTopActivity(ManualWebActivity.context));
                if (getTopActivity(ManualWebActivity.context).toString().contains("ManuaSetActivity")) {
                    ManuaSetActivity.context.finish();
                } else {
                    while (ManualWebActivity.webView.canGoBack()) {
                        ManualWebActivity.webView.goBack();
                    }
                }


            }
        });
    }

    @JavascriptInterface
    public String exitApp() {
        LogUtil.logError("=======getMode========" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context));
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
// 为Intent设置Action、Category属性
                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                ManualWebActivity.context.startActivity(intent);
            }
        });
        return SharedpreferencesUtil.getCarMode(ManualWebActivity.context);
    }

    private static ComponentName getTopActivity(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = activityManager.getRunningTasks(Integer.MAX_VALUE);
        for (ActivityManager.RunningTaskInfo taskInfo : runningTaskInfoList) {
            if (taskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                return taskInfo.topActivity;
            }
        }
        return null;
    }

    @JavascriptInterface
    public void upLoad() {
        LogUtil.logError("=======getMode========" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context));
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                ManuaSetActivity.isUpload = true;
                ManuaApi.getInstance().manuaUpLoadZip(ManuaSetActivity.context);
            }
        });
    }

    @JavascriptInterface
    public void Mp4start(final String url) {
        LogUtil.logError("=======Mp4start========" + SharedpreferencesUtil.getCarMode(ManualWebActivity.context));
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManualWebActivity.context, ManuaPlayerActivity.class);
                if ("0".equals(SharedpreferencesUtil.getCarMode(ManualWebActivity.context))) {
                    LogUtil.logError("url = " + "file://" + LibIOUtil.getDefaultPath(ManualWebActivity.context) + SharedpreferencesUtil.getModelLocal(ManualWebActivity.context) + url);
                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(ManualWebActivity.context) + SharedpreferencesUtil.getModelLocal(ManualWebActivity.context) + "/" + url);
                } else {
                    LogUtil.logError("url = " + ManuaConfig.getManuaUrl(ManualWebActivity.context) + "/" + url);
                    intent.putExtra("url", ManuaConfig.getManuaUrl(ManualWebActivity.context) + "/" + url);
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                ManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void toast() {
        LogUtil.logError("=======goHome========");
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(ManualWebActivity.context, "==========", Toast.LENGTH_LONG).show();


            }
        });
    }

    @JavascriptInterface
    public void goARPage() {
        LogUtil.logError("=======goARPage========");
        ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManualWebActivity.context, ManuaARActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                ManualWebActivity.context.startActivity(intent);
            }
        });
    }

}
