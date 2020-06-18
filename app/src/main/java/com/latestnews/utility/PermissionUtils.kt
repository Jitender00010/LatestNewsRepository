package com.latestnews.utility
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.latestnews.base.BaseActivity

class PermissionUtils(private val baseActivity: BaseActivity) {

    companion object {
        private val ALL_PERMISSION_REQUIRED = arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.READ_CONTACTS, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_PHONE_STATE)
        const val REQUEST_ALL_PERMISSION = 230
    }

    lateinit var callback: () -> Unit

    fun getAllPermissions(callback : () -> Unit,shouldDialogEnable : Boolean = true){
        this.callback = callback
        var allPermissionGranted = true
        var shouldShowDialog = false
        ALL_PERMISSION_REQUIRED.forEach {
            if(ActivityCompat.checkSelfPermission(baseActivity, it) != PackageManager.PERMISSION_GRANTED){
                allPermissionGranted = false
                return@forEach
            }
        }
        if(allPermissionGranted){
            this.callback.invoke()
        }else {
            ALL_PERMISSION_REQUIRED.forEach {
                if(ActivityCompat.shouldShowRequestPermissionRationale(baseActivity, it)){
                    shouldShowDialog = true
                    return@forEach
                }
            }
            if(shouldShowDialog && shouldDialogEnable){
                baseActivity.showAllPermissionDialog()
            }else{
                ActivityCompat.requestPermissions(baseActivity, ALL_PERMISSION_REQUIRED, REQUEST_ALL_PERMISSION)
            }
        }
    }

    fun grantPermission(){
        this.callback.invoke()
    }

}