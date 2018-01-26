package com.loop.service;

import android.app.Service;
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





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ILoopService;
    }
}
