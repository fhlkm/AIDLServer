package com.loop.service;

import android.app.ActivityManager;
import android.app.Service;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import com.loop.ILoopService;
import com.loop.data.DeviceResult;
import com.loop.data.IBindDeviceCallback;

/**
 * Created by hanlu.feng on 1/23/2018.
 */

public class AIDLService  extends Service{
    public final String TAG = "AIDLService";
    public static final String KEY_RESULT_RECEIVER = "KEY_RESULT_RECEIVER";
    private IBindDeviceCallback mIBindDeviceCallback = null;
    private String mAccessToken = null;
    private byte[] mDeviceCertifcate = null;

    private final ILoopService.Stub ILoopService = new ILoopService.Stub() {
        @Override
        public int bindDevice(String accessToken, byte[] deviceCertifcate, IBindDeviceCallback callback) throws RemoteException {

            Log.i(TAG, "init mIBindDeviceCallback" );
            printCurrentServiceStatus();
            mIBindDeviceCallback = callback;
            mAccessToken = accessToken;
            mDeviceCertifcate = deviceCertifcate;

            DeviceResult deviceResult = new DeviceResult();
            deviceResult.setName("tpd");
            deviceResult.setBrand("loop");
            mIBindDeviceCallback.onSuccess(deviceResult);
            return 0;
        }
    };


    private void printCurrentServiceStatus(){
        //Check if app is in background
       if( isServiceRunningInForeground(getApplicationContext(), AIDLService.class)){
           Log.i(TAG,"app is in foreground");
       }else{
           Log.i(TAG,"app is in background");
       }
    }

    public static boolean isServiceRunningInForeground(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return service.foreground;
            }
        }
        return false;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ILoopService;
    }
}
