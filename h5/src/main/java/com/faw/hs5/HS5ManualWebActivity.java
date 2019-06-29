package com.faw.hs5;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.che.codriversdk.manager.CdCarInfoQueryManager;
import com.faw.hs5.util.LibIOUtil;
import com.faw.hs5.util.LogUtil;
import com.faw.hs5.util.HS5ManuaConfig;
import com.faw.hs5.util.HS5NativeInterface;
import com.faw.hs5.util.HS5SharedpreferencesUtil;
import com.faw.h5.R;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;


/**
 * Created by zhangsan on 2018/2/11.
 */

public class HS5ManualWebActivity extends Activity {
    public static WebView webView;
    public static Activity context;
    private View error_view;
    private View error_alert;
    boolean isError = false;
    public static TextView progress_text;
    public static View downLoad_view;
    private boolean isExit = false;
    private String url;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //得到当前界面的装饰视图
        setContentView(R.layout.hs5_activity_web);
        error_view = findViewById(R.id.error_view);
        webView = (WebView) findViewById(R.id.web_view);
//        loading_icon = (AppCompatImageView) findViewById(R.id.loading_icon);
        progress_text = (TextView) findViewById(R.id.progress_text);
        downLoad_view = findViewById(R.id.downLoad_view);
        error_alert = findViewById(R.id.error_alert);
        webView.getSettings().setAllowFileAccess(true);
        webView.setBackgroundColor(0);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        CdCarInfoQueryManager.getInstance().setQueryCarInfoTool(new CdCarInfoQueryManager.QueryCarInfoTool() {
            @Override
            public boolean answerContent(final String feature, String extra) {

                LogUtil.logError("feature = " + feature);
                LogUtil.logError("extra = " + extra);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HS5ManualWebActivity.context, HS5ManuaSetActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtra("url", HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.context) + "/pages/voiceSearch.html?model=EV&car_version=" + HS5SharedpreferencesUtil.getCarModel(context) + "&keyWord=" + feature.replaceAll(" ", ","));
                        HS5ManualWebActivity.context.startActivity(intent);

                    }
                });
                return false;
            }
        });
        url = getIntent().getStringExtra("url");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                isError = true;
                if (isError) {
//                        error_view.setVisibility(View.VISIBLE);
//                        error_alert.setVisibility(View.VISIBLE);
//                        webView.setEnabled(true);// 当加载网页的时候将网页进行隐藏
                } else {
//                    error_view.setVisibility(View.GONE);
                }
                LogUtil.logError("isError = "+isError);
            }


            @Override

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:itemLoaderHide()");
                LogUtil.logError("onPageFinished isError = "+isError);
                if (isError) {
//
//                    if (webView.getUrl().equals(HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.this) + "?upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(context)) ? "0" : "1"))) {
//                        error_view.setVisibility(View.VISIBLE);
//                        error_alert.setVisibility(View.VISIBLE);
                    isError = false;
                    webView.reload();
                    }
//                } else {
//                    error_view.setVisibility(View.GONE);
//                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.logError("url = " + url);
                LogUtil.logError("url = " + HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.this));
                if (url != null) {
                    if (!url.contains("mp4")) {
                        LogUtil.logError("url = vr");
                        webView.setLayerType(View.LAYER_TYPE_NONE, null);
                        webView.setBackgroundResource(R.mipmap.hs5_m_home_bg);
                    } else {
                        LogUtil.logError("url = LAYER_TYPE_HARDWARE");
                        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                        webView.setBackgroundResource(R.mipmap.hs5_m_home_bg);
                    }
                }
                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
                LogUtil.logError("url = " + url);
                LogUtil.logError("url = " + HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.this));
                return false;
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
        webView.setLayerType(View.LAYER_TYPE_NONE, null);
        webView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }


        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.addJavascriptInterface(new HS5NativeInterface(), "app");
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        loadUrl();
        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
// 为Intent设置Action、Category属性
                intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
                intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
                HS5ManualWebActivity.context.startActivity(intent);
            }
        });
        findViewById(R.id.reload_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error_view.setVisibility(View.VISIBLE);
                error_alert.setVisibility(View.GONE);
                isError = false;
                webView.reload();
            }
        });
        findViewById(R.id.back_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    error_view.setVisibility(View.GONE);

                    webView.goBack(); // 后退
//                    if (webView.canGoBack()) {
//                        findViewById(R.id.back_icon).setVisibility(View.VISIBLE);
//                    } else {
//                        findViewById(R.id.back_icon).setVisibility(View.GONE);
//                    }
                } else {
                    exit();
                }
            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        LogUtil.logError("!webView.hasFocus = " + !webView.hasFocus());
                        if (!webView.hasFocus()) {
                            v.requestFocusFromTouch();
                        }
                        break;
                }
                return false;
            }
        });
//        try {
//            initSVG();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void loadUrl() {

        if ("0".equals(HS5SharedpreferencesUtil.getCarMode(this))) {
            LogUtil.logError("HS5ManuaConfig.getManuaUrl(context) = " + "file://" + LibIOUtil.getDefaultPath(context) + HS5SharedpreferencesUtil.getModelLocal(HS5ManualWebActivity.this) + "/index.html" + "?upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(this)) ? "0" : "1"));
            webView.loadUrl("file://" + LibIOUtil.getDefaultPath(context) + HS5SharedpreferencesUtil.getModelLocal(HS5ManualWebActivity.this) + "/index.html" + "?upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(this)) ? "0" : "1"));
        } else {
            LogUtil.logError("HS5ManuaConfig.getManuaUrl(context) = " + HS5ManuaConfig.getManuaUrl(context));
            webView.loadUrl(HS5ManuaConfig.getManuaUrl(context) + "?upLoad=" + (HS5ManuaConfig.VERSION.equals(HS5SharedpreferencesUtil.getVersion(this)) ? "0" : "1"));
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (webView.canGoBack()) {
                error_view.setVisibility(View.GONE);
                LogUtil.logError("===============");
                HS5ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
                webView.goBack(); // 后退

                return true;
            } else {
                exit();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exit() {
        if (isExit) {
            HS5ManualWebActivity.webView.loadUrl("javascript:RemoveLocalStorage()");
            finish();
//            System.exit(0);
        } else {
            isExit = true;
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitHandler.sendEmptyMessageDelayed(0, 2000);
        }
    }

    private Canvas canvas = new Canvas();


    private Handler exitHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            isExit = false;
        }

        ;
    };


    /**
     * 解压到指定目录
     */
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    /**
     * 解压文件到指定目录
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        //解决zip文件中有中文目录或者中文文件
        ZipFile zip = new ZipFile(zipFile, "GBK");
        for (Enumeration entries = zip.getEntries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            final String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            ;
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            //输出文件路径信息
//            System.out.println(outPath);
            HS5ManuaSetActivity.context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    HS5ManuaSetActivity.download_text.setTextSize(9f);
                    if (HS5ManuaSetActivity.download_text != null)
                        HS5ManuaSetActivity.download_text.setText("正在解压：" + outPath);
                }
            });
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[8192];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void resetUI() {
        while (webView.canGoBack()) {
            webView.goBack();
        }
        loadUrl();
    }

}
