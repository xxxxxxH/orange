package com.beta.orange.net

import okhttp3.ResponseBody
import retrofit2.http.GET

interface AppService {

    @GET("article/list/0/json")
    suspend fun requestData():ResponseBody
}