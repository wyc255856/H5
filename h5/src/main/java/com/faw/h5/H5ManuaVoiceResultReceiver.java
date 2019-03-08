package com.faw.h5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class H5ManuaVoiceResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.faw.h5.voice")) {
            Bundle result = intent.getExtras();
            String feature = result.getString("feature");
            Toast.makeText(context, feature, Toast.LENGTH_SHORT).show();
        }

    }
}
