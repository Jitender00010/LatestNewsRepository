package com.latestnews

import androidx.lifecycle.ViewModelProviders
import com.latestnews.base.BaseActivity
import com.latestnews.base.BaseViewModel

class MainActivity : BaseActivity() {
    override fun initView() {
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun provideBaseModel(): BaseViewModel? {
       return null
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_main)
    }*/
}
