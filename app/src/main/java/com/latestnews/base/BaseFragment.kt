package com.callmanagerfinal.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.callmanagerfinal.utility.Failure
import com.callmanagerfinal.utility.ViewStatus
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open abstract class BaseFragment : DaggerFragment()  {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: BaseViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = provideBaseModel()
        observeViewState()
    }

    fun observeViewState() {
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

    private var progressDialog: ProgressDialog? = null

    fun showProgressDialog(title: String?) {
        val pd = progressDialog
        try {
            if (pd == null || (pd != null && !pd.isShowing)) {
                progressDialog = ProgressDialog(activity)
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

    private fun showError(message: String, isFinish: Boolean) {}

    abstract fun provideBaseModel(): BaseViewModel

}