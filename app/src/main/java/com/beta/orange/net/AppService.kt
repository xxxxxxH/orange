package com.beta.orange.net

import com.beta.orange.entity.BaseEntity
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface AppService {

    @GET
    suspend fun requestData(@Url url:String="https://x2022-1310517219.cos.ap-shanghai.myqcloud.com/domain.json"):ResponseBody
}