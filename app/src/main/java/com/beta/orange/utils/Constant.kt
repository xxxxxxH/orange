package com.beta.orange.utils

import android.Manifest
import com.tencent.mmkv.MMKV

const val TAG = "xxxxxxH"

val BASE_URL = "https://www.wanandroid.com/"

var DOMAIN_NAME = ""

val permissions = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
)

val mmkv by lazy {
    MMKV.defaultMMKV()
}

var isLogin
    get() = mmkv.getBoolean("isLogin", false)
    set(value) {
        mmkv.putBoolean("isLogin", value)
    }

var token
    get() = mmkv.getString("token", "")
    set(value) {
        mmkv.putString("token", value)
    }