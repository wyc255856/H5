package com.faw.hs5;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.faw.hs5.util.HS5ManuaConfig;
import com.faw.hs5.util.HS5SharedpreferencesUtil;
import com.gh1.ghdownload.DownloadManager;


/**
 * Created by wyc on 18/3/23.
 */

public class HS5ManuaApi {
    public static int CAR_MODE = 1;
    static HS5ManuaApi mInstance;
    static final Object mInstanceSync = new Object();// 同步

    // 对外api
    static public HS5ManuaApi getInstance() {

        synchronized (mInstanceSync) {
            if (mInstance != null) {
                return mInstance;
            }

            mInstance = new HS5ManuaApi();

        }
        return mInstance;
    }

    public void initHS5ManuaApi(int car_mode) {
        HS5ManuaApi.CAR_MODE = car_mode;
//        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
//        OkHttpFinal.getInstance().init(builder.build());
    }

    public void manuaUpLoadZip(final Context context) {

        String url = HS5ManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
        Log.e("tag", "url = " + url);
        HS5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(HS5ManuaSetActivity.entry);
//        String url = HS5ManuaConfig.getManuaDownLoadUrl(context);
//        final File saveFile = new File(LibIOUtil.getDefaultUploadZipPath(context));
//        Log.e("tag", "saveFile = " + saveFile);
//        Log.e("tag", "url = " + url);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                HS5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    HS5ManuaSetActivity.downLoad_progress.setProgress(99);
//                    HS5ManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    HS5ManuaSetActivity.downLoad_progress.setProgress(progress);
//                    HS5ManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                HS5ManuaSetActivity.downLoad_progress.setProgress(0);
//                HS5ManuaSetActivity.progress_text.setText("0%");
//                HS5ManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                HS5ManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (HS5SharedpreferencesUtil.getIsFirst(HS5ManualWebActivity.context)) {
//                            FireUtil.isExist(HS5ManualWebActivity.context);
//                        }
//
////                        try {
////                            HS5ManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
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
//                                HS5ManualWebActivity.downLoad_progress.setProgress(100);
//                                HS5ManualWebActivity.progress_text.setText("100%");
//                                HS5ManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                HS5SharedpreferencesUtil.setHaveLocal(HS5ManualWebActivity.context, "1");
//                                HS5SharedpreferencesUtil.setModelLocal(context, HS5SharedpreferencesUtil.getCarModel(context));
//                                HS5SharedpreferencesUtil.setCarMode(context, "0");
//                                HS5SharedpreferencesUtil.setVersion(context, HS5ManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(HS5ManuaSetActivity.context, HS5ManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                HS5ManuaSetActivity.context.startActivity(intent);
//                                HS5ManuaSetActivity.context.finish();
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

        String url = HS5ManuaConfig.getManuaDownLoadUrl(context);

//        Log.e("tag", "saveFile = " + saveFile);
        Log.e("tag", "url = " + url);
        HS5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);

        DownloadManager.getInstance(context).add(HS5ManuaSetActivity.entry);
//        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
//            //开始下载
//            @Override
//            public void onStart() {
//                super.onStart();
//                HS5ManuaSetActivity.downLoad_view.setVisibility(View.VISIBLE);
//            }
//
//            //下载进度
//            @Override
//            public void onProgress(int progress, long networkSpeed) {
//                super.onProgress(progress, networkSpeed);
//                if (progress == 100) {
//                    HS5ManuaSetActivity.downLoad_progress.setProgress(99);
//                    HS5ManuaSetActivity.progress_text.setText("99%");
//                } else {
//                    HS5ManuaSetActivity.downLoad_progress.setProgress(progress);
//                    HS5ManuaSetActivity.progress_text.setText(progress + "%");
//                }
//                //String speed = FileUtils.generateFileSize(networkSpeed);
//            }
//
//            //下载失败
//            @Override
//            public void onFailure() {
//                super.onFailure();
//                HS5ManuaSetActivity.downLoad_progress.setProgress(0);
//                HS5ManuaSetActivity.progress_text.setText("0%");
//                HS5ManuaSetActivity.downLoad_view.setVisibility(View.GONE);
//                Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
//            }
//
//            //下载完成（下载成功）
//            @Override
//            public void onDone() {
//                super.onDone();
//                HS5ManuaSetActivity.downLoad_progress.setProgress(99);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        try {
////                            HS5ManualWebActivity.unZipFiles(LibIOUtil.getDefaultUploadZipPath(context),LibIOUtil.getDefaultPath(context));
////                        } catch (IOException e) {
////                            e.printStackTrace();
////                        }
//                        ZipUtil.unpack(saveFile, new File(LibIOUtil.getDefaultPath(context)), Charset.forName("GBK"));
//                        ((Activity) context).runOnUiThread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                // TODO Auto-generated method stub
//                                HS5ManualWebActivity.downLoad_progress.setProgress(100);
//                                HS5ManualWebActivity.progress_text.setText("100%");
//                                HS5ManualWebActivity.downLoad_view.setVisibility(View.GONE);
//                                HS5SharedpreferencesUtil.setHaveLocal(HS5ManualWebActivity.context, "1");
//                                HS5SharedpreferencesUtil.setModelLocal(context, HS5SharedpreferencesUtil.getCarModel(context));
//                                HS5SharedpreferencesUtil.setCarMode(context, "0");
//                                HS5SharedpreferencesUtil.setVersion(context, HS5ManuaConfig.VERSION);
//                                saveFile.delete();
//                                Intent intent = new Intent(HS5ManuaSetActivity.context, HS5ManualWebActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                HS5ManuaSetActivity.context.startActivity(intent);
//                                HS5ManuaSetActivity.context.finish();
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
            HS5SharedpreferencesUtil.setGuest(context, true);
            carModel="HS5_5";
        }else {
            HS5SharedpreferencesUtil.setGuest(context, false);
        }
        Intent intent = new Intent(context, HS5ManuaWelecomActivity.class);
        intent.putExtra("carModel", carModel);
        context.startActivity(intent);
    }


}
