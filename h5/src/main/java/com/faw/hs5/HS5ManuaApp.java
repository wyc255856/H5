package com.faw.hs5;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.baidu.che.codriversdk.InitListener;
import com.baidu.che.codriversdk.manager.CdAsrManager;
import com.baidu.che.codriversdk.manager.CdConfigManager;
import com.faw.hs5.util.LogUtil;

/**
 * Created by wyc on 18/3/26.
 */

public class HS5ManuaApp extends Application {
    public static Context sContext;//全局的Context对象
    @Override
    public void onCreate() {
        super.onCreate();sContext=this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        CdConfigManager.getInstance().initialize(getApplicationContext(), new InitListener() {
            @Override
            public void onConnectedToRemote() {
                // 与DuerOS连接成功：可以调用定制化接口
                // 这里初始化各种模块，进行功能定制
                // 建议将所有的sdk配置相关的都放到这里
                // 切不要重复配置，否则将可能引起一些未知的问题

                /**
                 * 打开sdk日志
                 */
                CdConfigManager.getInstance().openLog(true);

                /**
                 * 设置语音流程相关回调
                 */
                CdAsrManager.getInstance().setAsrTool(new CdAsrManager.AsrTool() {
                    @Override
                    public void onVrDialogShow() {
                        LogUtil.logError("显示对话流界面");
                        CdAsrManager.getInstance().openDialog("显示对话流界面");
                    }

                    @Override
                    public void onVrDialogDismiss() {

                        LogUtil.logError("退出对话流界面");
                        CdAsrManager.getInstance().closeDialog();
                    }

                    @Override
                    public void onWakeUp() {

                    }
                });
            }

            @Override
            public void onDisconnectedToRemote() {
                // 与DuerOS连接断开：可以做一些清理工作
            }
        });
    }
}
