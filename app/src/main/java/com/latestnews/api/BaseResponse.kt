package com.callmanagerfinal.api
import com.callmanagerfinal.api.ApiConstant.API_SUCCESS

open class BaseResponse<T>{
    fun isSuccess(): Boolean = when(code){
        API_SUCCESS -> true
        else -> false
    }

    var code: Int = -1
    var status : String? = null
    var message : String? = null


    var data : T? = null
}