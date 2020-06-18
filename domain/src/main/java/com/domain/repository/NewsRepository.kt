package com.domain.repository
import io.reactivex.Observable

interface UserRepository {

    fun sendOtp(otpRequestModel: OtpRequestModel) : Observable<OtpRequestResponseModel>
}