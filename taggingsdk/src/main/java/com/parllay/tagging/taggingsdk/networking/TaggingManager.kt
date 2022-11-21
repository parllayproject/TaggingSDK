package com.parllay.tagging.taggingsdk.networking

import android.app.Application
import android.os.Bundle
import android.util.Log
import com.parllay.tagging.taggingsdk.helper.BundleHelper
import com.parllay.tagging.taggingsdk.helper.CommonUtils
import kotlinx.coroutines.*
import org.json.JSONArray

/**
 * Created by Leons Chelladurai on 02November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
internal object TaggingManager {

    private val TAG = TaggingManager::class.java.simpleName
    private var installId:String=""
    lateinit var appInstance: Application


    @OptIn(DelicateCoroutinesApi::class)
    fun init(application: Application){

        appInstance=application
        GlobalScope.launch() {
            try {
                installId = CommonUtils.getInstallId(application.applicationContext)
                Log.i("##########", installId)
            } catch (t: Throwable) {
                Log.e(TAG, "Initialization Failed", t)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun tagging(tagId:String, tagParams: Bundle){
        val paramsString = BundleHelper.bundleToString(tagParams)
        val extraParamString = CommonUtils.getExtraParams(appInstance.applicationContext)
        GlobalScope.launch() {
            val tagString = ConnectionClient.getTagUrl (tagId)
            val url = (JSONArray(tagString)[0]) as String
            Log.i("##########", url)
            val tagRes = ConnectionClient.tagRequest("$installId.$url", paramsString+extraParamString)
            Log.i("##########", tagRes)
        }
    }
}