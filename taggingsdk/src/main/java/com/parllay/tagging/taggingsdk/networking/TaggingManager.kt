package com.parllay.tagging.taggingsdk.networking

import android.app.Application
import android.os.Bundle
import android.util.Log
import com.parllay.tagging.taggingsdk.helper.CommonUtils
import kotlinx.coroutines.*

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
            } catch (t: Throwable) {
                Log.e(TAG, "Initialization Failed", t)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun tagging(tagUrl:String, tagParams: Bundle){

        GlobalScope.launch() {
            try {
                val finalUrl = CommonUtils.getFinalUrl(appInstance.applicationContext,tagUrl,tagParams)
                ConnectionClient.tagRequest("$finalUrl&iid=$installId")
            } catch (t: Throwable) {
                Log.e(TAG, "Tagging Failed", t)
            }
        }
    }
}