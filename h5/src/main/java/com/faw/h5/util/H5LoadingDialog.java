package com.faw.h5.util;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;

import com.faw.h5.R;


public class H5LoadingDialog extends Dialog {

	private AsyncTask<?, ?, ?> asyncTask;

	private H5MyProgressBar myProgressBar;

	private OnKeyCancelListener keyCancelListener;

	private int event = -1;

	public H5LoadingDialog(Context context) {
		super(context);
	}

	public H5LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public H5LoadingDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.h5_m_load_dialog);
		myProgressBar = (H5MyProgressBar) findViewById(R.id.progress_bar);
	}

	public void setEvent(int event) {
		this.event = event;
	}

	@Override
	public void show() {
		super.show();
		myProgressBar.show();
	}

	public AsyncTask<?, ?, ?> getAsyncTask() {
		return asyncTask;
	}

	public void setAsyncTask(AsyncTask<?, ?, ?> asyncTask) {
		this.asyncTask = asyncTask;
	}

	@Override
	public void cancel() {
		super.cancel();
		if (asyncTask != null) {
			asyncTask.cancel(true);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
			if (keyCancelListener != null) {
				cancel();
				keyCancelListener.cancel(H5LoadingDialog.this.event);
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void dismiss() {
		if(myProgressBar != null){
			myProgressBar.stop();
		}
		super.dismiss();
	}

	public void setOnKeyCancelListener(OnKeyCancelListener keyCancelListener) {
		this.keyCancelListener = keyCancelListener;
	}

	public interface OnKeyCancelListener {
		public void cancel(int event);
	}

}