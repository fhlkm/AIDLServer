package com.looppay.service;

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

import com.looppay.ILoopPayService;
import com.looppay.PermissionActivity;
import com.looppay.ServiceData;
import com.looppay.data.DeviceResult;
import com.looppay.data.IBindDeviceCallback;

/**
 * Created by hanlu.feng on 1/23/2018.
 */

public class AIDLService  extends Service{
    public final String TAG = this.getClass().getSimpleName();
    public static final String KEY_RESULT_RECEIVER = "KEY_RESULT_RECEIVER";
    private IBindDeviceCallback mIBindDeviceCallback = null;
    private String mAccessToken = null;
    private byte[] mDeviceCertifcate = null;
    private ServiceData mServiceData = ServiceData.getInstance();
    ResultReceiver resultReceiver = new ResultReceiver(new Handler(Looper.getMainLooper())) {
        @Override
        protected void onReceiveResult (int resultCode, Bundle resultData) {
            DeviceResult deviceResult = new DeviceResult();
            deviceResult.setName("tpd");
            deviceResult.setBrand("Looppay");
            try {
                Log.i("PERMISSION", "Bind Service Success " );
                mIBindDeviceCallback.onSuccess(deviceResult);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private final ILoopPayService.Stub ILoopPayService = new ILoopPayService.Stub() {
        @Override
        public int bindDevice(String accessToken, byte[] deviceCertifcate, IBindDeviceCallback callback) throws RemoteException {

            Log.i("PERMISSION", "init mIBindDeviceCallback" );
            mIBindDeviceCallback = callback;
            mAccessToken = accessToken;
            mDeviceCertifcate = deviceCertifcate;
            startPermissionAc();
//            mServiceData.setmIBindDeviceCallback(mIBindDeviceCallback);

//            DeviceResult deviceResult = new DeviceResult();
//            deviceResult.setName("tpd");
//            deviceResult.setBrand("Looppay");
//            mIBindDeviceCallback.onSuccess(deviceResult);
            return 0;
        }
    };

    private void startPermissionAc(){
        Intent dialogIntent = new Intent(this, PermissionActivity.class);
        dialogIntent.putExtra(KEY_RESULT_RECEIVER, resultReceiver);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ILoopPayService;
    }
}
