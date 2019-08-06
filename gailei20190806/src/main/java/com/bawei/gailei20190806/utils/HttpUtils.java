package com.bawei.gailei20190806.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * author: 盖磊
 * data: 2019/8/6 09:9:25
 * function:
 */
public class HttpUtils {


    private static HttpUtils httpUtils =null;
    private HttpUtils(){}
    public static HttpUtils getInstance(){
        if (httpUtils == null){
            synchronized (HttpUtils.class){
                if (httpUtils == null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    public interface CallBackT{
        void onSuccess(Object obj);
        void onError(String msg);
    }
    private Handler handler = new Handler();

    public static boolean isNetWork(Context context){
        if (context != null){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null){
                return info.isAvailable();
            }
        }
        return false;
    }


    public void getJson(final String path, final CallBackT callBackT){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    int code = connection.getResponseCode();
                    if (code == 200){
                        InputStream stream = connection.getInputStream();
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];
                        int len = 0;
                        while ((len = stream.read(bytes)) != -1){
                            bao.write(bytes,0,len);
                        }
                        final String json = bao.toString();
                        stream.close();
                        bao.close();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (callBackT != null){
                                    callBackT.onSuccess(json);
                                }
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (callBackT != null){
                                    callBackT.onError("无连接");
                                }
                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBackT != null){
                                callBackT.onError(e.toString());
                            }
                        }
                    });
                }
            }
        }).start();
        handler.removeCallbacksAndMessages(null);
    }


    public void getImage(final String path, final CallBackT callBackT){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    int code = connection.getResponseCode();
                    if (code == 200){
                        InputStream stream = connection.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(stream);
                        stream.close();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (callBackT != null){
                                    callBackT.onSuccess(bitmap);
                                }
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (callBackT != null){
                                    callBackT.onError("无连接");
                                }
                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBackT != null){
                                callBackT.onError(e.toString());
                            }
                        }
                    });
                }
            }
        }).start();
        handler.removeCallbacksAndMessages(null);
    }

}
