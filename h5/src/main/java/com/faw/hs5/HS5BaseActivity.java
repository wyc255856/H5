package com.faw.hs5;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by wyouflf on 15/11/4.
 */
public abstract class HS5BaseActivity extends FragmentActivity {
    private BroadcastReceiver mScreenReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
//        if(Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            //设置状态栏颜色为透明
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        initData();
        initWidgetActions();
    }

    abstract protected void initData();


    abstract protected void initWidgetActions();




    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将广播接收器取消注册
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    protected void goActivityByClass(Class<HS5BaseActivity> mClass) {
        Intent intent = new Intent(this, mClass);
        startActivity(intent);
    }
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
