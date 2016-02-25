package com.athena.asm.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.athena.asm.aSMApplication;

public class NetworkUtil {
    private static ConnectivityManager mConnManager;

    private static void ensureManager(){
        if(mConnManager == null){
            Context context = aSMApplication.getCurrentApplication().getApplicationContext();
            mConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
    }

    public static boolean isAvailable(){
        ensureManager();
        NetworkInfo networkInfo = mConnManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }

    public static boolean isConnected(){
        ensureManager();
        NetworkInfo networkInfo = mConnManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isWifiConnected() {
        ensureManager();
        NetworkInfo networkInfo = mConnManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isMobileConnected() {
        ensureManager();
        NetworkInfo networkInfo = mConnManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static boolean isNetworkMetered(){
        ensureManager();
        NetworkInfo networkInfo = mConnManager.getActiveNetworkInfo();
        // 对于android API 16(4.1)之后的版本可以直接判断
        if(isLaterThanJellyBean()){
            return networkInfo != null && mConnManager.isActiveNetworkMetered();
        } else{
            // 对于之前的版本,如果是wifi默认返回false;如果是移动网络返回true
            return isMobileConnected();
        }
    }

    public static int getNetworkType(){
        ensureManager();
        NetworkInfo networkInfo = mConnManager.getActiveNetworkInfo();
        // TYPE_NONE            -1
        // TYPE_MOBILE           0
        // TYPE_WIFI             1
        // TYPE_BLUETOOTH        7
        // TYPE_ETHERNET         9
        // TYPE_USB_SHARE_NET    14
        if(networkInfo != null){
            return networkInfo.getType();
        }
        return -1;
    }

    public static boolean isLaterThanHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean isLaterThanJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }
}
