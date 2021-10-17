package com.aa.weatherforecast.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Chad Doepken
 * 1/5/21
 */
class NetworkService {

    private val HOST = "api.openweathermap.org"
    private val PROTOCOL = "https"
    private val API_VERSION = "2.5"
    private val PATH = "/data/${API_VERSION}/"

    private val WRITE_TIMEOUT = 60
    private val READ_TIMEOUT = 180
    private val CONNECT_TIMEOUT = 90

    private val moshi: Moshi
    private val moshiConverterFactory: MoshiConverterFactory

    init {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        moshiConverterFactory = MoshiConverterFactory.create(moshi)
    }


    fun getRetrofitInstance() : Retrofit {

        val okHttpClient = getOkHttpClient()

        val baseUrl = PROTOCOL + "://" + HOST + PATH

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(moshiConverterFactory)
            .build()

    }


    private fun getOkHttpClient() : OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                try {
                    Log.v("OkHttp", message)
                } catch (e: Exception) { }
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okBuilder.addInterceptor(loggingInterceptor)


        return okBuilder.build()


    }


}