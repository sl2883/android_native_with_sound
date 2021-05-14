package com.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import com.clevertap.android.sdk.*

class AppInboxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_inbox)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(this@AppInboxActivity)

//        cleverTapDefaultInstance?.apply {
//
//            ctNotificationInboxListener = this@AppInboxActivity
//
//            //Initialize the inbox and wait for callbacks on overridden methods
//            initializeInbox()
//        }
    }
//
//    override fun inboxMessagesDidUpdate() {
//        Logger.i("CleverTap", "inboxMessagesDidUpdate() called")
//    }
//
//    override fun inboxDidInitialize() {
//        Logger.i("CleverTap", "inboxDidInitialize() called")
//        val cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(this@AppInboxActivity)
//        cleverTapDefaultInstance?.showAppInbox()//Opens Activity with default style config
//
////        yourInboxButton.setOnClickListener(object:View.OnClickListener() {
////            fun onClick(v: View) {
////                val inboxTabs =
////                    arrayListOf("Promotions", "Offers", "Others")//Anything after the first 2 will be ignored
////                CTInboxStyleConfig().apply {
////                    tabs = inboxTabs //Do not use this if you don't want to use tabs
////                    tabBackgroundColor = "#FF0000"
////                    selectedTabIndicatorColor = "#0000FF"
////                    selectedTabColor = "#000000"
////                    unselectedTabColor = "#FFFFFF"
////                    backButtonColor = "#FF0000"
////                    navBarTitleColor = "#FF0000"
////                    navBarTitle = "MY INBOX"
////                    navBarColor = "#FFFFFF"
////                    inboxBackgroundColor = "#00FF00"
////                    firstTabTitle = "First Tab"
////                    cleverTapAPI?.showAppInbox(this) //Opens activity With Tabs
////                }
////                //OR
////                cleverTapDefaultInstance.showAppInbox()//Opens Activity with default style config
////            }
////        })
//    }
}