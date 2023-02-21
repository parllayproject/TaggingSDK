package com.parllay.tagging.taggingsdk.helper

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings.*
import java.io.*
import java.lang.System

/**
 * Created by Leons Chelladurai on 17November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
object CommonUtils {

    private fun generateInstallId(): String {
        val charPool: List<Char> = ('a'..'z') + ('0'..'9')

        return (1..10)
            .map{ kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    @SuppressLint("HardwareIds")
    fun setInitialConfig(appContext: Context){

        CommonData.installId = readFromFile(appContext)
        Secure.getString(appContext.contentResolver, Secure.ANDROID_ID)
            .also { CommonData.androidId = it }
        CommonData.appName = appContext.applicationInfo.loadLabel(appContext.packageManager).toString()

    }

    private fun readFromFile(context: Context): String {
        var ret = ""
        try {
            val inputStream: InputStream? = context.openFileInput("config.txt")
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String?
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append("\n").append(receiveString)
                }
                inputStream.close()
                ret = stringBuilder.toString().replace("\n","")
            }
        } catch (e: FileNotFoundException) {
            val installId:String =  generateInstallId()
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
        appExtras.putString("install_id",CommonData.installId)
        appExtras.putString("android_id",CommonData.androidId)
        appExtras.putString("app_name",CommonData.appName)
        appExtras.putString("package_name",applicationContext.packageName)
        appExtras.putString("android_version",Build.VERSION.RELEASE)
        appExtras.putString("android_internet_type",connectivityType(applicationContext))

        return BundleHelper.bundleToString(appExtras)
    }

    fun getFinalUrl(context: Context, tagId: String, tagParams: Bundle): String {
        val clientParams = BundleHelper.bundleToString(tagParams)
        val deviceParams = getExtraParams(context)

        return "${CommonData.tagUrl}?${CommonData.tagQueryKey}=$tagId&acp=$clientParams&adp=$deviceParams"
    }

    private fun connectivityType(context: Context): String {
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