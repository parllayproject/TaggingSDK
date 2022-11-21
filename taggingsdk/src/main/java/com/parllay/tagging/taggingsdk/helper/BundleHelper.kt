package com.parllay.tagging.taggingsdk.helper

import android.os.Bundle
import java.net.URLEncoder


/**
 * Created by Leons Chelladurai on 07November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
object BundleHelper {

    fun bundleToString(extras: Bundle):String{
        var paramsString =""
        val ks = extras.keySet()
        val iterator: Iterator<String> = ks.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            val paramValue = URLEncoder.encode(extras.get(key).toString(), "UTF-8")
            paramsString = "$paramsString$key=$paramValue&"
        }
        return paramsString
    }

    private fun removeLastChar(str: String): String {
        var str = str
        if (str.isNotEmpty() && str[str.length - 1] == '&') {
            str = str.substring(0, str.length - 1)
        }
        return str
    }
}