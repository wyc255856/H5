package com.faw.hs5.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.faw.hs5.HS5ManuaApi;
import com.faw.hs5.HS5ManuaPlayerActivity;
import com.faw.hs5.HS5ManuaSetActivity;
import com.faw.hs5.HS5ManualWebActivity;

import java.util.List;


/**
 * Created by wyc on 17/4/26.
 */

public class HS5NativeInterface {
    @JavascriptInterface
    public void selectModel(final String model) {
        LogUtil.logError("=======selectModel========" + model);
//        Toast.makeText(HS5ManualWebActivity.context, "执行到了selectModel", Toast.LENGTH_SHORT).show();
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HS5SharedpreferencesUtil.setCarModel(HS5ManualWebActivity.context, model);
                Intent intent = new Intent(HS5ManuaSetActivity.context, HS5ManualWebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                HS5ManuaSetActivity.context.startActivity(intent);
                HS5ManuaSetActivity.context.finish();
            }
        });
    }

    @JavascriptInterface
    public void cleanCache() {
        LogUtil.logError("=======cleanCache========");

        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HS5ManualWebActivity.context.deleteDatabase("webview.db");
                HS5ManualWebActivity.context.deleteDatabase("webviewCache.db");
                Toast.makeText(HS5ManualWebActivity.context, "缓存已清除", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @JavascriptInterface
    public void modeCheck(final String mode) {
        LogUtil.logError("=======modeCheck========" + mode);

        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS5ManualWebActivity.context, "执行到了modeCheck", Toast.LENGTH_SHORT).show();

                if (HS5SharedpreferencesUtil.getHaveLocal(HS5ManualWebActivity.context).equals("1")) {
                    HS5SharedpreferencesUtil.setCarMode(HS5ManualWebActivity.context, mode);
//                    Intent intent = new Intent(HS5ManuaSetActivity.context, HS5ManualWebActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    HS5ManuaSetActivity.context.startActivity(intent);
                    HS5ManuaSetActivity.context.finish();
                    ((HS5ManualWebActivity) HS5ManualWebActivity.context).resetUI();
                }

            }
        });
    }

    @JavascriptInterface
    public void downloadZip() {
        LogUtil.logError("=======downloadZip========");
        HS5ManuaSetActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS5ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                HS5ManuaSetActivity.isUpload = false;
                HS5ManuaApi.getInstance().manuaDownLoadZip(HS5ManuaSetActivity.context);
            }
        });

    }

    @JavascriptInterface
    public String getModel() {
        LogUtil.logError("=======getModel========" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context));
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS5ManualWebActivity.context, "执行到了getModel = " + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context);

    }

    @JavascriptInterface
    public String getMode() {
        LogUtil.logError("=======getMode========" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context));
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS5ManualWebActivity.context, "执行到了getMode = " + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context);
    }

    @JavascriptInterface
    public void goSetPage() {
        LogUtil.logError("=======goSetPage========" + "http://www.haoweisys.com/car_engine_C217/pages/set.html?model=" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context) + "&mode=" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context));
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HS5ManualWebActivity.context, HS5ManuaSetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                if (HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context).equals("1")) {
//
//                    intent.putExtra("url", HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.context) + "/pages/set.html?model=" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context) + "&mode=" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context) + "&haveLocalPackage=" + HS5SharedpreferencesUtil.getHaveLocal(HS5ManualWebActivity.context) + "&version=v" + HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context) + "&upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context)) ? "0" : "1"));
//
//                } else {
//                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS5ManualWebActivity.context) + HS5SharedpreferencesUtil.getModelLocal(HS5ManualWebActivity.context) + "/pages/set.html" + "?model=" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context) + "&mode=" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context) + "&haveLocalPackage=" + HS5SharedpreferencesUtil.getHaveLocal(HS5ManualWebActivity.context) + "&version=v" + HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context) + "&upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context)) ? "0" : "1"));
//
//                }
                if (HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context).equals("1")) {
                    if (HS5SharedpreferencesUtil.isGuest(HS5ManualWebActivity.context)) {
                        intent.putExtra("url", HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.context) + "/pages/set.html?model=" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context) + "&mode=" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context) + "&haveLocalPackage=" + HS5SharedpreferencesUtil.getHaveLocal(HS5ManualWebActivity.context) + "&version=v" + HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context) + "&upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.context) + "/pages/setPhone.html?model=" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context) + "&mode=" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context) + "&haveLocalPackage=" + HS5SharedpreferencesUtil.getHaveLocal(HS5ManualWebActivity.context) + "&version=v" + HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context) + "&upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context)) ? "0" : "1"));
                    }


                } else {
                    if (HS5SharedpreferencesUtil.isGuest(HS5ManualWebActivity.context)) {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS5ManualWebActivity.context) + HS5SharedpreferencesUtil.getModelLocal(HS5ManualWebActivity.context) + "/pages/set.html" + "?model=" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context) + "&mode=" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context) + "&haveLocalPackage=" + HS5SharedpreferencesUtil.getHaveLocal(HS5ManualWebActivity.context) + "&version=v" + HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context) + "&upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS5ManualWebActivity.context) + HS5SharedpreferencesUtil.getModelLocal(HS5ManualWebActivity.context) + "/pages/setPhone.html" + "?model=" + HS5SharedpreferencesUtil.getCarModel(HS5ManualWebActivity.context) + "&mode=" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context) + "&haveLocalPackage=" + HS5SharedpreferencesUtil.getHaveLocal(HS5ManualWebActivity.context) + "&version=v" + HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context) + "&upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(HS5ManualWebActivity.context)) ? "0" : "1"));
                    }
                }
                HS5ManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void goBack() {

        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HS5ManualWebActivity.context, "执行到了goBack  ", Toast.LENGTH_SHORT).show();
                if (getTopActivity(HS5ManualWebActivity.context).toString().contains("HS5ManuaSetActivity")) {
                    LogUtil.logError("=======goBack========" + "finish1");
                    if (HS5ManuaSetActivity.DOWNLOAD_STATE == HS5ManuaSetActivity.MACHINE_STATE.DOWN_LOADING) {
                        return;
                    }
                    HS5ManuaSetActivity.context.finish();
                    LogUtil.logError("=======goBack========" + "finish");
                } else {
                    LogUtil.logError("=======goBack========" + "goback1" + HS5ManualWebActivity.webView.canGoBack());
                    if (HS5ManualWebActivity.webView.canGoBack()) {
                        LogUtil.logError("=======goBack========" + "goback");

//                        HS5ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
                        HS5ManualWebActivity.webView.goBack();
//                        HS5ManualWebActivity.webView.goBackOrForward(-1);
                    } else {
                        LogUtil.logError("=======goBack========" + "goback2");
                    }
                }
//                Toast.makeText(HS5ManualWebActivity.context, "执行到了getMode = " + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void goHome() {
        LogUtil.logError("=======goHome========");
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LogUtil.logError("=======goHome========" + getTopActivity(HS5ManualWebActivity.context));
                if (getTopActivity(HS5ManualWebActivity.context).toString().contains("HS5ManuaSetActivity")) {
                    HS5ManuaSetActivity.context.finish();
                } else {
                    while (HS5ManualWebActivity.webView.canGoBack()) {
                        HS5ManualWebActivity.webView.goBack();
                    }
                }


            }
        });
    }

    @JavascriptInterface
    public String exitApp() {
        LogUtil.logError("=======getMode========" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context));
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
// 为Intent设置Action、Category属性
                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                HS5ManualWebActivity.context.startActivity(intent);
            }
        });
        return HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context);
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
        LogUtil.logError("=======upLoad========" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context));
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS5ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                HS5ManuaSetActivity.isUpload = true;
                HS5ManuaApi.getInstance().manuaUpLoadZip(HS5ManuaSetActivity.context);
            }
        });
    }

    @JavascriptInterface
    public void Mp4start(final String url) {
        LogUtil.logError("=======Mp4start========" + HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context));
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS5ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HS5ManualWebActivity.context, HS5ManuaPlayerActivity.class);
                if ("0".equals(HS5SharedpreferencesUtil.getCarMode(HS5ManualWebActivity.context))) {
                    LogUtil.logError("url = " + "file://" + LibIOUtil.getDefaultPath(HS5ManualWebActivity.context) + HS5SharedpreferencesUtil.getModelLocal(HS5ManualWebActivity.context) + url);
                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS5ManualWebActivity.context) + HS5SharedpreferencesUtil.getModelLocal(HS5ManualWebActivity.context) + "/" + url);
                } else {
                    LogUtil.logError("url = " + HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.context) + "/" + url);
                    intent.putExtra("url", HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.context) + "/" + url);
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                HS5ManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void toast() {
        LogUtil.logError("=======goHome========");
        HS5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(HS5ManualWebActivity.context, "==========", Toast.LENGTH_LONG).show();


            }
        });
    }

}
