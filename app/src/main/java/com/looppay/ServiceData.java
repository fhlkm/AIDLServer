package com.looppay;

import com.looppay.data.IBindDeviceCallback;

/**
 * Created by hanlu.feng on 1/23/2018.
 */

public class ServiceData {
    private static ServiceData mServiceData=null;
    private IBindDeviceCallback mIBindDeviceCallback = null;
    private ServiceData(){

    }

    public static ServiceData getInstance(){
        if(mServiceData == null){
            mServiceData = new ServiceData();
        }
        return  mServiceData;
    }

    public IBindDeviceCallback getmIBindDeviceCallback() {
        return mIBindDeviceCallback;
    }

    public void setmIBindDeviceCallback(IBindDeviceCallback mIBindDeviceCallback) {
        this.mIBindDeviceCallback = mIBindDeviceCallback;
    }
}
