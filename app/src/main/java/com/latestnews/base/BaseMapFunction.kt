package com.callmanagerfinal.base
import com.callmanagerfinal.api.BaseResponse
import com.callmanagerfinal.utility.Failure
import io.reactivex.functions.Function

abstract class BaseMapFunction <DATA ,RESULT : DATA>(private var baseDisplayData: RESULT) : Function<BaseResponse<DATA>, DATA> {

        override fun apply(data: BaseResponse<DATA>): DATA? {
            if (!data.isSuccess())
                throw Failure.ServerError(data.message ?: "" ,data.code)

            data.data?.let {
              return it
            }

            return baseDisplayData
        }

}