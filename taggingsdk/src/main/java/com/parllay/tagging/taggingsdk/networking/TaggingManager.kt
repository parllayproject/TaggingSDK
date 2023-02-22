package com.parllay.tagging.taggingsdk.networking

import android.app.Application
import android.os.Bundle
import android.util.Log
import com.parllay.tagging.taggingsdk.helper.CommonData.TAG
import com.parllay.tagging.taggingsdk.helper.CommonUtils
import kotlinx.coroutines.*

/**
 * Created by Leons Chelladurai on 02November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
internal object TaggingManager {

    lateinit var appInstance: Application


    @OptIn(DelicateCoroutinesApi::class)
    fun init(application: Application){

        appInstance=application
        GlobalScope.launch() {
            try {
                CommonUtils.setInitialConfig(application.applicationContext)
            } catch (t: Throwable) {
                Log.e(TAG, "Initialization Failed", t)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun tagging(tagId:String, tagParams: Bundle){
        GlobalScope.launch() {
            try {
                val finalUrl = CommonUtils.getFinalUrl(appInstance.applicationContext,tagId,tagParams)
                ConnectionClient.tagRequest("$finalUrl")
            } catch (t: Throwable) {
                Log.e(TAG, "Tagging Failed", t)
            }
        }
    }
}