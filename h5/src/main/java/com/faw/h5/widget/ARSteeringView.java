package com.faw.h5.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.faw.h5.ManuaPlayerActivity;
import com.faw.h5.ManuaSetActivity;
import com.faw.h5.ManualWebActivity;
import com.faw.h5.R;
import com.faw.h5.util.LibIOUtil;
import com.faw.h5.util.LogUtil;
import com.faw.h5.util.ManuaConfig;
import com.faw.h5.util.NativeInterface;
import com.faw.h5.util.PhoneUtil;
import com.faw.h5.util.SharedpreferencesUtil;

import java.text.DecimalFormat;

/**
 * Created by wyc on 2018/8/1.
 */

public class ARSteeringView extends BaseARView {
    private Activity mContext;
    private ImageView candy, right;
    private TextView t0, t1, t2, t3;
    private View layout;
    private LinearLayout single_light_layout;
    private LinearLayout hongqi_layout;
    private LinearLayout control_layout;
    private LinearLayout steering_layout;
    private SensorManager MyManage; //新建sensor的管理器
    private WebView webView;
    private long timestamp = 0;

    public ARSteeringView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ARSteeringView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        // TODO Auto-generated method stub
        this.mContext = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.view_ar_steering,
                this, true);

        MyManage = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);    //获得系统传感器服务管理权
        boolean enable = MyManage.registerListener(MySensor_listener,
                MyManage.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_UI);
        single_light_layout = (LinearLayout) findViewById(R.id.single_light_layout);
        hongqi_layout = (LinearLayout) findViewById(R.id.hongqi_layout);
        control_layout = (LinearLayout) findViewById(R.id.control_layout);
        steering_layout = (LinearLayout) findViewById(R.id.steering_layout);
        t0 = (TextView) findViewById(R.id.t0);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        single_light_text = (TextView) findViewById(R.id.single_light_text);
        hongqi_text = (TextView) findViewById(R.id.hongqi_text);
        control_text = (TextView) findViewById(R.id.control_text);
        steering__text = (TextView) findViewById(R.id.steering__text);
        single_light_text.setOnClickListener(onClickListener);
        hongqi_text.setOnClickListener(onClickListener);
        control_text.setOnClickListener(onClickListener);
        steering__text.setOnClickListener(onClickListener);
        initWebView();
        reset();
    }

    private void initWebView() {
        webView = (WebView)  findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.logError("url = " + url);

                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
//
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
        webView.setBackgroundColor(0x00000000);
        webView.addJavascriptInterface(new NativeInterface(), "app");
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
    }

    TextView single_light_text, hongqi_text, control_text, steering__text;
    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.single_light_text) {
                showNormalAlert(mContext, ManuaConfig.getManuaUrl(mContext) + "/power_h5/contentBox.html");
            } else if (v.getId() == R.id.hongqi_text) {
                showNormalAlert(mContext, ManuaConfig.getManuaUrl(mContext) + "/power_h5/contentBox3.html");
            } else if (v.getId() == R.id.control_text) {
                showNormalAlert(mContext, ManuaConfig.getManuaUrl(mContext) + "/power_h5/contentBox2.html");
            } else if (v.getId() == R.id.steering__text) {
                showNormalAlert(mContext, ManuaConfig.getManuaUrl(mContext) + "/power_h5/contentBox4.html");
            }
        }
    };

    @Override
    public void reset() {
        FrameLayout.LayoutParams single_light_layout_lp = (FrameLayout.LayoutParams) single_light_layout.getLayoutParams();
        single_light_layout_lp.topMargin = (int) (PhoneUtil.getDisplayHeight(mContext) * 0.538);
        single_light_layout_lp.leftMargin = (int) (PhoneUtil.getDisplayWidth(mContext) * 0.323);
        single_light_layout.setLayoutParams(single_light_layout_lp);

        FrameLayout.LayoutParams hongqi_layout_lp = (FrameLayout.LayoutParams) hongqi_layout.getLayoutParams();
        hongqi_layout_lp.topMargin = (int) (PhoneUtil.getDisplayHeight(mContext) * 0.295);
        hongqi_layout_lp.leftMargin = (int) (PhoneUtil.getDisplayWidth(mContext) * 0.435);
        hongqi_layout.setLayoutParams(hongqi_layout_lp);


        FrameLayout.LayoutParams control_layout_lp = (FrameLayout.LayoutParams) control_layout.getLayoutParams();
        control_layout_lp.topMargin = (int) (PhoneUtil.getDisplayHeight(mContext) * 0.347);
        control_layout_lp.leftMargin = (int) (PhoneUtil.getDisplayWidth(mContext) * 0.583);
        control_layout.setLayoutParams(control_layout_lp);


        FrameLayout.LayoutParams steering_layout_lp = (FrameLayout.LayoutParams) steering_layout.getLayoutParams();
        steering_layout_lp.topMargin = (int) (PhoneUtil.getDisplayHeight(mContext) * 0.702);
        steering_layout_lp.leftMargin = (int) (PhoneUtil.getDisplayWidth(mContext) * 0.553);
        steering_layout.setLayoutParams(steering_layout_lp);
        positionXYZ = new PositionXYZ();
    }

    @Override
    public boolean webViewIsShow() {
        return webView.getVisibility() == VISIBLE;
    }

    @Override
    public void hideWebView() {
        webView.setVisibility(GONE);
    }

    public void changeUI(float x, float y, float z) {
        FrameLayout.LayoutParams single_light_layout_lp = (FrameLayout.LayoutParams) single_light_layout.getLayoutParams();
        single_light_layout_lp.topMargin = (int) (single_light_layout_lp.topMargin + y);
        single_light_layout_lp.leftMargin = (int) (single_light_layout_lp.leftMargin + x);
        single_light_layout.setLayoutParams(single_light_layout_lp);

        FrameLayout.LayoutParams hongqi_layout_lp = (FrameLayout.LayoutParams) hongqi_layout.getLayoutParams();
        hongqi_layout_lp.topMargin = (int) (hongqi_layout_lp.topMargin + y);
        hongqi_layout_lp.leftMargin = (int) (hongqi_layout_lp.topMargin + x);
        hongqi_layout.setLayoutParams(hongqi_layout_lp);


        FrameLayout.LayoutParams control_layout_lp = (FrameLayout.LayoutParams) control_layout.getLayoutParams();
        control_layout_lp.topMargin = (int) (control_layout_lp.topMargin + y);
        control_layout_lp.leftMargin = (int) (control_layout_lp.leftMargin + x);
        control_layout.setLayoutParams(control_layout_lp);


        FrameLayout.LayoutParams steering_layout_lp = (FrameLayout.LayoutParams) steering_layout.getLayoutParams();
        steering_layout_lp.topMargin = (int) (steering_layout_lp.topMargin + y);
        steering_layout_lp.leftMargin = (int) (steering_layout_lp.leftMargin + x);
        steering_layout.setLayoutParams(steering_layout_lp);
        positionXYZ = new PositionXYZ();
    }

    class PositionXYZ {
        public float x;
        public float y;
        public float z;
    }

    PositionXYZ positionXYZ;

    float NS2S = 1.0f / 1000000000.0f;
    SensorEventListener MySensor_listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor == null) {    //如果没有传感器事件则返回
                return;
            }
            //新建加速度计变化事件
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {    //传感器发生改变获取加速度计传感器的数据
                if (timestamp != 0) {
                    // 得到两次检测到手机旋转的时间差（纳秒），并将其转化为秒
//                    final float dT = (event.timestamp - timestamp) * NS2S;
//                    // 将手机在各个轴上的旋转角度相加，即可得到当前位置相对于初始位置的旋转弧度
//                    positionXYZ.x = event.values[0] * dT;
//                    positionXYZ.y = event.values[1] * dT;
//                    positionXYZ.z = event.values[2] * dT;
//                    // 将弧度转化为角度
//                    float anglex = (float) Math.toDegrees(positionXYZ.x);
//                    float angley = (float) Math.toDegrees(positionXYZ.y);
//                    float anglez = (float) Math.toDegrees(positionXYZ.z);
//
//                    if (angley > 80) {
//                        LogUtil.logError(String.valueOf(angley));
//                    }
//                    if (angley < -80) {
//                        LogUtil.logError(String.valueOf(angley));
//                    }
//                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
//

                    float x = event.values[0];

                    float y = event.values[1];

                    float z = event.values[2];

// 根据三个方向上的加速度值得到总的加速度值a

                    float a = (float) Math.sqrt(x * x + y * y + z * z);

                    System.out.println("a---------->" + a);
//                    changeUI(x,y,z);
//                    changeUI( Float.valueOf(decimalFormat.format(anglex)),  Float.valueOf(decimalFormat.format(anglez)),  Float.valueOf(decimalFormat.format(anglez)));
                    t0.setText(String.valueOf(x));
                    t1.setText(String.valueOf(y));
                    t2.setText(String.valueOf(z));
//                    t3.setText(String.valueOf(dT));
                }
                //将当前时间赋值给timestamp
                timestamp = event.timestamp;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void showNormalAlert(Context context, String url) {
        webView.loadUrl(url);
        webView.setVisibility(VISIBLE);
    }

    class ARNativeInterface {
        @JavascriptInterface
        public void selectModel(final String url) {
            mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                Toast.makeText(mContext, "执行到了downloadZip", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, ManuaPlayerActivity.class);
                    if ("0".equals(SharedpreferencesUtil.getCarMode(mContext))) {
                        LogUtil.logError("url = " + "file://" + LibIOUtil.getDefaultPath(mContext) + SharedpreferencesUtil.getModelLocal(mContext) + url);
                        intent.putExtra("url", "file://" + LibIOUtil.getDefaultPath(mContext) + SharedpreferencesUtil.getModelLocal(mContext) + "/" + url);
                    } else {
                        LogUtil.logError("url = " + ManuaConfig.getManuaUrl(mContext) + "/" + url);
                        intent.putExtra("url", ManuaConfig.getManuaUrl(mContext) + "/" + url);
                    }

                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}