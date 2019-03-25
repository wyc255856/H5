package com.faw.hs7;

import android.app.Application;
import android.content.Context;

import com.baidu.che.codriversdk.InitListener;
import com.baidu.che.codriversdk.manager.CdAsrManager;
import com.baidu.che.codriversdk.manager.CdConfigManager;
import com.faw.hs7.util.LogUtil;

/**
 * Created by wyc on 18/3/26.
 */

public class HS7ManuaApp extends Application {
    public static Context sContext;//全局的Context对象
    @Override
    public void onCreate() {
        super.onCreate();sContext=this;

    }
}
