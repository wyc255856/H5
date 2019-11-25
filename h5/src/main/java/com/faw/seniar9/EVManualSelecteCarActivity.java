package com.faw.seniar9;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.faw.seniar9.util.EVSharedpreferencesUtil;
import com.faw.h5.R;

/**
 * Created by wyc on 2018/4/19.
 */

public class EVManualSelecteCarActivity extends EVBaseActivity implements View.OnClickListener {
    View spinner, spinner_layout;
    TextView spinner_text;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;

    @Override
    protected void initData() {
        setContentView(R.layout.ev_activity_select);
        spinner_layout = findViewById(R.id.spinner_layout);
        spinner_text = (TextView) findViewById(R.id.model);
        spinner = findViewById(R.id.spinner);
//        ev_m_yes_btn = findViewById(R.id.ev_m_yes_btn);
//        ev_m_no_btn = findViewById(R.id.ev_m_no_btn);
        initSelect();
    }


    @Override
    protected void initWidgetActions() {

//        ev_m_no_btn.setOnClickListener(new View.OnClickListener() {
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
//        ev_m_yes_btn.setOnClickListener(new View.OnClickListener() {
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
//        textView5 = (TextView) findViewById(R.id.text_5);
//        textView6 = (TextView) findViewById(R.id.text_6);
//        textView7 = (TextView) findViewById(R.id.text_7);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
//        textView5.setOnClickListener(this);
//        textView6.setOnClickListener(this);
//        textView7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        spinner_layout.setVisibility(View.GONE);
        spinner_text.setText(((TextView) v).getText().toString());
        String str = spinner_text.getText().toString();
        if (str.equals("两驱舒适型-南方版")) {
            EVSharedpreferencesUtil.setCarModel(EVManualSelecteCarActivity.this, "EV_1");
        } else if (str.equals("两驱舒适型-北方版")) {
            EVSharedpreferencesUtil.setCarModel(EVManualSelecteCarActivity.this, "EV_2");
        } else if (str.equals("四驱豪华型-南方版")) {
            EVSharedpreferencesUtil.setCarModel(EVManualSelecteCarActivity.this, "EV_3");
        } else if (str.equals("四驱豪华型-北方版")) {
            EVSharedpreferencesUtil.setCarModel(EVManualSelecteCarActivity.this, "EV_4");
        }
//        else if (str.equals("自动豪华")) {
//            EVSharedpreferencesUtil.setCarModel(EVManualSelecteCarActivity.this, "C217_5");
//        } else if (str.equals("自动尊贵")) {
//            EVSharedpreferencesUtil.setCarModel(EVManualSelecteCarActivity.this, "C217_6");
//        } else if (str.equals("自动旗舰")) {
//            EVSharedpreferencesUtil.setCarModel(EVManualSelecteCarActivity.this, "C217_7");
//        }
        EVSharedpreferencesUtil.setIsFirst(EVManualSelecteCarActivity.this, false);
        startActivity(new Intent(EVManualSelecteCarActivity.this, EVManualWebActivity.class));
        finish();

    }

}
