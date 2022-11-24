package com.parllay.tagging.taggingsdk.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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

    private fun getExtraParams(applicationContext: Context): String {

        val appExtras= Bundle()
        appExtras.putString("timestamp", System.currentTimeMillis().toString())
        appExtras.putString("packageName",applicationContext.packageName)
        appExtras.putString("android_version",Build.VERSION.RELEASE)
        appExtras.putString("android_device",Build.DEVICE)
        appExtras.putString("android_model",Build.MODEL)
        appExtras.putString("android_manufacturer",Build.MANUFACTURER)
        appExtras.putString("android_brand",Build.BRAND)
        appExtras.putString("android_internet_type",connectivityType(applicationContext))

        return BundleHelper.bundleToString(appExtras)
    }

    fun getFinalUrl(context: Context, tagUrl: String, tagParams: Bundle): String {
        val clientParams = BundleHelper.bundleToString(tagParams)
        val deviceParams = getExtraParams(context)
        var urlTag = ""
        if(tagUrl.contains("http://",true)){
            urlTag=tagUrl;
        }else{
            urlTag="http://$tagUrl"
        }
        return "$urlTag?acp=$clientParams&adp=$deviceParams"
    }

    fun connectivityType(context: Context): String {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return "TRANSPORT_CELLULAR"
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return "TRANSPORT_WIFI"
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return "TRANSPORT_ETHERNET"
                }
            }
        }
        return "NONE"
    }
}