package com.faw.hs7;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.faw.hs7.util.HS7ManuaConfig;
import com.faw.hs7.util.HS7SharedpreferencesUtil;
import com.gh1.ghdownload.DownloadManager;


/**
 * Created by wyc on 18/3/23.
 */

public class HS7ManuaApi {
    public static int CAR_MODE = 1;
    static HS7ManuaApi mInstance;
    static final Object mInstanceSync = new Object();// 同步

    // 对外api
    static public HS7ManuaApi getInstance() {

        synchronized (mInstanceSync) {
            if (mInstance != null) {
                return mInstance;
            }

            mInstance = new HS7ManuaApi();

        }
        return mInstance;
    }

    public void initHS7ManuaApi(int car_mode) {
        HS7ManuaApi.CAR_MODE = car_mode;
//        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
//        OkHttpFinal.getInstance().init(builder.build());
    }

    public void manuaUpLoadZip(final Context context) {

        String url = HS7ManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
        Log.e("tag", "url = " + url);
        HS7ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(HS7ManuaSetActivity.entry);
//        String url = HS7ManuaConfig.getManuaDownLoadUrl(context);
//        final File saveFile = new File(LibIOUtil.getDefaultUploadZipPath(context));
//        Log.e("tag", "saveFile = " + saveFile);
//        Log.e("tag", "url = " + url);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                HS7ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    HS7ManuaSetActivity.downLoad_progress.setProgress(99);
//                    HS7ManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    HS7ManuaSetActivity.downLoad_progress.setProgress(progress);
//                    HS7ManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                HS7ManuaSetActivity.downLoad_progress.setProgress(0);
//                HS7ManuaSetActivity.progress_text.setText("0%");
//                HS7ManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                HS7ManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (HS7SharedpreferencesUtil.getIsFirst(HS7ManualWebActivity.context)) {
//                            FireUtil.isExist(HS7ManualWebActivity.context);
//                        }
//
////                        try {
////                            HS7ManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
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
//                                HS7ManualWebActivity.downLoad_progress.setProgress(100);
//                                HS7ManualWebActivity.progress_text.setText("100%");
//                                HS7ManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                HS7SharedpreferencesUtil.setHaveLocal(HS7ManualWebActivity.context, "1");
//                                HS7SharedpreferencesUtil.setModelLocal(context, HS7SharedpreferencesUtil.getCarModel(context));
//                                HS7SharedpreferencesUtil.setCarMode(context, "0");
//                                HS7SharedpreferencesUtil.setVersion(context, HS7ManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(HS7ManuaSetActivity.context, HS7ManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                HS7ManuaSetActivity.context.startActivity(intent);
//                                HS7ManuaSetActivity.context.finish();
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

        String url = HS7ManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
        Log.e("tag", "url = " + url);
        HS7ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(HS7ManuaSetActivity.entry);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                HS7ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    HS7ManuaSetActivity.downLoad_progress.setProgress(99);
//                    HS7ManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    HS7ManuaSetActivity.downLoad_progress.setProgress(progress);
//                    HS7ManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                HS7ManuaSetActivity.downLoad_progress.setProgress(0);
//                HS7ManuaSetActivity.progress_text.setText("0%");
//                HS7ManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                HS7ManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        try {
////                            HS7ManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
//                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)), Charset.forName("GBK"));
//                        ((Activity) context).runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                HS7ManualWebActivity.downLoad_progress.setProgress(100);
//                                HS7ManualWebActivity.progress_text.setText("100%");
//                                HS7ManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                HS7SharedpreferencesUtil.setHaveLocal(HS7ManualWebActivity.context, "1");
//                                HS7SharedpreferencesUtil.setModelLocal(context, HS7SharedpreferencesUtil.getCarModel(context));
//                                HS7SharedpreferencesUtil.setCarMode(context, "0");
//                                HS7SharedpreferencesUtil.setVersion(context, HS7ManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(HS7ManuaSetActivity.context, HS7ManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                HS7ManuaSetActivity.context.startActivity(intent);
//                                HS7ManuaSetActivity.context.finish();
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
            HS7SharedpreferencesUtil.setGuest(context, true);
            carModel="EV_1";
        }else {
            HS7SharedpreferencesUtil.setGuest(context, false);
        }
        Intent intent = new Intent(context, HS7ManuaWelecomActivity.class);
        intent.putExtra("carModel", carModel);
        context.startActivity(intent);
    }


}
