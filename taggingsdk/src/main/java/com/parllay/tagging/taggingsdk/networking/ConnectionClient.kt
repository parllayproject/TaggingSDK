package com.parllay.tagging.taggingsdk.networking

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Leons Chelladurai on 02November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */

private val TAG = ConnectionClient::class.java.simpleName

internal object ConnectionClient {

    fun getTagUrl(tagId: String) : String {

        val url = "https://637636827e93bcb006c63f66.mockapi.io/$tagId"
        return getRequest(url)
    }

    fun tagRequest(tagUrl:String, extraParams: String):String{
        val finalUrl = "http://$tagUrl?$extraParams"
        return getRequest(finalUrl)
    }

    private fun getRequest(url: String) : String {

        val url = URL(url)
        var responseString=""
        val urlConnection = url.openConnection() as HttpURLConnection
        val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
        val reader = BufferedReader(inputStream.reader())
        try {

            val sBuilder = StringBuilder()
            var line = reader.readLine()
            while (line != null) {
                sBuilder.append(line)
                line = reader.readLine()
            }
            responseString = sBuilder.toString()
        }catch (e:Exception){
            return responseString
        }
        finally {
            urlConnection.disconnect()
            reader.close()
        }
        return responseString
    }
}