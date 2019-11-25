package com.faw.h5.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.faw.h5.H5ManuaApi;
import com.faw.h5.H5ManuaPlayerActivity;
import com.faw.h5.H5ManuaSetActivity;
import com.faw.h5.H5ManualWebActivity;
import com.faw.h5.ar.ManuaARActivity;

import java.util.List;


/**
 * Created by wyc on 17/4/26.
 */

public class H5NativeInterface {
    @JavascriptInterface
    public void selectModel(final String model) {
        LogUtil.logError("=======selectModel========" + model);
//        Toast.makeText(H5ManualWebActivity.context, "执行到了selectModel", Toast.LENGTH_SHORT).show();
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                H5SharedpreferencesUtil.setCarModel(H5ManualWebActivity.context, model);
                Intent intent = new Intent(H5ManuaSetActivity.context, H5ManualWebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                H5ManuaSetActivity.context.startActivity(intent);
                H5ManuaSetActivity.context.finish();
            }
        });
    }

    @JavascriptInterface
    public void cleanCache() {
        LogUtil.logError("=======cleanCache========");

        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                H5ManualWebActivity.context.deleteDatabase("webview.db");
                H5ManualWebActivity.context.deleteDatabase("webviewCache.db");
                Toast.makeText(H5ManualWebActivity.context, "缓存已清除", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @JavascriptInterface
    public void modeCheck(final String mode) {
        LogUtil.logError("=======modeCheck========" + mode);

        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(H5ManualWebActivity.context, "执行到了modeCheck", Toast.LENGTH_SHORT).show();

                if (H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context).equals("1")) {
                    H5SharedpreferencesUtil.setCarMode(H5ManualWebActivity.context, mode);
                    Intent intent = new Intent(H5ManuaSetActivity.context, H5ManualWebActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    H5ManuaSetActivity.context.startActivity(intent);
                    H5ManuaSetActivity.context.finish();
                }

            }
        });
    }

    @JavascriptInterface
    public void downloadZip() {
        LogUtil.logError("=======downloadZip========");
        H5ManuaSetActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(H5ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                H5ManuaSetActivity.isUpload = false;
                H5ManuaApi.getInstance().manuaDownLoadZip(H5ManuaSetActivity.context);
            }
        });

    }

    @JavascriptInterface
    public String getModel() {
        LogUtil.logError("=======getModel========" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context));
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(H5ManualWebActivity.context, "执行到了getModel = " + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context);

    }

    @JavascriptInterface
    public String getMode() {
        LogUtil.logError("=======getMode========" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context));
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(H5ManualWebActivity.context, "执行到了getMode = " + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context);
    }

    @JavascriptInterface
    public void goSetPage() {
        LogUtil.logError("=======goSetPage========" + "http://www.haoweisys.com/car_engine_C217/pages/setting.html?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context));
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(H5ManualWebActivity.context, H5ManuaSetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                if (H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context).equals("1")) {
//
//                    intent.putExtra("url", H5ManuaConfig.getManuaUrl(H5ManualWebActivity.context) + "/pages/set.html?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));
//
//                } else {
//                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(H5ManualWebActivity.context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.context) + "/pages/set.html" + "?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));
//
//                }
                if (H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context).equals("1")) {
                    if (H5SharedpreferencesUtil.isGuest(H5ManualWebActivity.context)) {
                        intent.putExtra("url", H5ManuaConfig.getManuaUrl(H5ManualWebActivity.context) + "/pages/setting.html?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", H5ManuaConfig.getManuaUrl(H5ManualWebActivity.context) + "/pages/settingPhone.html?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));
                    }


                } else {
                    if (H5SharedpreferencesUtil.isGuest(H5ManualWebActivity.context)) {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(H5ManualWebActivity.context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.context) + "/pages/setting.html" + "?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(H5ManualWebActivity.context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.context) + "/pages/settingPhone.html" + "?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));
                    }
                }
                H5ManualWebActivity.context.startActivity(intent);
            }
        });
    }


    @JavascriptInterface
    public void goBack() {

        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (getTopActivity(H5ManualWebActivity.context).toString().contains("H5ManuaSetActivity")) {
                    LogUtil.logError("=======goBack========" + "finish1");
//                    if (H5ManuaSetActivity.DOWNLOAD_STATE == H5ManuaSetActivity.MACHINE_STATE.DOWN_LOADING) {
//                        return;
//                    }
                    H5ManuaSetActivity.context.finish();
                    LogUtil.logError("=======goBack========" + "finish");
                } else {
                    LogUtil.logError("=======goBack========" + "goback1" + H5ManualWebActivity.webView.canGoBack());
                    if (H5ManualWebActivity.webView.canGoBack()) {
                        LogUtil.logError("=======goBack========" + "goback");

//                        H5ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
                        H5ManualWebActivity.webView.goBack();
//                        H5ManualWebActivity.webView.goBackOrForward(-1);
                    } else {
                        LogUtil.logError("=======goBack========" + "goback2");
                    }
                }
//                Toast.makeText(H5ManualWebActivity.context, "执行到了getMode = " + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void goHome() {
        LogUtil.logError("=======goHome========");
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LogUtil.logError("=======goHome========" + getTopActivity(H5ManualWebActivity.context));
                if (getTopActivity(H5ManualWebActivity.context).toString().contains("H5ManuaSetActivity")) {
                    H5ManuaSetActivity.context.finish();
                } else {
                    while (H5ManualWebActivity.webView.canGoBack()) {
                        H5ManualWebActivity.webView.goBack();
                    }
                }


            }
        });
    }

    @JavascriptInterface
    public String exitApp() {
        LogUtil.logError("=======getMode========" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context));
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent();
//// 为Intent设置Action、Category属性
//                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
//                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
//                H5ManualWebActivity.context.startActivity(intent);
                H5ManualWebActivity.context.finish();
            }
        });
        return H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context);
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
        LogUtil.logError("=======getMode========" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context));
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(H5ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                H5ManuaSetActivity.isUpload = true;
                H5ManuaApi.getInstance().manuaUpLoadZip(H5ManuaSetActivity.context);
            }
        });
    }

    @JavascriptInterface
    public void Mp4start(final String url) {
        LogUtil.logError("=======Mp4start========" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context));
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(H5ManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(H5ManualWebActivity.context, H5ManuaPlayerActivity.class);
                if ("0".equals(H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context))) {
                    LogUtil.logError("url = " + "file://" + LibIOUtil.getDefaultPath(H5ManualWebActivity.context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.context) + url);
                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(H5ManualWebActivity.context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.context) + "/" + url);
                } else {
                    LogUtil.logError("url = " + H5ManuaConfig.getManuaUrl(H5ManualWebActivity.context) + "/" + url);
                    intent.putExtra("url", H5ManuaConfig.getManuaUrl(H5ManualWebActivity.context) + "/" + url);
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                H5ManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void toast() {
        LogUtil.logError("=======goHome========");
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(H5ManualWebActivity.context, "==========", Toast.LENGTH_LONG).show();


            }
        });
    }

    @JavascriptInterface
    public void goARPage() {
        LogUtil.logError("=======goARPage========");
        H5ManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(H5ManualWebActivity.context, ManuaARActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                H5ManualWebActivity.context.startActivity(intent);
            }
        });
    }

}
