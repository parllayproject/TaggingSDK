package com.parllay.tagging.taggingsdk

import android.app.Application
import android.os.Bundle
import com.parllay.tagging.taggingsdk.helper.BundleHelper
import com.parllay.tagging.taggingsdk.networking.TaggingManager
import java.lang.IllegalArgumentException

/**
 * Created by Leons Chelladurai on 02November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
class Tagging {

    companion object{

        /**
         * Initialize the Tagging SDK should be called in App Application class
         * @param application The application context..
         */
        @JvmStatic
        fun start(application: Application) {
            TaggingManager.init(application)
        }

        /**
         * Used to tag a event that happens within the application
         * @param tagId The Tag Id. You get it from https://tagging.parllay.com.
         */
        @JvmStatic
        fun tagEvent(tagId: String) {
            tagEvent(tagId, Bundle())
        }

        /**
         * Used to tag a event that happens within the application along with parameter that
         * needed.
         * @param tagId The Tag Id. You get it from https://tagging.parllay.com.
         * @param tagParams The Tag parameters. Any extra parameters that has to be tagged
         * for this event has to be passed as a bundle parameter.
         */
        @JvmStatic
        fun tagEvent(tagId: String, tagParams: Bundle?) {
            if(tagParams==null){
                throw IllegalArgumentException("Extra params can't be null")
            }
            TaggingManager.tagging(tagId, tagParams)
        }


    }
}