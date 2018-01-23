package com.looppay.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.looppay.ILoopPayService;
import com.looppay.ServiceData;
import com.looppay.data.IBindDeviceCallback;

/**
 * Created by hanlu.feng on 1/23/2018.
 */

public class AIDLService  extends Service{
    public final String TAG = this.getClass().getSimpleName();
    private IBindDeviceCallback mIBindDeviceCallback = null;
    private String mAccessToken = null;
    private byte[] mDeviceCertifcate = null;
    private ServiceData mServiceData = ServiceData.getInstance();
    private final ILoopPayService.Stub ILoopPayService = new ILoopPayService.Stub() {
        @Override
        public int bindDevice(String accessToken, byte[] deviceCertifcate, IBindDeviceCallback callback) throws RemoteException {
            Log.i(TAG, "Bind Service Success " );
            mIBindDeviceCallback = callback;
            mAccessToken = accessToken;
            mDeviceCertifcate = deviceCertifcate;

            mServiceData.setmIBindDeviceCallback(mIBindDeviceCallback);
            return 0;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ILoopPayService;
    }
}
