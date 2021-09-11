package com.simbirsoft.bonus.data.ext

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

inline fun <reified T> Gson.fromJson(
    context: Context,
    fileName: String
): T {
    val jsonString = readMockFile(context, fileName)
    return fromJson(jsonString, object: TypeToken<T>() {}.type)
}

fun readMockFile(context: Context, fileName: String): String {
    return context.assets
        .open(fileName)
        .bufferedReader()
        .use(BufferedReader::readText)
}