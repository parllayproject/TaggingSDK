package com.parllay.tagging.example

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.parllay.tagging.taggingsdk.Tagging


/**
 * Created by Leons Chelladurai on 02November2022
 * Copyright © 2023 Parllay. a division of Parllay Inc. All Rights Reserved
 */

class TaggingDemoActivity : AppCompatActivity() {

    lateinit var eventOne:Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagging_demo)

       Tagging.tagEvent("http://8d7b4d459f84498cd8fdb79352453620.ctaghk.com")


        eventOne = findViewById(R.id.eventOne)
        eventOne.setOnClickListener(View.OnClickListener {

           Tagging.tagEvent("http://22a3bc1cbeb283d9fb4af1d64e992b52.ctaghk.com")
        })
    }

}