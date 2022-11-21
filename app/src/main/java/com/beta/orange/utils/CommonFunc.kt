package com.beta.orange.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.hjq.permissions.XXPermissions


fun Any.print() {
    Log.e(TAG, "$this")
}

fun AppCompatActivity.requestPermissions(block: () -> Unit, block2: () -> Unit) {
    XXPermissions.with(this).permission(permissions).request { permissions, all ->
        if (all) {
            block()
        } else {
            block2()
        }
    }
}

fun AppCompatActivity.getWindowsWidth() :Int{
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}

fun AppCompatActivity.getWindowsHeight():Int{
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
}

fun Context.dp2px(dp:Float):Int{
    val scale: Float = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.px2dp(px:Float):Int{
    val scale: Float = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}



