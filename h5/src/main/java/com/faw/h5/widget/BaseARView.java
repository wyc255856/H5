package com.faw.h5.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by wyc on 2018/8/1.
 */

public abstract class BaseARView extends LinearLayout {
    public BaseARView(Context context) {
        super(context);
    }

    public BaseARView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseARView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void reset();

    public abstract boolean webViewIsShow();

    public abstract void hideWebView();
}
