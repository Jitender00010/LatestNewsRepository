package com.callmanagerfinal.di.modules

import android.content.Context
import android.util.Log
import com.callmanagerfinal.BuildConfig
import com.callmanagerfinal.api.BaseArguments
import com.callmanagerfinal.api.api.AppAPI
import com.callmanagerfinal.api.api.UserAPI
import com.callmanagerfinal.di.qualifier.Application
import com.callmanagerfinal.utility.ConnectionDetector
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModules {

    private val CONNECTION_TIMEOUT: Long = 6000

    /*@Singleton
    @Provides
    internal fun provideRetrofitInstance(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = getOkHttpClient()

        return Retrofit.Builder()
            .baseUrl(BaseArguments.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    }*/

    @Singleton
    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory, adapterFactory: CallAdapter.Factory, client: OkHttpClient,
                        @Named("baseUrl") baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(adapterFactory)
        .client(client)
        .baseUrl(baseUrl)
        .build()


    @Singleton
    @Provides
    fun provideOkHttp(interceptor: Interceptor): OkHttpClient {
        val okClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            okClientBuilder.addInterceptor(loggingInterceptor)
        }
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.e(
                    "CallManagerAppResponse",
                    message
                )
            })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okClientBuilder.addInterceptor(httpLoggingInterceptor)
        okClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return okClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGSONFactory(): Converter.Factory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRxAdapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()


    @Singleton
    @Provides
    fun provideInterceptor(@Application context: Context): Interceptor
            = Interceptor { chain: Interceptor.Chain ->
        run {

            val request = chain.request()
            val builder = request.newBuilder()

           /* builder.addHeader(BaseArguments.ARG_CONTENT_TYPE, "application/json")
            builder.addHeader(BaseArguments.ARG_LANGUAGE_NAME, "en")
            builder.addHeader(BaseArguments.ARG_APP_NAME,BaseArguments.APP_NAME)
            builder.addHeader(BaseArguments.ARG_DEVICE_TYPE, ConnectionDetector.getDeviceId(context))
            builder.addHeader(BaseArguments.ARG_DEVICE_ID, ConnectionDetector.getDeviceId(context))
            builder.addHeader(BaseArguments.ARG_CHANNEL, BaseArguments.USER_CHANNEL)
            builder.addHeader(BaseArguments.ARG_APP_VERSION, ConnectionDetector.versionCode(context).toString())
         */   val response  = chain.proceed(builder.build())
            response
        }
    }


   /* private fun getOkHttpClient(): OkHttpClient {
        val okClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.e(
                    "CallManagerAppResponse",
                    message
                )
            })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okClientBuilder.addInterceptor(httpLoggingInterceptor)
        okClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        okClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return okClientBuilder.build()
    }*/

    @Singleton
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = BaseArguments.API_URL


    @Singleton
    @Provides
    fun provideUserAPI(retrofit: Retrofit) = retrofit.create(UserAPI::class.java)

    @Singleton
    @Provides
    fun provideAppAPI(retrofit: Retrofit) = retrofit.create(AppAPI::class.java)

}