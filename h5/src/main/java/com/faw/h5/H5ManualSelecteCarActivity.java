package com.faw.h5;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.faw.h5.util.FireUtil;
import com.faw.h5.util.H5SharedpreferencesUtil;

/**
 * Created by wyc on 2018/4/19.
 */

public class H5ManualSelecteCarActivity extends H5BaseActivity implements View.OnClickListener {
    View spinner, spinner_layout;
    TextView h5_spinner_text;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;

    @Override
    protected void initData() {
        setContentView(R.layout.h5_activity_select);
        spinner_layout = findViewById(R.id.spinner_layout);
        h5_spinner_text = (TextView) findViewById(R.id.model);
        spinner = findViewById(R.id.spinner);
//        h5_muana_yes_btn = findViewById(R.id.h5_muana_yes_btn);
        initSelect();
    }


    @Override
    protected void initWidgetActions() {
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner_layout.getVisibility() == View.GONE) {
                    spinner.setVisibility(View.GONE);
                    spinner_layout.setVisibility(View.VISIBLE);
                } else {
                    spinner_layout.setVisibility(View.GONE);
                }
            }
        });
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
    }

    @Override
    public void onClick(View v) {
        spinner_layout.setVisibility(View.GONE);
        h5_spinner_text.setText(((TextView) v).getText().toString());
        String str = h5_spinner_text.getText().toString();
        if (str.equals("型动版")) {
            H5SharedpreferencesUtil.setCarModel(H5ManualSelecteCarActivity.this, "HONGQIH5_1");
        } else if (str.equals("智联灵动版")) {
            H5SharedpreferencesUtil.setCarModel(H5ManualSelecteCarActivity.this, "HONGQIH5_4");
        } else if (str.equals("智联韵动版")) {
            H5SharedpreferencesUtil.setCarModel(H5ManualSelecteCarActivity.this, "HONGQIH5_5");
        } else if (str.equals("智联享动版")) {
            H5SharedpreferencesUtil.setCarModel(H5ManualSelecteCarActivity.this, "HONGQIH5_6");
        } else if (str.equals("智联御动版")) {
            H5SharedpreferencesUtil.setCarModel(H5ManualSelecteCarActivity.this, "HONGQIH5_7");
        } else if (str.equals("移动出行版")) {
            H5SharedpreferencesUtil.setCarModel(H5ManualSelecteCarActivity.this, "HONGQIH5_2");
        } else if (str.equals("灵动版")) {
            H5SharedpreferencesUtil.setCarModel(H5ManualSelecteCarActivity.this, "HONGQIH5_3");
        }
        H5SharedpreferencesUtil.setIsFirst(H5ManualSelecteCarActivity.this, false);
        startActivity(new Intent(H5ManualSelecteCarActivity.this, H5ManualWebActivity.class));
        finish();

    }

}
