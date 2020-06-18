package com.latestnews.base
import com.domain.entity.BaseDisplayData
import com.latestnews.api.BaseResponse
import com.latestnews.utility.Failure
import io.reactivex.functions.Function

abstract class BaseMapFunction<DATA, RESULT : BaseDisplayData<DATA>>(private val baseDisplayData: RESULT) : Function<BaseResponse<DATA>, RESULT> {

        override fun apply(data: BaseResponse<DATA>): RESULT? {
            if (!data.isSuccess())
                throw Failure.ServerError(data.message ?: "" ,data.message?:"")

            data.articles?.let {
                baseDisplayData.update(it)
            }

            return baseDisplayData
        }

}