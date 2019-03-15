package com.faw.seniar9.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.faw.seniar9.EVManuaApi;
import com.faw.seniar9.EVManuaPlayerActivity;
import com.faw.seniar9.EVManuaSetActivity;
import com.faw.seniar9.EVManualWebActivity;

import java.util.List;


/**
 * Created by wyc on 17/4/26.
 */

public class NativeInterface {
    @JavascriptInterface
    public void selectModel(final String model) {
        LogUtil.logError("=======selectModel========" + model);
//        Toast.makeText(EVManualWebActivity.context, "执行到了selectModel", Toast.LENGTH_SHORT).show();
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                EVSharedpreferencesUtil.setCarModel(EVManualWebActivity.context, model);
                Intent intent = new Intent(EVManuaSetActivity.context, EVManualWebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                EVManuaSetActivity.context.startActivity(intent);
                EVManuaSetActivity.context.finish();
            }
        });
    }

    @JavascriptInterface
    public void cleanCache() {
        LogUtil.logError("=======cleanCache========");

        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                EVManualWebActivity.context.deleteDatabase("webview.db");
                EVManualWebActivity.context.deleteDatabase("webviewCache.db");
                Toast.makeText(EVManualWebActivity.context, "缓存已清除", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @JavascriptInterface
    public void modeCheck(final String mode) {
        LogUtil.logError("=======modeCheck========" + mode);

        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(EVManualWebActivity.context, "执行到了modeCheck", Toast.LENGTH_SHORT).show();

                if (EVSharedpreferencesUtil.getHaveLocal(EVManualWebActivity.context).equals("1")) {
                    EVSharedpreferencesUtil.setCarMode(EVManualWebActivity.context, mode);
//                    Intent intent = new Intent(EVManuaSetActivity.context, EVManualWebActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                    EVManuaSetActivity.context.startActivity(intent);
                    EVManuaSetActivity.context.finish();
                    ((EVManualWebActivity) EVManualWebActivity.context).resetUI();
                }

            }
        });
    }

    @JavascriptInterface
    public void downloadZip() {
        LogUtil.logError("=======downloadZip========");
        EVManuaSetActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(EVManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                EVManuaSetActivity.isUpload = false;
                EVManuaApi.getInstance().manuaDownLoadZip(EVManuaSetActivity.context);
            }
        });

    }

    @JavascriptInterface
    public String getModel() {
        LogUtil.logError("=======getModel========" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context));
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(EVManualWebActivity.context, "执行到了getModel = " + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context);

    }

    @JavascriptInterface
    public String getMode() {
        LogUtil.logError("=======getMode========" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context));
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(EVManualWebActivity.context, "执行到了getMode = " + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
        return EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context);
    }

    @JavascriptInterface
    public void goSetPage() {
        LogUtil.logError("=======goSetPage========" + "http://www.haoweisys.com/car_engine_C217/pages/set.html?model=" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context) + "&mode=" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context));
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(EVManualWebActivity.context, EVManuaSetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                if (EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context).equals("1")) {
//
//                    intent.putExtra("url", EVManuaConfig.getManuaUrl(EVManualWebActivity.context) + "/pages/set.html?model=" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context) + "&mode=" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context) + "&haveLocalPackage=" + EVSharedpreferencesUtil.getHaveLocal(EVManualWebActivity.context) + "&version=v" + EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context) + "&upLoad=" + (EVManuaConfig.VERSION.equals(EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context)) ? "0" : "1"));
//
//                } else {
//                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(EVManualWebActivity.context) + EVSharedpreferencesUtil.getModelLocal(EVManualWebActivity.context) + "/pages/set.html" + "?model=" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context) + "&mode=" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context) + "&haveLocalPackage=" + EVSharedpreferencesUtil.getHaveLocal(EVManualWebActivity.context) + "&version=v" + EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context) + "&upLoad=" + (EVManuaConfig.VERSION.equals(EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context)) ? "0" : "1"));
//
//                }
                if (EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context).equals("1")) {
                    if (EVSharedpreferencesUtil.isGuest(EVManualWebActivity.context)) {
                        intent.putExtra("url", EVManuaConfig.getManuaUrl(EVManualWebActivity.context) + "/pages/set.html?model=" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context) + "&mode=" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context) + "&haveLocalPackage=" + EVSharedpreferencesUtil.getHaveLocal(EVManualWebActivity.context) + "&version=v" + EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context) + "&upLoad=" + (EVManuaConfig.VERSION.equals(EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", EVManuaConfig.getManuaUrl(EVManualWebActivity.context) + "/pages/setPhone.html?model=" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context) + "&mode=" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context) + "&haveLocalPackage=" + EVSharedpreferencesUtil.getHaveLocal(EVManualWebActivity.context) + "&version=v" + EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context) + "&upLoad=" + (EVManuaConfig.VERSION.equals(EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context)) ? "0" : "1"));
                    }


                } else {
                    if (EVSharedpreferencesUtil.isGuest(EVManualWebActivity.context)) {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(EVManualWebActivity.context) + EVSharedpreferencesUtil.getModelLocal(EVManualWebActivity.context) + "/pages/set.html" + "?model=" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context) + "&mode=" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context) + "&haveLocalPackage=" + EVSharedpreferencesUtil.getHaveLocal(EVManualWebActivity.context) + "&version=v" + EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context) + "&upLoad=" + (EVManuaConfig.VERSION.equals(EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context)) ? "0" : "1"));
                    } else {
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(EVManualWebActivity.context) + EVSharedpreferencesUtil.getModelLocal(EVManualWebActivity.context) + "/pages/setPhone.html" + "?model=" + EVSharedpreferencesUtil.getCarModel(EVManualWebActivity.context) + "&mode=" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context) + "&haveLocalPackage=" + EVSharedpreferencesUtil.getHaveLocal(EVManualWebActivity.context) + "&version=v" + EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context) + "&upLoad=" + (EVManuaConfig.VERSION.equals(EVSharedpreferencesUtil.getVersion(EVManualWebActivity.context)) ? "0" : "1"));
                    }
                }
                EVManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void goBack() {

        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(EVManualWebActivity.context, "执行到了goBack  ", Toast.LENGTH_SHORT).show();
                if (getTopActivity(EVManualWebActivity.context).toString().contains("EVManuaSetActivity")) {
                    LogUtil.logError("=======goBack========" + "finish1");
                    if (EVManuaSetActivity.DOWNLOAD_STATE == EVManuaSetActivity.MACHINE_STATE.DOWN_LOADING) {
                        return;
                    }
                    EVManuaSetActivity.context.finish();
                    LogUtil.logError("=======goBack========" + "finish");
                } else {
                    LogUtil.logError("=======goBack========" + "goback1" + EVManualWebActivity.webView.canGoBack());
                    if (EVManualWebActivity.webView.canGoBack()) {
                        LogUtil.logError("=======goBack========" + "goback");

//                        EVManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
                        EVManualWebActivity.webView.goBack();
//                        EVManualWebActivity.webView.goBackOrForward(-1);
                    } else {
                        LogUtil.logError("=======goBack========" + "goback2");
                    }
                }
//                Toast.makeText(EVManualWebActivity.context, "执行到了getMode = " + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void goHome() {
        LogUtil.logError("=======goHome========");
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LogUtil.logError("=======goHome========" + getTopActivity(EVManualWebActivity.context));
                if (getTopActivity(EVManualWebActivity.context).toString().contains("EVManuaSetActivity")) {
                    EVManuaSetActivity.context.finish();
                } else {
                    while (EVManualWebActivity.webView.canGoBack()) {
                        EVManualWebActivity.webView.goBack();
                    }
                }


            }
        });
    }

    @JavascriptInterface
    public String exitApp() {
        LogUtil.logError("=======getMode========" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context));
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
// 为Intent设置Action、Category属性
                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                EVManualWebActivity.context.startActivity(intent);
            }
        });
        return EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context);
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
        LogUtil.logError("=======upLoad========" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context));
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(EVManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                EVManuaSetActivity.isUpload = true;
                EVManuaApi.getInstance().manuaUpLoadZip(EVManuaSetActivity.context);
            }
        });
    }

    @JavascriptInterface
    public void Mp4start(final String url) {
        LogUtil.logError("=======Mp4start========" + EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context));
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(EVManualWebActivity.context, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EVManualWebActivity.context, EVManuaPlayerActivity.class);
                if ("0".equals(EVSharedpreferencesUtil.getCarMode(EVManualWebActivity.context))) {
                    LogUtil.logError("url = " + "file://" + LibIOUtil.getDefaultPath(EVManualWebActivity.context) + EVSharedpreferencesUtil.getModelLocal(EVManualWebActivity.context) + url);
                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(EVManualWebActivity.context) + EVSharedpreferencesUtil.getModelLocal(EVManualWebActivity.context) + "/" + url);
                } else {
                    LogUtil.logError("url = " + EVManuaConfig.getManuaUrl(EVManualWebActivity.context) + "/" + url);
                    intent.putExtra("url", EVManuaConfig.getManuaUrl(EVManualWebActivity.context) + "/" + url);
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                EVManualWebActivity.context.startActivity(intent);
            }
        });
    }

    @JavascriptInterface
    public void toast() {
        LogUtil.logError("=======goHome========");
        EVManualWebActivity.context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(EVManualWebActivity.context, "==========", Toast.LENGTH_LONG).show();


            }
        });
    }

}
