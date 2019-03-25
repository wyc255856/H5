package com.faw.hs5;

/**
 * Created by wyc on 2018/6/11.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class HS5TargetService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //创建Intent
        Intent intent = new Intent(this, this.getClass());
        PendingIntent pendingIntent = PendingIntent.getService(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //定义开始时间
        long triggerAtTime = SystemClock.elapsedRealtime();
        //周期触发
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime, 5 * 1000, pendingIntent);


        Log.d("un", "Service onCreate");
        super.onCreate();

    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        Log.d("un", "Service onStart");
//        CdCarInfoQueryManager.getInstance().setQueryCarInfoTool(new CdCarInfoQueryManager.QueryCarInfoTool() {
//            @Override
//            public boolean answerContent(String feature, String extra) {
//
//                LogUtil.logError("feature = " + feature);
//                LogUtil.logError("extra = " + extra);
//
//                Intent intent = new Intent(HS5ManualWebActivity.context, HS5ManuaSetActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.putExtra("url", HS5ManuaConfig.getManuaUrl(HS5ManualWebActivity.context) + "/pages/search.html?keyWord=" + feature);
//                HS5ManualWebActivity.context.startActivity(intent);
//                return false;
//            }
//        });
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("un", "Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("un", "Service onDestory");
        Intent i = new Intent("com.example.service_destory");
        sendBroadcast(i);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        return super.onUnbind(intent);
    }

}