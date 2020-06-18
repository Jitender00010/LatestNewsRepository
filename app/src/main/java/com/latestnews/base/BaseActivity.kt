package com.callmanagerfinal.base

import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.callmanagerfinal.utility.EmptyViewModel
import com.callmanagerfinal.utility.Failure
import com.callmanagerfinal.utility.PermissionUtils

import com.callmanagerfinal.utility.ViewStatus
import dagger.android.support.DaggerAppCompatActivity

open abstract class BaseActivity : DaggerAppCompatActivity() {

    lateinit var viewModel: BaseViewModel

    val permissionUtils by lazy {
        PermissionUtils(this)
    }

    fun observeViewState(){
        viewModel.viewStatus.observe(this, Observer {
            run {
                it?.let {
                    when (it) {
                        is ViewStatus.FAIL -> {
                            dismissProgressDialog()
                            progressDialog = null
                            when (it.failure) {
                                is Failure.NetworkConnection -> showError(it.failure.message, false)
                                is Failure.ServerError -> showError(it.failure.message, false)
                                is Failure.APIFailure -> showError(it.failure.message, false)
                                is Failure.TemperedRequestFailure -> showError(
                                    it.failure.message,
                                    false
                                )
                                is Failure.TokenFailure -> showError(it.failure.message, false)

                                else -> {
                                }
                            }
                        }
                        is ViewStatus.LOADING -> {
                            showProgressDialog(it.message)
                        }
                        is ViewStatus.SUCCESS -> {
                            dismissProgressDialog()

                        }
                    }

                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId())

        viewModel = provideBaseModel()!!
        observeViewState()
        initView()
    }

    private var progressDialog: ProgressDialog? = null

    fun showProgressDialog(title: String?) {
        val pd = progressDialog
        try {
            if (pd == null || (pd != null && !pd.isShowing)) {
                progressDialog = ProgressDialog(this)
                progressDialog?.setCancelable(false)
                progressDialog?.setCanceledOnTouchOutside(false)
                progressDialog?.setTitle(title)
                //progressDialog?.setMessage(message)
                progressDialog?.show()
            }
        } catch (e: Exception) {

        }
    }

    fun dismissProgressDialog() {
        try {
            if (progressDialog != null) {
                progressDialog?.dismiss()
                progressDialog = null
            }
        } catch (e: Exception) {

        }
    }

    private fun showError(message: String, isFinish: Boolean) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        var allgranted = false
        for (i in grantResults.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                allgranted = true
            } else {
                allgranted = false
                break
            }
        }
        when (requestCode) {
            PermissionUtils.REQUEST_ALL_PERMISSION -> {
                if (allgranted) {
                    permissionUtils.grantPermission()
                } else {
                    showAllPermissionDialog()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun showAllPermissionDialog() {
        val builder = AlertDialog.Builder(this@BaseActivity)
        builder.setTitle("Need Multiple Permissions")
        builder.setMessage("This app needs Camera and Location permissions.")
        builder.setPositiveButton("Grant") { dialog, _ ->
            dialog.cancel()
            permissionUtils.getAllPermissions(permissionUtils.callback, false)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
            finish()
        }
        builder.show()
    }

    abstract fun provideBaseModel() : BaseViewModel?

    abstract fun layoutId() : Int

    protected abstract fun initView()
}