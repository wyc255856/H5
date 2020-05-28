package com.faw.h5.ar;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.faw.h5.H5BaseActivity;
import com.faw.h5.R;
import com.faw.h5.util.PhoneUtil;
import com.faw.h5.widget.ARSteeringView;
import com.faw.h5.widget.BaseARView;

import java.util.HashMap;

//import cn.easyar.Engine;
//import cn.easyar.ImageTarget;

/**
 * Created by wyc on 2018/7/31.
 */

public class ManuaARActivity extends H5BaseActivity {
    ImageView iv_ar_line;
    public static boolean ar_switch = true;
    public static boolean ar_hide_switch = true;
    BaseARView currentARView = null;
    ARSteeringView ar_steering_view;

    @Override
    protected void initData() {
        setContentView(R.layout.h5_activity_m_ar);
        context = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//
//        if (!Engine.initialize(this, key)) {
////            Log.e("HelloAR", "Initialization Failed.");
//        }
//
//        glView = new GLView(this);
//        glView.setArChangeListener(new GLView.ARChangeListener() {
//            @Override
//            public void onShowARImage(ImageTarget imagetarget) {
//                stopLineAnimation();
//                if (imagetarget.name().equals("方向盘")) {
//                    currentARView = ar_steering_view;
//                    ar_steering_view.reset();
//                    ar_steering_view.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onHideARImage() {
//
//                ar_steering_view.setVisibility(View.GONE);
//                startLineAnimation();
//            }
//        });
//        requestCameraPermission(new PermissionCallback() {
//            @Override
//            public void onSuccess() {
//                ((ViewGroup) findViewById(R.id.preview)).addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            }
//
//            @Override
//            public void onFailure() {
//            }
//        });
//        iv_ar_line = (ImageView) findViewById(R.id.ar_line);
//        ar_steering_view = (ARSteeringView) findViewById(R.id.ar_steering_view);
    }

    @Override
    protected void initWidgetActions() {
        startLineAnimation();
    }

    private static String key = "q9ccowfLejgFHUxZQT1OflLc7189zwbXWEtAogJalGJqGM54W45gs3UHQV1iC9b7pFM4caZGK05tYK7WyaP40yQre3QGypNJ7xiq7a5gyi4feR9lA4OnMpNDENpQdqncDF9XpDslouF5enMfymZiHSyLKubeBi6eb9heHTXBo5oz6Vb8nWMqreM0URlUbqybm7Kg3klv";
    private GLView glView;
    public static Activity context;


    private interface PermissionCallback {
        void onSuccess();

        void onFailure();
    }

    private HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<Integer, PermissionCallback>();
    private int permissionRequestCodeSerial = 0;

    @TargetApi(23)
    private void requestCameraPermission(PermissionCallback callback) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                int requestCode = permissionRequestCodeSerial;
                permissionRequestCodeSerial += 1;
                permissionCallbacks.put(requestCode, callback);
                requestPermissions(new String[]{Manifest.permission.CAMERA}, requestCode);
            } else {
                callback.onSuccess();
            }
        } else {
            callback.onSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissionCallbacks.containsKey(requestCode)) {
            PermissionCallback callback = permissionCallbacks.get(requestCode);
            permissionCallbacks.remove(requestCode);
            boolean executed = false;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    executed = true;
                    callback.onFailure();
                }
            }
            if (!executed) {
                callback.onSuccess();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (glView != null) {
            glView.onResume();
        }
    }

    @Override
    public void onPause() {
        if (glView != null) {
            glView.onPause();
        }
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ar_switch = true;
    }

    Animation translateAnimation;

    /*
    开始扫描线动画
     */
    private void startLineAnimation() {
        iv_ar_line.setVisibility(View.VISIBLE);
        translateAnimation = new TranslateAnimation(0, (float) (PhoneUtil.getDisplayWidth(this) * 0.88), 0, 0);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatMode(Animation.RESTART);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        // 固定属性的设置都是在其属性前加“set”，如setDuration（）
        iv_ar_line.startAnimation(translateAnimation);


    }

    /*
     停止扫描dong'hua
     */
    private void stopLineAnimation() {
        iv_ar_line.setVisibility(View.GONE);
        iv_ar_line.clearAnimation();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
//            H5ManualWebActivity.webView.loadUrl("javascript:closeLocalStorage()");
            if (currentARView.webViewIsShow()) {
                currentARView.hideWebView();
                return true;
            } else {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
