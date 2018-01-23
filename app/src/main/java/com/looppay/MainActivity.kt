package com.looppay

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.looppay.data.DeviceResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        R.layout.activity_main

        bind_device.setOnClickListener{

            var mCallback = ServiceData.getInstance().getmIBindDeviceCallback()
            if(null != mCallback){
                Log.i(TAG, "callback success")
                var mDeviceRsult = DeviceResult()
                mDeviceRsult.name ="TPD"
                mDeviceRsult.brand ="Looppay"
                mCallback.onSuccess(mDeviceRsult)
            }else{
                Log.i(TAG, "callback is null")
            }
        }


    }
}
