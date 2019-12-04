package com.yufuparty.LHR.ARShow;

import android.app.Activity;
import android.widget.Toast;

public class Unity2Android {


    public boolean showToast(String content){
        Toast.makeText(UnityPlayerActivity._unityActivity,content,Toast.LENGTH_SHORT).show();
        //这里是主动调用Unity中的方法，该方法之后unity部分会讲到
        return true;
    }
}
