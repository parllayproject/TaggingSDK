package com.parllay.tagging.taggingsdk.helper

import android.os.Bundle
import android.util.Base64
import android.util.Log
import org.json.JSONException
import org.json.JSONObject


/**
 * Created by Leons Chelladurai on 07November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
object BundleHelper {
    private val TAG = BundleHelper::class.java.simpleName
    fun bundleToString(bundle: Bundle):String{

        val json = JSONObject()
        val keys: Set<String> = bundle.keySet()
        for (key in keys) {
            try {
                json.put(key, JSONObject.wrap(bundle.get(key)))
            } catch (t:Throwable) {
                Log.e(TAG, "Bundle conversion error", t)
            }
        }
        return Base64.encodeToString(json.toString().toByteArray(),Base64.DEFAULT)
    }
}