package com.looppay;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.looppay.service.AIDLService;

/**
 * Created by hanlu.feng on 1/24/2018.
 */

public class PermissionActivity extends Activity {
    public final String TAG = this.getClass().getSimpleName();
    public final static Integer PERM_REQ_CODE = 0;
    ResultReceiver resultReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perm);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("PERMSISSION", "activity" );
        Log.i("PERMSISSION", "requestPermissions" );
        resultReceiver = this.getIntent().getParcelableExtra(AIDLService.KEY_RESULT_RECEIVER);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Log.i("PERMSISSION","Take picture right is granted");
        }else{
            Log.i("PERMSISSION","Take picture right is not granted");
        }
        requestPermissions(new String[]{Manifest.permission.CAMERA}, PERM_REQ_CODE);
    }

    @Override
    public  void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        Log.i("PERMSISSION", "onRequestPermissionsResult" );
        resultReceiver.send(PERM_REQ_CODE, null);
        finish();

    }
            }
