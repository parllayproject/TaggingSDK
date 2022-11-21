package com.parllay.tagging.example

import android.app.Application
import com.parllay.tagging.taggingsdk.Tagging

/**
 * Created by Leons Chelladurai on 09November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */
class TagApp: Application() {
    override fun onCreate() {
        super.onCreate()

        Tagging.start(this)
    }
}