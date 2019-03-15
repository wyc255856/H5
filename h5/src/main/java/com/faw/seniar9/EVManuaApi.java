package com.faw.seniar9;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.faw.seniar9.util.EVManuaConfig;
import com.faw.seniar9.util.EVSharedpreferencesUtil;
import com.gh1.ghdownload.DownloadManager;


/**
 * Created by wyc on 18/3/23.
 */

public class EVManuaApi {
    public static int CAR_MODE = 1;
    static EVManuaApi mInstance;
    static final Object mInstanceSync = new Object();// 同步

    // 对外api
    static public EVManuaApi getInstance() {

        synchronized (mInstanceSync) {
            if (mInstance != null) {
                return mInstance;
            }

            mInstance = new EVManuaApi();

        }
        return mInstance;
    }

    public void initEVManuaApi(int car_mode) {
        EVManuaApi.CAR_MODE = car_mode;
//        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
//        OkHttpFinal.getInstance().init(builder.build());
    }

    public void manuaUpLoadZip(final Context context) {

        String url = EVManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
        Log.e("tag", "url = " + url);
        EVManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(EVManuaSetActivity.entry);
//        String url = EVManuaConfig.getManuaDownLoadUrl(context);
//        final File saveFile = new File(LibIOUtil.getDefaultUploadZipPath(context));
//        Log.e("tag", "saveFile = " + saveFile);
//        Log.e("tag", "url = " + url);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                EVManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    EVManuaSetActivity.downLoad_progress.setProgress(99);
//                    EVManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    EVManuaSetActivity.downLoad_progress.setProgress(progress);
//                    EVManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                EVManuaSetActivity.downLoad_progress.setProgress(0);
//                EVManuaSetActivity.progress_text.setText("0%");
//                EVManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                EVManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (EVSharedpreferencesUtil.getIsFirst(EVManualWebActivity.context)) {
//                            FireUtil.isExist(EVManualWebActivity.context);
//                        }
//
////                        try {
////                            EVManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
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
//                                EVManualWebActivity.downLoad_progress.setProgress(100);
//                                EVManualWebActivity.progress_text.setText("100%");
//                                EVManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                EVSharedpreferencesUtil.setHaveLocal(EVManualWebActivity.context, "1");
//                                EVSharedpreferencesUtil.setModelLocal(context, EVSharedpreferencesUtil.getCarModel(context));
//                                EVSharedpreferencesUtil.setCarMode(context, "0");
//                                EVSharedpreferencesUtil.setVersion(context, EVManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(EVManuaSetActivity.context, EVManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                EVManuaSetActivity.context.startActivity(intent);
//                                EVManuaSetActivity.context.finish();
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

        String url = EVManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
        Log.e("tag", "url = " + url);
        EVManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(EVManuaSetActivity.entry);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                EVManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    EVManuaSetActivity.downLoad_progress.setProgress(99);
//                    EVManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    EVManuaSetActivity.downLoad_progress.setProgress(progress);
//                    EVManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                EVManuaSetActivity.downLoad_progress.setProgress(0);
//                EVManuaSetActivity.progress_text.setText("0%");
//                EVManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                EVManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        try {
////                            EVManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
//                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)), Charset.forName("GBK"));
//                        ((Activity) context).runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                EVManualWebActivity.downLoad_progress.setProgress(100);
//                                EVManualWebActivity.progress_text.setText("100%");
//                                EVManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                EVSharedpreferencesUtil.setHaveLocal(EVManualWebActivity.context, "1");
//                                EVSharedpreferencesUtil.setModelLocal(context, EVSharedpreferencesUtil.getCarModel(context));
//                                EVSharedpreferencesUtil.setCarMode(context, "0");
//                                EVSharedpreferencesUtil.setVersion(context, EVManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(EVManuaSetActivity.context, EVManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                EVManuaSetActivity.context.startActivity(intent);
//                                EVManuaSetActivity.context.finish();
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
            EVSharedpreferencesUtil.setGuest(context, true);
            carModel="EV_1";
        }else {
            EVSharedpreferencesUtil.setGuest(context, false);
        }
        Intent intent = new Intent(context, EVManuaWelecomActivity.class);
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
