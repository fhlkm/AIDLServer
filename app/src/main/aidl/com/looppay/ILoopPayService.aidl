
package com.looppay;
import com.looppay.data.IBindDeviceCallback;
interface ILoopPayService {
   /**
     * accessToken - Bind the device using this accessToken.
     * deviceCertifcate - device public certificate which is used to sign the accessToken.
     * IBindDeviceCallback is used to update the device info.
     */
    int bindDevice(in String accessToken, in byte[] deviceCertifcate, in IBindDeviceCallback callback);
}