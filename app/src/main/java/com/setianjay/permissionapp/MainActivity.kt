package com.setianjay.permissionapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission()
    }

    private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(
            applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasCoarseLocationPermission() = ActivityCompat.checkSelfPermission(
            applicationContext,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasBackgroundLocationPermission() = ActivityCompat.checkSelfPermission(
            applicationContext,Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission(){
        val permissionDecline = mutableListOf<String>() // Permission yang tidak di allow akan masuk ke array sini

        if (!hasWriteExternalStoragePermission()){
            permissionDecline.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!hasCoarseLocationPermission()){
            permissionDecline.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasBackgroundLocationPermission()){
            permissionDecline.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if (permissionDecline.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionDecline.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 0 && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "${permissions[i]} is granted")
                }else{
                    Log.d(TAG,"${permissions[i]} is not granted")
                }
            }
        }
    }
}