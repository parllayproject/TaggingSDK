package com.parllay.tagging.taggingsdk.networking

import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Leons Chelladurai on 02November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */

private val TAG = ConnectionClient::class.java.simpleName

internal object ConnectionClient {

    fun tagRequest(url: String) {

        val finalUrl = URL(url)
        val urlConnection = finalUrl.openConnection() as HttpURLConnection
        var reader: BufferedReader? =null
        try {
            val inputStream = BufferedInputStream(urlConnection.inputStream)
            reader = BufferedReader(inputStream.reader())

            val sBuilder = StringBuilder()
            var line = reader.readLine()
            while (line != null) {
                sBuilder.append(line)
                line = reader.readLine()
            }
            Log.i(TAG,sBuilder.toString())
        }catch (t:Throwable){
            Log.e(TAG,"HTTP Request Failed",t)
        }
        finally {
            urlConnection.disconnect()
            reader?.close()
        }
    }
}