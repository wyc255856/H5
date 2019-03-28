package com.faw.h5;

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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.faw.h5.util.LibIOUtil;
import com.faw.h5.util.LogUtil;
import com.faw.h5.util.H5ManuaConfig;
import com.faw.h5.util.H5NativeInterface;
import com.faw.h5.util.H5SharedpreferencesUtil;

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

public class H5ManualWebActivity extends Activity {
    public static WebView webView;
    public static Activity context;
    private TextView textView;
    private View error_view;
    private View error_alert;
    boolean isError = false;
    public static ProgressBar downLoad_progress;
    public static H5H5StrokeTextView progress_text;
    public static View downLoad_view;
    private boolean isExit = false;
    private String url;
    //    public AppCompatImageView loading_icon;

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
        setContentView(R.layout.h5_activity_m_web);

        textView = (TextView) findViewById(R.id.text);
        error_view = findViewById(R.id.error_view);
        webView = (WebView) findViewById(R.id.web_view);
//        loading_icon = (AppCompatImageView) findViewById(R.id.loading_icon);
        progress_text = (H5H5StrokeTextView) findViewById(R.id.progress_text);
        downLoad_progress = (ProgressBar) findViewById(R.id.downLoad_progress);
        downLoad_view = findViewById(R.id.downLoad_view);
        error_alert = findViewById(R.id.error_alert);
        webView.getSettings().setAllowFileAccess(true);
        webView.setBackgroundColor(0);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    error_view.setVisibility(View.GONE);
//                    isError = false;
//                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(H5ManualWebActivity.context, H5ManuaSetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                if (H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context).equals("1")) {

                    intent.putExtra("url", H5ManuaConfig.getManuaUrl(H5ManualWebActivity.context) + "/pages/setting.html?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));

                } else {
                    intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(H5ManualWebActivity.context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.context) + "/pages/setting.html" + "?model=" + H5SharedpreferencesUtil.getCarModel(H5ManualWebActivity.context) + "&mode=" + H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.context) + "&haveLocalPackage=" + H5SharedpreferencesUtil.getHaveLocal(H5ManualWebActivity.context) + "&version=v" + H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context) + "&upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(H5ManualWebActivity.context)) ? "0" : "1"));

                }
//                Intent intent = new Intent(H5ManualWebActivity.context, ManuaARActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                H5ManualWebActivity.context.startActivity(intent);
            }
        });
//        CdCarInfoQueryManager.getInstance().setQueryCarInfoTool(new CdCarInfoQueryManager.QueryCarInfoTool() {
//            @Override
//            public boolean answerContent(String feature, String extra) {
//
//                LogUtil.logError("feature = " + feature);
//                LogUtil.logError("extra = " + extra);
//
//                Intent intent = new Intent(H5ManualWebActivity.context, H5ManuaSetActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.putExtra("url", H5ManuaConfig.getManuaUrl(H5ManualWebActivity.context) + "/pages/voiceSearch.html?model=C217&car_version=" + H5SharedpreferencesUtil.getCarModel(context) + "&keyWord=" + feature.replaceAll(" ", ","));
//                H5ManualWebActivity.context.startActivity(intent);
//                return false;
//            }
//        });
//        ToastUtils.show("设置电子手册监听");
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
                if (isError) {
                    if (webView.getUrl().equals(H5ManuaConfig.getManuaUrl(H5ManualWebActivity.this) + "?upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(context)) ? "0" : "1"))) {
                        error_view.setVisibility(View.VISIBLE);
                        error_alert.setVisibility(View.VISIBLE);
                        webView.setEnabled(true);// 当加载网页的时候将网页进行隐藏
                    }
                } else {
                    error_view.setVisibility(View.GONE);
                }
            }


            @Override

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                view.loadUrl(BrowserJsInject.fullScreenByJs(url));
//                TextView tv= (TextView) findViewById(R.id.text);
//                tv.setText("加载完成");
                if (isError) {

                    if (webView.getUrl().equals(H5ManuaConfig.getManuaUrl(H5ManualWebActivity.this) + "?upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(context)) ? "0" : "1"))) {
                        error_view.setVisibility(View.VISIBLE);
                        error_alert.setVisibility(View.VISIBLE);
                        webView.setEnabled(true);// 当加载网页的时候将网页进行隐藏
                    }
                } else {
                    error_view.setVisibility(View.GONE);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.logError("url = " + url);
                LogUtil.logError("url = " + H5ManuaConfig.getManuaUrl(H5ManualWebActivity.this));
                if (url != null) {
                    if (!url.contains("mp4")) {
                        LogUtil.logError("url = vr");
                        webView.setLayerType(View.LAYER_TYPE_NONE, null);
                        webView.setBackgroundResource(R.mipmap.h5_manua_vr_bg);
                    } else {
                        LogUtil.logError("url = LAYER_TYPE_HARDWARE");
                        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                        webView.setBackgroundResource(R.mipmap.h5_m_home_bg);
                    }
                }
                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
//                if ("0".equals(H5SharedpreferencesUtil.getCarMode(H5ManualWebActivity.this))) {
//                    LogUtil.logError("H5ManuaConfig.getManuaUrl(context) = " + H5ManuaConfig.getManuaUrl(context));
//                    view.loadUrl("file:///"+ LibIOUtil.getDefaultPath(context)+"C217_1");
//                } else {
//                    LogUtil.logError("H5ManuaConfig.getManuaUrl(context) = " + H5ManuaConfig.getManuaUrl(context));
//                    view.loadUrl(H5ManuaConfig.getManuaUrl(context));
////            webView.loadUrl("http://www.haoweisys.com/C217/C217_1");
//                }
                LogUtil.logError("url = " + url);
                LogUtil.logError("url = " + H5ManuaConfig.getManuaUrl(H5ManualWebActivity.this));
//                if (url.equals(H5ManuaConfig.getManuaUrl(H5ManualWebActivity.this) + "/") || url.equals("file:///storage/emulated/0/manua/com.wyc.zhangsan.htmlapi/C217_1/index.html")) {
//                    findViewById(R.id.back_icon).setVisibility(View.GONE);
//                } else {
//                    findViewById(R.id.back_icon).setVisibility(View.VISIBLE);
//                }
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
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        webView.addJavascriptInterface(new H5NativeInterface(), "app");


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
                H5ManualWebActivity.context.startActivity(intent);
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
//        try {
//            initSVG();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void loadUrl() {
        webView.loadUrl("file:///android_asset/index.html");

        if ("0".equals(H5SharedpreferencesUtil.getCarMode(this))) {
            LogUtil.logError("H5ManuaConfig.getManuaUrl(context) = " + "file://" + LibIOUtil.getDefaultPath(context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.this) + "/index.html" + "?upLoad=0");// + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(this)) ? "0" : "1")
            webView.loadUrl("file://" + LibIOUtil.getDefaultPath(context) + H5SharedpreferencesUtil.getModelLocal(H5ManualWebActivity.this) + "/index.html" + "?upLoad=false");// + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(this)) ? "0" : "1")
        } else {
            LogUtil.logError("H5ManuaConfig.getManuaUrl(context) = " + H5ManuaConfig.getManuaUrl(context));
//            webView.loadUrl("file://" + LibIOUtil.getDefaultPath(context) + "C217_1/index.html");
            webView.loadUrl(H5ManuaConfig.getManuaUrl(context) + "?upLoad=0");//+ (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(this)) ? "0" : "1")
//            webView.loadUrl("http://www.haoweisys.com/C217/C217_1");
        }

//        if ("0".equals(H5SharedpreferencesUtil.getCarMode(this))) {
//            webView.loadUrl("file://" + LibIOUtil.getDefaultPath(context) +  "pano/index.html" + "?upLoad=" + (H5ManuaConfig.VERSION.equals(H5SharedpreferencesUtil.getVersion(this)) ? "0" : "1"));
//        } else {
//
//            webView.loadUrl("http://www.haoweisys.com/bentengzixun/pano/");
//        }
//        webView.loadUrl("http://www.haoweisys.com/CarEV/");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
//            H5ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
            if (webView.canGoBack()) {
                error_view.setVisibility(View.GONE);
                LogUtil.logError("===============");
                H5ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
                webView.goBack(); // 后退
//                if (webView.canGoBack()) {
//                    findViewById(R.id.back_icon).setVisibility(View.VISIBLE);
//                } else {
//                    findViewById(R.id.back_icon).setVisibility(View.GONE);
//                }

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
            H5ManualWebActivity.webView.loadUrl("javascript:RemoveLocalStorage()");
            finish();
            System.exit(0);
        } else {
            isExit = true;
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitHandler.sendEmptyMessageDelayed(0, 2000);
        }
    }

    private Canvas canvas = new Canvas();

//    private void initSVG() throws IOException {
//        SVG svg = new SVGBuilder().readFromAsset(getAssets(), "loading.svg").build();
//
//        canvas.drawPicture(svg.getPicture());
//        //github上的svg.createDrawable()没有了,现在只有这个方法
//        PictureDrawable drawable = svg.getDrawable();
//        drawable.draw(canvas);
//        loading_icon.setImageDrawable(drawable);
//    }

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
            H5ManuaSetActivity.context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (H5ManuaSetActivity.unZipText != null)
                        H5ManuaSetActivity.unZipText.setText("正在解压：" + outPath);
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
    //    @Override
//
//    protected void onDestroy() {
//
//        if (webView != null) {
//
//            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//
//            webView.clearHistory();
//
//            ((ViewGroup) webView.getParent()).removeView(webView);
//
//            webView.destroy();
//
//            webView = null;
//
//        }
//
//        super.onDestroy();
//
//    }


}
