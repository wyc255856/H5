package com.faw.h5.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.faw.h5.R;

/**
 * Created by wyc on 18/3/23.
 */

public class DownloadZipAlert {
    public NumberProgressBar number_progress_bar;
    private FrameLayout layout;
    private Context mContext;

    public DownloadZipAlert(Context context) {
        LayoutInflater inflaterDl = LayoutInflater.from(context);
        layout = (FrameLayout) inflaterDl.inflate(R.layout.h5_m_alert_progress, null);
        number_progress_bar = (NumberProgressBar) layout.findViewById(R.id.number_progress_bar);
        dialog = new AlertDialog.Builder(context).create();
    }

    Dialog dialog;

    public void showAlert() {

        dialog.show();
        dialog.getWindow().setContentView(layout);

    }

    public void updateProgress(int progress) {
        number_progress_bar.setProgress(progress);
    }

    public void dismissAlert() {
        dialog.dismiss();
    }
}
