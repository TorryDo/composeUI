package com.torrydo.composeui.utils

import android.content.Context
import android.widget.Toast

fun Context.showShortToast(s: String) = Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
fun Context.showLongToast(s: String) = Toast.makeText(this, s, Toast.LENGTH_LONG).show()