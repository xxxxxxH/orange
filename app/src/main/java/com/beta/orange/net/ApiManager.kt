package com.beta.orange.net

import com.beta.orange.base.xApp
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ApiManager {
    private val DEFAULT_TIMEOUT = 15_000L

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(Cache(File(xApp.instance.cacheDir, "cache"), 1024 * 1024 * 100))
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    fun <T> createService(clazz: Class<T>, baseUrl: String) =
        Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(clazz)

    inline fun < reified T> createService(baseUrl: String = "") =
        createService(T::class.java, baseUrl)
}