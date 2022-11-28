package com.beta.orange.net

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface AppService {

    @GET("domain.json")
    fun getDomain(): Call<ResponseBody>
}