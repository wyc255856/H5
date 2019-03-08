package com.faw.h5.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.faw.h5.R;

/**
 * Created by wyc on 2018/8/1.
 */

public class ARPointView extends LinearLayout {
    private Activity mContext;
    private ImageView iv_point_out, iv_point_middle, iv_point_center;
    private TextView num;
    private View layout;

    public ARPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ARPointView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        // TODO Auto-generated method stub
        this.mContext = (Activity) context;
        LayoutInflater.from(context).inflate(R.layout.h5_view_ar_point,
                this, true);
        iv_point_center = (ImageView) findViewById(R.id.center_round);
        initAnimation();
    }
    AnimationDrawable animationDrawable;

    public void initAnimation() {

        iv_point_center.setImageResource(R.drawable.h5_m_notice_anim);
        animationDrawable = (AnimationDrawable) iv_point_center.getDrawable();
        animationDrawable.start();

    }

}