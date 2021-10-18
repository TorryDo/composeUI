package com.torrydo.composeui.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun Context.showShortToast(s: String) = Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
fun Context.showLongToast(s: String) = Toast.makeText(this, s, Toast.LENGTH_LONG).show()

fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Activity.getDeviceScreenInfo(
    isWidth: Boolean
): Int {
    var returnNum = 0

    val outMetrics = DisplayMetrics()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val display = this.display
        display?.getRealMetrics(outMetrics)

        returnNum = if (isWidth) {
            outMetrics.widthPixels
        } else {
            outMetrics.heightPixels
        }

    } else {
        @Suppress("DEPRECATION")
        val display = this.windowManager.defaultDisplay
        @Suppress("DEPRECATION")
        display.getRealMetrics(outMetrics)

        returnNum = if (isWidth) {
            outMetrics.widthPixels
        } else {
            outMetrics.heightPixels
        }

    }
    return returnNum

}