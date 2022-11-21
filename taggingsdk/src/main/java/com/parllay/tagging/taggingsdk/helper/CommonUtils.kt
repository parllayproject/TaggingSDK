package com.parllay.tagging.taggingsdk.helper

import android.content.Context
import android.os.Build
import android.os.Bundle
import java.io.*

/**
 * Created by Leons Chelladurai on 17November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
object CommonUtils {



    private fun generateInstallId(length:Int): String {
        val charPool: List<Char> = ('a'..'z') + ('0'..'9')

        return (1..length)
            .map{ kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun getInstallId(appContext: Context): String {
        return readFromFile(appContext);
    }

    private fun readFromFile(context: Context): String {
        var ret = ""
        try {
            val inputStream: InputStream? = context.openFileInput("config.txt")
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String? = ""
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                ret = stringBuilder.toString().replace("\n","")
            }
        } catch (e: FileNotFoundException) {
            val installId:String =  generateInstallId(10)
            writeToFile(installId,context)
            ret = installId
        }
        return ret
    }

    private fun writeToFile(data: String, context: Context) {
        val outputStreamWriter =
            OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    }

    fun getExtraParams(applicationContext: Context): String {

        val appExtras= Bundle()
        appExtras.putString("timestamp", System.currentTimeMillis().toString())
        appExtras.putString("packageName",applicationContext.packageName)
        appExtras.putString("android_version",Build.VERSION.RELEASE)
        appExtras.putString("android_device",Build.DEVICE)
        appExtras.putString("android_model",Build.MODEL)
        appExtras.putString("android_manufacturer",Build.MANUFACTURER)
        appExtras.putString("android_brand",Build.BRAND)

        return BundleHelper.bundleToString(appExtras)
    }
}