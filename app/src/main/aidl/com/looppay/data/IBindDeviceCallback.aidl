package com.looppay.data;import com.looppay.data.DeviceResult;interface IBindDeviceCallback {    void onSuccess(in DeviceResult result);    void onFail(int errorCode,in DeviceResult result);}