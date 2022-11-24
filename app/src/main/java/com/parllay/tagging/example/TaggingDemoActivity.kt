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

            Tagging.tagEvent("testing.theprophetsgame.com")
        })

        eventTwo = findViewById(R.id.eventTwo)
        eventTwo.setOnClickListener(View.OnClickListener {

            val bundle = Bundle()
            bundle.putInt("purchase_id",321321)
            bundle.putString("purchase_category","games")
            bundle.putString("purchase_price","19.99$")
            Tagging.tagEvent("testing.theprophetsgame.com",bundle)
        })
    }

}