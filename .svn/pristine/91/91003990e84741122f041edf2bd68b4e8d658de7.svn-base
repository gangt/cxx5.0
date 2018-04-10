package com.xi6666.common;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

/**
 * Created by Mr_yang on 2016/12/1.
 */

public class UpVersionUtils {
    public void downLoadApk(Context context, String url, String AppName) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalPublicDir("download", AppName + ".apk");
        request.setDescription("新版车小喜");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setMimeType("application/vnd.android.package-archive");

        // 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();
        // 设置为可见和可管理
        request.setVisibleInDownloadsUi(true);

        // 将下载任务加入到下载队列里面
        long refernece = downloadManager.enqueue(request);


        // 把当前下载的ID保存在sp当中
        SharedPreferences sPreferences = context.getSharedPreferences("versiondownload", 0);
        sPreferences.edit().putLong("version", refernece).commit();
    }
}
