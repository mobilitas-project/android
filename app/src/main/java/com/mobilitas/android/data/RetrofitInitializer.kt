package com.mobilitas.android.data

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mobilitas.android.job.JobService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
            .baseUrl("http://mobilitas.mybluemix.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build())
            .build()

    fun jobService() = retrofit.create(JobService::class.java)
}