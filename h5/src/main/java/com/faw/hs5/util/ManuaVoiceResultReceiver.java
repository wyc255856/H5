package com.faw.hs5.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ManuaVoiceResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals("com.faw.hs5.voice")) {
            Bundle result = intent.getExtras();
            String feature = result.getString("feature");
            Toast.makeText(context, feature, Toast.LENGTH_SHORT).show();
        }

    }
}
