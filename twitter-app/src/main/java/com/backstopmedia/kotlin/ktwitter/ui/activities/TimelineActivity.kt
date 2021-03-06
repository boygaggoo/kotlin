package com.backstopmedia.kotlin.ktwitter.ui.activities

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.Gravity
import com.backstopmedia.kotlin.ktwitter.R
import com.backstopmedia.kotlin.ktwitter.ui.NavigationHelper
import com.twitter.sdk.android.tweetcomposer.TweetComposer
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter
import com.twitter.sdk.android.tweetui.UserTimeline
import kotlinx.android.synthetic.activity_timeline.*
import org.jetbrains.anko.*

class TimelineActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationHelper.setup(this, R.layout.activity_timeline)

        val userTimeline = UserTimeline.Builder()
                .screenName("fabric")
                .build()

        val adapter = TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build()

        timeline_list.adapter = adapter
        timeline_list.emptyView = empty

        fab.onClick { showAlert() }
    }

    private fun showAlert() {
        alert("So how many pages of our book have you read so far?") {
            customView {
                val pages = editText {
                    hint = "Enter pages count"
                    inputType = InputType.TYPE_CLASS_NUMBER
                    gravity = Gravity.CENTER
                }
                positiveButton {
                    composeTweet(pages.text)
                }
            }
        }.show()
    }

    private fun composeTweet(pages: Editable) {
        val text = "I've read ${pages.count} pages of \"Kotlin Book\"! #AndroidDev #Kotlin"
        val imageUri = Uri.parse("android.resource://$packageName/drawable/kotlin_logo")
        TweetComposer.Builder(this)
                .text(text)
                .image(imageUri)
                .show()
    }

    private val Editable.count: Int
        get() = if (toString().isEmpty()) 0 else try {
            toString().toInt()
        } catch(e: NumberFormatException) {
            warn { "inputType should be set to TYPE_CLASS_NUMBER" }
            0
        }

}