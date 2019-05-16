package com.faw.seniar9.util;

import android.content.Context;

import java.io.File;

/**
 * Created by wyc on 2018/4/22.
 */

public class FireUtil {
    public static void delete(Context context) {
        String basePath = LibIOUtil.getDefaultPath(context);
        File file = new File(basePath);
        if (EVSharedpreferencesUtil.getIsDELETE(context)) {
            deleteDir(basePath);
            EVSharedpreferencesUtil.setIsDELETE(context, false);
        }


    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
}
