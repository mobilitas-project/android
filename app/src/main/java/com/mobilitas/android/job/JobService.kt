package com.mobilitas.android.job

import retrofit2.Call
import retrofit2.http.GET

interface JobService {
    @GET("employment")
    fun fetchJobs(): Call<List<Job>>
}