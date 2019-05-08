package com.faw.hs5;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.faw.hs5.util.FireUtil;
import com.faw.hs5.util.HS5SharedpreferencesUtil;
import com.faw.h5.R;
/**
 * Created by wyc on 2018/4/19.
 */

public class HS5ManualSelecteCarActivity extends HS5BaseActivity implements View.OnClickListener {
    TextView   car_text;

    @Override
    protected void initData() {
        setContentView(R.layout.hs5_activity_select);
        car_text = (TextView) findViewById(R.id.car_text);
        initSelect();
    }


    @Override
    protected void initWidgetActions() {
        if (HS5SharedpreferencesUtil.getIsFirst(this)) {
            FireUtil.isExist(this);
        }
    }

    private void initSelect() {
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.car_1) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_1");
            car_text.setText("智联旗悦版");
        } else if (v.getId() == R.id.car_2) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_2");
            car_text.setText("智联旗享版");
        } else if (v.getId() == R.id.car_3) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_3");
            car_text.setText("智联四驱旗享版");
        } else if (v.getId() == R.id.car_4) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_4");
            car_text.setText("智联旗领版");
        } else if (v.getId() == R.id.car_5) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_5");
            car_text.setText("智联旗领四驱版");
        }

        HS5SharedpreferencesUtil.setIsFirst(HS5ManualSelecteCarActivity.this, false);
        startActivity(new Intent(HS5ManualSelecteCarActivity.this, HS5ManualWebActivity.class));
        finish();

    }

}
