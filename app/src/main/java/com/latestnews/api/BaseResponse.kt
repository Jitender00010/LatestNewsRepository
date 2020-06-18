package com.latestnews.api

import com.latestnews.api.ApiConstant.API_SUCCESS

open class BaseResponse<T>{
    fun isSuccess(): Boolean = when(status){
        API_SUCCESS -> true
        else -> false
    }

    var status: String = "error"
    var totalResults : Int = -1
    var message : String? = null


    var articles : T? = null
}