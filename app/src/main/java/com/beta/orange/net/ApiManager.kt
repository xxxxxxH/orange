package com.beta.orange.net

import com.beta.orange.base.xApp
import com.beta.orange.entity.BaseEntity
import com.beta.orange.utils.print
import com.google.gson.Gson
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class ApiManager {
    private val DEFAULT_TIMEOUT = 15_000L

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(Cache(File(xApp.instance.cacheDir, "cache"), 1024 * 1024 * 100))
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(Interceptor())
            .build()
    }

    inner class Interceptor : okhttp3.Interceptor {
        override fun intercept(chain: okhttp3.Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)
            if (request.url().toString() != "https://x2022-1310517219.cos.ap-shanghai.myqcloud.com/domain.json") {
                val code = response.code()
                "code = $code".print()
                val charset: Charset = Charset.forName("UTF-8")
                val responseBody = response.peekBody(Long.MAX_VALUE)
                val jsonReader: Reader = InputStreamReader(responseBody.byteStream(), charset)
                val reader = BufferedReader(jsonReader)
                val json: String = reader.readLine()
                val result = Gson().fromJson(json, BaseEntity::class.java)
                result.code = code
                "any = ${result.data}".print()
                val myBody =
                    ResponseBody.create(MediaType.parse("text/plain"), Gson().toJson(result))
                return response.newBuilder().body(myBody).build()
            }
            return response
        }
    }

    fun <T> createService(clazz: Class<T>, baseUrl: String) =
        Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(clazz)

    inline fun <reified T> createService(baseUrl: String = "") =
        createService(T::class.java, baseUrl)


}