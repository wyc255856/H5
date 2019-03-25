package com.faw.hs7;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

import com.faw.hs7.util.HS7SharedpreferencesUtil;
import com.faw.hs7.util.LoadingDialog;
import com.faw.hs7.util.LogUtil;
import com.faw.hs7.util.fullScreen;
import com.faw.h5.R;

/**
 * Created by wyc on 2018/6/7.
 */

public class HS7ManuaPlayerActivity extends HS7BaseActivity {
    private fullScreen videoView;
    protected LoadingDialog loadingDialog;
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
        setContentView(R.layout.hs7_activity_m_player);
        initLoadingDialog();
        LogUtil.logError("HS7SharedpreferencesUtil.getCarMode(this).equals(\"0\") = " + HS7SharedpreferencesUtil.getCarMode(this).equals("0"));
        if (HS7SharedpreferencesUtil.getCarMode(this).equals("1")) {
            showLoadingDialog();
        }
        //本地的视频  需要在手机SD卡根目录添加一个 fl1234.mp4 视频
        String url = getIntent().getStringExtra("url")+"";
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
                                    hideLoadingDialog();
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
        start();
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

            pause();
            finish();
        }
    }

    protected void initLoadingDialog() {
        loadingDialog = new LoadingDialog(this, R.style.hs7_load_dialog);
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

    protected void showLoadingDialog() {

        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }

    protected void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            LogUtil.logError("+++++++++");
            loadingDialog.dismiss();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        pause();
    }

    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener;

    public void start() {
        if (requestTheAudioFocus() == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //焦点获取成功，播放操作
        }else {
            //提示用户关闭其他音频再播放，不然用户以为是bug呢...
        }
    }
    public void pause() {
        releaseTheAudioFocus(mAudioFocusChangeListener);
        //暂停逻辑
    }

    //zxzhong 请求音频焦点 设置监听
    private int requestTheAudioFocus() {
        if (Build.VERSION.SDK_INT < 8) {//Android 2.2开始(API8)才有音频焦点机制
            return 0;
        }
        if (mAudioManager == null) {
            mAudioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
        if (mAudioFocusChangeListener == null) {
            mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {//监听器
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                            //播放操作
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            //暂停操作
                            break;
                        default:
                            break;
                    }
                }
            };
        }
        //下面两个常量参数试过很多 都无效，最终反编译了其他app才搞定，汗~
        int requestFocusResult = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        return requestFocusResult;
    }
    //zxzhong 暂停、播放完成或退到后台释放音频焦点
    private void releaseTheAudioFocus(AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener) {
        if (mAudioManager != null && mAudioFocusChangeListener != null) {
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
