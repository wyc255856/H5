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

    @Override
    protected void initData() {
        setContentView(R.layout.hs5_activity_select);
        initSelect();
    }


    @Override
    protected void initWidgetActions() {
    }

    private void initSelect() {
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.car_1) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_1");
        } else if (v.getId() == R.id.car_2) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_2");
        } else if (v.getId() == R.id.car_3) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_3");
        } else if (v.getId() == R.id.car_4) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_4");
        } else if (v.getId() == R.id.car_5) {
            HS5SharedpreferencesUtil.setCarModel(HS5ManualSelecteCarActivity.this, "HS5_5");
        }

        HS5SharedpreferencesUtil.setIsFirst(HS5ManualSelecteCarActivity.this, false);
        startActivity(new Intent(HS5ManualSelecteCarActivity.this, HS5ManualWebActivity.class));
        finish();

    }

}
