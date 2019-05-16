package com.faw.hs7;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.faw.hs7.util.FireUtil;
import com.faw.hs7.util.HS7SharedpreferencesUtil;
import com.faw.h5.R;

/**
 * Created by wyc on 2018/4/19.
 */

public class HS7ManualSelecteCarActivity extends HS7BaseActivity implements View.OnClickListener {
    View spinner, spinner_layout;
    TextView spinner_text;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;
    String texr1Str = "旗悦四驱版";
    String texr2Str = "智联旗享四驱版-五";
    String texr3Str = "智联旗享四驱版-七";
    String texr4Str = "智联旗畅四驱版-五";
    String texr5Str = "智联旗畅四驱版-七";
    String texr6Str = "智联旗领四驱版-五";
    String texr7Str = "智联旗领四驱版-七";


    @Override
    protected void initData() {
        setContentView(R.layout.hs7_activity_select);
        spinner_layout = findViewById(R.id.spinner_layout);
        spinner_text = (TextView) findViewById(R.id.model);
        spinner = findViewById(R.id.spinner);
//        hs7_yes_btn = findViewById(R.id.hs7_yes_btn);
//        hs7_no_btn = findViewById(R.id.hs7_no_btn);
        initSelect();
    }


    @Override
    protected void initWidgetActions() {
//        hs7_no_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner_layout.getVisibility() == View.GONE) {
                    spinner_layout.setVisibility(View.VISIBLE);
                } else {
                    spinner_layout.setVisibility(View.GONE);
                }
            }
        });
//        hs7_yes_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private void initSelect() {
        textView1 = (TextView) findViewById(R.id.text_1);
        textView2 = (TextView) findViewById(R.id.text_2);
        textView3 = (TextView) findViewById(R.id.text_3);
        textView4 = (TextView) findViewById(R.id.text_4);
        textView5 = (TextView) findViewById(R.id.text_5);
        textView6 = (TextView) findViewById(R.id.text_6);
        textView7 = (TextView) findViewById(R.id.text_7);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView1.setText(texr1Str);
        textView2.setText(texr2Str);
        textView3.setText(texr3Str);
        textView4.setText(texr4Str);
        textView5.setText(texr5Str);
        textView6.setText(texr6Str);
        textView7.setText(texr7Str);

    }

    @Override
    public void onClick(View v) {
        spinner_layout.setVisibility(View.GONE);
        spinner_text.setText(((TextView) v).getText().toString());
        String str = spinner_text.getText().toString();
        if (str.equals(texr1Str)) {
            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "HS7_1");
        } else if (str.equals(texr2Str)) {
            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "HS7_2");
        } else if (str.equals(texr3Str)) {
            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "HS7_3");
        } else if (str.equals(texr5Str)) {
            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "HS7_4");
        } else if (str.equals(texr5Str)) {
            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "HS7_5");
        } else if (str.equals(texr6Str)) {
            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "HS7_6");
        } else if (str.equals(texr7Str)) {
            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "HS7_7");
        }
//        else if (str.equals("自动豪华")) {
//            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "C217_5");
//        } else if (str.equals("自动尊贵")) {
//            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "C217_6");
//        } else if (str.equals("自动旗舰")) {
//            HS7SharedpreferencesUtil.setCarModel(HS7ManualSelecteCarActivity.this, "C217_7");
//        }
        HS7SharedpreferencesUtil.setIsFirst(HS7ManualSelecteCarActivity.this, false);
        startActivity(new Intent(HS7ManualSelecteCarActivity.this, HS7ManualWebActivity.class));
        finish();

    }

}
