package com.callmanagerfinal.data.repositoryImp

import com.callmanagerfinal.api.api.UserAPI
import com.callmanagerfinal.base.BaseMapFunction
import com.e.domain.entity.OtpRequestModel
import com.e.domain.entity.OtpRequestResponseModel
import com.e.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userAPI: UserAPI) : UserRepository{
    override fun sendOtp(otpRequestModel: OtpRequestModel): Observable<OtpRequestResponseModel> {
        return userAPI.sendOtp("application/json",otpRequestModel.languageName,otpRequestModel.appName,otpRequestModel.deviceId,otpRequestModel.deviceType
            ,otpRequestModel.channel,otpRequestModel.appVersion.toString(),otpRequestModel).map (object : BaseMapFunction<OtpRequestResponseModel, OtpRequestResponseModel>(OtpRequestResponseModel()){})
    }

}