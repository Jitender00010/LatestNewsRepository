package com.latestnews.base
import com.latestnews.api.BaseResponse
import com.latestnews.utility.Failure
import io.reactivex.functions.Function

abstract class BaseMapFunction <DATA ,RESULT : DATA>(private var baseDisplayData: RESULT) : Function<BaseResponse<DATA>, DATA> {

        override fun apply(data: BaseResponse<DATA>): DATA? {
            if (!data.isSuccess())
                throw Failure.ServerError(data.message ?: "" ,data.message?:"")

            data.articles?.let {
              return it
            }

            return baseDisplayData
        }

}