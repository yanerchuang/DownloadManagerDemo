package com.ywj.downloadmanagerdemo;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private String Tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void download(View view) {
        downLoadAPK(this, "http://dda.5lpx.com/download/Amap_V8.55.0.2282_android_C021100013835_(Build1805292055).apk");
    }

    public void downLoadAPK(Context context, String url) {
        try {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.allowScanningByMediaScanner();
            request.setVisibleInDownloadsUi(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setMimeType("application/vnd.android.package-archive");

            String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "test0626.apk";
            File file = new File(downLoadPath, fileName);
            if (file.exists()) {
                file.delete();
            }
//            request.setDestinationInExternalPublicDir(  Environment.DIRECTORY_DOWNLOADS  , fileName ) ;//miui提示找不到目录,sd卡Download目录 原生支持
//            request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, fileName);//miui提示找不到目录,data/data/包名/Download目录 ,原生支持
            request.setDestinationUri(Uri.fromFile(file));
            downloadManager.enqueue(request);
        } catch (Exception exception) {
            Log.e(Tag, "exception");
            exception.printStackTrace();
        }

    }

}

