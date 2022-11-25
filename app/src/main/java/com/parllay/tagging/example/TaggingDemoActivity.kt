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
    lateinit var eventTwo:Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagging_demo)


        eventOne = findViewById(R.id.eventOne)
        eventOne.setOnClickListener(View.OnClickListener {

            Tagging.tagEvent("tag01.ctaghk.com")
        })

        eventTwo = findViewById(R.id.eventTwo)
        eventTwo.setOnClickListener(View.OnClickListener {

            val bundle = Bundle()
            bundle.putString("param1","value1")
            bundle.putString("param2","value2")
            Tagging.tagEvent("tag02.ctaghk.com",bundle)
        })
    }

}