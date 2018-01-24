package com.looppay

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
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

        ask_permission.setOnClickListener {
            askTakePciturePermission();
        }

        finish.setOnClickListener {
            finish();
        }

    }


    fun askTakePciturePermission(){
        val permissionCheck = ContextCompat.checkSelfPermission(this@MainActivity,
                Manifest.permission.CAMERA);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
           Log.i(TAG,"Take picture right is granted")
        }else{
            ActivityCompat.requestPermissions(this,  arrayOf( Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE ), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 0) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG,"Take picture right is granted")
            }
        }
    }

}
