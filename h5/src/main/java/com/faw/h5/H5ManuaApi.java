package com.faw.h5;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.faw.h5.util.H5ManuaConfig;
import com.faw.h5.util.H5SharedpreferencesUtil;
import com.gh1.ghdownload.DownloadManager;


/**
 * Created by wyc on 18/3/23.
 */

public class H5ManuaApi {
    public static int CAR_MODE = 1;
    static H5ManuaApi mInstance;
    static final Object mInstanceSync = new Object();// 同步

    // 对外api
    static public H5ManuaApi getInstance() {

        synchronized (mInstanceSync) {
            if (mInstance != null) {
                return mInstance;
            }

            mInstance = new H5ManuaApi();

        }
        return mInstance;
    }

    public void initH5ManuaApi(int car_mode) {
        H5ManuaApi.CAR_MODE = car_mode;
//        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
//        OkHttpFinal.getInstance().init(builder.build());
    }

    public void manuaUpLoadZip(final Context context) {

        String url = H5ManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
//        Log.e("tag", "url = " + url);
        H5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(H5ManuaSetActivity.entry);
//        String url = H5ManuaConfig.getManuaDownLoadUrl(context);
//        final File saveFile = new File(LibIOUtil.getDefaultUploadZipPath(context));
//        Log.e("tag", "saveFile = " + saveFile);
//        Log.e("tag", "url = " + url);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                H5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    H5ManuaSetActivity.downLoad_progress.setProgress(99);
//                    H5ManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    H5ManuaSetActivity.downLoad_progress.setProgress(progress);
//                    H5ManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                H5ManuaSetActivity.downLoad_progress.setProgress(0);
//                H5ManuaSetActivity.progress_text.setText("0%");
//                H5ManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                H5ManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (H5SharedpreferencesUtil.getIsFirst(H5ManualWebActivity.context)) {
//                            FireUtil.isExist(H5ManualWebActivity.context);
//                        }
//
////                        try {
////                            H5ManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
//
//                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)), Charset.forName("GBK"));
////                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)));
//                        ((Activity) context).runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//
//                                H5ManualWebActivity.downLoad_progress.setProgress(100);
//                                H5ManualWebActivity.progress_text.setText("100%");
//                                H5ManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                H5SharedpreferencesUtil.setHaveLocal(H5ManualWebActivity.context, "1");
//                                H5SharedpreferencesUtil.setModelLocal(context, H5SharedpreferencesUtil.getCarModel(context));
//                                H5SharedpreferencesUtil.setCarMode(context, "0");
//                                H5SharedpreferencesUtil.setVersion(context, H5ManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(H5ManuaSetActivity.context, H5ManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                H5ManuaSetActivity.context.startActivity(intent);
//                                H5ManuaSetActivity.context.finish();
//                                Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }).start();
//
//
//            }
//        });
    }

    public void manuaDownLoadZip(final Context context) {

        String url = H5ManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
//        Log.e("tag", "url = " + url);
        H5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(H5ManuaSetActivity.entry);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                H5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    H5ManuaSetActivity.downLoad_progress.setProgress(99);
//                    H5ManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    H5ManuaSetActivity.downLoad_progress.setProgress(progress);
//                    H5ManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                H5ManuaSetActivity.downLoad_progress.setProgress(0);
//                H5ManuaSetActivity.progress_text.setText("0%");
//                H5ManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                H5ManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        try {
////                            H5ManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
//                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)), Charset.forName("GBK"));
//                        ((Activity) context).runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                H5ManualWebActivity.downLoad_progress.setProgress(100);
//                                H5ManualWebActivity.progress_text.setText("100%");
//                                H5ManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                H5SharedpreferencesUtil.setHaveLocal(H5ManualWebActivity.context, "1");
//                                H5SharedpreferencesUtil.setModelLocal(context, H5SharedpreferencesUtil.getCarModel(context));
//                                H5SharedpreferencesUtil.setCarMode(context, "0");
//                                H5SharedpreferencesUtil.setVersion(context, H5ManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(H5ManuaSetActivity.context, H5ManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                H5ManuaSetActivity.context.startActivity(intent);
//                                H5ManuaSetActivity.context.finish();
//                                Toast.makeText(context, "下载成功", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }).start();
//
//
//            }
//        });
    }
    public void openManua(Context context, String carModel) {

        if (TextUtils.isEmpty(carModel)) {
            H5SharedpreferencesUtil.setGuest(context, true);
            carModel="HONGQIH5_1";
        }else {
            H5SharedpreferencesUtil.setGuest(context, false);
        }
        Intent intent = new Intent(context, H5ManuaWelecomActivity.class);
        intent.putExtra("carModel", carModel);
        context.startActivity(intent);
    }

    public String changeCarModel(String model) {
        if ("CA6457-JCSMBW".equals(model) || "CA6457-JCSMCW".equals(model)) {
            return "C217_1";
        } else if ("CA64571-JCHMBW".equals(model) || "CA64571-JCHMCW".equals(model) || "CA6457-CJCHMBW".equals(model) || "CA6457-CJCHMCW".equals(model) || "CA6457-JCHMBW".equals(model) || "CA6457-JCHMCW".equals(model)) {
            return "C217_2";
        } else if ("CA64571-CJCH2MBW".equals(model) || "CA64571-CJCH2MCW".equals(model) || "CA6457-CJCH2MBW".equals(model) || "CA6457-CJCH2MCW".equals(model) || "CA6457-CJCH2MRW".equals(model)) {
            return "C217_3";
        } else if ("CA6457A1-JCSAB".equals(model) || "CA6457A-JCSAB".equals(model) || "CA6457A-JCSAC".equals(model)) {
            return "C217_4";
        } else if ("CA6457A1-JCHABW".equals(model) || "CA6457A1-JCHACW".equals(model) || "CA6457A-JCHABW".equals(model) || "CA6457A-JCHACW".equals(model)) {
            return "C217_5";
        } else if ("CA6457A1-CJCH2ABW".equals(model) || "CA6457A1-CJCH2ACW".equals(model) || "CA6457A-CJCH2ABW".equals(model) || "CA6457A-CJCH2ACW".equals(model)) {
            return "C217_6";
        } else if ("CA6457A-CJCH4ABW".equals(model) || "CA6457A-CJCH4ACW".equals(model) || "CA6457A-CJCH4ARW".equals(model)) {
            return "C217_7";
        } else {
            return "C217_1";
        }
    }
}
