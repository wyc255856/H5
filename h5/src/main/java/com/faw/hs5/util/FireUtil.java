package com.faw.hs5.util;

import android.content.Context;

import java.io.File;

/**
 * Created by wyc on 2018/4/22.
 */

public class FireUtil {
    public static void isExist(Context context) {
        String basePath = LibIOUtil.getDefaultPath(context);
        File file = new File(basePath  );

        deleteDir(basePath);
//        if (file1.exists()) {
//            LogUtil.logError("111111");
//            file1.delete();
//        }
//        if (file2.exists()) {
//            file2.delete();
//        }
//        if (file3.exists()) {
//            file3.delete();
//        }
//        if (file4.exists()) {
//            file4.delete();
//        }
//        if (file5.exists()) {
//            file5.delete();
//        }
//        if (file6.exists()) {
//            file6.delete();
//        }
//        if (file7.exists()) {
//            file7.delete();
//        }

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
