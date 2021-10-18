package com.torrydo.composeui.utils

import android.content.res.Resources

fun Int.toPx(): Int {
    return try {
        (this * Resources.getSystem().displayMetrics.density).toInt()
    } catch (e: Exception) {
        0
    }
}

fun Int.toDp(): Int {
    return try {
        (this / Resources.getSystem().displayMetrics.density).toInt()
    } catch (e: Exception) {
        0
    }
}