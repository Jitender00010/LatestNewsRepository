package com.callmanagerfinal.api.api

import com.callmanagerfinal.api.ApiConstant
import com.callmanagerfinal.api.BaseArguments
import com.callmanagerfinal.api.BaseResponse
import com.e.domain.entity.OtpRequestModel
import com.e.domain.entity.OtpRequestResponseModel
import retrofit2.http.Body
import retrofit2.http.POST
import io.reactivex.Observable
import retrofit2.http.Header

interface UserAPI{

    @POST(ApiConstant.METHOD_SEND_OTP)
    fun sendOtp(@Header(BaseArguments.ARG_CONTENT_TYPE) contentType :String? ,
                @Header(BaseArguments.ARG_LANGUAGE_NAME) language :String? ,
                @Header(BaseArguments.ARG_APP_NAME) appName :String? ,
                @Header(BaseArguments.ARG_DEVICE_ID) deviceId :String? ,
                @Header(BaseArguments.ARG_DEVICE_TYPE) deviceType :String? ,
                @Header(BaseArguments.ARG_CHANNEL) channel :String? ,
                @Header(BaseArguments.ARG_APP_VERSION) appVersion :String? ,

                @Body body : OtpRequestModel): Observable<BaseResponse<OtpRequestResponseModel>>
}