package com.faw.hs7.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.faw.hs7.HS7ManuaApi;
import com.faw.hs7.HS7ManuaPlayerActivity;
import com.faw.hs7.HS7ManuaSetActivity;
import com.faw.hs7.HS7ManualWebActivity;

import java.util.List;


/**
 * Created by wyc on 17/4/26.
 */

public class NativeInterface {
    @JavascriptInterface
    public void selectModel(final String model) {
        LogUtil.logError("=======selectModel========" + model);
//        Toast.makeText(HS7ManualWebActivity.context, "执行到了selectModel", Toast.LENGTH_SHORT).show();
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HS7SharedpreferencesUtil.setCarModel(HS7ManualWebActivity.context, model);
                Intent intent = new Intent(HS7ManuaSetActivity.context, HS7ManualWebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                HS7ManuaSetActivity.context.startActivity(intent);
                HS7ManuaSetActivity.context.finish();
            }
        });
    }

    @JavascriptInterface
    public void cleanCache() {
        LogUtil.logError("=======cleanCache========");

        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HS7ManualWebActivity.context.deleteDatabase("webview.db");
                HS7ManualWebActivity.context.deleteDatabase("webviewCache.db");
                Toast.makeText(HS7ManualWebActivity.context, "缓存已清除", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @JavascriptInterface
    public void modeCheck(final String mode) {
        LogUtil.logError("=======modeCheck========" + mode);

        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS7ManualWebActivity.context, "执行到了modeCheck", Toast.LENGTH_SHORT).show();

                if (HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context).equals("1")) {
                    HS7SharedpreferencesUtil.setCarMode(HS7ManualWebActivity.context, mode);
//                    Intent intent = new Intent(HS7ManuaSetActivity.context, HS7ManualWebActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    HS7ManuaSetActivity.context.startActivity(intent);
                    HS7ManuaSetActivity.context.finish();
                    ((HS7ManualWebActivity) HS7ManualWebActivity.context).resetUI();
                }

            }
        });
    }

    @JavascriptInterface
    public void downloadZip() {
        LogUtil.logError("=======downloadZip========");
        HS7ManuaSetActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS7ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                HS7ManuaSetActivity.isUpload = false;
                HS7ManuaApi.getInstance().manuaDownLoadZip(HS7ManuaSetActivity.context);
            }
        });

    }

    @JavascriptInterface
    public String getModel() {
        LogUtil.logError("=======getModel========" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context));
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS7ManualWebActivity.context, "执行到了getModel = " + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context);

    }

    @JavascriptInterface
    public String getMode() {
        LogUtil.logError("=======getMode========" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context));
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS7ManualWebActivity.context, "执行到了getMode = " + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context);
    }

    @JavascriptInterface
    public void goSetPage() {
        LogUtil.logError("=======goSetPage========" + "http://www.haoweisys.com/car_engine_C217/pages/set.html?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context));
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HS7ManualWebActivity.context, HS7ManuaSetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                if (HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context).equals("1")) {
//
//                    intent.putExtra("url", HS7ManuaConfig.getManuaUrl(HS7ManualWebActivity.context) + "/pages/set.html?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context) + "&haveLocalPackage=" + HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context) + "&version=v" + HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context) + "&upLoad=" + (HS7ManuaConfig.VERSION.equals(HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context)) ? "0" : "1"));
//
//                } else {
//                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS7ManualWebActivity.context) + HS7SharedpreferencesUtil.getModelLocal(HS7ManualWebActivity.context) + "/pages/set.html" + "?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context) + "&haveLocalPackage=" + HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context) + "&version=v" + HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context) + "&upLoad=" + (HS7ManuaConfig.VERSION.equals(HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context)) ? "0" : "1"));
//
//                }
                if (HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context).equals("1")) {
                    if (HS7SharedpreferencesUtil.isGuest(HS7ManualWebActivity.context)) {
                        intent.putExtra("url", HS7ManuaConfig.getManuaUrl(HS7ManualWebActivity.context) + "/pages/set.html?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context) + "&haveLocalPackage=" + HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context) + "&version=v" + HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context) + "&upLoad=" + (HS7ManuaConfig.VERSION.equals(HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", HS7ManuaConfig.getManuaUrl(HS7ManualWebActivity.context) + "/pages/setPhone.html?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context) + "&haveLocalPackage=" + HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context) + "&version=v" + HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context) + "&upLoad=" + (HS7ManuaConfig.VERSION.equals(HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context)) ? "0" : "1"));
                    }


                } else {
                    if (HS7SharedpreferencesUtil.isGuest(HS7ManualWebActivity.context)) {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS7ManualWebActivity.context) + HS7SharedpreferencesUtil.getModelLocal(HS7ManualWebActivity.context) + "/pages/set.html" + "?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context) + "&haveLocalPackage=" + HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context) + "&version=v" + HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context) + "&upLoad=" + (HS7ManuaConfig.VERSION.equals(HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS7ManualWebActivity.context) + HS7SharedpreferencesUtil.getModelLocal(HS7ManualWebActivity.context) + "/pages/setPhone.html" + "?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context) + "&haveLocalPackage=" + HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context) + "&version=v" + HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context) + "&upLoad=" + (HS7ManuaConfig.VERSION.equals(HS7SharedpreferencesUtil.getVersion(HS7ManualWebActivity.context)) ? "0" : "1"));
                    }
                }
                HS7ManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void goBack() {

        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HS7ManualWebActivity.context, "执行到了goBack  ", Toast.LENGTH_SHORT).show();
                if (getTopActivity(HS7ManualWebActivity.context).toString().contains("HS7ManuaSetActivity")) {
                    LogUtil.logError("=======goBack========" + "finish1");
                    if (HS7ManuaSetActivity.DOWNLOAD_STATE == HS7ManuaSetActivity.MACHINE_STATE.DOWN_LOADING) {
                        return;
                    }
                    HS7ManuaSetActivity.context.finish();
                    LogUtil.logError("=======goBack========" + "finish");
                } else {
                    LogUtil.logError("=======goBack========" + "goback1" + HS7ManualWebActivity.webView.canGoBack());
                    if (HS7ManualWebActivity.webView.canGoBack()) {
                        LogUtil.logError("=======goBack========" + "goback");

//                        HS7ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
                        HS7ManualWebActivity.webView.goBack();
//                        HS7ManualWebActivity.webView.goBackOrForward(-1);
                    } else {
                        LogUtil.logError("=======goBack========" + "goback2");
                    }
                }
//                Toast.makeText(HS7ManualWebActivity.context, "执行到了getMode = " + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void goHome() {
        LogUtil.logError("=======goHome========");
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LogUtil.logError("=======goHome========" + getTopActivity(HS7ManualWebActivity.context));
                if (getTopActivity(HS7ManualWebActivity.context).toString().contains("HS7ManuaSetActivity")) {
                    HS7ManuaSetActivity.context.finish();
                } else {
                    while (HS7ManualWebActivity.webView.canGoBack()) {
                        HS7ManualWebActivity.webView.goBack();
                    }
                }


            }
        });
    }

    @JavascriptInterface
    public String exitApp() {
        LogUtil.logError("=======getMode========" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context));
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
// 为Intent设置Action、Category属性
                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                HS7ManualWebActivity.context.startActivity(intent);
            }
        });
        return HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context);
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
        LogUtil.logError("=======upLoad========" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context));
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS7ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                HS7ManuaSetActivity.isUpload = true;
                HS7ManuaApi.getInstance().manuaUpLoadZip(HS7ManuaSetActivity.context);
            }
        });
    }

    @JavascriptInterface
    public void Mp4start(final String url) {
        LogUtil.logError("=======Mp4start========" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context));
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(HS7ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HS7ManualWebActivity.context, HS7ManuaPlayerActivity.class);
                if ("0".equals(HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context))) {
                    LogUtil.logError("url = " + "file://" + LibIOUtil.getDefaultPath(HS7ManualWebActivity.context) + HS7SharedpreferencesUtil.getModelLocal(HS7ManualWebActivity.context) + url);
                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(HS7ManualWebActivity.context) + HS7SharedpreferencesUtil.getModelLocal(HS7ManualWebActivity.context) + "/" + url);
                } else {
                    LogUtil.logError("url = " + HS7ManuaConfig.getManuaUrl(HS7ManualWebActivity.context) + "/" + url);
                    intent.putExtra("url", HS7ManuaConfig.getManuaUrl(HS7ManualWebActivity.context) + "/" + url);
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                HS7ManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void toast() {
        LogUtil.logError("=======goHome========");
        HS7ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(HS7ManualWebActivity.context, "==========", Toast.LENGTH_LONG).show();


            }
        });
    }

}
