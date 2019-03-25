package com.faw.hs5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wyc on 2018/6/11.
 */

public class HS5PushReciver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
//        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
//            Log.d("un", "开机启动");
//        }else if(Intent.ACTION_USER_PRESENT.equals(intent.getAction())){
//            Log.d("un", "手机被唤醒");
//            Intent i = new Intent();
//            i.setClass(context, HS5TargetService.class);
//            context.startService(i);
//        }else if("com.example.service_destory".equals(intent.getAction())){
//            Log.d("un", "上次服务被挂了");
//            Intent i = new Intent();
//            i.setClass(context, HS5TargetService.class);
//            context.startService(i);
//        }else if("com.example.clock".equals(intent.getAction())){
//            Log.d("un", "定时闹钟的广播");
//            Intent i = new Intent();
//            i.setClass(context, HS5TargetService.class);
//            context.startService(i);
//        }
    }
}
