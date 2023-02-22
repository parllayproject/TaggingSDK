package com.parllay.tagging.taggingsdk.networking

import android.util.Log
import com.parllay.tagging.taggingsdk.helper.CommonData.TAG
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

/**
 * Created by Leons Chelladurai on 02November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */


internal object ConnectionClient {

    fun tagRequest(url: String) {

        val con: URLConnection = URL(url).openConnection()
        try {
            con.connect()
            val inputStream: InputStream = con.getInputStream()
            con.getURL()
            inputStream.close()
        }catch (t:Throwable){
            Log.e(TAG,"HTTP Request Failed",t)
        }
    }
}