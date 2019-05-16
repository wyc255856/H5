package com.faw.hs7;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.faw.hs7.util.FireUtil;
import com.faw.hs7.util.HS7ManuaConfig;
import com.faw.hs7.util.HS7SharedpreferencesUtil;
import com.faw.hs7.util.LibIOUtil;
import com.faw.hs7.util.LogUtil;
import com.faw.hs7.util.NativeInterface;
import com.gh1.ghdownload.DownloadConfig;
import com.gh1.ghdownload.DownloadManager;
import com.gh1.ghdownload.entity.DownloadEntry;
import com.gh1.ghdownload.notify.DataWatcher;
import com.faw.h5.R;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

//import cn.finalteam.okhttpfinal.OkHttpFinal;
//import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by wyc on 2018/4/23.
 */

public class HS7ManuaSetActivity extends Activity {
    public static WebView webView;
    public static Activity context;
    //    private View error_view;
    private View error_alert;
    boolean isError = false;
    public static HS7MyProgressView downLoad_progress;
    public static TextView progress_text;
    public static TextView download_text;

    public static View downLoad_view;
    private boolean isExit = false;
    private String url;
    public static DownloadEntry entry;
    public static File saveFile;
    Window window;

    public static boolean isUpload = false;

    public enum MACHINE_STATE {
        NORMAL, FINISH, DOWN_LOADING
    }

    public static MACHINE_STATE DOWNLOAD_STATE = MACHINE_STATE.NORMAL;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
//        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
//        OkHttpFinal.getInstance().init(builder.build());
        window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
//        }
        setContentView(R.layout.hs7_activity_web);
        entry = new DownloadEntry(HS7ManuaConfig.getManuaDownLoadUrl(this));
        DownloadConfig.DOWNLOAD_PATH = LibIOUtil.getDefaultPath(this);

        entry.name = "hs7"+LibIOUtil.UPLOAD_ZIP_FILE;
//        error_view = findViewById(R.id.error_view);
        webView = (WebView) findViewById(R.id.web_view);
        progress_text = (TextView) findViewById(R.id.progress_text);
        download_text = (TextView) findViewById(R.id.download_text);
        downLoad_progress = (HS7MyProgressView) findViewById(R.id.downLoad_progress);
        downLoad_view = findViewById(R.id.downLoad_view);
        error_alert = findViewById(R.id.error_alert);
        webView.getSettings().setAllowFileAccess(true);
        webView.setBackgroundColor(0);
        webView.setWebChromeClient(new WebChromeClient() {


            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(HS7ManualWebActivity.context);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                showCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                hideCustomView();
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    error_view.setVisibility(View.GONE);
//                    isError = false;
//                }
            }
        });
        url = getIntent().getStringExtra("url");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                error_view.setVisibility(View.VISIBLE);
//                error_alert.setVisibility(View.GONE);
                webView.setEnabled(false);// 当加载网页的时候将网页进行隐藏
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                isError = true;
//                if (isError) {
//                    error_view.setVisibility(View.VISIBLE);
//                    error_alert.setVisibility(View.VISIBLE);
//                    webView.setEnabled(true);// 当加载网页的时候将网页进行隐藏
//                } else {
//                    error_view.setVisibility(View.GONE);
//                }
            }


            @Override

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:itemLoaderHide()");
//                if (isError) {
//                    error_view.setVisibility(View.VISIBLE);
//                    error_alert.setVisibility(View.VISIBLE);
//                    webView.setEnabled(true);// 当加载网页的时候将网页进行隐藏
//                } else {
//                    error_view.setVisibility(View.GONE);
//                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//                if ("0".equals(HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.this))) {
//                    LogUtil.logError("HS7ManuaConfig.getManuaUrl(context) = " + HS7ManuaConfig.getManuaUrl(context));
//                    view.loadUrl("file:///"+ LibIOUtil.getDefaultPath(context)+"C217_1");
//                } else {
//                    LogUtil.logError("HS7ManuaConfig.getManuaUrl(context) = " + HS7ManuaConfig.getManuaUrl(context));
//                    view.loadUrl(HS7ManuaConfig.getManuaUrl(context));
////            webView.loadUrl("http://www.haoweisys.com/C217/C217_1");
//                }
                return true;
            }
        });
        //支持App内部javascript交互
        webView.getSettings().setJavaScriptEnabled(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        //设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);


        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);

        webView.addJavascriptInterface(new NativeInterface(), "app");
        loadUrl();
        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// 为Intent设置Action、Category属性

                finish();
            }
        });
        findViewById(R.id.reload_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                error_view.setVisibility(View.VISIBLE);
//                error_alert.setVisibility(View.GONE);
//                isError = false;
//                webView.reload();
            }
        });
        findViewById(R.id.back_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadUrl() {
        LogUtil.logError("url = " + url);
        webView.loadUrl(url);
//        webView.loadUrl("http://www.haoweisys.com/C217Two/pages/set.html?model=" + HS7SharedpreferencesUtil.getCarModel(HS7ManualWebActivity.context) + "&mode=" + HS7SharedpreferencesUtil.getCarMode(HS7ManualWebActivity.context) + "&haveLocalPackage=" + HS7SharedpreferencesUtil.getHaveLocal(HS7ManualWebActivity.context)+"&upLoad=1"+"&version=v"+HS7SharedpreferencesUtil.getVersion(this));
    }


    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;

    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }

        this.getWindow().getDecorView();

        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(this);
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }

        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        webView.setVisibility(View.VISIBLE);
    }


    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                /** 回退键 事件处理 优先级:视频播放全屏-网页回退-关闭页面 */
                if (!(HS7ManuaSetActivity.DOWNLOAD_STATE == HS7ManuaSetActivity.MACHINE_STATE.DOWN_LOADING)) {
                    finish();
                }

                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    private boolean isfirst = true;

    @Override
    protected void onResume() {


        super.onResume();
        if (isfirst) {
            if (isUpload) {
                DownloadManager.getInstance(this).addObserver(updataWatcher);
            } else {
                DownloadManager.getInstance(this).addObserver(dataWatcher);
            }


            isfirst = false;
        } else {
            DownloadManager.getInstance(this).resume(entry);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        DownloadManager.getInstance(this).removeObserver(dataWatcher);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownloadManager.getInstance(this).removeObserver(dataWatcher);
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public static DataWatcher dataWatcher = new DataWatcher() {

        @Override
        public void onDataChanged(DownloadEntry data) {
            HS7ManuaSetActivity.entry = data;
            download_text.setText("正在下载离线文件...");
            download_text.setTextSize(16f);
            Log.e("tag", "data.percent = " + data.percent);
            if (data.percent == 100) {
                downLoad_progress.setProgress(99);
                progress_text.setText("99");
                HS7ManuaSetActivity.saveFile = new File(LibIOUtil.getDefaultUploadZipPath(context,"hs7"));
                //HS7ManuaSetActivity.downLoad_progress.setProgress(99);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        try {
//                            HS7ManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        if (!saveFile.exists()) {
                            return;
                        }
                        if (DOWNLOAD_STATE == MACHINE_STATE.FINISH) {
                            return;
                        }
                        try {
                            HS7ManuaSetActivity.DOWNLOAD_STATE = HS7ManuaSetActivity.MACHINE_STATE.DOWN_LOADING;
                            HS7ManualWebActivity.unZipFiles(saveFile, LibIOUtil.getDefaultPath(context));
                            ((Activity) context).runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    DOWNLOAD_STATE = MACHINE_STATE.FINISH;
                                    downLoad_progress.setProgress(100);
                                    progress_text.setText("100");
                                    downLoad_view.setVisibility(View.GONE);
                                    HS7SharedpreferencesUtil.setHaveLocal(HS7ManualWebActivity.context, "1");
                                    HS7SharedpreferencesUtil.setModelLocal(context, HS7SharedpreferencesUtil.getCarModel(context));
                                    HS7SharedpreferencesUtil.setCarMode(context, "0");
                                    HS7SharedpreferencesUtil.setVersion(context, HS7ManuaConfig.VERSION);
                                    saveFile.delete();
                                    Intent intent = new Intent(HS7ManuaSetActivity.context, HS7ManualWebActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    HS7ManuaSetActivity.context.startActivity(intent);
                                    HS7ManuaSetActivity.context.finish();
                                    Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                                    HS7SharedpreferencesUtil.setCarMode(context, "0");
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)), Charset.forName("GBK"));

                    }
                }).start();
            } else {
                downLoad_progress.setProgress(data.percent);
                progress_text.setText(data.percent + "");
            }
//                entry = data;
//                showText.setText(entry.toString());
        }
    };


    public static DataWatcher updataWatcher = new DataWatcher() {

        @Override
        public void onDataChanged(DownloadEntry data) {
            HS7ManuaSetActivity.entry = data;
            Log.e("tag", "data.percent = " + data.percent);
            if (data.percent == 100) {
                downLoad_progress.setProgress(99);
                progress_text.setText("99");
                HS7ManuaSetActivity.saveFile = new File(LibIOUtil.getDefaultUploadZipPath(context,"hs7"));
                //HS7ManuaSetActivity.downLoad_progress.setProgress(99);
                new Thread(new Runnable() {
                    @Override
                    public void run() {



                        if (!saveFile.exists()) {
                            return;
                        }


                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)), Charset.forName("GBK"));
                        ((Activity) context).runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                downLoad_progress.setProgress(100);
                                progress_text.setText("100");
                                downLoad_view.setVisibility(View.GONE);
                                HS7SharedpreferencesUtil.setHaveLocal(HS7ManualWebActivity.context, "1");
                                HS7SharedpreferencesUtil.setModelLocal(context, HS7SharedpreferencesUtil.getCarModel(context));
                                HS7SharedpreferencesUtil.setCarMode(context, "0");
                                HS7SharedpreferencesUtil.setVersion(context, HS7ManuaConfig.VERSION);
                                saveFile.delete();
                                Intent intent = new Intent(HS7ManuaSetActivity.context, HS7ManualWebActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                HS7ManuaSetActivity.context.startActivity(intent);
                                HS7ManuaSetActivity.context.finish();
                                Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
                                HS7SharedpreferencesUtil.setCarMode(context, "0");
                            }
                        });
                    }
                }).start();
            } else {
                downLoad_progress.setProgress(data.percent);
                progress_text.setText(data.percent + "");
            }
//                entry = data;
//                showText.setText(entry.toString());
        }
    };
}
