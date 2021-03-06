package com.faw.h5;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

import com.faw.h5.util.H5LoadingDialog;
import com.faw.h5.util.LogUtil;
import com.faw.h5.util.H5SharedpreferencesUtil;
import com.faw.h5.util.fullScreen;

/**
 * Created by wyc on 2018/6/7.
 */

public class H5ManuaPlayerActivity extends H5BaseActivity {
    private fullScreen videoView;
    protected H5LoadingDialog loadingDialog;
    Window window;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.h5_activity_m_player);
        initH5LoadingDialog();
        LogUtil.logError("H5SharedpreferencesUtil.getCarMode(this).equals(\"0\") = " + H5SharedpreferencesUtil.getCarMode(this).equals("0"));
        if (H5SharedpreferencesUtil.getCarMode(this).equals("1")) {
            showH5LoadingDialog();
        }
        //本地的视频  需要在手机SD卡根目录添加一个 fl1234.mp4 视频
        String url = getIntent().getStringExtra("url");
        //网络视频
        Uri uri = Uri.parse(url);
        videoView = (fullScreen) this.findViewById(R.id.videoView);
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {

                        if (mp.isPlaying()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideH5LoadingDialog();
                                }
                            });

                        }
                    }
                });
            }
        });

        //设置视频路径
        videoView.setVideoURI(uri);

        //开始播放视频
        videoView.start();
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initWidgetActions() {

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            LogUtil.logDebug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            finish();
        }
    }

    protected void initH5LoadingDialog() {
        loadingDialog = new H5LoadingDialog(this, R.style.h5_load_dialog);
        loadingDialog.setCancelable(false);

        loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (loadingDialog != null && loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                        return false;
                    }
                }
                return true;
            }
        });
    }

    protected void showH5LoadingDialog() {

        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }

    protected void hideH5LoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            LogUtil.logError("+++++++++");
            loadingDialog.dismiss();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
