package com.xi6666.main;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;


/**
 * @项目名称: DownloadTwo
 * @包名: com.downloadtwo
 * @作者: Mr.yang
 * @创建时间:2015-12-1下午6:38:12
 * @描述: 接收广播下载安装新版的app
 */
public class DownLoadBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        //下载的广播的id
        long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


        //得到我们存在sp中的id
        SharedPreferences sPreferences = context.getSharedPreferences("versiondownload", 0);
        long refernece = sPreferences.getLong("version", 0);


        //和我们保存在sp中的id进行比对
        if (refernece == myDwonloadID) {
            if (Build.VERSION.SDK_INT < 23) {
                //对app进行安装
                String serviceString = Context.DOWNLOAD_SERVICE;

                DownloadManager dManager = (DownloadManager) context.getSystemService(serviceString);

                Intent install = new Intent(Intent.ACTION_VIEW);

                Uri downloadFileUri = dManager.getUriForDownloadedFile(myDwonloadID);

                install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");

                install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.d("down", "downloadFileUri==" + downloadFileUri.toString());

                context.startActivity(install);


            } else {
                //针对手机的系统是6.0及以上需要使用该方法才能够成功
                String downloadService = Context.DOWNLOAD_SERVICE;
                DownloadManager downloadManager = (DownloadManager) context.getSystemService(downloadService);
                Uri downloadedFile = downloadManager.getUriForDownloadedFile(myDwonloadID);
                String filePath = uriToRealFilePath(context, downloadedFile);
                File file = new File(filePath);
                if (file.exists()) {
                    openFile(context, file);
                } else {
                    Toast.makeText(context, "下载失败！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /**
     * 将uri转换成String类型的真实路径
     *
     * @param context
     * @param uri
     * @return
     */
    public String uriToRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void openFile(Context context, File file) {
        Intent intent = new Intent();

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.intent.action.VIEW");
        String mimeType = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), mimeType);
        try {
            context.startActivity(intent);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(context, "抱歉，没有找到打开此类文件的程序！", Toast.LENGTH_SHORT).show();
        }
    }

    public String getMIMEType(File file) {
        String typeOne = "";
        String typeTwo = file.getName();
        String typeThr = typeTwo.substring(typeTwo.lastIndexOf(".") + 1, typeTwo.length()).toLowerCase();
        typeOne = MimeTypeMap.getSingleton().getMimeTypeFromExtension(typeThr);
        return typeOne;
    }
}