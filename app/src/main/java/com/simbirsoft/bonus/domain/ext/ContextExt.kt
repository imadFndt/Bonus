package com.simbirsoft.bonus.domain.ext

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.sendEmail(email: String, chooserTitle: String, body: String) {
    val uri = Uri.fromParts("mailto", email, null)
    val intent = Intent(Intent.ACTION_SENDTO, uri).apply {
        putExtra(Intent.EXTRA_SUBJECT, chooserTitle)
        putExtra(Intent.EXTRA_TEXT, body)
    }
    val chooserIntent = Intent.createChooser(intent, chooserTitle)
    startActivity(chooserIntent)
}