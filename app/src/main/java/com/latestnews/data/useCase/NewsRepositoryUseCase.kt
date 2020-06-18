package com.e.domain.useCase

import android.content.Context
import android.widget.Toast
import com.callmanagerfinal.api.BaseArguments
import com.callmanagerfinal.di.qualifier.Application
import com.callmanagerfinal.utility.ConnectionDetector
import com.e.domain.R
import com.e.domain.entity.OtpRequestModel
import com.e.domain.entity.OtpRequestResponseModel
import com.e.domain.repository.UserRepository
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class UserRepositoryUseCase @Inject constructor(val userRepository: UserRepository,@Application val context: Context) {

    fun sendOtp(strMobileNumber : String) : Observable<OtpRequestResponseModel>
    {
        if (strMobileNumber.isEmpty()) {
            Toast.makeText(context,context.getString(R.string.validation_mobile_number), Toast.LENGTH_SHORT).show()
            return Observable.error(Throwable("Mobile number can not be empty"))
        } else if (strMobileNumber.length < 10) {
            Toast.makeText(
                context, context.getResources().getString(R.string.validation_mobile_number_length),
                Toast.LENGTH_SHORT
            ).show()
            return Observable.error(Throwable("Mobile number should be of 10 digits"))
        } else if (strMobileNumber.equals("000000000", ignoreCase = true) || strMobileNumber.equals(
                "0000000000",
                ignoreCase = true
            ) || strMobileNumber.equals("00000000000", ignoreCase = true) || strMobileNumber.equals(
                "000000000000",
                ignoreCase = true
            ) || strMobileNumber.equals("0000000000000", ignoreCase = true)
        ) {
            Toast.makeText(
                context,
                context.getString(R.string.invalid_number),
                Toast.LENGTH_SHORT
            ).show()
            return Observable.error(Throwable("Invalid Number !!"))
        }
       /* while (strMobileNumber.startsWith("0")) {
            strMobileNumber = strMobileNumber.substring(1, strMobileNumber.length)
        }*/

        val request = OtpRequestModel()
        request.channel = BaseArguments.USER_CHANNEL
        request.msisdn = strMobileNumber
        request.deviceId = ConnectionDetector.getDeviceId(context)
        request.deviceType = ConnectionDetector.getDeviceId(context)
        request.appName = BaseArguments.APP_NAME
        request.languageName = "en"
        request.appVersion = ConnectionDetector.versionCode(context)
        return userRepository.sendOtp(request)
    }
}